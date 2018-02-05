package com.yunyisheng.app.yunys.main.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.yunyisheng.app.yunys.main.model.WarningMessageEvent;
import com.yunyisheng.app.yunys.main.model.WarnningMessageBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LogUtils;

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
 * 
 */
public class MessageService extends Service {

	private static final String TAG = "MessageService";
	
	private boolean isRun;
	
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
	    startService(new Intent(this,MessageService.class));
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

				if (netWorkType==NETWORKTYPE_INVALID) { // 没联网
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

		//步骤4:创建Retrofit对象
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(Api.BASE_PATH) // 设置 网络请求 Url
				.addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
				.build();


		HomeService request = retrofit.create(HomeService.class);
		int userid = SharedPref.getInstance(this).getInt("userid", 0);
		//对 发送请求 进行封装
		Call<WarnningMessageBean> call = request.getWarningSize(userid+"");
        call.enqueue(new Callback<WarnningMessageBean>() {
			@Override
			public void onResponse(Call<WarnningMessageBean> call, Response<WarnningMessageBean> response) {
				WarnningMessageBean body = response.body();
				int respBody = body.getRespBody();
				LogUtils.i("servicehflkdh",body.getRespBody()+"");
				if (respBody>0){
					doVibrator();
				}
				EventBus.getDefault().post(new WarningMessageEvent(respBody));
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


	private void doVibrator(){
		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		vibrator.vibrate(1500);//振动两秒
	}



}