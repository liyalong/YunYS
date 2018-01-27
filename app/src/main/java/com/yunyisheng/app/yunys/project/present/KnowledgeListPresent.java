package com.yunyisheng.app.yunys.project.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.KnowledgeListActivity;
import com.yunyisheng.app.yunys.project.model.KnowledgeListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/24.
 */

public class KnowledgeListPresent extends XPresent<KnowledgeListActivity> {
    /**
     * 获取设备的相关知识库列表
     * @param projectId
     * @param deviceId
     * @param pageNum
     * @param pageSize
     */
    public void getKnowledgeList(String projectId,String deviceId,int pageNum,int pageSize){
        Api.projectService().getKnowledgeList(projectId,deviceId,pageNum,pageSize)
                .compose(XApi.<KnowledgeListModel>getApiTransformer())
                .compose(XApi.<KnowledgeListModel>getScheduler())
                .compose(getV().<KnowledgeListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<KnowledgeListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(KnowledgeListModel knowledgeListModel) {
                        if (knowledgeListModel.getRespCode() == 1){
                            ToastUtils.showToast(knowledgeListModel.getErrorMsg());
                            return;
                        }
                        getV().setAdapter(knowledgeListModel);
                    }
                });
    }

    /**
     * 获取工艺模块相关知识列表
     * @param projectId
     * @param modelId
     * @param pageNum
     * @param pageSize
     */
    public void getModelKnowledgeList(String projectId,String modelId,int pageNum,int pageSize){
        Api.projectService().getKnowledgeList(projectId,modelId,pageNum,pageSize)
                .compose(XApi.<KnowledgeListModel>getApiTransformer())
                .compose(XApi.<KnowledgeListModel>getScheduler())
                .compose(getV().<KnowledgeListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<KnowledgeListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                        return;
                    }

                    @Override
                    public void onNext(KnowledgeListModel knowledgeListModel) {
                        if (knowledgeListModel.getRespCode() == 1){
                            ToastUtils.showToast(knowledgeListModel.getErrorMsg());
                            return;
                        }
                        getV().setAdapter(knowledgeListModel);
                    }
                });
    }
}
