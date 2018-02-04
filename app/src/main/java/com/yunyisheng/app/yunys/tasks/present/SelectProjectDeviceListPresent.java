package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectDeviceActivity;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/3.
 */

public class SelectProjectDeviceListPresent extends XPresent<SelectProjectDeviceActivity> {

    public void getProjectDeviceList(String projectId,int pageNum,int pageSize,String deviceName){
        Api.projectService().getProjectDeviceList(projectId,pageNum,pageSize,deviceName)
                .compose(XApi.<DeviceListModel>getApiTransformer())
                .compose(XApi.<DeviceListModel>getScheduler())
                .compose(getV().<DeviceListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求失败！");
                        return;
                    }

                    @Override
                    public void onNext(DeviceListModel deviceListModel) {
                        XLog.d(deviceListModel.toString());
                        if (deviceListModel.getRespCode() == 1){
                            ToastUtils.showToast(deviceListModel.getRespMsg());
                            return;
                        }
                        getV().setAdapterData(deviceListModel);
                    }
                });
    }
}
