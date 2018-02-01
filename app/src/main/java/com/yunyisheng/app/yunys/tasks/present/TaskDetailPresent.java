package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.TaskDetailActivity;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.tasks.model.TaskDetailModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/31.
 */

public class TaskDetailPresent extends XPresent<TaskDetailActivity> {
    public void getTask(String taskId,String taskType,String userId){
        if (userId == null){
            getMyTaskDetail(taskId,taskType);
        }else {
            getTaskDetailByUserId(taskId,taskType,userId);
        }
    }

    /**
     * 获取当前登录人的任务详情
     * @param taskId
     * @param taskType
     */
    public void getMyTaskDetail(String taskId,String taskType){
        Api.taskService().getMyTaskDetail(taskId,taskType)
                .compose(XApi.<TaskDetailModel>getApiTransformer())
                .compose(XApi.<TaskDetailModel>getScheduler())
                .compose(getV().<TaskDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(TaskDetailModel taskDetailModel) {
                        if (taskDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(taskDetailModel.getRespMsg());
                            return;
                        }
                        getV().setDetail(taskDetailModel);
                    }
                });
    }

    public void getTaskDetailByUserId(String taskId,String taskType,String userId){
        Api.taskService().getTaskDetailByUser(userId,taskId,taskType)
                .compose(XApi.<TaskDetailModel>getApiTransformer())
                .compose(XApi.<TaskDetailModel>getScheduler())
                .compose(getV().<TaskDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(TaskDetailModel taskDetailModel) {
                        if (taskDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(taskDetailModel.getRespMsg());
                            return;
                        }
                        getV().setDetail(taskDetailModel);
                    }
                });
    }

}
