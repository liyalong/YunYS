package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.DeviceAlarmRulesActivity;
import com.yunyisheng.app.yunys.project.model.DeviceAlarmRulesModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/24.
 */

public class DeviceAlarmRulesPresent extends XPresent<DeviceAlarmRulesActivity> {
    /**
     * 获取报警规则列表
     * @param projectId
     * @param deviceId
     * @param pageNum
     * @param pageSize
     */
    public void getDeviceAlarmRulesList(String projectId,String deviceId,int pageNum,int pageSize){
        LoadingDialog.show(getV());
        Api.projectService().getDeviceAlarmRulesList(projectId,deviceId,pageNum,pageSize)
                .compose(XApi.<DeviceAlarmRulesModel>getApiTransformer())
                .compose(XApi.<DeviceAlarmRulesModel>getScheduler())
                .compose(getV().<DeviceAlarmRulesModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<DeviceAlarmRulesModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        getV().initRefresh();
                        ToastUtils.showToast("网络请求错误");
                        return;
                    }

                    @Override
                    public void onNext(DeviceAlarmRulesModel deviceAlarmRulesModel) {
                        LoadingDialog.dismiss(getV());
                        if (deviceAlarmRulesModel.getRespCode() == 1){
                            getV().initRefresh();
                            ToastUtils.showToast(deviceAlarmRulesModel.getErrorMsg());
                            return;
                        }
                        getV().setDeviceAlarmRulesModel(deviceAlarmRulesModel);
                    }
                });
    }
}
