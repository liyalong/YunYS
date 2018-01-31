package com.yunyisheng.app.yunys.schedule.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.schedule.fragement.OurProjeceScheduleFragement;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
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

public class MySchedulePresent extends XPresent<OurProjeceScheduleFragement> {

    /**
     * @author fuduo
     * @time 2018/1/29  19:21
     * @describe 14.1    获取指定日期的当前登录员工的日程列表
     */
    public void getMySchedulrList(int pageNum, long startTime, long endTim) {
        LoadingDialog.show(getV().getContext());
        Api.scheduleService().getMyschedulelist(pageNum, 10, startTime, endTim)
                .compose(XApi.<MyScheduleBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MyScheduleBean>getScheduler()) //线程调度
                .compose(getV().<MyScheduleBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MyScheduleBean>() {
                    @Override
                    public void onNext(MyScheduleBean myScheduleBean) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (myScheduleBean.getRespCode() == 0) {
                            getV().getResultList(myScheduleBean);
                        } else {
                            ToastUtils.showToast(myScheduleBean.getRespMsg());
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
    public void getMyProjectSchedulrList(int pageNum,String projectid, long startTime, long endTim) {
        LoadingDialog.show(getV().getContext());
        Api.scheduleService().getProjectschedulelist(pageNum, 10,projectid, startTime, endTim)
                .compose(XApi.<MyScheduleBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MyScheduleBean>getScheduler()) //线程调度
                .compose(getV().<MyScheduleBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MyScheduleBean>() {
                    @Override
                    public void onNext(MyScheduleBean myScheduleBean) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (myScheduleBean.getRespCode() == 0) {
                            getV().getProjectResultList(myScheduleBean);
                        } else {
                            ToastUtils.showToast(myScheduleBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("获取数据失败");
                    }
                });
    }

}
