package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.fragment.DeviceTemporaryTaskFargment;
import com.yunyisheng.app.yunys.tasks.model.ReleaseTaskDetailModel;
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
    public void getReleaseTaskDetail(String projectId,String taskId){
        Api.taskService().getReleaseTaskDetail(projectId,taskId)
                .compose(XApi.<ReleaseTaskDetailModel>getApiTransformer())
                .compose(XApi.<ReleaseTaskDetailModel>getScheduler())
                .compose(getV().<ReleaseTaskDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ReleaseTaskDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ReleaseTaskDetailModel releaseTaskDetail) {
                        if (releaseTaskDetail.getRespCode() == 1){
                            ToastUtils.showToast(releaseTaskDetail.getRespMsg());
                            return;
                        }
                        getV().setDetail(releaseTaskDetail );
                    }
                });
    }
}
