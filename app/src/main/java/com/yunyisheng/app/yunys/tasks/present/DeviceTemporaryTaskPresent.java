package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.fragment.DeviceTemporaryTaskFargment;
import com.yunyisheng.app.yunys.tasks.model.TaskDetailModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/4.
 */

public class DeviceTemporaryTaskPresent extends XPresent<DeviceTemporaryTaskFargment> {
    public void getTexporaryTaskInfo(String projectId,String taskId){
        Api.taskService().getTaskDetail(projectId,taskId)
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
                        getV().setDetail(taskDetailModel.getRespBody().getTask(),
                                taskDetailModel.getRespBody().getTaskback(),
                                taskDetailModel.getRespBody().getForm());
                    }
                });
    }
}
