package com.yunyisheng.app.yunys.userset.present;

import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.userset.fragement.MineFragement;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/23 11:59
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MinePresent extends XPresent<MineFragement> {
    /**
     * @author fuduo
     * @time 2018/1/21  16:51
     * @describe 获取个人信息
     */
    public void getUserInfo(){
        Api.homeService().getuserinfo()
                .compose(XApi.<UserModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<UserModel>getScheduler()) //线程调度
                .compose(getV().<UserModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<UserModel>() {
                    @Override
                    public void onNext(UserModel userModel) {
                        getV().getUserInfo(userModel);
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }
}
