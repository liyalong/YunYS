package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.DevicePartsListActivity;
import com.yunyisheng.app.yunys.project.bean.DevicePartsBean;
import com.yunyisheng.app.yunys.project.model.DevicePartsListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/25.
 */

public class DevicePartsListPresent extends XPresent<DevicePartsListActivity> {

    public void getDevicePartsList(String projectId,String deviceId,int pageNum,int pageSize){
        Api.projectService().getDevicePartsList(projectId,deviceId,pageNum,pageSize)
                .compose(XApi.<DevicePartsListModel>getApiTransformer())
                .compose(XApi.<DevicePartsListModel>getScheduler())
                .compose(getV().<DevicePartsListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DevicePartsListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误!");
                        return;
                    }

                    @Override
                    public void onNext(DevicePartsListModel devicePartsListModel) {
                        if (devicePartsListModel.getRespCode() == 1){
                            ToastUtils.showToast(devicePartsListModel.getRespMsg());
                            return;
                        }
                        getV().setAdapter(devicePartsListModel);
                    }
                });
    }

}
