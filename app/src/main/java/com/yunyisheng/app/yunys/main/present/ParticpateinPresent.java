package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.fragement.ParticipateinFragement;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/2/2 12:01
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ParticpateinPresent extends XPresent<ParticipateinFragement> {

    /**
     * 获取其他人参与的项目列表
     */
    public void getOtherProjectList(int otheruserid,String projectname){
        Api.homeService().getOtherProjectList(1,100,projectname,otheruserid)
                .compose(XApi.<ProjectListModel>getApiTransformer())
                .compose(XApi.<ProjectListModel>getScheduler())
                .compose(getV().<ProjectListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProjectListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :"+error.toString());
                        ToastUtils.showToast("网络请求错误！");
                        if (error.getType()==5){
                            getV().setImgQuesheng();
                        }
                        return;
                    }

                    @Override
                    public void onNext(ProjectListModel projectListModel) {
                        try {
                            if (projectListModel.getRespCode() == 1){
                                ToastUtils.showToast(projectListModel.getRespMsg());
                                getV().setGoneQuesheng();
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
