package com.yunyisheng.app.yunys.net;

import com.yunyisheng.app.yunys.base.BaseStatusModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liyalong on 2018/1/5.
 */

public interface ShortMessageService {
    @FormUrlEncoded
    @POST("fairyland-system/system/android/user/login")
    Flowable<BaseStatusModel> getShortMessage(@Field("phone") String userPhone);

}
