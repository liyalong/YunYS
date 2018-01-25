package com.yunyisheng.app.yunys.net;

import com.yunyisheng.app.yunys.utils.TokenHeaderInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：fuduo on 2018/1/24 17:04
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class RetrofitManager {
    private Retrofit mRetrofit;

    private volatile static RetrofitManager instance;

    private RetrofitManager() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addNetworkInterceptor(new TokenHeaderInterceptor());

        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_PATH)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();

    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = RetrofitManager.getInstance().initRetrofit();
        }
        return mRetrofit;
    }
    private Retrofit initRetrofit() {
        return mRetrofit;
    }
}
