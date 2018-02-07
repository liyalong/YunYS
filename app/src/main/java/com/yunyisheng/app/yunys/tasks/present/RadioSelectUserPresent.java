package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.activity.RadioSelectUserActivity;
import com.yunyisheng.app.yunys.tasks.model.ProjectUserListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/4.
 */

public class RadioSelectUserPresent extends XPresent<RadioSelectUserActivity> {
    public void getAllUserLists(){
        Api.taskService().getAllUserLists("activiti")
                .compose(XApi.<ProjectUserListModel>getApiTransformer())
                .compose(XApi.<ProjectUserListModel>getScheduler())
                .compose(getV().<ProjectUserListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProjectUserListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(ProjectUserListModel projectUserListModel) {
                        if (projectUserListModel.getRespCode() == 1){
                            ToastUtils.showToast(projectUserListModel.getRespMsg());
                            return;
                        }
                        getV().setAdapterData(projectUserListModel);
                    }
                });
    }

}
