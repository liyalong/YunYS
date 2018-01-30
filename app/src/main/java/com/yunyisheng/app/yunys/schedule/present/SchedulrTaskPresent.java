package com.yunyisheng.app.yunys.schedule.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.schedule.fragement.ScheduleTaskFragement;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/30 17:53
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SchedulrTaskPresent extends XPresent<ScheduleTaskFragement> {

    /**
     * 获取我参与的项目列表
     */
    public void getMyProjectList(){
        Api.projectService().getMyProjectList(1,0,"")
                .compose(XApi.<ProjectListModel>getApiTransformer())
                .compose(XApi.<ProjectListModel>getScheduler())
                .compose(getV().<ProjectListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProjectListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :"+error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(ProjectListModel projectListModel) {
                        try {
                            if (projectListModel.getRespCode() == 1){
                                ToastUtils.showToast(projectListModel.getRespMsg());
                                return;
                            }
                            getV().setProjectListModel(projectListModel);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

    }

}
