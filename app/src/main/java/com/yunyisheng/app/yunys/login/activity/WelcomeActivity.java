package com.yunyisheng.app.yunys.login.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.yunyisheng.app.yunys.MainActivity;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.login.present.WelcomePagePresent;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.Constans;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import cn.droidlover.xdroidbase.cache.SharedPref;

/**
 * Created by liyalong on 2017/12/15.
 */

public class WelcomeActivity extends BaseActivity<WelcomePagePresent> {

    @BindView(R.id.welcomeView)
    ImageView welcomeView;
    private mHandler handler = new mHandler(this);
    private String enterpriseimg;
    private String companyimg;

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void initView() {
        SharedPref.init(Constans.SHARD_NAME);
        SharedPref sharedPref = SharedPref.getInstance(this);

        boolean isFirstOpen = sharedPref.getBoolean(Constans.FIRST_OPEN, true);
        if (!isFirstOpen) {
            //添加引导流程页
        }
        if (!isFirstOpen) {
            netError();
        } else {
            String token = SharedPref.getInstance(context).getString("TOKEN", "");
            if (token != null && !token.equals("")) {
                enterpriseimg = SharedPref.getInstance(mContext).getString("enterpriseimg", "");
                if (enterpriseimg != null && !enterpriseimg.equals("")) {
                    try {
                        Bitmap bitmap = CommonUtils.stringtoBitmap(enterpriseimg);
                        welcomeView.setImageBitmap(bitmap);
                        handler.sendEmptyMessageDelayed(0, 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        netError();
                    }
                } else {
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            } else {
                netError();
            }
        }
    }

    public class mHandler extends Handler {
        WeakReference<WelcomeActivity> activity;

        public mHandler(WelcomeActivity welcomeActivity) {
            activity = new WeakReference<WelcomeActivity>(welcomeActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            WelcomeActivity welcomeActivity = activity.get();
            if (what == 0) {
                try {
                    companyimg = SharedPref.getInstance(mContext).getString("companyimg", "");
                    if (companyimg != null && !companyimg.equals("")) {
                        Bitmap bitmap = CommonUtils.stringtoBitmap(companyimg);
                        welcomeView.setImageBitmap(bitmap);
                        handler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        netError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    netError();
                }
            } else if (what == 1) {
                if (welcomeActivity != null) {
                    gotoMainActivity();
                }
            } else {
                if (welcomeActivity != null) {
                    gotoMainActivity();
                }
            }
        }
    }

    @Override
    public void initAfter() {
    }

    public void netError() {
        handler.sendEmptyMessageDelayed(2, 1000);
    }

    @Override
    public int bindLayout() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
        }
        return R.layout.active_welcome;
    }

    @Override
    public WelcomePagePresent bindPresent() {
        return new WelcomePagePresent();
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
