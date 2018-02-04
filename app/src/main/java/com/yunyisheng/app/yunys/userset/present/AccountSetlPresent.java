package com.yunyisheng.app.yunys.userset.present;

import android.util.Log;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.userset.activity.AccountSetActivity;
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

public class AccountSetlPresent extends XPresent<AccountSetActivity> {

    /**
     * @author fuduo
     * @time 2018/2/4  12:21
     * @describe 发送验证码
     */
    public void sendCode(String phone) {
        LoadingDialog.show(getV());
        Api.userSetService().sendCode(phone)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        Log.i("ERROR", error.toString());
                        ToastUtils.showToast("发送失败！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast(baseModel.getRespMsg());

                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/2/4  12:21
     * @describe 校验验证码
     */
    public void checkCode(String code) {
        LoadingDialog.show(getV());
        Api.userSetService().checkCode(code)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV());
                        if (baseModel.getRespCode()==0){
                            getV().checkCode();
                        }else {
                            ToastUtils.showToast(baseModel.getRespMsg());
                        }
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/2/4  12:21
     * @describe 修改手机号
     */
    public void changePhone(String code,String phone) {
        LoadingDialog.show(getV());
        Api.userSetService().chengPhone(code,phone)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("修改失败！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV());
                        if (baseModel.getRespCode()==0){
                            getV().changePhone();
                        }else {
                            ToastUtils.showToast(baseModel.getRespMsg());
                        }
                    }
                });
    }

}
