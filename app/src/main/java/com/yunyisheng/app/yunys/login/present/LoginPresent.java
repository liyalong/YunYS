package com.yunyisheng.app.yunys.login.present;

import android.util.Log;

import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.login.model.LoginModel;
import com.yunyisheng.app.yunys.main.activity.OtherActivity;
import com.yunyisheng.app.yunys.net.Api;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2017/12/16.
 */

public class LoginPresent extends XPresent<LoginActivity> {
    public void  Login(String userPhone ,String password,String uuid){
        XLog.d(userPhone+"----"+password);
        Api.userService().login(userPhone,password,uuid)
                .compose(XApi.<LoginModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<LoginModel>getScheduler()) //线程调度
                .compose(getV().<LoginModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<LoginModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        Log.i("ERROR", error.toString());
                        getV().showToastMsg("请求数据失败！");
                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        Log.i("LOGIN",loginModel.toString());
                        getV().checkLogin(loginModel);
                    }
                });



    }
}
