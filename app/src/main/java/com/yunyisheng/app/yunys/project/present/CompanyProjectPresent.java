package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.fragement.CompanyProjectFragment;
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

public class CompanyProjectPresent extends XPresent<CompanyProjectFragment> {
    /**
     * 获取公司项目列表
     * @param pageNum
     * @param pageSize
     */
    public void getCompanyProjectList(final int pageNum, int pageSize){
        LoadingDialog.show(getV().getContext());
        Api.projectService().getCompanyProjectList(pageNum,pageSize)
                .compose(XApi.<ProjectListModel>getApiTransformer())
                .compose(XApi.<ProjectListModel>getScheduler())
                .compose(getV().<ProjectListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProjectListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        XLog.d("NET ERROR :"+error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        getV().initRefresh();
                        if (error.getType()==5) {
                            getV().setNoNetWork();
                        }
                        return;
                    }

                    @Override
                    public void onNext(ProjectListModel projectListModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        try {
                            if (projectListModel.getRespCode() == 1){
                                ToastUtils.showToast(projectListModel.getRespMsg());
                                getV().initRefresh();
                            }
                            getV().setProjectListModel(projectListModel);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });

    }

}
