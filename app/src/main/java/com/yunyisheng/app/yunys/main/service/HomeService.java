package com.yunyisheng.app.yunys.main.service;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.main.model.BuMenBean;
import com.yunyisheng.app.yunys.main.model.FindProjectWorkerBean;
import com.yunyisheng.app.yunys.main.model.FindWorkerBean;
import com.yunyisheng.app.yunys.main.model.GetOtherinfoBean;
import com.yunyisheng.app.yunys.main.model.MemorandumBean;
import com.yunyisheng.app.yunys.main.model.MessageBean;
import com.yunyisheng.app.yunys.main.model.NoticeDetailBean;
import com.yunyisheng.app.yunys.main.model.ProjectFromWorkBean;
import com.yunyisheng.app.yunys.main.model.ReportFormBean;
import com.yunyisheng.app.yunys.main.model.RoleBean;
import com.yunyisheng.app.yunys.main.model.SendNoticeBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

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
    @POST("announcement/list/publish")
    Flowable<SendNoticeBean> getSendNoticelist(@Field("pageNum") int pageNum,
                                               @Field("pageSize") int pageSize,
                                               @Field("title") String title);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 获取接收的公告列表
     */
    @FormUrlEncoded
    @POST("announcement/list/receive")
    Flowable<SendNoticeBean> getReciveNoticelist(@Field("pageNum") int pageNum,
                                                 @Field("pageSize") int pageSize,
                                                 @Field("title") String title);

    /**
     * @param announcementId 公告id
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 7.3    获取发布的公告详情
     */
    @FormUrlEncoded
    @POST("announcement/info/publish")
    Flowable<NoticeDetailBean> getSendNoticeDetail(@Field("announcementId") int announcementId);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 7.3    获取接收的公告详情
     */
    @FormUrlEncoded
    @POST("announcement/info/publish")
    Flowable<NoticeDetailBean> getReciveNoticeDetail(@Field("announcementId") int announcementId);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 7.5    发布公告
     */
    @POST()
    Call<BaseModel> sendNotice(@Header("token") String token,
                               @Url() String url,
                               @Body RequestBody Body);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 删除公告
     */
    @FormUrlEncoded
    @POST("announcement/delete/publish")
    Flowable<BaseModel> deleteNotice(@Field("announcementId") int announcementId);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 添加新人员
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("enterprise/user/add")
    Flowable<BaseModel> addPeople(@Field("userName") String userName,
                                  @Field("userSex") String userSex,
                                  @Field("userPhone") String userPhone,
                                  @Field("userMailbox") String userMailbox,
                                  @Field("userNumber") String userNumber,
                                  @Field("userJobTitle") String userJobTitle,
                                  @Field("enterpriseSectionId") int enterpriseSectionId,
                                  @Field("enterpriseRolesId") int enterpriseRolesId,
                                  @Field("userPicture") String userPicture);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 修改其他员工资料
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("system/update/enterprirUser/employee/info")
    Flowable<BaseModel> changeOtherWorkeninfo(@Field("userName") String userName,
                                              @Field("userPhone") String userPhone,
                                              @Field("userMailbox") String userMailbox,
                                              @Field("userJobTitle") String userJobTitle,
                                              @Field("userId") int userId,
                                              @Field("userPicture") String userPicture,
                                              @Field("userIsShow") boolean userIsShow
    );

    /**
     * @author fuduo
     * @time 2018/1/23  14:02
     * @describe 查询备忘录
     */
    @GET("memo/selectmemo")
    Flowable<MemorandumBean> getMemorandumList(@Query("pagenum") int pagenum,
                                               @Query("pagerows") int pagerows);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 创建备忘录
     */
    @FormUrlEncoded
    @POST("memo/creatememo")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<BaseModel> createMemo(@Field("memoVal") String memoVal);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 修改备忘录
     */
    @FormUrlEncoded
    @POST("memo/updatememo")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<BaseModel> updateMemo(@Field("memoVal") String memoValz, @Field("memoId") int memoId);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 删除备忘录
     */
    @FormUrlEncoded
    @POST("memo/deletememo")
    Flowable<BaseModel> deleteMemo(@Field("ids") int ids);

    /**
     * 获取用户通讯录
     *
     * @return
     */
    @POST("android/enterprise/section/show")
    Call<String> getUserFromwork();

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 10.3    根据userid获取对应人员的基本信息
     */
    @FormUrlEncoded
    @POST("android/enterprise/user/info")
    Flowable<GetOtherinfoBean> getOtherinfo(@Field("userId") int userId);

    /**
     * 获取报表列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("myReport/select/all")
    Flowable<ReportFormBean> getBaobiaolist(@Field("pagenum") int Pagenum, @Field("pagerows") int Pagerows);

    /**
     * @author fuduo
     * @time 2018/1/26  20:26
     * @describe 获取项目架构
     */
    @POST("project/projectsAndPeoples")
    Flowable<ProjectFromWorkBean> getProjectFromwork();


    /**
     * 通讯录全文检索
     *
     * @return
     */
    @FormUrlEncoded
    @POST("look/addressBook/user/search")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<FindWorkerBean> getfindworkerlist(@Field("parameter") String parameter);

    /**
     * 项目检索
     *
     * @return
     */
    @FormUrlEncoded
    @POST("project/searchPeoplesInProjects")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<FindProjectWorkerBean> getfindProjectworkerlist(@Field("content") String content);

    /**
     * @author fuduo
     * @time 2018/1/26  20:26
     * @describe 获取所有部门
     */
    @POST("enterprise/section/show")
    Flowable<BuMenBean> getBumenlist();

    /**
     * @author fuduo
     * @time 2018/1/26  20:26
     * @describe 获取所在通讯录角色
     */
    @POST("look/addressBook/role")
    Flowable<RoleBean> getRolelist();

    /**
     *  @author fuduo
     *  @time 2018/1/30  14:56
     *  @describe 查询所有消息
     */
    @FormUrlEncoded
    @POST("message/selectMessage")
    Flowable<MessageBean> getMessagelist(@Field("pagenum") int pagenum,
                                         @Field("pagerows") int pagerows,
                                         @Field("messageReceiveUserId") int userid);

}
