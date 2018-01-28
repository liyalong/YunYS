package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.activity.ReportformActivity;
import com.yunyisheng.app.yunys.main.model.ReportFormBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/26 18:43
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ReportFormPresent extends XPresent<ReportformActivity> {

    /**
     *  @author fuduo
     *  @time 2018/1/26  18:44
     *  @describe 获取报表列表
     */
    public void getBaobiaoList(int Pagenum,int Pagerows){
        Api.homeService().getBaobiaolist(Pagenum,Pagerows)
                .compose(XApi.<ReportFormBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<ReportFormBean>getScheduler()) //线程调度
                .compose(getV().<ReportFormBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<ReportFormBean>() {
                    @Override
                    public void onNext(ReportFormBean reportFormBean) {
                        if (reportFormBean.getRespCode()==0){
                            getV().getResultList(reportFormBean);
                        }else {
                            ToastUtils.showToast(reportFormBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

}