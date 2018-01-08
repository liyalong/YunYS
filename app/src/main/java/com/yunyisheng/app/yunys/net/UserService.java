package com.yunyisheng.app.yunys.net;

import com.yunyisheng.app.yunys.model.BaseStatusModel;
import com.yunyisheng.app.yunys.model.LoginModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liyalong on 2017/12/20.
 */

public interface UserService {
    /**
     * 登录
     * @param userPhone 手机号
     * @param userPassword 密码
     * @return
     */
    @FormUrlEncoded
    @POST("fairyland-system/system/android/user/login")
    Flowable<LoginModel> login(@Field("userName") String userPhone,
                               @Field("password") String userPassword,
                               @Field("androidID") String uuid);

    /**
     * 找回密码
     * @param phone
     * @param code
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("fairyland-system/system/android/user/changePassword")
    Flowable<BaseStatusModel> changePassword(@Field("phone") String phone,
                                                   @Field("code") String code,
                                                   @Field("newPassword") String password);


}
