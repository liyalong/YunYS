package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.activity.InviteWorkerActivity;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/26 17:32
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class InviteWorkerPresent extends XPresent<InviteWorkerActivity> {

    /**
     *  @author fuduo
     *  @time 2018/1/26  17:33
     *  @describe 邀请同事
     */
    public void inviteWorker(String userName,String userSex,
                             String userPhone, String userMailbox,
                             String userNumber,String userJobTitle,
                             int enterpriseSectionId,int enterpriseRolesId){
        LoadingDialog.show(getV());
        Api.homeService().addPeople(userName,userSex,userPhone,userMailbox,
                userNumber,userJobTitle,enterpriseSectionId,enterpriseRolesId,null)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV());
                        if (baseModel.getRespCode()==0){
                            getV().finish();
                            ToastUtils.showToast("添加成功");
                        }else {
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
