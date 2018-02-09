package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.RenwuFankuiFormActivity;
import com.yunyisheng.app.yunys.project.model.TaskMessageEvent;
import com.yunyisheng.app.yunys.schedule.model.RenWuFanKuiDetailBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/31 18:21
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class RenwuFankuiDetailPresent extends XPresent<RenwuFankuiFormActivity> {

    /**
     * 14.2	查看任务反馈项
     */
    public void getScheduleDetail(String projectid,int taskid) {
        LoadingDialog.show(getV());
        Api.scheduleService().getTaskInfo(projectid,taskid)
                .compose(XApi.<RenWuFanKuiDetailBean>getApiTransformer())
                .compose(XApi.<RenWuFanKuiDetailBean>getScheduler())
                .compose(getV().<RenWuFanKuiDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<RenWuFanKuiDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        XLog.d("NET ERROR :" + error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(RenWuFanKuiDetailBean renWuFanKuiDetailBean) {
                        LoadingDialog.dismiss(getV());
                        try {
                            if (renWuFanKuiDetailBean.getRespCode() == 1) {
                                ToastUtils.showToast(renWuFanKuiDetailBean.getRespMsg());
                                return;
                            }
                            if (renWuFanKuiDetailBean.getRespBody().getFeedbackItem()!=null&&
                                    renWuFanKuiDetailBean.getRespBody().getFeedbackItem().size()>0){
                                getV().setRenwuFormDetail(renWuFanKuiDetailBean);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 提交任务反馈项
     */
    public void getScheduleDetail(int taskId, String instanceFormStr) {
        Api.scheduleService().putRenwuDetail(taskId, instanceFormStr)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :" + error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        try {
                            if (baseModel.getRespCode() == 1) {
                                ToastUtils.showToast(baseModel.getRespMsg());
                                return;
                            }
                            EventBus.getDefault().post(new TaskMessageEvent("updateOK"));
                            ToastUtils.showToast("提交成功");
                            getV().finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }



}
