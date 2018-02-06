package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.model.ProcessTaskFormDetailBean;
import com.yunyisheng.app.yunys.tasks.activity.ProcessTaskFormActivity;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/2/5 17:12
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProcessTaskPresent extends XPresent<ProcessTaskFormActivity> {

    /**
     * @author fuduo
     * @time 2018/2/5  17:15
     * @describe 获取流程任务表单信息
     */
    public void getProcessTaskDetail(String processDefinitionId) {
        Api.projectService().getProcessTaskForm(processDefinitionId)
                .compose(XApi.<ProcessTaskFormDetailBean>getApiTransformer())
                .compose(XApi.<ProcessTaskFormDetailBean>getScheduler())
                .compose(getV().<ProcessTaskFormDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProcessTaskFormDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ProcessTaskFormDetailBean processTaskFormDetailBean) {
                        if (processTaskFormDetailBean.getRespCode() == 1) {
                            ToastUtils.showToast(processTaskFormDetailBean.getRespMsg());
                            return;
                        }
                        getV().getProcessTaskForm(processTaskFormDetailBean);
                    }
                });
    }


    /**
     * @author fuduo
     * @time 2018/2/5  17:15
     * @describe 提交流程任务表单
     */
    public void putProcessTaskForm(String instanceFormStr, int assignee,
                                   String processDefinitionId, String endTime) {
        Api.projectService().putProcessTaskForm(instanceFormStr, assignee, processDefinitionId, endTime)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1) {
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        ToastUtils.showToast("提交成功");
                        getV().finish();
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/2/5  17:15
     * @describe 转办流程任务表单
     */
    public void zhuanProcessTaskForm(String taskId, int userId) {
        Api.projectService().zhaunBanTask(taskId, userId)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode() == 1) {
                            ToastUtils.showToast(baseModel.getRespMsg());
                            return;
                        }
                        ToastUtils.showToast("转办成功");
                    }
                });
    }

}
