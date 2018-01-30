package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.KnowledgeDetailActivity;
import com.yunyisheng.app.yunys.project.model.KnowledgDetailModel;
import com.yunyisheng.app.yunys.project.model.KnowledgeListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/29.
 */

public class KnowledgeDetailPresent extends XPresent<KnowledgeDetailActivity> {
    public void getKnowledgeDetail(String projectId,String knowledgeId){
        Api.projectService().getKnowledgeDetail(projectId,knowledgeId)
                .compose(XApi.<KnowledgDetailModel>getApiTransformer())
                .compose(XApi.<KnowledgDetailModel>getScheduler())
                .compose(getV().<KnowledgDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<KnowledgDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(KnowledgDetailModel knowledgDetailModel) {
                        if (knowledgDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(knowledgDetailModel.getRespMsg());
                            return;
                        }
                        getV().setAdapter(knowledgDetailModel);
                    }
                });
    }
}
