package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.fragment.DeviceCycleTaskFargment;
import com.yunyisheng.app.yunys.tasks.model.TaskDetailModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/4.
 */

public class DeviceCycleTaskPresent extends XPresent<DeviceCycleTaskFargment> {
    public void getCycleTaskInfo(String projectId,String taskId){
        Api.taskService().getTaskDetail(projectId,taskId)
                .compose(XApi.<TaskDetailModel>getApiTransformer())
                .compose(XApi.<TaskDetailModel>getScheduler())
                .compose(getV().<TaskDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(TaskDetailModel taskDetailModel) {
                        if (taskDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(taskDetailModel.getRespMsg());
                            return;
                        }
                        getV().setDetailData(taskDetailModel);
                    }
                });
    }
}
