package com.yunyisheng.app.yunys.login.service;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.login.model.CityModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by liyalong on 2018/1/5.
 * 企业注册Service
 */

public interface CompanyService {
    /**
     * @author fuduo
     * @time 2018/1/16  10:59
     * @describe 注册
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("enterpriseConflict/add")
    Flowable<BaseModel> registerCompany(@Field("enterpriseName") String enterpriseName,
                                        @Field("enterpriseAddressProvince") String enterpriseAddressProvince,
                                        @Field("enterpriseAddressCity") String enterpriseAddressCity,
                                        @Field("enterpriseAddressDistrict") String enterpriseAddressDistrict,
                                        @Field("enterpriseAddressParticular") String enterpriseAddressParticular,
                                        @Field("enterpriseContact") String enterpriseContact,
                                        @Field("enterprisePhone") String enterprisePhone,
                                        @Field("enterpriseMailbox") String enterpriseMailbox,
                                        @Field("description") String description);
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("area/getSubArea")
    Flowable<CityModel> getSubArea(@Field("pId") Integer pid);
}
