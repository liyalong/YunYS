package com.yunyisheng.app.yunys;

import android.app.Application;
import android.content.Context;

import com.yunyisheng.app.yunys.utils.TokenHeaderInterceptor;
import com.yunyisheng.app.yunys.utils.UnCeHandler;

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

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = this;
        init();
        XApi.registerProvider(new NetProvider() {

            @Override
            public Interceptor[] configInterceptors() {
                Interceptor interceptor = new TokenHeaderInterceptor();
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
