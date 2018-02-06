package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.DeviceDetailActivity;
import com.yunyisheng.app.yunys.project.model.DeviceInfoModel;
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
                .compose(XApi.<DeviceInfoModel>getApiTransformer())
                .compose(XApi.<DeviceInfoModel>getScheduler())
                .compose(getV().<DeviceInfoModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceInfoModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(DeviceInfoModel deviceInfoModel) {
                        if (deviceInfoModel.getRespCode() == 1){
                            ToastUtils.showToast(deviceInfoModel.getErrorMsg());
                            return;
                        }
                        XLog.d(deviceInfoModel.toString());
                        getV().displayDeviceInfoList(deviceInfoModel);
                    }
                });

    }

    /**
     * 获取设备的报警信息列表
     * @param deviceId
     */
    public void getDeviceWarningList(String projectId,int pageNum,int pageSize,String deviceId){
        Api.projectService().getWarningLists(projectId,pageNum,pageSize,projectId,deviceId,1,0)
                .compose(XApi.<DeviceWarningListModel>getApiTransformer())
                .compose(XApi.<DeviceWarningListModel>getScheduler())
                .compose(getV().<DeviceWarningListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceWarningListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(DeviceWarningListModel deviceWarningListModel) {
                        if (deviceWarningListModel.getRespCode() == 1){
                            ToastUtils.showToast(deviceWarningListModel.getRespMsg());
                            return;
                        }
                        getV().setDeviceWarningList(deviceWarningListModel);
                    }
                });
    }


    /**
     * 获取设备的实时指标
     * @param projectId
     * @param deviceId
     */
    public void getDevicePlcValueList(String projectId,String deviceId){
        Api.projectService().getDevicePLCValueList(projectId,deviceId)
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

    public void warningReset(int warningId){
        Api.projectService().warningReset(warningId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkWarningReset(baseModel);
                    }
                });
    }

}
