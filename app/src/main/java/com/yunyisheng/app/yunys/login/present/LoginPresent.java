package com.yunyisheng.app.yunys.login.present;

import android.util.Log;

import com.yunyisheng.app.yunys.base.BaseStatusModel;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.login.model.LoginModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2017/12/16.
 */

public class LoginPresent extends XPresent<LoginActivity> {
    /**
     * 登录接口
     * @param userPhone
     * @param password
     * @param uuid
     * @param yzm
     */
    public void  Login(String userPhone ,String password,String uuid,String yzm){
        XLog.d(userPhone+"----"+password);
        Api.userService().login(userPhone,password,uuid,yzm)
                .compose(XApi.<LoginModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<LoginModel>getScheduler()) //线程调度
                .compose(getV().<LoginModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<LoginModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        Log.i("ERROR", error.toString());
                        ToastUtils.showToast("请求数据失败！");
                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        Log.i("LOGIN",loginModel.toString());
                        getV().checkLogin(loginModel);
                    }
                });



    }

    /**
     * 发送短信验证码
     * @param phone
     */
    public void getShortMessage(String phone){
        Api.shortMessageService().getShortMessage(phone)
                .compose(XApi.<BaseStatusModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseStatusModel>getScheduler()) //线程调度
                .compose(getV().<BaseStatusModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseStatusModel>() {
                    @Override
                    public void onNext(BaseStatusModel baseStatusModel) {
                        getV().checkMsgResault(baseStatusModel);
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }

                });
    }
}
