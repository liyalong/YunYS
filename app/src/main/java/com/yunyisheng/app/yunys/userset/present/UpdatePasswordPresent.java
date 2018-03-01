package com.yunyisheng.app.yunys.userset.present;

import android.util.Log;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.userset.activity.MimaManagerActivity;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/21 10:30
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class UpdatePasswordPresent extends XPresent<MimaManagerActivity> {

    /**
     * @author fuduo
     * @time 2018/2/2  16:07
     * @describe 修改密码
     */
    public void updatePassword(String oldpassword, String newpassword) {
        LoadingDialog.show(getV());
        Api.userSetService().upDatepassword(oldpassword, newpassword)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        Log.i("ERROR", error.toString());
                        ToastUtils.showToast("请求数据失败！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV());
                        getV().checkIssuccess(baseModel);
                    }
                });
    }

}
