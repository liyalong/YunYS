package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.activity.RoleActivity;
import com.yunyisheng.app.yunys.main.model.RoleBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/28 14:15
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class RolePresent extends XPresent<RoleActivity>{

    /**
     *  @author fuduo
     *  @time 2018/1/28  14:17
     *  @describe 获取角色列表
     */
    public void getRoleList(){
        LoadingDialog.show(getV());
        Api.homeService().getRolelist()
                .compose(XApi.<RoleBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<RoleBean>getScheduler()) //线程调度
                .compose(getV().<RoleBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<RoleBean>() {
                    @Override
                    public void onNext(RoleBean roleBean) {
                        LoadingDialog.dismiss(getV());
                        if (roleBean.getRespCode()==0){
                            getV().getBumenList(roleBean);
                        }else {
                            ToastUtils.showToast(roleBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }
    
}
