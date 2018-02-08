package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.activity.NoticeActivity;
import com.yunyisheng.app.yunys.main.model.NoticeBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/2/9 01:19
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NoticeActicityPresent extends XPresent<NoticeActivity> {

    /**
     * @author fuduo
     * @time 2018/1/26  12:09
     * @describe 获取用户是否有发布公告权限
     */
    public void getNoticeQuanxian(){
        LoadingDialog.show(getV());
        Api.homeService().getisSendNotice()
                .compose(XApi.<NoticeBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<NoticeBean>getScheduler()) //线程调度
                .compose(getV().<NoticeBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<NoticeBean>() {
                    @Override
                    public void onNext(NoticeBean noticeBean) {
                        LoadingDialog.dismiss(getV());
                        if (noticeBean.getRespCode()==0){
                            getV().getQuanResultInfo(noticeBean);
                        }else {
                            ToastUtils.showToast(noticeBean.getRespMsg());
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
