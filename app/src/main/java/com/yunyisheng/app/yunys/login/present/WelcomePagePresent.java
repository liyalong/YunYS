package com.yunyisheng.app.yunys.login.present;

import com.yunyisheng.app.yunys.login.activity.WelcomeActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/2/5 12:43
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class WelcomePagePresent extends XPresent<WelcomeActivity> {

//    /**
//     *  @author fuduo
//     *  @time 2018/2/5  12:43
//     *  @describe 获取启动页
//     */
//    public void getWelcomePage(){
//        Api.userService().getWelcomePage()
//                .compose(XApi.<WelcomePageBean>getApiTransformer()) //统一异常处理
//                .compose(XApi.<WelcomePageBean>getScheduler()) //线程调度
//                .compose(getV().<WelcomePageBean>bindToLifecycle()) //内存泄漏处理
//                .subscribe(new ApiSubscriber<WelcomePageBean>() {
//                    @Override
//                    public void onNext(WelcomePageBean welcomePageBean) {
//                        if (welcomePageBean.getRespCode()==0){
//                            getV().setImageBac(welcomePageBean);
//                        }else {
//                            ToastUtils.showToast(welcomePageBean.getRespMsg());
//                        }
//                    }
//
//                    @Override
//                    protected void onFail(NetError error) {
//                        XLog.d("error",error);
//                        getV().netError();
//                    }
//
//                });
//    }

}
