package com.yunyisheng.app.yunys.schedule.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.schedule.fragement.ProjeceScheduleFragement;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.schedule.model.ScheduleNoSizeBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/29 19:20
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProjectSchedulePresent extends XPresent<ProjeceScheduleFragement> {

    /**
     * @author fuduo
     * @time 2018/1/29  19:21
     * @describe 获取项目未完成任务的日期
     */
    public void getProjectscheduleDatelist(String startTime, String endTim,String projectid) {
        LoadingDialog.show(getV().getContext());
        Api.scheduleService().getProjectscheduleDatelist(startTime, endTim,projectid)
                .compose(XApi.<ScheduleNoSizeBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<ScheduleNoSizeBean>getScheduler()) //线程调度
                .compose(getV().<ScheduleNoSizeBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<ScheduleNoSizeBean>() {
                    @Override
                    public void onNext(ScheduleNoSizeBean scheduleNoSizeBean) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (scheduleNoSizeBean.getRespCode() == 0) {
                            getV().getProjectNoScheduleResultList(scheduleNoSizeBean);
                        } else {
                            if (getV().getFragmentPosition() == 1) {
                                ToastUtils.showToast(scheduleNoSizeBean.getRespMsg());
                            }
                            getV().setClearNodate();
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("获取数据失败");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/29  19:21
     * @describe 14.1    获取指定日期的当前登录员工的项目日程列表
     */
    public void getMyProjectSchedulrList(int pageNum,String projectid, String startTime, String endTim) {
        Api.scheduleService().getProjectschedulelist(pageNum, 100,projectid, startTime, endTim)
                .compose(XApi.<MyScheduleBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MyScheduleBean>getScheduler()) //线程调度
                .compose(getV().<MyScheduleBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MyScheduleBean>() {
                    @Override
                    public void onNext(MyScheduleBean myScheduleBean) {
                        if (myScheduleBean.getRespCode() == 0) {
                            getV().getProjectResultList(myScheduleBean);
                        } else {
                            LogUtils.i("fragmentPosition", String.valueOf(getV().getFragmentPosition()));
                            if (getV().getFragmentPosition() == 1) {
                                ToastUtils.showToast(myScheduleBean.getRespMsg());
                            }
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        if (error.getType()==5){
                            getV().setProjimgBac();
                        }
                        ToastUtils.showToast("获取数据失败");
                    }
                });
    }

}
