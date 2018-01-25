package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.fragement.ModelListFragment;
import com.yunyisheng.app.yunys.project.model.ModelListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/25.
 */

public class ModelListPresent extends XPresent<ModelListFragment> {
    public void getModelList(String projectId,int pageNum,int pageSize){
        Api.projectService().getPrjectModelList(projectId,pageNum,pageSize)
                .compose(XApi.<ModelListModel>getApiTransformer())
                .compose(XApi.<ModelListModel>getScheduler())
                .compose(getV().<ModelListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ModelListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d(error.getMessage());
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(ModelListModel modelListModel) {
                        if(modelListModel.getRespCode() == 1){
                            ToastUtils.showToast(modelListModel.getRespMsg());
                            return;
                        }
                        getV().setAdapter(modelListModel);
                    }
                });
    }
}
