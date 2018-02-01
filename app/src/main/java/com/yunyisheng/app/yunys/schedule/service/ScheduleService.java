package com.yunyisheng.app.yunys.schedule.service;

import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
                                               @Field("startTime") long startTime,
                                               @Field("endTime") long endTime);

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
                                                  @Field("startTime") long startTime,
                                                  @Field("endTime") long endTime);
    /**
     *  @author fuduo
     *  @time 2018/1/31  18:18
     *  @describe 14.2	查看日程详情(解析任务表单)
     */
    @FormUrlEncoded
    @POST("task/information/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<ScheduleDetailBean> getScheduleDetail(@Field("userId") int userId,
                                                   @Field("taskId") String taskId,
                                                   @Field("type") int type);
}
