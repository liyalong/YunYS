package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.model.TaskListModel;
import com.yunyisheng.app.yunys.tasks.activity.MyPushTaskChildListActivity;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/2.
 */

public class MyPushTaskChildPersen extends XPresent<MyPushTaskChildListActivity> {
    public void getMyPushTaskChildList(String projectId,String releaseId,int pageNum,int pageSize){
        LoadingDialog.show(getV());
        Api.taskService().getMyPushTaskChildList(projectId,releaseId,pageNum,pageSize)
                .compose(XApi.<TaskListModel>getApiTransformer())
                .compose(XApi.<TaskListModel>getScheduler())
                .compose(getV().<TaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("网络链接错误！");
                        getV().stopRefresh();
                        getV().setNoNetwork();
                    }

                    @Override
                    public void onNext(TaskListModel taskListModel) {
                        LoadingDialog.dismiss(getV());
                        if (taskListModel.getRespCode() == 1){
                            ToastUtils.showToast(taskListModel.getRespMsg());
                            getV().stopRefresh();
                            return;
                        }
                        getV().setDataList(taskListModel);
                    }
                });

    }

}
