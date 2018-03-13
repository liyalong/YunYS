package com.yunyisheng.app.yunys.schedule.service;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.UploadDynamicFormImageBean;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.schedule.model.RenWuFanKuiDetailBean;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.schedule.model.ScheduleNoSizeBean;
import com.yunyisheng.app.yunys.schedule.model.SeeScheduleDetailBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * 作者：fuduo on 2018/1/29 19:17
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public interface ScheduleService {

    /**
     * 14.1	获取指定日期的当前登录员工的日程列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("android/enterpriseUser/schedule/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<MyScheduleBean> getMyschedulelist(@Field("pageNum") int pageNum,
                                               @Field("pageSize") int pageSize,
                                               @Field("startTime") String startTime,
                                               @Field("endTime") String endTime);

    /**
     * 根据项目id获取项目日程
     *
     * @return
     */
    @FormUrlEncoded
    @POST("android/project/projectSchedule/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<MyScheduleBean> getProjectschedulelist(@Field("pageNum") int pageNum,
                                                    @Field("pageSize") int pageSize,
                                                    @Field("projectId") String projectId,
                                                    @Field("startTime") String startTime,
                                                    @Field("endTime") String endTime);

    /**
     * 根据项目id获取项目未完成日程日期
     *
     * @return
     */
    @FormUrlEncoded
    @POST("android/enterpriseUser/project/schedule/numList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<ScheduleNoSizeBean> getProjectscheduleDatelist(@Field("startTime") String startTime,
                                                            @Field("endTime") String endTime,
                                                            @Field("projectId") String projectId);

    /**
     * 获取未完成任务的日期
     *
     * @return
     */
    @FormUrlEncoded
    @POST("android/enterpriseUser/schedule/numList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<ScheduleNoSizeBean> getNoschedulelist(@Field("startTime") String startTime,
                                                   @Field("endTime") String endTime,
                                                   @Field("type") int type);

    /**
     * @author fuduo
     * @time 2018/1/31  18:18
     * @describe 14.2    查看别人日程详情(解析任务表单)
     */
    @FormUrlEncoded
    @POST("task/information/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<ScheduleDetailBean> getOtherScheduleDetail(@Field("userId") int userId,
                                                        @Field("taskId") String taskId,
                                                        @Field("type") int type);

    /**
     * @author fuduo
     * @time 2018/1/31  18:18
     * @describe 14.2    查看别人日程详情(解析任务表单)
     */
    @FormUrlEncoded
    @POST("task/information/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<SeeScheduleDetailBean> getOtherFormScheduleDetail(@Field("userId") int userId,
                                                               @Field("taskId") String taskId,
                                                               @Field("type") int type);

    /**
     * @author fuduo
     * @time 2018/1/21  10:38
     * @describe 上传表单图片
     */
    @POST()
    Call<UploadDynamicFormImageBean> uploadImage(@Header("token") String token,
                                                 @Url() String url,
                                                 @Body RequestBody Body);

    /**
     * @author fuduo
     * @time 2018/1/31  18:18
     * @describe 下载展示表单图片
     */
    @FormUrlEncoded
    @POST("formFile/download")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<UploadDynamicFormImageBean> getFormImage(@Field("fileUrl") String fileUrl);

    /**
     * @author fuduo
     * @time 2018/1/31  18:18
     * @describe 14.2    查看自己日程详情(解析任务表单)
     */
    @FormUrlEncoded
    @POST("task/information/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<ScheduleDetailBean> getMineScheduleDetail(@Field("taskId") String taskId,
                                                       @Field("type") int type);

    /**
     * @author fuduo
     * @time 2018/1/31  18:18
     * @describe 14.2    查看自己日程详情(解析任务表单)
     */
    @FormUrlEncoded
    @POST("task/information/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<SeeScheduleDetailBean> getMineFormScheduleDetail(@Field("taskId") String taskId,
                                                              @Field("type") int type);

    /**
     * @author fuduo
     * @time 2018/1/31  18:18
     * @describe 提交任务
     */
    @FormUrlEncoded
    @POST("task/execute")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<BaseModel> putScheduleDetail(@Field("taskId") int taskId,
                                          @Field("instanceFormStr") String instanceFormStr);

    /**
     * @author fuduo
     * @time 2018/1/31  18:18
     * @describe 提交任务
     */
    @FormUrlEncoded
    @POST("task/execute")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<BaseModel> putRenwuDetail(@Field("taskId") int taskId,
                                       @Field("feedbackStr") String feedbackStr);

    /**
     * @author fuduo
     * @time 2018/1/31  18:18
     * @describe 14.2   (解析任务反馈项)
     */
    @FormUrlEncoded
    @POST("task/getTaskInfo/{projectId}")
    Flowable<RenWuFanKuiDetailBean> getTaskInfo(@Path("projectId") String projectId,
                                                @Field("taskId") int taskId);

    /**
     * @author fuduo
     * @time 2018/2/1  18:05
     * @describe 提交任务反馈项的图片
     */
    @POST()
    Call<BaseModel> putRenwuPic(@Header("token") String token,
                                @Url() String url,
                                @Body RequestBody Body);
}
