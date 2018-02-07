package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.fragement.TaskPoolFragment;
import com.yunyisheng.app.yunys.project.model.TaskListModel;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.List;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/29.
 */

public class TaskListPresent extends XPresent<TaskPoolFragment> {
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
            case 9:
                getFinishTaskList(projectId,pageNum,pageSize);
                break;
        }
    }

    private void getFinishTaskList(String projectId, int pageNum, int pageSize) {
        LoadingDialog.show(getV().getContext());
        Api.taskService().getFinishTaskList(projectId,pageNum,pageSize)
                .compose(XApi.<TaskListModel>getApiTransformer())
                .compose(XApi.<TaskListModel>getScheduler())
                .compose(getV().<TaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        getV().initRefresh();
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(TaskListModel taskListModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (taskListModel.getRespCode() == 1){
                            ToastUtils.showToast(taskListModel.getRespMsg());
                            getV().initRefresh();
                            return;
                        }
                        XLog.d(taskListModel.toString());
                        getV().setAdapter(taskListModel);
                    }
                });
    }

    /**
     * 获取已发布的任务列表
     * @param projectId
     * @param pageNum
     * @param pageSize
     */
    public void getMyTaskList(String projectId,int pageNum,int pageSize){
        LoadingDialog.show(getV().getContext());
        Api.taskService().getMyTaskList(projectId,pageNum,pageSize)
                .compose(XApi.<TaskListModel>getApiTransformer())
                .compose(XApi.<TaskListModel>getScheduler())
                .compose(getV().<TaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("网络链接错误！");
                        getV().initRefresh();
                    }

                    @Override
                    public void onNext(TaskListModel taskListModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (taskListModel.getRespCode() == 1){
                            ToastUtils.showToast(taskListModel.getRespMsg());
                            getV().initRefresh();
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
        LoadingDialog.show(getV().getContext());
        Api.taskService().getClaimTaskList(projectId,pageNum,pageSize)
                .compose(XApi.<TaskListModel>getApiTransformer())
                .compose(XApi.<TaskListModel>getScheduler())
                .compose(getV().<TaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("网络链接错误！");
                        getV().initRefresh();
                    }

                    @Override
                    public void onNext(TaskListModel taskListModel) {
                        LoadingDialog.dismiss(getV().getContext());
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
     * 获取未认领的任务列表
     * @param projectId
     * @param pageNum
     * @param pageSize
     */
    public void getUnClaimTaskList(String projectId,int pageNum,int pageSize){
        LoadingDialog.show(getV().getContext());
        Api.taskService().getUnClaimTaskList(projectId,pageNum,pageSize)
                .compose(XApi.<TaskListModel>getApiTransformer())
                .compose(XApi.<TaskListModel>getScheduler())
                .compose(getV().<TaskListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<TaskListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("网络链接错误！");
                        getV().initRefresh();
                    }

                    @Override
                    public void onNext(TaskListModel taskListModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (taskListModel.getRespCode() == 1){
                            ToastUtils.showToast(taskListModel.getRespMsg());
                            getV().initRefresh();
                            return;
                        }
                        XLog.d(taskListModel.toString());
                        getV().setAdapter(taskListModel);
                    }
                });
    }

    /**
     * 认领任务
     * @param taskId
     */
    public void claimTask(String taskId){
        LoadingDialog.show(getV().getContext());
        Api.taskService().claimTask(taskId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkClaimTaskStatus(baseModel);
                    }
                });
    }
    public void backTask(String taskId,String backText){
        LoadingDialog.show(getV().getContext());
        Api.taskService().backTask(taskId,backText)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkTaskBack(baseModel);
                    }
                });

    }
    public void repealTask(String projectId,String releaseId){
        LoadingDialog.show(getV().getContext());
        Api.taskService().repealTask(projectId,releaseId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkRepealTaskStatus(baseModel);
                    }
                });
    }
    public void assingTaskByUser(String projectId, String list,String releaseId){
        Api.taskService().assignTask(projectId,list,releaseId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1){
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        getV().checkAssignTaskResult(baseModel);
                    }
                });
    }
}
