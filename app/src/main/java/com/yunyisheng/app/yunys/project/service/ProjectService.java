package com.yunyisheng.app.yunys.project.service;

import com.yunyisheng.app.yunys.project.bean.DeviceBean;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;
import com.yunyisheng.app.yunys.project.model.DevicePLCValueListModel;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.project.model.ModelListModel;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liyalong on 2018/1/17.
 */

public interface ProjectService {
    /**
     * 获取公司项目列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/list/company")
    Flowable<ProjectListModel> getCompanyProjectList(@Field("pageNum") int pageNum,
                                                     @Field("pageSize") int pageSize);

    /**
     * 获取我参与的项目列表
     * @param pageNum
     * @param pageSize
     * @param projectName
     * @return
     */
    @FormUrlEncoded
    @POST("project/list/myJoin")
    Flowable<ProjectListModel> getMyProjectList(@Field("pageNum") int pageNum,
                                                @Field("pageSize") int pageSize,
                                                @Field("projectName") String projectName);

    /**
     * 获取项目下的设备列表
     * @param projectId
     * @param pageNum
     * @param PageSize
     * @param equipmentName
     * @return
     */
    @FormUrlEncoded
    @POST("project/equip/list/{projectId}")
    Flowable<DeviceListModel> getProjectDeviceList(@Path("projectId") String projectId,
                                                   @Field("pageNum") int pageNum,
                                                   @Field("pageSize") int PageSize,
                                                   @Field("equipmentName") String equipmentName);

    /**
     * 获取项目下工艺模块列表
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/pcm/list/{projectId}")
    Flowable<ModelListModel> getPrjectModelList(@Path("projectId") String projectId,
                                                @Field("pageNum") int pageNum,
                                                @Field("pageSize") int pageSize);

    /**
     * 获取设备的实时报警列表
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST("project/pcm/list")
    Flowable<DeviceWarningListModel> getDeviceWarningList(@Field("deviceId") String deviceId);

    /**
     * 获取设备的实时指标列表
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST()
    Flowable<DevicePLCValueListModel> getDevicePlcValueList(@Field("deviceId") String deviceId);

    /**
     * 获取设备的基本信息
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST("project/equip/info/{projectId}")
    Flowable<DeviceBean> getDeviceInfo(@Path("projectId") String projectId,
                                       @Field("equipmentId") String deviceId);
}
