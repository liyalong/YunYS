package com.yunyisheng.app.yunys.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.yunyisheng.app.yunys.utils.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo
 * 时间 2018-1-9
 * 邮箱：duoendeavor@163.com
 * 用途：基类activity
 */


public abstract class BaseActivity< M extends XPresent> extends XActivity<M> implements View.OnClickListener {

    /**
     * 退出程序的时间间隔
     */
    private long exitTime = 0;

    /**
     * 是否允许全屏,此处全屏是指将状态栏显示
     **/
    private boolean onlyShowStatusBar = false;
    /**
     * 是否允许全屏,此处全屏是指将状态栏都隐藏掉
     **/
    private boolean mAllowFullScreen = false;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 上下文
     **/
    public Context mContext = this;

    /**
     * 运行时权限的监听回调
     **/
    private static PressionListener mListener;
    private ActivityManager activityManager;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initAfter();
        setListener();
        activityManager = ActivityManager.getScreenManager();
        activityManager.pushActivity(this);
    }

    @Override
    public int getLayoutId() {
        if (mAllowFullScreen) {
            //隐藏状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        if (onlyShowStatusBar) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        return bindLayout();
    }

    @Override
    public M newP() {
        return bindPresent();
    }

    /**
     * [找控件]
     *
     * @return
     */
    public abstract void initView();

    /**
     * [找完控件后执行]
     *
     * @return
     */
    public abstract void initAfter();

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract M bindPresent();

    /**
     * [设置监听]
     */
    public abstract void setListener();

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View view) {
        if (fastClick())
        widgetClick(view);
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * 6.0以上检查申请权限时调用
     *
     * @param activity 当前使用的Activity
     * @param pression 需要申请检查的权限集合
     * @param listener 是否成功的回调方法
     */
    public static void requestRunTimePression(Activity activity, String[] pression, PressionListener listener) {
        mListener = listener;
        List<String> pressionList = new ArrayList<>();
        for (int i = 0; i < pression.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, pression[i]) != PackageManager.PERMISSION_GRANTED) {
                pressionList.add(pression[i]);
            }
        }

        if (!pressionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, pressionList.toArray(new String[pressionList.size()]), 1);
        } else {
            //do something
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> failureList = new ArrayList<>();
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        int granted = grantResults[i];
                        String pression = permissions[i];
                        if (granted != PackageManager.PERMISSION_GRANTED) {
                            failureList.add(pression);
                        }
                    }
                    if (failureList.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onFailure(failureList);
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * 描述：退出程序
     */
    protected void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            activityManager.popAllActivity();
        }
    }

    public void setOnlyShowStatusBar(boolean onlyShowStatusBar) {
        this.onlyShowStatusBar = onlyShowStatusBar;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManager.popActivity(this);
        Log.d(TAG, "onDestroy()");
    }
}