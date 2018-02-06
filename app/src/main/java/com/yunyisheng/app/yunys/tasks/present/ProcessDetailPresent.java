package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.activity.ProcessDetailActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProcessDetailBean;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/2/6 17:40
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProcessDetailPresent extends XPresent<ProcessDetailActivity> {

    /**
     *  @author fuduo
     *  @time 2018/2/6  17:42
     *  @describe 获取流程任务详情
     */
    public void getProcessTaskDetailByUser(String taskId,String taskType,String userId){
        Api.taskService().getProcessTaskDetailByUser(userId,taskId,taskType)
                .compose(XApi.<ProcessDetailBean>getApiTransformer())
                .compose(XApi.<ProcessDetailBean>getScheduler())
                .compose(getV().<ProcessDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProcessDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ProcessDetailBean processDetailBean) {
                        if (processDetailBean.getRespCode() == 1){
                            ToastUtils.showToast(processDetailBean.getRespMsg());
                            getV().finish();
                            return;
                        }
                       getV().getProcessDetailResult(processDetailBean);
                    }
                });
    }

}
