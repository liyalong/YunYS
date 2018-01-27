package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.ModelDetailActivity;
import com.yunyisheng.app.yunys.project.bean.ModelInfoBean;
import com.yunyisheng.app.yunys.project.model.DeviceInfoModel;
import com.yunyisheng.app.yunys.project.model.ModelDetailModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/25.
 */

public class ModelDetailPresent extends XPresent<ModelDetailActivity> {
    /**
     * 获取工艺模块详细信息
     * @param projectId
     * @param modelId
     */
    public void getModelDetail(String projectId,String modelId){
        Api.projectService().getModelDetail(projectId,modelId)
                .compose(XApi.<ModelDetailModel>getApiTransformer())
                .compose(XApi.<ModelDetailModel>getScheduler())
                .compose(getV().<ModelDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ModelDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ModelDetailModel modelDetailModel) {
                        if (modelDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(modelDetailModel.getRespMsg());
                            return;
                        }
                        getV().setModelInfo(modelDetailModel);
                    }
                });
    }
}
