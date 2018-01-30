package com.yunyisheng.app.yunys.main.present;


import com.yunyisheng.app.yunys.main.activity.MessageActivity;
import com.yunyisheng.app.yunys.main.model.MessageBean;
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
     * @describe 获取消息集合
     */
    public void getMessageList(int pagenum, int userid) {
        LoadingDialog.show(getV());
        Api.homeService().getMessagelist(pagenum, 10, userid)
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
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

}
