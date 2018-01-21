package com.yunyisheng.app.yunys.main.service;

import com.yunyisheng.app.yunys.login.model.UserModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：fuduo on 2018/1/21 12:37
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public interface HomeService {

    /**
     * 获取用户个人信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("system/select/enterprise/user")
    Flowable<UserModel> getuserinfo(@Field("1") int name);
}
