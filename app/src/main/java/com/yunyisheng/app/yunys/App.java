package com.yunyisheng.app.yunys;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.yunyisheng.app.yunys.utils.ResultInterceptor;
import com.yunyisheng.app.yunys.utils.TokenHeaderInterceptor;

import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by liyalong on 2017/12/6.
 */

public class App extends Application {
    public static Context context;
    public static App app;
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = this;
        init();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                mContext = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        XApi.registerProvider(new NetProvider() {

            @Override
            public Interceptor[] configInterceptors() {
                Interceptor interceptor = new TokenHeaderInterceptor();
                Interceptor resultinterceptor = new ResultInterceptor(mContext);
                return new Interceptor[]{interceptor};
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }
        });
    }

    private void init() {
//        UnCeHandler.getInstance().init(this);
    }

    public static Context getContext() {
        return context;
    }

    public static App getInstance() {
        return app;
    }
}
