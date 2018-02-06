package com.yunyisheng.app.yunys.project.service;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.model.DeviceAlarmRulesModel;
import com.yunyisheng.app.yunys.project.model.DeviceInfoModel;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;
import com.yunyisheng.app.yunys.project.model.DevicePLCValueListModel;
import com.yunyisheng.app.yunys.project.model.DevicePartsListModel;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.project.model.KnowledgDetailModel;
import com.yunyisheng.app.yunys.project.model.KnowledgeListModel;
import com.yunyisheng.app.yunys.project.model.ModelAlarmRulesListModel;
import com.yunyisheng.app.yunys.project.model.ModelDetailModel;
import com.yunyisheng.app.yunys.project.model.ModelListModel;
import com.yunyisheng.app.yunys.project.model.PLCListModel;
import com.yunyisheng.app.yunys.project.model.PeriodicTaskListModel;
import com.yunyisheng.app.yunys.project.model.ProcessTaskFormDetailBean;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    Flowable<DeviceInfoModel> getDeviceInfo(@Path("projectId") String projectId,
                                            @Field("equipmentId") String deviceId);

    /**
     * 获取设备的报警规则列表
     * @param projectId
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/equip/warnList/{projectId}")
    Flowable<DeviceAlarmRulesModel> getDeviceAlarmRulesList(@Path("projectId") String projectId,
                                                            @Field("equipmentId") String deviceId,
                                                            @Field("pageNum") int pageNum,
                                                            @Field("pageSize") int pageSize);

    /**
     * 获取设备相关知识列表
     * @param projectId
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/knowledge/list/{projectId}")
    Flowable<KnowledgeListModel> getKnowledgeList(@Path("projectId") String projectId,
                                                  @Field("equipmentId") String deviceId,
                                                  @Field("pageNum") int pageNum,
                                                  @Field("pageSize") int pageSize);

    /**
     * 获取设备的备件列表
     * @param projectId
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/equip/spareList/{projectId}")
    Flowable<DevicePartsListModel> getDevicePartsList(@Path("projectId") String projectId,
                                                      @Field("equipmentId") String deviceId,
                                                      @Field("pageNum") int pageNum,
                                                      @Field("pageSize") int pageSize);

    /**
     * 获取设备的周期任务类别
     * @param projectId
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/equip/cycleTaskList/{projectId}")
    Flowable<PeriodicTaskListModel> getPeriodicTaskList(@Path("projectId") String projectId,
                                                        @Field("equipmentId") String deviceId,
                                                        @Field("pageNum") int pageNum,
                                                        @Field("pageSize") int pageSize);

    /**
     * 获取工艺模块下的设备列表
     * @param projectId
     * @param modelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/pcm/equip/list/{projectId}")
    Flowable<DeviceListModel> getModelDeviceList(@Path("projectId") String projectId,
                                                 @Field("pcmId") String modelId,
                                                 @Field("pageNum") int pageNum,
                                                 @Field("pageSize") int pageSize);

    /**
     * 获取工艺模块详情
     * @param projectId
     * @param modelId
     * @return
     */
    @FormUrlEncoded
    @POST("project/pcm/info/{projectId}")
    Flowable<ModelDetailModel> getModelDetail(@Path("projectId") String projectId,
                                              @Field("pcmId") String modelId);

    /**
     * 获取工艺模块相关知识列表
     * @param projectId
     * @param modelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/pcm/knowList/{projectId}")
    Flowable<KnowledgeListModel> getModelKnowledgeList(@Path("projectId") String projectId,
                                                       @Field("pcmId") String modelId,
                                                       @Field("pageNum") int pageNum,
                                                       @Field("pageSize") int pageSize);

    /**
     * 获取工艺模块相关报警规则列表
     * @param projectId
     * @param modelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/pcm/warnList/{projectId}")
    Flowable<ModelAlarmRulesListModel> getModelAlarmRulesList(@Path("projectId") String projectId,
                                                              @Field("pcmId") String modelId,
                                                              @Field("pageNum") int pageNum,
                                                              @Field("pageSize") int pageSize);

    /**
     * 获取知识详情
     * @param projectId
     * @param knowledgeId
     * @return
     */
    @FormUrlEncoded
    @POST("project/knowledge/info/{projectId}")
    Flowable<KnowledgDetailModel> getKnowledgeDetail(@Path("projectId") String projectId,
                                                     @Field("knowledgeId") String knowledgeId);

    /**
     * 获取项目的报警记录列表
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("alarmLogging/getAlarmLogging/{projectId}")
    Flowable<DeviceWarningListModel> getProjectWarningLists(@Path("projectId") String projectId,
                                                            @Field("pageNum") int pageNum,
                                                            @Field("pageSize") int pageSize);

    /**
     * 1.3	1.2	获取表单模版下的所有控件
     *
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("selectProcFormBasicDataList")
    Flowable<ProcessTaskFormDetailBean> getProcessTaskForm(@Field("processDefinitionId") String processDefinitionId);

    /**
     * 1.3	启动流程，并且设置当前流程实例的下一个办理人
     *
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("startProInsWithObj")
    Flowable<BaseModel> putProcessTaskForm(@Field("instanceFormStr") String instanceFormStr,
                                           @Field("assignee") int assignee,
                                           @Field("processDefinitionId") String processDefinitionId,
                                           @Field("endTime") String endTime);

    /**
     * 1.3	流程任务的转办
     *
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("task/setNextTaskUser")
    Flowable<BaseModel> zhaunBanTask(@Field("taskId") String taskId,
                                     @Field("userId") int userId);

    /**
     * 获取项目报警历史记录，设备、工艺模块实时报警记录
     *
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @param alarmProjectId
     * @param alarmEquPcmId
     * @param alarmType
     * @param alarmHandleType
     * @return
     */
    @FormUrlEncoded
    @POST("alarmLogging/getAlarmLogging/{projectId}")
    Flowable<DeviceWarningListModel> getWarningLists(@Path("projectId") String projectId,
                                                     @Field("pageNum") int pageNum,
                                                     @Field("pageSize") int pageSize,
                                                     @Field("alarmProjectId") String alarmProjectId,
                                                     @Field("alarmEquPcmId") String alarmEquPcmId,
                                                     @Field("alarmType") Integer alarmType,
                                                     @Field("alarmHandleType") Integer alarmHandleType);

    /**
     * 获取设备的实时指标列表
     * @param projectId
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST("property/selectProperty/{projectId}")
    Flowable<DevicePLCValueListModel> getDevicePLCValueList(@Path("projectId") String projectId,
                                                            @Field("deviceId") String deviceId);

    /**
     * 获取工艺模块plc实时指标
     * @param projectId
     * @param modelId
     * @return
     */
    @FormUrlEncoded
    @POST("property/selectPcmAllProperty/{projectId}")
    Flowable<DevicePLCValueListModel> getModelPLCValueList(@Path("projectId") String projectId,
                                                            @Field("pcmId") String modelId);

    /**
     * 报警重置
     * @param alarmLoggingId
     * @return
     */
    @FormUrlEncoded
    @POST("alarmLogging/reset")
    Flowable<BaseModel> warningReset(@Field("alarmId") Integer alarmLoggingId);

    /**
     * 获取单个plc指定时间的数据
     * @param propertyId
     * @param interval
     * @return
     */
    @FormUrlEncoded
    @POST("equip/select/propertyPlc")
    Flowable<PLCListModel> getPLCList(@Field("propertyId") String propertyId,
                                      @Field("interval") int interval);


}
