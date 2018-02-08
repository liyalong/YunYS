package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.fragement.BasicDataFragement;
import com.yunyisheng.app.yunys.main.model.GetOtherinfoBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/26 12:08
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class BasicDataPresent extends XPresent<BasicDataFragement> {

    /**
     * @author fuduo
     * @time 2018/1/26  12:09
     * @describe 获取其他人信息
     */
    public void getOtherInfo(int userid){
        LoadingDialog.show(getV().getContext());
        Api.homeService().getOtherinfo(userid)
                .compose(XApi.<GetOtherinfoBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<GetOtherinfoBean>getScheduler()) //线程调度
                .compose(getV().<GetOtherinfoBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<GetOtherinfoBean>() {
                    @Override
                    public void onNext(GetOtherinfoBean getOtherinfoBean) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (getOtherinfoBean.getRespCode()==0){
                            getV().getResultInfo(getOtherinfoBean);
                        }else {
                            ToastUtils.showToast(getOtherinfoBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

}
