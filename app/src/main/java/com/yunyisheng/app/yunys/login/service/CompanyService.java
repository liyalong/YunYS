package com.yunyisheng.app.yunys.login.service;

import com.yunyisheng.app.yunys.base.BaseStatusModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liyalong on 2018/1/5.
 */

public interface CompanyService {
    /**
     * @author fuduo
     * @time 2018/1/16  10:59
     * @describe 注册
     */
    @FormUrlEncoded
    @POST("system/android/company/add")
    Flowable<BaseStatusModel> registerCompany(@Field("commpany_name") String company_name,
                                              @Field("name") String name,
                                              @Field("phone") String phone,
                                              @Field("password") String password,
                                              @Field("code") String code);
}
