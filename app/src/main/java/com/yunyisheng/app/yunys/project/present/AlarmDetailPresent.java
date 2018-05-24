package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.AlarmDetailActivity;
import com.yunyisheng.app.yunys.project.fragement.AlarmListFragment;
import com.yunyisheng.app.yunys.project.model.AlarmDetailModel;
import com.yunyisheng.app.yunys.project.model.AlarmPLCDataModel;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.project.model.PLCListModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/5.
 */

public class AlarmDetailPresent extends XPresent<AlarmDetailActivity> {

    public void getAlarmDetail(String projectId,String alarmId){
        Api.projectService().getAlarmDetail(projectId,alarmId)
                .compose(XApi.<AlarmDetailModel>getApiTransformer())
                .compose(XApi.<AlarmDetailModel>getScheduler())
                .compose(getV().<AlarmDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<AlarmDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("获取数据失败！");
                    }

                    @Override
                    public void onNext(AlarmDetailModel alarmDetailModel) {
                        LoadingDialog.dismiss(getV().mContext);
                        if (alarmDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(alarmDetailModel.getErrorMsg());
                            return;
                        }
                        getV().setDetail(alarmDetailModel.getRespBody());
                    }
                });

    }
    public void getAlarmChartData(String projectId,String reportInstances){
        LogUtils.v("reportInstances",reportInstances);
        Api.projectService().getAlarmPCLList(projectId,reportInstances)
                .compose(XApi.<AlarmPLCDataModel>getApiTransformer())
                .compose(XApi.<AlarmPLCDataModel>getScheduler())
                .compose(getV().<AlarmPLCDataModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<AlarmPLCDataModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("获取plc数据失败！");
                    }

                    @Override
                    public void onNext(AlarmPLCDataModel AlarmPLCDataModel) {
                        if (AlarmPLCDataModel.getRespCode() == 1){
                            ToastUtils.showToast(AlarmPLCDataModel.getRespMsg());
                            return;
                        }
                        getV().LoadChartData(AlarmPLCDataModel);
                    }
                });
    }
}
