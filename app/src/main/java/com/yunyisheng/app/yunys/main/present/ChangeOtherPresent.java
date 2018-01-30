package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.activity.ChangeOtherUserinfoActivity;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/29 11:19
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ChangeOtherPresent extends XPresent<ChangeOtherUserinfoActivity> {

    /**
     * @author fuduo
     * @time 2018/1/26  17:33
     * @describe 修改其他人资料
     */
    public void changeOtherInf(String userName,
                               String userPhone, String userMailbox,
                               int userid, String userJobTitle,
                               boolean userIsShow) {
        LoadingDialog.show(getV());
        Api.homeService().changeOtherWorkeninfo(userName, userPhone, userMailbox,
                userJobTitle, userid, null, userIsShow)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV());
                        if (baseModel.getRespCode() == 0) {
                            getV().setResultFinish();
                            ToastUtils.showToast("修改成功");
                        } else {
                            ToastUtils.showToast(baseModel.getRespMsg());
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
