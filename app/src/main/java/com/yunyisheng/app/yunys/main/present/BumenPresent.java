package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.activity.BumenActivity;
import com.yunyisheng.app.yunys.main.model.BuMenBean;
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

public class BumenPresent extends XPresent<BumenActivity>{

    /**
     *  @author fuduo
     *  @time 2018/1/28  14:17
     *  @describe 获取部门列表
     */
    public void getBumenList(){
        LoadingDialog.show(getV());
        Api.homeService().getBumenlist()
                .compose(XApi.<BuMenBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<BuMenBean>getScheduler()) //线程调度
                .compose(getV().<BuMenBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BuMenBean>() {
                    @Override
                    public void onNext(BuMenBean buMenBean) {
                        LoadingDialog.dismiss(getV());
                        if (buMenBean.getRespCode()==0){
                            getV().getBumenList(buMenBean);
                        }else {
                            ToastUtils.showToast(buMenBean.getRespMsg());
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
