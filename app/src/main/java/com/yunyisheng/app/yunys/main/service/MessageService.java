package com.yunyisheng.app.yunys.main.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.main.model.NoReadMessage;
import com.yunyisheng.app.yunys.main.model.NoReadMessageEvent;
import com.yunyisheng.app.yunys.main.model.WarningMessageEvent;
import com.yunyisheng.app.yunys.main.model.WarnningMessageBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import cn.droidlover.xdroidbase.cache.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yunyisheng.app.yunys.utils.CommonUtils.NETWORKTYPE_INVALID;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getNetWorkType;

/**
 * 消息接收处理服务
 *
 * @author 金龙
 * @version 1.0 $2014-12-2$
 */
public class MessageService extends Service {

    private static final String TAG = "MessageService";

    private boolean isRun;

    private int allsize;

    private int wanringsize;

    MediaPlayer mMediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            Log.i(TAG, "onStartCommand:");
            // 开启线程
            isRun = true;
            new MessageThread().start();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy:");
        isRun = false;
        super.onDestroy();
    }

    private class MessageThread extends Thread {

        @Override
        public void run() {
            Log.i("MOBOBOX", "MessageThread start");
            while (isRun) {
                try {
                    waitIfNecessary();
                    readMessage();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                    }
                } catch (Exception e) {
                    Log.e(TAG, "处理消息时，出现了错误", e);
                }
            }
            Log.i(TAG, "MessageThread end");
        }
    }

    /**
     * 根据缓存、网络状态判断是否需要等待
     */
    private void waitIfNecessary() {
        boolean shouldWait = false;
        do {
            shouldWait = false;
            try {
                int netWorkType = getNetWorkType(this);

                if (netWorkType == NETWORKTYPE_INVALID) { // 没联网
                    shouldWait |= true;
                }
//				else if (!isWifiConnected && (DataUsageManager.getInstance().getRestrictionLevel() == DataUsageManager.RESTRICTION_LEVEL_HIGH)) {
//					// 移动网络，但是最严格的级别的网络限定
//					shouldWait |= true;
//				}
            } catch (Exception e) {
                Log.e("MOBOBOX", "判定网络状态以及缓存状态时，发生了错误", e);
                shouldWait |= true;
            } finally {
                if (shouldWait) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        } while (shouldWait && isRun);
    }


    /**
     * 读取消息
     *
     * @throws IOException
     */
    private void readMessage() {
        Log.e(TAG, "start read");
        if (null == getActiveNetworkInfo(this)) {
            Toast.makeText(this, "连接已断开", Toast.LENGTH_SHORT).show();
            stopSelf();
            return;
        }
        readWarning();
        readNoRead();
    }

    private void readNoRead() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_PATH) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        HomeService request = retrofit.create(HomeService.class);
        String token = SharedPref.getInstance(MessageService.this).getString("TOKEN", null);
        //对 发送请求 进行封装
        Call<NoReadMessage> call = request.getServiceNoReadMessage(token);
        call.enqueue(new Callback<NoReadMessage>() {
            @Override
            public void onResponse(Call<NoReadMessage> call, Response<NoReadMessage> response) {
                NoReadMessage body = response.body();
                try {
                    //
                    if (body != null) {
                        LogUtils.i("servicehflkdh", body.getRespCode() + "");
                        if (body.getRespCode() == 3) {
                            ToastUtils.showToast(body.getRespMsg());
                            Intent intent = new Intent(MessageService.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("errorlog", body.getRespMsg());
                            startActivity(intent);
                        } else {
                            NoReadMessage.RespBodyBean respBody = body.getRespBody();
                            if (respBody != null) {
                                if (respBody.getMids() != null && respBody.getMids().size() > 0) {
                                    int size = respBody.getMids().size();
                                    if (allsize >= size) {
                                    } else {
                                        allsize = size;
                                        EventBus.getDefault().post(new NoReadMessageEvent(size));
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NoReadMessage> call, Throwable t) {

            }
        });
    }

    private void readWarning() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_PATH) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        HomeService request = retrofit.create(HomeService.class);
        int userid = SharedPref.getInstance(this).getInt("userid", 0);
        String token = SharedPref.getInstance(MessageService.this).getString("TOKEN", null);
        //对 发送请求 进行封装
        Call<WarnningMessageBean> call = request.getWarningSize(token, userid + "");
        call.enqueue(new Callback<WarnningMessageBean>() {
            @Override
            public void onResponse(Call<WarnningMessageBean> call, Response<WarnningMessageBean> response) {
                try {
                    WarnningMessageBean body = response.body();
                    int respBody = body.getRespBody();
                    LogUtils.i("servicehflkdh", body.getRespBody() + "");
                    if (body.getRespCode() == 3) {
                        ToastUtils.showToast(body.getRespMsg());
                        Intent intent = new Intent(MessageService.this, LoginActivity.class);
                        intent.putExtra("errorlog", body.getRespMsg());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        if (respBody > 0) {
                            if (wanringsize == respBody) {
                            } else {
                                wanringsize = respBody;
                                doVibrator();
                                playAudio();
                                EventBus.getDefault().post(new WarningMessageEvent(respBody));
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<WarnningMessageBean> call, Throwable t) {

            }
        });
    }

    private static NetworkInfo getActiveNetworkInfo(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }

    private void playAudio() {
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.msg);
            mMediaPlayer.setLooping(false);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mMediaPlayer.release();
                    mMediaPlayer = null;
                }
            });
            mMediaPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doVibrator() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1500);//振动两秒
    }
}