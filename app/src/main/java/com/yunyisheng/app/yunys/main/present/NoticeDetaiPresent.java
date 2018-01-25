package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.activity.NoticeDeatilActivity;
import com.yunyisheng.app.yunys.main.model.NoticeDetailBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/25 19:59
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NoticeDetaiPresent extends XPresent<NoticeDeatilActivity> {

    /**
     * @author fuduo
     * @time 2018/1/25  20:02
     * @describe 获取我发布的公告详情
     */
    public void getMineSendNotice(int announcementId) {
        Api.homeService().getSendNoticeDetail(announcementId)
                .compose(XApi.<NoticeDetailBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<NoticeDetailBean>getScheduler()) //线程调度
                .compose(getV().<NoticeDetailBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<NoticeDetailBean>() {
                    @Override
                    public void onNext(NoticeDetailBean noticeDetailBean) {
                        getV().getResultDetail(noticeDetailBean);
                        ToastUtils.showToast(noticeDetailBean.getRespMsg());
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/25  20:02
     * @describe 获取发给我的公告详情
     */
    public void getSendMineNotice(int announcementId) {
        Api.homeService().getReciveNoticeDetail(announcementId)
                .compose(XApi.<NoticeDetailBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<NoticeDetailBean>getScheduler()) //线程调度
                .compose(getV().<NoticeDetailBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<NoticeDetailBean>() {
                    @Override
                    public void onNext(NoticeDetailBean noticeDetailBean) {
                        getV().getResultDetail(noticeDetailBean);
                        ToastUtils.showToast(noticeDetailBean.getRespMsg());
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/25  20:02
     * @describe 删除公告
     */
    public void deleteNotice(int announcementId) {
        Api.homeService().deleteNotice(announcementId)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    public void onNext(BaseModel baseModel) {
                        ToastUtils.showToast(baseModel.getRespMsg());
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

}
