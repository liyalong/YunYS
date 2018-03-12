package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.TaskDetailActivity;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.tasks.model.TaskDetailModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/31.
 */

public class TaskDetailPresent extends XPresent<TaskDetailActivity> {
    public void getTask(String projectId,String taskId,String taskType,int userId){
        if (userId == 0){
            getMyTaskDetail(projectId,taskId);
        }else {
            getTaskDetailByUserId(taskId,taskType,userId);
        }
    }

    /**
     * 获取当前登录人的任务详情
     * @param projectId
     * @param taskId
     */
    public void getMyTaskDetail(String projectId,String taskId){
        LoadingDialog.show(getV().mContext);
        Api.taskService().getTaskDetail(projectId,taskId)
                .compose(XApi.<TaskDetailModel>getApiTransformer())
                .compose(XApi.<TaskDetailModel>getScheduler())
                .compose(getV().<TaskDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().mContext);
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(TaskDetailModel taskDetailModel) {
                        LoadingDialog.dismiss(getV().mContext);
                        if (taskDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(taskDetailModel.getRespMsg());
                            getV().goFinish();
                            return;
                        }
                        getV().setDetail(taskDetailModel.getRespBody().getTask(),
                                taskDetailModel.getRespBody().getTaskback(),
                                taskDetailModel.getRespBody().getForm());
                    }
                });
    }

    /**
     * 获取指定人员的任务详情
     * @param taskId
     * @param taskType
     * @param userId
     */
    public void getTaskDetailByUserId(String taskId,String taskType,int userId){
        LoadingDialog.show(getV().mContext);
        Api.taskService().getTaskDetailByUser(userId,taskId,taskType)
                .compose(XApi.<ScheduleDetailBean>getApiTransformer())
                .compose(XApi.<ScheduleDetailBean>getScheduler())
                .compose(getV().<ScheduleDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<ScheduleDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().mContext);
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ScheduleDetailBean taskDetailModel) {
                        LoadingDialog.dismiss(getV().mContext);
                        if (taskDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(taskDetailModel.getRespMsg());
                            getV().goFinish();
                            return;
                        }
                        getV().setDetail(taskDetailModel.getRespBody().getTask(),
                                taskDetailModel.getRespBody().getTaskback(),
                                taskDetailModel.getRespBody().getForm());
                    }
                });
    }
    public void backTask(String taskId,String backText){
        Api.taskService().backTask(taskId,backText)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkTaskBack(baseModel);
                    }
                });

    }
    /**
     * 认领任务
     * @param taskId
     */
    public void claimTask(String taskId){
        Api.taskService().claimTask(taskId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkClaimTaskStatus(baseModel);
                    }
                });
    }
    public void repealTask(String projectId,String releaseId){
        Api.taskService().repealTask(projectId,releaseId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkRepealTaskStatus(baseModel);
                    }
                });
    }
    public void assingTaskByUser(String projectId, String list,String releaseId){
        Api.taskService().assignTask(projectId,list,releaseId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkAssignTaskResult(baseModel);
                    }
                });
    }
}
