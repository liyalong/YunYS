package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.main.fragement.HomeFragement;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/17 10:22
 * 邮箱：duoendeavor@163.com
 * 用途：首页Present
 */

public class HomePresent extends XPresent<HomeFragement> {

    /**
     * @author fuduo
     * @time 2018/1/21  16:51
     * @describe 获取个人信息
     */
    public void getUserInfo() {
        Api.homeService().getuserinfo()
                .compose(XApi.<UserModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<UserModel>getScheduler()) //线程调度
                .compose(getV().<UserModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<UserModel>() {
                    @Override
                    public void onNext(UserModel userModel) {
                        if (userModel.getRespCode() == 0) {
                            getV().getUserInfo(userModel);
                        } else {
                            ToastUtils.showToast(userModel.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

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
                        ToastUtils.showToast("获取数据失败！");
                    }
                });
    }

}
