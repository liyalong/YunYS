package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.ModelAlarmRulesActivity;
import com.yunyisheng.app.yunys.project.model.DeviceAlarmRulesModel;
import com.yunyisheng.app.yunys.project.model.ModelAlarmRulesListModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/26.
 */

public class ModelAlarmRulesPresent extends XPresent<ModelAlarmRulesActivity> {
    public void getModelAlarmRulesList(String projectId,String modelId,int pageNum,int pageSize){
        LoadingDialog.show(getV());
        Api.projectService().getModelAlarmRulesList(projectId,modelId,pageNum,pageSize)
                .compose(XApi.<ModelAlarmRulesListModel>getApiTransformer())
                .compose(XApi.<ModelAlarmRulesListModel>getScheduler())
                .compose(getV().<ModelAlarmRulesListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ModelAlarmRulesListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ModelAlarmRulesListModel modelAlarmRulesListModel) {
                        LoadingDialog.dismiss(getV());
                        if (modelAlarmRulesListModel.getRespCode() == 1){
                            ToastUtils.showToast(modelAlarmRulesListModel.getRespMsg());
                            getV().initRefresh();
                            return;
                        }
                        getV().setAdapter(modelAlarmRulesListModel);

                    }
                });
    }
}
