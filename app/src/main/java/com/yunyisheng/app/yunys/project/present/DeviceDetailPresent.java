package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.DeviceDetailActivity;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;
import com.yunyisheng.app.yunys.project.model.DevicePLCValueListModel;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/23.
 */

public class DeviceDetailPresent extends XPresent<DeviceDetailActivity> {
    /**
     * 获取设备基本信息
     * @param projectId
     * @param deviceId
     */
    public void getDeviceInfo(String projectId,String deviceId){
        Api.projectService().getDeviceInfo(projectId,deviceId)
                .compose(XApi.<DeviceBean>getApiTransformer())
                .compose(XApi.<DeviceBean>getScheduler())
                .compose(getV().<DeviceBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(DeviceBean deviceBean) {
                        if (deviceBean.getRespCode() == 1){
                            ToastUtils.showToast(deviceBean.getErrorMsg());
                            return;
                        }
                        XLog.d(deviceBean.toString());
                        getV().displayDeviceInfoList(deviceBean);
                    }
                });

    }

    /**
     * 获取设备的报警信息列表
     * @param deviceId
     */
    public void getDeviceWarningList(String deviceId){
        Api.projectService().getDeviceWarningList(deviceId)
                .compose(XApi.<DeviceWarningListModel>getApiTransformer())
                .compose(XApi.<DeviceWarningListModel>getScheduler())
                .compose(getV().<DeviceWarningListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceWarningListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(DeviceWarningListModel deviceWarningListModel) {
                        if (deviceWarningListModel.getRespCode() == 1){
                            ToastUtils.showToast(deviceWarningListModel.getErrorMsg());
                            return;
                        }
                        getV().setDeviceWarningList(deviceWarningListModel);
                    }
                });
    }

    /**
     * 获取设备的实时指标
     * @param deviceId
     */
    public void getDevicePlcValueList(String deviceId){
        Api.projectService().getDevicePlcValueList(deviceId)
                .compose(XApi.<DevicePLCValueListModel>getApiTransformer())
                .compose(XApi.<DevicePLCValueListModel>getScheduler())
                .compose(getV().<DevicePLCValueListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DevicePLCValueListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(DevicePLCValueListModel devicePLCValueListModel) {
                        if (devicePLCValueListModel.getRespCode() == 1){
                            ToastUtils.showToast(devicePLCValueListModel.getErrorMsg());
                            return;
                        }
                        getV().setDevicePLCValueList(devicePLCValueListModel);
                    }
                });
    }
}
