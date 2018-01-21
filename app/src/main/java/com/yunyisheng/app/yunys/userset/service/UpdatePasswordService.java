package com.yunyisheng.app.yunys.userset.service;

import com.yunyisheng.app.yunys.base.BaseModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：fuduo on 2018/1/21 10:33
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public interface UpdatePasswordService {

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 修改密码
     */
    @FormUrlEncoded
    @POST("system/update/enterprirUser/password")
    Flowable<BaseModel> upDatepassword(@Field("oldPassword") String oldPassword,
                                       @Field("newPassword") String newPassword);
}
