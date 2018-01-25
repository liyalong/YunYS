package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.PeriodicTaskListActivity;
import com.yunyisheng.app.yunys.project.model.PeriodicTaskListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/25.
 */

public class PeriodicTaskPresent extends XPresent<PeriodicTaskListActivity> {
    public void getPeriodicTaskList(String projectId,String deviceId,int pageNum,int pageSize){
        Api.projectService().getPeriodicTaskList(projectId,deviceId,pageNum,pageSize)
                .compose(XApi.<PeriodicTaskListModel>getApiTransformer())
                .compose(XApi.<PeriodicTaskListModel>getScheduler())
                .compose(getV().<PeriodicTaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<PeriodicTaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(PeriodicTaskListModel periodicTaskListModel) {
                        if (periodicTaskListModel.getRespCode() == 1){
                            ToastUtils.showToast(periodicTaskListModel.getErrorMsg());
                            return;
                        }
                        getV().setAdapter(periodicTaskListModel);
                    }
                });

    }
}
