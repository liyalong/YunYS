package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.activity.WorkerDataActivity;
import com.yunyisheng.app.yunys.main.model.QuanxianBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.bean.UploadDynamicFormImageBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/2/7 18:32
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class WorkerDataPresent extends XPresent<WorkerDataActivity> {

    /**
     * @author fuduo
     * @time 2018/1/26  12:09
     * @describe 获取用户权限
     */
    public void getQuanxian(int userid){
        LoadingDialog.show(getV());
        Api.homeService().getUserQuanxian(userid)
                .compose(XApi.<QuanxianBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<QuanxianBean>getScheduler()) //线程调度
                .compose(getV().<QuanxianBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<QuanxianBean>() {
                    @Override
                    public void onNext(QuanxianBean quanxianBean) {
                        LoadingDialog.dismiss(getV());
                        if (quanxianBean.getRespCode()==0){
                            getV().getQuanResultInfo(quanxianBean);
                        }else {
                            ToastUtils.showToast(quanxianBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * 获取图片
     */
    public void getFormImage(String fileurl) {
        Api.scheduleService().getFormImage(fileurl)
                .compose(XApi.<UploadDynamicFormImageBean>getApiTransformer())
                .compose(XApi.<UploadDynamicFormImageBean>getScheduler())
                .compose(getV().<UploadDynamicFormImageBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<UploadDynamicFormImageBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :" + error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(UploadDynamicFormImageBean uploadDynamicFormImageBean) {
                        try {
                            if (uploadDynamicFormImageBean.getRespCode() == 1) {
                                ToastUtils.showToast(uploadDynamicFormImageBean.getRespMsg());
                                return;
                            }
                            String respBody = uploadDynamicFormImageBean.getRespBody();
                            getV().setWorkerHead(respBody);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

}
