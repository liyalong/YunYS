package com.yunyisheng.app.yunys.main.roadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yunyisheng.app.yunys.main.activity.MessageActivity;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.LogUtils;

/**
 * 作者：fuduo on 2018/3/5 15:56
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("str");
        LogUtils.i("strtr", str);
        //判断app进程是否存活
        if (CommonUtils.isAppRunning(context, "com.yunyisheng.app.yunys")) {
            intent.setClass(context, MessageActivity.class);
            context.startActivity(intent);
        } else {
            //如果app进程已经被杀死，先重新启动app，将DetailActivity的启动参数传入Intent中，参数经过 //SplashActivity传入MainActivity，此时app的初始化已经完成，在MainActivity中就可以根据传入 //参数跳转到DetailActivity中去了
            Log.i("NotificationReceiver", "the app process is dead");
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.yunyisheng.app.yunys");
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(launchIntent);
        }
    }
}
