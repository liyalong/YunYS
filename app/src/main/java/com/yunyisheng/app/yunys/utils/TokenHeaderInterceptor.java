package com.yunyisheng.app.yunys.utils;

import java.io.IOException;

import cn.droidlover.xdroidbase.cache.SharedPref;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.yunyisheng.app.yunys.App.context;

/**
 * Created by liyalong on 2018/1/3.
 * 拦截器 加入token
 */

public class TokenHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (!originalRequest.url().toString().contains("login")){
            String token = SharedPref.getInstance(context).getString("TOKEN",null);
            Request updateRequest = originalRequest.newBuilder()
                    .header("token", token)
                    .build();
            return chain.proceed(updateRequest);
        }else{
            return chain.proceed(originalRequest);
        }

    }
}
