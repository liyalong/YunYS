package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectForm;
import com.yunyisheng.app.yunys.tasks.model.ProjectFormListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/4.
 */

public class ProjectFormPresent extends XPresent<SelectProjectForm> {
    public void getProjectFormList(String projectId){
        Api.taskService().getProjectFormList(projectId)
                .compose(XApi.<ProjectFormListModel>getApiTransformer())
                .compose(XApi.<ProjectFormListModel>getScheduler())
                .compose(getV().<ProjectFormListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProjectFormListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ProjectFormListModel projectFormListModel) {
                        if(projectFormListModel.getRespCode() == 1){
                            ToastUtils.showToast(projectFormListModel.getRespMsg());
                            return;
                        }
                        getV().setAdapterData(projectFormListModel);
                    }
                });
    }
}
