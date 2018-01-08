package com.yunyisheng.app.yunys.net;

import com.yunyisheng.app.yunys.model.BaseStatusModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liyalong on 2018/1/5.
 */

public interface CompanyService {
    @FormUrlEncoded
    @POST("fairyland-system/system/android/company/add")
    Flowable<BaseStatusModel> registerCompany(@Field("commpany_name") String company_name,
                                              @Field("name") String name,
                                              @Field("phone") String phone,
                                              @Field("password") String password,
                                              @Field("code") String code);
}
