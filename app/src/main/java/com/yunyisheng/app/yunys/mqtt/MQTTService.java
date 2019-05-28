package com.yunyisheng.app.yunys.mqtt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.main.service.MessageService;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.AESBelle;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.TokenHeaderInterceptor;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import cn.droidlover.xdroidbase.cache.SharedPref;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

import static com.yunyisheng.app.yunys.App.context;

/**
 * 作者：fuduo on 2018/2/27 14:06
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MQTTService extends Service {

    public static final String TAG = MQTTService.class.getSimpleName();

    private static MqttAndroidClient client;
    private MqttConnectOptions conOpt;

    //    private String host = "tcp://10.0.2.2:61613";

    private String host;
    private String userName;
    private String passWord;
    private static String myTopic = "YunYSss";
    private String clientId;
    private MyTopicsModel myTopics;
    final Gson gs = new Gson();
    private UserModel userModel;
    private boolean sub_status = false;

    @Override
    public void onCreate() {
        String token = SharedPref.getInstance(context).getString("TOKEN","");
        if (token.equals("")){
            LogUtils.d("token", "token los");
            return;
        }
        clientId = String.valueOf(SharedPref.getInstance(context).getInt("userid",0));

        host = AESBelle.decode(getResources().getString(R.string.mqtt_server));
        userName = AESBelle.decode(getResources().getString(R.string.mqtt_user));
        passWord = AESBelle.decode(getResources().getString(R.string.mqtt_pass));
//        host = "tcp://123.127.2.206:1883";
//        userName = "fairyland-server";
//        passWord = "yoyosys";
        if (clientId.equals("0")){
            getUserInfo();
        }else {
            clientId += System.currentTimeMillis();
        }
        String topics = SharedPref.getInstance(context).getString("myTopics",null);
        if (topics != null && !topics.equals("")){
            myTopics = gs.fromJson(topics,MyTopicsModel.class);
        }else {
            getMyTopics();
        }
        init();
        super.onCreate();


    }


    //获取用户信息
    private void getUserInfo(){
        OkHttpClient userClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new TokenHeaderInterceptor())
                .build();
        final Request requests = new Request.Builder()
                .url(Api.BASE_PATH+"system/select/enterprise/user")
                .post(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                })
                .build();
        userClient.newCall(requests).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtils.showToast("获取用户信息失败！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code "+response);
                if (!response.body().toString().equals("")){
                    userModel = gs.fromJson(response.body().string(),UserModel.class);
                    if (userModel.getRespCode() == 0){
                        clientId = String.valueOf(userModel.getRespBody().getEnterpriseUser().getUserId())+""+System.currentTimeMillis();
                        SharedPref.getInstance(context).putInt("userid", userModel.getRespBody().getEnterpriseUser().getUserId());
                        SharedPref.getInstance(context).putString("username", userModel.getRespBody().getEnterpriseUser().getUserName());
                        SharedPref.getInstance(context).putString("usersex", userModel.getRespBody().getEnterpriseUser().getUserSex());
                        SharedPref.getInstance(context).putString("userphone", userModel.getRespBody().getEnterpriseUser().getUserPhone());
                        SharedPref.getInstance(context).putString("userjob", userModel.getRespBody().getEnterpriseUser().getUserJobTitle());
                        SharedPref.getInstance(context).putString("userhead", userModel.getRespBody().getEnterpriseUser().getUserPicture());
                        SharedPref.getInstance(context).putString("useremail", userModel.getRespBody().getEnterpriseUser().getUserMailbox());
                        List<UserModel.RespBodyBean.SectionBean> section = userModel.getRespBody().getSection();
                        String str = "";
                        for (int i = 0; i < section.size(); i++) {
                            String sectionName = section.get(i).getSectionName();
                            if (i != section.size() - 1) {
                                str += sectionName + ",";
                            } else {
                                str += sectionName;
                            }
                        }
                        SharedPref.getInstance(context).putString("userbumen", str);
                        SharedPref.getInstance(context).putString("userrole", userModel.getRespBody().getReloName());
                    }else {
//                        ToastUtils.showToast(userModel.getErrorMsg());
                    }
                }
            }
        });

    }
    private void init(){
        // 服务器地址（协议+地址+端口号）
        String uri = host;
        client = new MqttAndroidClient(this, uri, clientId);
        // 设置MQTT监听并且接受消息
        client.setCallback(mqttCallback);

        conOpt = new MqttConnectOptions();
        //断开后自动连接
        conOpt.setAutomaticReconnect(true);
        // 清除缓存
        conOpt.setCleanSession(false);
        // 设置超时时间，单位：秒
        conOpt.setConnectionTimeout(10);
        // 心跳包发送间隔，单位：秒
        conOpt.setKeepAliveInterval(20);

        conOpt.setMaxInflight(100);
        // 用户名
        conOpt.setUserName(userName);
        // 密码
        conOpt.setPassword(passWord.toCharArray());


        doClientConnection();

    }
    public void getMyTopics(){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new TokenHeaderInterceptor())
                    .build();
            final Request request = new Request.Builder()
                    .url(Api.BASE_PATH+"topic")
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ToastUtils.showToast("获取订阅消息列表失败！");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code "+response);
                    System.out.println(response.body());
                    if (!response.body().toString().equals("")){
                        myTopics = gs.fromJson(response.body().string(),MyTopicsModel.class);
                        if (myTopics.getRespCode() == 3) {
                            ToastUtils.showToast(myTopics.getRespMsg());
                            SharedPref.getInstance(context).clear();
                            context.stopService(new Intent(context, MessageService.class));
                            MQTTService.this.stopSelf();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("errorlog", myTopics.getRespMsg());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else if (myTopics.getRespCode() == 0){
                            SharedPref.getInstance(context).putString("myTopics",gs.toJson(myTopics));
                        }else {
                            ToastUtils.showToast(myTopics.getErrorMsg());
                        }
                    }
                }
            });

    }

    @Override
    public void onDestroy() {
        try{
//            client.disconnect();
            client.close();
            client.unregisterResources();
            LogUtils.d("mqttStop","mqtt has destroy");
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroy();
    }

    /**
     * 连接MQTT服务器
     */
    private void doClientConnection() {
        if (!sub_status && !client.isConnected() && isConnectIsNomarl()) {
            try {
                client.connect(conOpt, null, iMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }

    // MQTT是否连接成功
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            Log.i(TAG, "连接成功 ");
            try {
                // 订阅myTopic话题
                if (client == null) {
                    return;
                }
                if (myTopics != null && myTopics.getRespCode() != null && myTopics.getRespCode() == 0){
                    if (myTopics.getRespBody().size() > 0){
                        for(int i=0;i<myTopics.getRespBody().size();i++){
                            LogUtils.i("topic----->",myTopics.getRespBody().get(i));
                            client.subscribe(myTopics.getRespBody().get(i), 2);
                        }
                        sub_status = true;
                    }
                }

            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
            arg1.printStackTrace();
            // 连接失败，重连
//            doClientConnection();


        }
    };

    // MQTT监听并且接受消息
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            boolean inTopic = false;
            if (myTopics.getRespBody().size() > 0){
                for (int i=0;i<myTopics.getRespBody().size();i++){
                    if (topic.equals(myTopics.getRespBody().get(i))){
                        inTopic  =  true;
                        continue;
                    }
                }
            }
            String str1 = new String(message.getPayload());
            if (inTopic){
                MQTTMessage msg = new MQTTMessage();
                msg.setMessage(str1);
                EventBus.getDefault().post(msg);
            }
            String str2 = topic + ";qos:" + message.getQos() + ";retained:" + message.isRetained();
            Log.i(TAG, "messageArrived:" + str1);
            Log.i(TAG, str2);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {
            return;
        }

        @Override
        public void connectionLost(Throwable arg0) {
            // 失去连接，重连
            LogUtils.d("lose","aaaaaaaaaa");

            return;
        }
    };

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            Log.i(TAG, "MQTT当前网络名称：" + name);
            return true;
        } else {
            sub_status = false;
            Log.i(TAG, "MQTT 没有可用网络");
            return false;
        }
    }
    private boolean pingIpAddress(String ipAddress) {
        try {
            Process process = Runtime.getRuntime().exec("/system/bin/ping -c 1 -w 3 " + ipAddress);
            int status = process.waitFor();
            if (status == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
