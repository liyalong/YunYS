package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.fragement.TaskPoolFragment;
import com.yunyisheng.app.yunys.project.model.TaskListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/29.
 */

public class TaskPresent extends XPresent<TaskPoolFragment> {
    /**
     * 根据下拉选项加载对应任务列表
     * @param selected
     * @param projectId
     * @param pageNum
     * @param pageSize
     */
    public void getTaskList(int selected,String projectId,int pageNum,int pageSize){
        switch (selected){
            case 1:
                getUnClaimTaskList(projectId,pageNum,pageSize);
                break;
            case 2:
                getClaimTaskList(projectId,pageNum,pageSize);
                break;
            case 3:
                getMyTaskList(projectId,pageNum,pageSize);
                break;
        }
    }
    /**
     * 获取已发布的任务列表
     * @param projectId
     * @param pageNum
     * @param pageSize
     */
    public void getMyTaskList(String projectId,int pageNum,int pageSize){
        Api.taskService().getMyTaskList(projectId,pageNum,pageSize)
                .compose(XApi.<TaskListModel>getApiTransformer())
                .compose(XApi.<TaskListModel>getScheduler())
                .compose(getV().<TaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(TaskListModel taskListModel) {
                        if (taskListModel.getRespCode() == 1){
                            ToastUtils.showToast(taskListModel.getRespMsg());
                            return;
                        }
                        XLog.d(taskListModel.toString());
                        getV().setAdapter(taskListModel);
                    }
                });
    }

    /**
     * 获取未完成的任务列表
     * @param projectId
     * @param pageNum
     * @param pageSize
     */
    public void getClaimTaskList(String projectId,int pageNum,int pageSize){
        Api.taskService().getClaimTaskList(projectId,pageNum,pageSize)
                .compose(XApi.<TaskListModel>getApiTransformer())
                .compose(XApi.<TaskListModel>getScheduler())
                .compose(getV().<TaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(TaskListModel taskListModel) {
                        if (taskListModel.getRespCode() == 1){
                            ToastUtils.showToast(taskListModel.getRespMsg());
                            return;
                        }
                        XLog.d(taskListModel.toString());
                        getV().setAdapter(taskListModel);
                    }
                });
    }
    public void getUnClaimTaskList(String projectId,int pageNum,int pageSize){
        Api.taskService().getUnClaimTaskList(projectId,pageNum,pageSize)
                .compose(XApi.<TaskListModel>getApiTransformer())
                .compose(XApi.<TaskListModel>getScheduler())
                .compose(getV().<TaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(TaskListModel taskListModel) {
                        if (taskListModel.getRespCode() == 1){
                            ToastUtils.showToast(taskListModel.getRespMsg());
                            return;
                        }
                        XLog.d(taskListModel.toString());
                        getV().setAdapter(taskListModel);
                    }
                });
    }
}
