package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.fragement.DeviceListFragment;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/22.
 */

public class DeviceListPresent extends XPresent<DeviceListFragment> {
    public void getProjectDeviceList(String projectId,int pageNum,int pageSize,String deviceName){
        LoadingDialog.show(getV().getContext());
        Api.projectService().getProjectDeviceList(projectId,pageNum,pageSize,deviceName)
                .compose(XApi.<DeviceListModel>getApiTransformer())
                .compose(XApi.<DeviceListModel>getScheduler())
                .compose(getV().<DeviceListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("网络请求失败！");
                        getV().initRefresh();
                        getV().setNoNetwork();
                        return;
                    }

                    @Override
                    public void onNext(DeviceListModel deviceListModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (deviceListModel.getRespCode() == 1){
                            ToastUtils.showToast(deviceListModel.getRespMsg());
                            getV().initRefresh();
                            return;
                        }
                        getV().setAdapterData(deviceListModel);
                    }
                });
    }
}
