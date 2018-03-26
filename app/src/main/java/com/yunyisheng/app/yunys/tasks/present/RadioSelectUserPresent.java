package com.yunyisheng.app.yunys.tasks.present;

import android.util.Log;

import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.net.RetrofitManager;
import com.yunyisheng.app.yunys.tasks.activity.RadioSelectUserActivity;
import com.yunyisheng.app.yunys.tasks.model.ProjectUserListModel;
import com.yunyisheng.app.yunys.tasks.service.TaskService;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by liyalong on 2018/2/4.
 */

public class RadioSelectUserPresent extends XPresent<RadioSelectUserActivity> {
    public void getAllUserLists(Integer createUserId){
        LoadingDialog.show(getV());
        TaskService mMallRequest = RetrofitManager.getInstance().getRetrofit().create(TaskService.class);
        Call<String> call = mMallRequest.getCheckUserList("activiti",createUserId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                try {
                    Log.d("debug", response.body());
                    LoadingDialog.dismiss(getV());
                    getV().getResultList(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                try {
                    LoadingDialog.dismiss(getV());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void getProjectUserList(String projectId){
        LoadingDialog.show(getV().mContext);
        Api.taskService().getProjectUserList(projectId,"1")
                .compose(XApi.<ProjectUserListModel>getApiTransformer())
                .compose(XApi.<ProjectUserListModel>getScheduler())
                .compose(getV().<ProjectUserListModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<ProjectUserListModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().mContext);
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(ProjectUserListModel projectUserListModel) {
                        LoadingDialog.dismiss(getV().mContext);
                        if (projectUserListModel.getRespCode() == 1){
                            ToastUtils.showToast(projectUserListModel.getRespMsg());
                            return;
                        }
                        getV().setAdapterData(projectUserListModel);
                    }
                });
    }

}
