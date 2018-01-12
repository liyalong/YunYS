package com.yunyisheng.app.yunys.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunyisheng.app.yunys.MainActivity;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.utils.Constans;

import java.lang.ref.WeakReference;

import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2017/12/15.
 */

public class WelcomeActivity extends BaseActivity {

    private mHandler handler=new mHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void initView() {
        SharedPref.init(Constans.SHARD_NAME);
        SharedPref sharedPref = SharedPref.getInstance(this);

        boolean isFirstOpen = sharedPref.getBoolean(Constans.FIRST_OPEN,true);
        if(!isFirstOpen){
            //添加引导流程页

        }
        handler.sendEmptyMessageDelayed(0,2000);
    }

    public class mHandler extends Handler{
        WeakReference<WelcomeActivity> activity;

        public mHandler(WelcomeActivity welcomeActivity) {
            activity=new WeakReference<WelcomeActivity>(welcomeActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            WelcomeActivity welcomeActivity = activity.get();
            if (welcomeActivity!=null){
                gotoMainActivity();
            }
        }
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
        }
        return R.layout.active_welcome;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }
}
