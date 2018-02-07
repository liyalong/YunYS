package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.fragment.DeviceCycleTaskFargment;
import com.yunyisheng.app.yunys.tasks.model.CycleTaskDetailModel;
import com.yunyisheng.app.yunys.tasks.model.TaskDetailModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
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
        LoadingDialog.show(getV().getContext());
        Api.taskService().getCycleTaskDetail(projectId,taskId)
                .compose(XApi.<CycleTaskDetailModel>getApiTransformer())
                .compose(XApi.<CycleTaskDetailModel>getScheduler())
                .compose(getV().<CycleTaskDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<CycleTaskDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(CycleTaskDetailModel cycleTaskDetailModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (cycleTaskDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(cycleTaskDetailModel.getRespMsg());
                            return;
                        }
                        getV().setDetailData(cycleTaskDetailModel);
                    }
                });
    }
}
