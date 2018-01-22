package com.yunyisheng.app.yunys.userset.present;

import android.util.Log;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.userset.activity.EnterpriseinformationActivity;
import com.yunyisheng.app.yunys.userset.model.CompanyBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/22 10:28
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class EnterpriseinformationPresent extends XPresent<EnterpriseinformationActivity> {

    /**
     * @author fuduo
     * @time 2018/1/22  10:31
     * @describe 获取个人企业信息
     */
    public void getCompanyinfo() {
        LoadingDialog.show(getV());
        Api.userSetService().getCompanyinfo()
                .compose(XApi.<CompanyBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<CompanyBean>getScheduler()) //线程调度
                .compose(getV().<CompanyBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<CompanyBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        Log.i("ERROR", error.toString());
                        ToastUtils.showToast("获取企业信息失败！");
                    }

                    @Override
                    public void onNext(CompanyBean companyBean) {
                        if (companyBean.getRespCode()==1){
                            ToastUtils.showToast("获取企业信息失败！");
                        }else {
                            getV().getCompanyinfo(companyBean);
                        }
                        LoadingDialog.dismiss(getV());
                    }
                });
    }
}
