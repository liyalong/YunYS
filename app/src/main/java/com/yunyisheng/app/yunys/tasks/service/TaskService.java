package com.yunyisheng.app.yunys.tasks.service;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.model.TaskListModel;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.tasks.model.TaskDetailModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liyalong on 2018/1/29.
 */

public interface TaskService {
    /**
     * 获取已发布的任务列表
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    //@FormUrlEncoded
    @GET("task/getMyTaskList/{projectId}")
    Flowable<TaskListModel> getMyTaskList(@Path("projectId") String projectId,
                                          @Query("pagenum") int pageNum,
                                          @Query("pagerows") int pageSize);

    /**
     * 获取未认领的任务列表
     * @param projectId
     * @param pageNum
     * @param pgaeSize
     * @return
     */
    //@FormUrlEncoded
    @GET("task/getUnClaimTask/{projectId}")
    Flowable<TaskListModel> getUnClaimTaskList(@Path("projectId") String projectId,
                                               @Query("pagenum") int pageNum,
                                               @Query("pagerows") int pgaeSize);

    /**
     * 获取待完成的任务列表
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    //@FormUrlEncoded
    @GET("task/getClaimTask/{projectId}")
    Flowable<TaskListModel> getClaimTaskList(@Path("projectId") String projectId,
                                             @Query("pagenum") int pageNum,
                                             @Query("pagerows") int pageSize);

    /**
     * 认领任务
     * @param taskId
     * @return
     */
    @FormUrlEncoded
    @POST("task/claimTask")
    Flowable<BaseModel> claimTask(@Field("taskId") String taskId);

    /**
     * 获取任务详情
     * @param projectId
     * @param taskId
     * @return
     */
    @FormUrlEncoded
    @POST("task/getTaskInfo/{projectId}")
    Flowable<TaskDetailModel> getTaskDetail(@Path("projectId") String projectId,
                                            @Field("taskId") String taskId);

    /**
     *  @author fuduo
     *  @time 2018/1/31  18:18
     *  @describe 14.2	获取我的任务详情
     */
    @FormUrlEncoded
    @POST("task/information/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<TaskDetailModel> getMyTaskDetail(@Field("taskId") String taskId,
                                                   @Field("type") String type);
    /**
     *  @author fuduo
     *  @time 2018/1/31  18:18
     *  @describe 14.2	获取指定用户的任务详情
     */
    @FormUrlEncoded
    @POST("task/information/lookList")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Flowable<TaskDetailModel> getTaskDetailByUser(@Field("userId") String userId,
                                                     @Field("taskId") String taskId,
                                                   @Field("type") String type);


}
