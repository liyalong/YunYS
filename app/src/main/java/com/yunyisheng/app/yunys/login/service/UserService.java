package com.yunyisheng.app.yunys.login.service;

import com.yunyisheng.app.yunys.base.BaseStatusModel;
import com.yunyisheng.app.yunys.login.model.LoginModel;
import com.yunyisheng.app.yunys.login.model.WelcomePageBean;
import com.yunyisheng.app.yunys.userset.model.MySourceModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liyalong on 2017/12/20.
 * UserService
 */

public interface UserService {
    /**
     * 登录
     * @param userPhone 手机号
     * @param userPassword 密码
     * @return
     */
    @FormUrlEncoded
    @POST("system/android/user/login")
    Flowable<LoginModel> login(@Field("userName") String userPhone,
                               @Field("password") String userPassword,
                               @Field("androidID") String uuid,
                               @Field("authCode") String yzm);

    /**
     * 找回密码
     * @param phone
     * @param code
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("system/update/enterprirUser/feorget/password")
    Flowable<BaseStatusModel> changePassword(@Field("userName") String phone,
                                                   @Field("authCode") String code,
                                                   @Field("newPassword") String password,
                                             @Field("type")  int type,
                                             @Field("key") String key);

    /**
     * 获取欢迎页
     */
    @POST("enterprise/companysApp")
    Flowable<WelcomePageBean> getWelcomePage();
    /**
     * 获取随机数
     * @return
     */
    @POST("system/source")
    Flowable<MySourceModel> getSource(@Field("type") Integer type);

}
