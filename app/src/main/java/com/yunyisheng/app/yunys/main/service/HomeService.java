package com.yunyisheng.app.yunys.main.service;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.main.model.ReciveNoticeBean;
import com.yunyisheng.app.yunys.main.model.SendNoticeBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * 作者：fuduo on 2018/1/21 12:37
 * 邮箱：duoendeavor@163.com
 * 用途：首页相关接口service
 */

public interface HomeService {

    /**
     * 获取用户个人信息
     *
     * @return
     */
    @POST("system/select/enterprise/user")
    Flowable<UserModel> getuserinfo();

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 获取发布的公告列表
     */
    @FormUrlEncoded
    @POST("system/announcement/list/publish")
    Flowable<SendNoticeBean> getSendNoticelist(@Field("pageNum") int pageNum,
                                               @Field("pageSize") int pageSize,
                                               @Field("title") String title);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 获取接收的公告列表
     */
    @FormUrlEncoded
    @POST("system/announcement/list/receive")
    Flowable<ReciveNoticeBean> getReciveNoticelist(@Field("pageNum") int pageNum,
                                                   @Field("pageSize") int pageSize,
                                                   @Field("title") String title);

    /**
     * @param announcementId 公告id
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 7.3    获取发布的公告详情
     */
    @FormUrlEncoded
    @POST("system/announcement/info/publish")
    Flowable<BaseModel> getSendNoticeDetail(@Field("announcementId") int announcementId);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 7.3    获取接收的公告详情
     */
    @FormUrlEncoded
    @POST("system/announcement/info/publish")
    Flowable<BaseModel> getReciveNoticeDetail(@Field("announcementId") int announcementId);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 7.5    发布公告
     */
    @FormUrlEncoded
    @Multipart
    @POST("system/announcement/publish")
    Flowable<BaseModel> sendNotice(@Field("title") String title,
                                   @Field("content") String content,
                                   @Field("receiverMap") String receiverMap,
                                   @PartMap Map<String, ResponseBody> map
    );

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 删除公告
     */
    @FormUrlEncoded
    @POST("system/announcement/delete")
    Flowable<BaseModel> deleteNotice(@Field("announcementId") int announcementId);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 添加新人员
     */
    @FormUrlEncoded
    @Multipart
    @POST("system/announcement/publish")
    Flowable<BaseModel> addPeople(@Field("userName") String userName,
                                  @Field("userSex") String userSex,
                                  @Field("userPhone") String userPhone,
                                  @Field("userMailbox") String userMailbox,
                                  @Field("userNumber") String userNumber,
                                  @Field("userJobTitle") String userJobTitle,
                                  @Field("enterpriseSectionId") String enterpriseSectionId,
                                  @Field("enterpriseRolesId") String enterpriseRolesId,
                                  @Field("userPicture") String userPicture
    );

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 修改其他员工资料
     */
    @FormUrlEncoded
    @Multipart
    @POST("system/update/enterprirUser/employee/info")
    Flowable<BaseModel> changeOtherWorkeninfo(@Field("userName") String userName,
                                              @Field("userSex") String userSex,
                                              @Field("userPhone") String userPhone,
                                              @Field("userMailbox") String userMailbox,
                                              @Field("userJobTitle") String userJobTitle,
                                              @Field("userId") String userId,
                                              @Field("userPicture") String userPicture
    );
}
