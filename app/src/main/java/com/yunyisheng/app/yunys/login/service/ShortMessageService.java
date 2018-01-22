package com.yunyisheng.app.yunys.login.service;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.base.BaseStatusModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liyalong on 2018/1/5.
 */

public interface ShortMessageService {
    /**
     * 获取短信验证码
     * @param userPhone
     * @return
     */
    //fairyland-system/system/select/enterprirUser/authCode
    @FormUrlEncoded
    @POST("system/select/enterprirUser/authCode")
    Flowable<BaseStatusModel> getShortMessage(@Field("userName") String userPhone);

}
