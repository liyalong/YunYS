package com.yunyisheng.app.yunys.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.MainActivity;
import com.yunyisheng.app.yunys.utils.Constans;

import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * Created by liyalong on 2017/12/15.
 */

public class WelcomeActivity extends XActivity {
    @Override
    public void initData(Bundle savedInstanceState) {
        SharedPref.init(Constans.SHARD_NAME);
        SharedPref sharedPref = SharedPref.getInstance(this);

        boolean isFirstOpen = sharedPref.getBoolean(Constans.FIRST_OPEN,true);
        if(!isFirstOpen){
            //TODO 添加引导流程页

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMainActivity();
            }
        },2000);
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public int getLayoutId() {
        return R.layout.active_welcome;
    }

    @Override
    public Object newP() {
        return null;
    }
}
