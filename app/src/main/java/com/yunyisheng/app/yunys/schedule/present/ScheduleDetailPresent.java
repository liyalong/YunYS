package com.yunyisheng.app.yunys.schedule.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.DynamicFormActivity;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.utils.ToastUtils;

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

public class ScheduleDetailPresent extends XPresent<DynamicFormActivity> {

    /**
     * 14.2	查看别人任务表单
     */
    public void getOtherScheduleDetail(int userid,String taskid,int type){
        Api.scheduleService().getOtherScheduleDetail(userid,taskid,type)
                .compose(XApi.<ScheduleDetailBean>getApiTransformer())
                .compose(XApi.<ScheduleDetailBean>getScheduler())
                .compose(getV().<ScheduleDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<ScheduleDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :"+error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(ScheduleDetailBean scheduleDetailBean) {
                        try {
                            if (scheduleDetailBean.getRespCode() == 1){
                                ToastUtils.showToast(scheduleDetailBean.getRespMsg());
                                return;
                            }
                            getV().setFormDetail(scheduleDetailBean);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 14.2	查看自己任务表单
     */
    public void getMineScheduleDetail(String taskid,int type){
        Api.scheduleService().getMineScheduleDetail(taskid,type)
                .compose(XApi.<ScheduleDetailBean>getApiTransformer())
                .compose(XApi.<ScheduleDetailBean>getScheduler())
                .compose(getV().<ScheduleDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<ScheduleDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :"+error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(ScheduleDetailBean scheduleDetailBean) {
                        try {
                            if (scheduleDetailBean.getRespCode() == 1){
                                ToastUtils.showToast(scheduleDetailBean.getRespMsg());
                                return;
                            }
                            getV().setFormDetail(scheduleDetailBean);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 提交任务表单
     */
    public void getScheduleDetail(int taskId,String instanceFormStr){
        Api.scheduleService().putScheduleDetail(taskId,instanceFormStr)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :"+error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        try {
                            if (baseModel.getRespCode() == 1){
                                ToastUtils.showToast(baseModel.getRespMsg());
                                return;
                            }
                            ToastUtils.showToast("提交成功");
                            getV().finish();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

    }
    
}
