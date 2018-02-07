package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.ModelDetailActivity;
import com.yunyisheng.app.yunys.project.bean.ModelInfoBean;
import com.yunyisheng.app.yunys.project.model.DeviceInfoModel;
import com.yunyisheng.app.yunys.project.model.DevicePLCValueListModel;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.project.model.ModelDetailModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
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
        LoadingDialog.show(getV());
        Api.projectService().getModelDetail(projectId,modelId)
                .compose(XApi.<ModelDetailModel>getApiTransformer())
                .compose(XApi.<ModelDetailModel>getScheduler())
                .compose(getV().<ModelDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ModelDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ModelDetailModel modelDetailModel) {
                        LoadingDialog.dismiss(getV());
                        if (modelDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(modelDetailModel.getRespMsg());
                            return;
                        }
                        getV().setModelInfo(modelDetailModel);
                    }
                });
    }
    /**
     * 获取设备的报警信息列表
     * @param modelId
     */
    public void getModelWarningList(String projectId,int pageNum,int pageSize,String modelId){
        Api.projectService().getWarningLists(projectId,pageNum,pageSize,projectId,modelId,2,0)
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
                        getV().setModelWarningList(deviceWarningListModel);
                    }
                });
    }


    /**
     * 获取设备的实时指标
     * @param projectId
     * @param modelId
     */
    public void getModelPlcValueList(String projectId,String modelId){
        Api.projectService().getModelPLCValueList(projectId,modelId)
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
                        getV().setModelPLCValueList(devicePLCValueListModel);
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
