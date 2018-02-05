package com.yunyisheng.app.yunys.tasks.present;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.tasks.activity.SelectProcessFormActivity;
import com.yunyisheng.app.yunys.tasks.model.ProcessFormListModel;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/2/4.
 */

public class ProcessFormListPresent extends XPresent<SelectProcessFormActivity> {
    public void getProcessFormList(){
        Api.taskService().getProecssList(1,999,"88d3c7fccd154c66861621c45ed4d75e")
                .compose(XApi.<ProcessFormListModel>getApiTransformer())
                .compose(XApi.<ProcessFormListModel>getScheduler())
                .compose(getV().<ProcessFormListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProcessFormListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络链接错误！");
                    }

                    @Override
                    public void onNext(ProcessFormListModel processFormListModel) {
                        if (processFormListModel.getRespCode() == 1){
                            ToastUtils.showToast(processFormListModel.getRespMsg());
                            return;
                        }
                        getV().setAdapterList(processFormListModel);
                    }
                });
    }
}
