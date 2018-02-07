package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.PLCDetailActivity;
import com.yunyisheng.app.yunys.project.model.PLCListModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/5.
 */

public class PLCDetailPresent extends XPresent<PLCDetailActivity> {
    public void getPlcDetail(String propertyId){
        Api.projectService().getPLCList(propertyId,10)
                .compose(XApi.<PLCListModel>getApiTransformer())
                .compose(XApi.<PLCListModel>getScheduler())
                .compose(getV().<PLCListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<PLCListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(PLCListModel plcListModel) {
                        LoadingDialog.dismiss(getV());
                        if (plcListModel.getRespCode() == 1){
                            ToastUtils.showToast(plcListModel.getRespMsg());
                            return;
                        }
                        getV().setPLCData(plcListModel);
                    }
                });
    }
}
