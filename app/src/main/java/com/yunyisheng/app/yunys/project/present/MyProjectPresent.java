package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.fragement.MyProjectFargment;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/17.
 */

public class MyProjectPresent extends XPresent<MyProjectFargment> {
    /**
     * 获取我参与的项目列表
     * @param pageNum
     * @param pageSize
     * @param projectName
     */
    public void getMyProjectList(int pageNum,int pageSize,String projectName){
        LoadingDialog.show(getV().getContext());
        Api.projectService().getMyProjectList(pageNum,pageSize,projectName,null)
                .compose(XApi.<ProjectListModel>getApiTransformer())
                .compose(XApi.<ProjectListModel>getScheduler())
                .compose(getV().<ProjectListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProjectListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :"+error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        LoadingDialog.dismiss(getV().getContext());
                        getV().initRefresh();
                        return;
                    }

                    @Override
                    public void onNext(ProjectListModel projectListModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        try {
                            if (projectListModel.getRespCode() == 1){
                                ToastUtils.showToast(projectListModel.getRespMsg());
                                return;
                            }
                            getV().setProjectListModel(projectListModel);
                        }catch (Exception e){
                            getV().initRefresh();
                            e.printStackTrace();

                        }
                    }
                });

    }
}
