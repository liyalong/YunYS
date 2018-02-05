package com.yunyisheng.app.yunys.login.present;

import com.yunyisheng.app.yunys.login.activity.WelcomeActivity;
import com.yunyisheng.app.yunys.login.model.WelcomePageBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/2/5 12:43
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class WelcomePagePresent extends XPresent<WelcomeActivity> {

    /**
     *  @author fuduo
     *  @time 2018/2/5  12:43
     *  @describe 获取启动页
     */
    public void getWelcomePage(){
        Api.userService().getWelcomePage()
                .compose(XApi.<WelcomePageBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<WelcomePageBean>getScheduler()) //线程调度
                .compose(getV().<WelcomePageBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<WelcomePageBean>() {
                    @Override
                    public void onNext(WelcomePageBean welcomePageBean) {
                        if (welcomePageBean.getRespCode()==0){
                            getV().setImageBac(welcomePageBean);
                        }else {
                            ToastUtils.showToast(welcomePageBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("error",error);
                        getV().netError();
                    }

                });
    }

}
