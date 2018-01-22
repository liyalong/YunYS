package com.yunyisheng.app.yunys.userset.present;

import android.util.Log;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.userset.activity.ChangeInformationActivity;
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

public class ChangeInformationPresent extends XPresent<ChangeInformationActivity> {

    public void updateEmail(String userMailbox) {
        LoadingDialog.show(getV());
        Api.userSetService().upDateemail(userMailbox)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        Log.i("ERROR", error.toString());
                        ToastUtils.showToast("修改失败！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast(baseModel.getRespMsg());
                        getV().getResult(baseModel);
                    }
                });
    }

}
