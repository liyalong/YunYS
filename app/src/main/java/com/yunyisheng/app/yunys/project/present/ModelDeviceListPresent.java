package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.ModelDeviceListActivity;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;
import com.yunyisheng.app.yunys.project.model.ModelListModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/25.
 */

public class ModelDeviceListPresent extends XPresent<ModelDeviceListActivity> {
    public void getModelDeviceList(String projectId,String modelId,int pageNum,int pageSize){
        LoadingDialog.show(getV());
        Api.projectService().getModelDeviceList(projectId,modelId,pageNum,pageSize)
                .compose(XApi.<DeviceListModel>getApiTransformer())
                .compose(XApi.<DeviceListModel>getScheduler())
                .compose(getV().<DeviceListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("网络请求错误！");
                        getV().setNoNetwork();
                    }

                    @Override
                    public void onNext(DeviceListModel deviceListModel) {
                        LoadingDialog.dismiss(getV());
                        if (deviceListModel.getRespCode() == 1){
                            ToastUtils.showToast(deviceListModel.getRespMsg());
                            getV().initRefresh();
                            return;
                        }
                        getV().setAdapter(deviceListModel);
                    }
                });

    }
}
