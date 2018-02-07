package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.fragement.AlarmListFragment;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/5.
 */

public class AlarmListPresent extends XPresent<AlarmListFragment> {

    public void getProjectAlarmLists(String projectId,int pageNum,int pageSize){
        LoadingDialog.show(getV().getContext());
        Api.projectService().getWarningLists(projectId,pageNum,pageSize,null,null,null,null)
                .compose(XApi.<DeviceWarningListModel>getApiTransformer())
                .compose(XApi.<DeviceWarningListModel>getScheduler())
                .compose(getV().<DeviceWarningListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceWarningListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        getV().initRefresh();
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(DeviceWarningListModel deviceWarningListModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (deviceWarningListModel.getRespCode() == 1){
                            getV().initRefresh();
                            ToastUtils.showToast(deviceWarningListModel.getRespMsg());
                            return;
                        }
                        getV().setAdapterData(deviceWarningListModel);
                    }
                });
    }
}
