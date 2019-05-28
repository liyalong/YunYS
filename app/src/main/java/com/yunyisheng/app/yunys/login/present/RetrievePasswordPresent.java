package com.yunyisheng.app.yunys.login.present;


import android.util.Log;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.login.activity.RetrievePassword;
import com.yunyisheng.app.yunys.base.BaseStatusModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.userset.model.MySourceModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/5.
 */

public class RetrievePasswordPresent extends XPresent<RetrievePassword> {
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
                        XLog.d("error",error);
                        ToastUtils.showToast("请求数据失败！");
                    }

                });



    }

    public void changePassword(final String phone, String code, final String password,String key) {
        Api.userService().changePassword(phone,code,password,2,key)
                .compose(XApi.<BaseStatusModel>getApiTransformer())
                .compose(XApi.<BaseStatusModel>getScheduler())
                .compose(getV().<BaseStatusModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseStatusModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }

                    @Override
                    public void onNext(BaseStatusModel baseStatusModel) {
                        getV().checkResault(baseStatusModel,phone,password);
                    }
                });
    }
    public void getSource(){
        Api.userSetService().getSource(2)
                .compose(XApi.<MySourceModel>getApiTransformer())
                .compose((XApi.<MySourceModel>getScheduler()))
                .compose(getV().<MySourceModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<MySourceModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        Log.i("ERROR", error.toString());
                    }

                    @Override
                    public void onNext(MySourceModel mySourceModel) {
                        getV().getSource(mySourceModel);
                    }
                });
    }

}
