package com.yunyisheng.app.yunys.main.present;


import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.activity.MessageActivity;
import com.yunyisheng.app.yunys.main.model.MessageBean;
import com.yunyisheng.app.yunys.main.model.MessageTypeBean;
import com.yunyisheng.app.yunys.main.model.MsgBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/30 14:59
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MessagePresent extends XPresent<MessageActivity> {

    /**
     * @author fuduo
     * @time 2018/1/30  15:27
     * @describe 获取消息类型
     */
    public void getMessageTypeList() {
        LoadingDialog.show(getV());
        Api.homeService().getMessagetypelist()
                .compose(XApi.<MessageTypeBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MessageTypeBean>getScheduler()) //线程调度
                .compose(getV().<MessageTypeBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MessageTypeBean>() {
                    @Override
                    public void onNext(MessageTypeBean messageTypeBean) {
                        LoadingDialog.dismiss(getV());
                        if (messageTypeBean.getRespCode() == 0) {
                            getV().getMessageType(messageTypeBean);
                        } else {
                            ToastUtils.showToast(messageTypeBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        if (error.getType()==5) {
                            getV().setimgBac();
                        }
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/30  15:27
     * @describe 获取消息集合
     */
    public void getMessageList(String string,int pagenum) {
        LoadingDialog.show(getV());
        Api.homeService().getTypeMessagelist(string,pagenum,10)
                .compose(XApi.<MessageBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MessageBean>getScheduler()) //线程调度
                .compose(getV().<MessageBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MessageBean>() {
                    @Override
                    public void onNext(MessageBean messageBean) {
                        LoadingDialog.dismiss(getV());
                        if (messageBean.getRespCode() == 0) {
                            getV().getMessageList(messageBean);
                        } else {
                            ToastUtils.showToast(messageBean.getRespMsg());
                            getV().stopRefresh();
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        getV().stopRefresh();
                        getV().setimgBac();
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/30  15:27
     * @describe 修改消息 – 将消息置为已读
     */
    public void updateMessage(int messageid, final int position) {
        Api.homeService().updateMessage(messageid)
                .compose(XApi.<MsgBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MsgBean>getScheduler()) //线程调度
                .compose(getV().<MsgBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MsgBean>() {
                    @Override
                    public void onNext(MsgBean respBodyBean) {
                        LoadingDialog.dismiss(getV());
                        if (respBodyBean.getRespCode() == 0) {
                           getV().setVoalGone(position,respBodyBean);
                        } else {
                            ToastUtils.showToast(respBodyBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络连接失败");
                    }
                });
    }

}
