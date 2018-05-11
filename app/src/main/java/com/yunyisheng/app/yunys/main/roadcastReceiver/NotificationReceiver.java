package com.yunyisheng.app.yunys.main.roadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.yunyisheng.app.yunys.main.activity.MessageActivity;
import com.yunyisheng.app.yunys.main.activity.NoticeDeatilActivity;
import com.yunyisheng.app.yunys.main.model.FindWorkerBean;
import com.yunyisheng.app.yunys.main.model.MessageBean;
import com.yunyisheng.app.yunys.main.model.MsgBean;
import com.yunyisheng.app.yunys.mqtt.MyTopicsModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.TaskDetailActivity;
import com.yunyisheng.app.yunys.tasks.activity.ProcessDetailActivity;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.TokenHeaderInterceptor;

import java.io.IOException;

import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.router.Router;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.POST;

import static com.yunyisheng.app.yunys.App.context;

/**
 * 作者：fuduo on 2018/3/5 15:56
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NotificationReceiver extends BroadcastReceiver {
    private Gson gs = new Gson();
    private MessageBean.RespBodyBean msg;
    @Override
    public void onReceive(final Context context, Intent intent) {
        String str = intent.getStringExtra("str");
        LogUtils.i("strtr", str);
        msg = gs.fromJson(str,MessageBean.RespBodyBean.class);

        //判断app进程是否存活
        if (CommonUtils.isAppRunning(context, "com.yunyisheng.app.yunys")) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new TokenHeaderInterceptor())
                    .build();
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("messageId", String.valueOf(msg.getMessageId()));
            final Request request = new Request.Builder()
                    .url(Api.BASE_PATH+"message/updateMessage")
                    .post(formBody.build())
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ToastUtils.showToast("获取消息失败！");
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code "+response);
                    if (!response.body().toString().equals("")){
                        MsgBean msgBean = gs.fromJson(response.body().string(),MsgBean.class);
                        if(msgBean.getRespCode() == 0){
                            Intent intent1;
                            switch (msgBean.getRespBody().getMessageType()){
                                //任务
                                case "1":
                                    if (msgBean.getRespBody().getProjectId() == null){
                                        //流程任务
                                        intent1 = new Intent(context, ProcessDetailActivity.class);
                                        intent1.putExtra("taskId",msgBean.getRespBody().getMessageInfoId());
                                        intent1.putExtra("taskType","3");
                                    }else {
                                        //项目任务
                                        intent1 = new Intent(context, TaskDetailActivity.class);
                                        intent1.putExtra("taskId",msgBean.getRespBody().getMessageInfoId());
                                        intent1.putExtra("projectId",msgBean.getRespBody().getProjectId());
                                    }
                                    break;
                                //报警
//                                case "2":
//                                    intent1 = new Intent(context,MessageActivity.class);
//                                    break;
                                //公告，需要参数为公告id
                                case "3":
                                    intent1 = new Intent(context,NoticeDeatilActivity.class);
                                    intent1.putExtra("noticeid",Integer.parseInt(msgBean.getRespBody().getMessageInfoId()));
                                    intent1.putExtra("type",1);
                                    break;
                                default:
                                    intent1 = new Intent(context,MessageActivity.class);
                                    break;
                            }
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent1);
                        }
                    }
                }
            });

        } else {
            //如果app进程已经被杀死，先重新启动app，将DetailActivity的启动参数传入Intent中，参数经过
            // SplashActivity传入MainActivity，此时app的初始化已经完成，在MainActivity中就可以根据传入
            // 参数跳转到DetailActivity中去了
            Log.i("NotificationReceiver", "the app process is dead");
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.yunyisheng.app.yunys");
            launchIntent.putExtra("msgId",msg.getMessageId());
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(launchIntent);
        }
    }
}
