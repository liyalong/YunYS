package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.fragement.ScheduleFragement;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/31 10:33
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class WorkerSchedulePresent extends XPresent<ScheduleFragement> {

    /**
     *  @author fuduo
     *  @time 2018/1/28  14:17
     *  @describe 员工日程
     */
    public void getWorkerScheduleList(int pageNum,int userid,long startTime,long endTime){
        LoadingDialog.show(getV().getContext());
        Api.homeService().getOtherSchedulelist(pageNum,10,userid,startTime,endTime)
                .compose(XApi.<MyScheduleBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MyScheduleBean>getScheduler()) //线程调度
                .compose(getV().<MyScheduleBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MyScheduleBean>() {
                    @Override
                    public void onNext(MyScheduleBean myScheduleBean) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (myScheduleBean.getRespCode()==0){
                            getV().getWorkerSchedule(myScheduleBean);
                        }else {
                            ToastUtils.showToast(myScheduleBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        getV().stopRefresh();
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }
    
}
