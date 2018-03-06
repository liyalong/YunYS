package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.fragement.NoticeFragement;
import com.yunyisheng.app.yunys.main.model.ReceiveMeMessageBean;
import com.yunyisheng.app.yunys.main.model.SendNoticeBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/21 16:52
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NoticePresent extends XPresent<NoticeFragement> {

    /**
     * @author fuduo
     * @time 2018/1/23  20:05
     * @describe 获取发布的公告列表
     */
    public void getSendNoticelist(int pagenum,int pageindex,String title){
        LoadingDialog.show(getV().getContext());
        Api.homeService().getSendNoticelist(pagenum,pageindex,title)
                .compose(XApi.<SendNoticeBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<SendNoticeBean>getScheduler()) //线程调度
                .compose(getV().<SendNoticeBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<SendNoticeBean>() {
                    @Override
                    public void onNext(SendNoticeBean sendNoticeBean) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (sendNoticeBean.getRespCode()==0){
                            getV().getPublishList(sendNoticeBean);
                        }else {
                            ToastUtils.showToast(sendNoticeBean.getRespMsg());
                            getV().stopRefresh();
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        getV().stopRefresh();
                        if (error.getType()==5){
                            getV().setimgBac();
                        }
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/23  20:05
     * @describe 获取发布给我的公告列表
     */
    public void getReceiveNoticelist(int pagenum,int pageindex,String title){
        Api.homeService().getReciveNoticelist(pagenum,pageindex,title)
                .compose(XApi.<ReceiveMeMessageBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<ReceiveMeMessageBean>getScheduler()) //线程调度
                .compose(getV().<ReceiveMeMessageBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<ReceiveMeMessageBean>() {
                    @Override
                    public void onNext(ReceiveMeMessageBean receiveMeMessageBean) {
                        if (receiveMeMessageBean.getRespCode()==0){
                            getV().getRecelveList(receiveMeMessageBean);
                        }else {
                            ToastUtils.showToast(receiveMeMessageBean.getRespMsg());
                            getV().stopRefresh();
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().stopRefresh();
                        if (error.getType()==5){
                            getV().setimgBac();
                        }
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

}
