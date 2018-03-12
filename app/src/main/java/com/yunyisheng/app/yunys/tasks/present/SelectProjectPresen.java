package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/3.
 */

public class SelectProjectPresen extends XPresent<SelectProjectActivity> {
    /**
     * 获取我参与的项目列表
     * @param pageNum
     * @param pageSize
     * @param projectName
     */
    public void getMyProjectList(int pageNum,int pageSize,String projectName,String userId){
        LoadingDialog.show(getV().mContext);
        Api.projectService().getMyProjectList(pageNum,pageSize,projectName,userId)
                .compose(XApi.<ProjectListModel>getApiTransformer())
                .compose(XApi.<ProjectListModel>getScheduler())
                .compose(getV().<ProjectListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProjectListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().mContext);
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(ProjectListModel projectListModel) {
                        LoadingDialog.dismiss(getV().mContext);
                        try {
                            if (projectListModel.getRespCode() == 1){
                                ToastUtils.showToast(projectListModel.getRespMsg());
                                return;
                            }
                            getV().setAdapterData(projectListModel);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

    }
}
