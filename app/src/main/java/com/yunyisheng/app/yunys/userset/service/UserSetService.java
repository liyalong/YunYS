package com.yunyisheng.app.yunys.userset.service;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.userset.model.CompanyBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 作者：fuduo on 2018/1/21 16:15
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public interface UserSetService {
    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 修改密码
     */
    @FormUrlEncoded
    @POST("system/update/enterprirUser/password")
    Flowable<BaseModel> upDatepassword(@Field("oldPassword") String oldPassword,
                                       @Field("newPassword") String newPassword);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 修改头像
     */
    @POST()
    Call<BaseModel> changeHead(@Header("token") String token,
            @Url() String url,
            @Body RequestBody Body);


    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 修改邮箱
     */
    @FormUrlEncoded
    @POST("system/update/enterprirUser/userMailbox")
    Flowable<BaseModel> upDateemail(@Field("userMailbox") String userMailbox);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 3.1	修改手机号时的短信验证码
     */
    @FormUrlEncoded
    @POST("system/enterprirUser/obtain/authCode")
    Flowable<BaseModel> sendCode(@Field("phone") String phone);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 获取个人所在的企业信息
     */
    @POST("enterprise/forent")
    Flowable<CompanyBean> getCompanyinfo();

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 3.1	反馈意见
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("idea/createidea")
    Flowable<BaseModel> sendFankui(@Field("ideaVal") String ideaVal);
}
