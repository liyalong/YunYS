package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.bean.UpdateCycleTaskBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateTemporaryTaskBean;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/4.
 */

public class NoneDeviceTaskPresent extends XPresent<CreateNoneDeviceTaskAcitvity> {
    //添加，更新临时任务
    public void updateDeviceTemporaryTask(UpdateTemporaryTaskBean task){
        Api.taskService().createReleaseTask(task.getProjectId(), String.valueOf(task.getReleaseTaskType()),
                task.getReleaseName(),task.getReleaseRemark(),task.getReleaseBegint(),task.getReleaseEndt(),
                task.getListStr(),task.getReleaseBaseformId(),task.getEquipmentId(),task.getFeedbackJSON())
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkUpdateDeviceTemporaryTaskStatus(baseModel);
                    }
                });
    }
    //添加更新周期任务
    public void updateCycleTask(UpdateCycleTaskBean task){
        Api.taskService().createCycleTask(task.getProjectId(),task.getCycletaskId(),task.getCycletaskName(),
                task.getCycletaskRemark(),task.getCycletaskStat(),task.getCycletaskBegint(),task.getCycletaskEndt(),
                task.getCorn(),task.getCycletaskType(),task.getEquipmentId(),task.getTimeLength(),task.getTemplateId(),
                task.getUserIds(),task.getUserIdStr(),task.getFeedbackJSON())
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
                        getV().checkCycleTaskStatus(baseModel);
                    }
                });
    }
}
