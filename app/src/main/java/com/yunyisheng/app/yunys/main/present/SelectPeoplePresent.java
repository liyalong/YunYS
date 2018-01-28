package com.yunyisheng.app.yunys.main.present;

import android.util.Log;

import com.yunyisheng.app.yunys.main.fragement.OrganizationFragement;
import com.yunyisheng.app.yunys.main.model.FindProjectWorkerBean;
import com.yunyisheng.app.yunys.main.model.FindWorkerBean;
import com.yunyisheng.app.yunys.main.model.ProjectFromWorkBean;
import com.yunyisheng.app.yunys.main.service.HomeService;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.net.RetrofitManager;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 作者：fuduo on 2018/1/24 15:42
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SelectPeoplePresent extends XPresent<OrganizationFragement> {

    /**
     * @author fuduo
     * @time 2018/1/24  15:42
     * @describe 获取通讯录列表
     */
    public void getMaillist() {
        LoadingDialog.show(getV().getContext());
        HomeService mMallRequest = RetrofitManager.getInstance().getRetrofit().create(HomeService.class);
        Call<String> call = mMallRequest.getUserFromwork();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                Log.d("debug", response.body());
                LoadingDialog.dismiss(getV().getContext());
                getV().getResultList(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LoadingDialog.dismiss(getV().getContext());
            }
        });
    }

    /**
     * @author fuduo
     * @time 2018/1/23  20:05
     * @describe 获取通讯录搜索人员列表
     */
    public void getFindList(String title) {
        LoadingDialog.show(getV().getContext());
        Api.homeService().getfindworkerlist(title)
                .compose(XApi.<FindWorkerBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<FindWorkerBean>getScheduler()) //线程调度
                .compose(getV().<FindWorkerBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<FindWorkerBean>() {
                    @Override
                    public void onNext(FindWorkerBean FindWorkerBean) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (FindWorkerBean.getRespCode() == 0) {
                            getV().getFindList(FindWorkerBean);
                        } else {
                            ToastUtils.showToast(FindWorkerBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/26  20:35
     * @describe 获取项目架构
     */
    public void getFromworklist() {
        LoadingDialog.show(getV().getContext());
        Api.homeService().getProjectFromwork()
                .compose(XApi.<ProjectFromWorkBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<ProjectFromWorkBean>getScheduler()) //线程调度
                .compose(getV().<ProjectFromWorkBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<ProjectFromWorkBean>() {
                    @Override
                    public void onNext(ProjectFromWorkBean projectFromWorkBean) {
                        if (projectFromWorkBean.getRespCode() == 0) {
                            getV().getResult(projectFromWorkBean);
                        } else {
                            ToastUtils.showToast(projectFromWorkBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/23  20:05
     * @describe 获取通讯录搜索人员列表
     */
    public void getFindProjectList(String title) {
        LoadingDialog.show(getV().getContext());
        Api.homeService().getfindProjectworkerlist(title)
                .compose(XApi.<FindProjectWorkerBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<FindProjectWorkerBean>getScheduler()) //线程调度
                .compose(getV().<FindProjectWorkerBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<FindProjectWorkerBean>() {
                    @Override
                    public void onNext(FindProjectWorkerBean findProjectWorkerBean) {
                        LoadingDialog.dismiss(getV().getContext());
                        if (findProjectWorkerBean.getRespCode() == 0) {
                            getV().getFindProjectList(findProjectWorkerBean);
                        } else {
                            ToastUtils.showToast(findProjectWorkerBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV().getContext());
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

}
