package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.main.activity.ProjectFromWorkActivity;
import com.yunyisheng.app.yunys.main.model.FindProjectWorkerBean;
import com.yunyisheng.app.yunys.main.model.ProjectFromWorkBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/29 15:01
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProjectFromworkPresent extends XPresent<ProjectFromWorkActivity> {

    /**
     * @author fuduo
     * @time 2018/1/26  20:35
     * @describe 获取项目架构
     */
    public void getFromworklist() {
        LoadingDialog.show(getV());
        Api.homeService().getProjectFromwork()
                .compose(XApi.<ProjectFromWorkBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<ProjectFromWorkBean>getScheduler()) //线程调度
                .compose(getV().<ProjectFromWorkBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<ProjectFromWorkBean>() {
                    @Override
                    public void onNext(ProjectFromWorkBean projectFromWorkBean) {
                        LoadingDialog.dismiss(getV());
                        if (projectFromWorkBean.getRespCode() == 0) {
                            getV().getResult(projectFromWorkBean);
                        } else {
                            ToastUtils.showToast(projectFromWorkBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/23  20:05
     * @describe 获取项目搜索人员列表
     */
    public void getFindProjectList(String title) {
        LoadingDialog.show(getV());
        Api.homeService().getfindProjectworkerlist(title)
                .compose(XApi.<FindProjectWorkerBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<FindProjectWorkerBean>getScheduler()) //线程调度
                .compose(getV().<FindProjectWorkerBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<FindProjectWorkerBean>() {
                    @Override
                    public void onNext(FindProjectWorkerBean findProjectWorkerBean) {
                        LoadingDialog.dismiss(getV());
                        if (findProjectWorkerBean.getRespCode() == 0) {
                            getV().getFindProjectList(findProjectWorkerBean);
                        } else {
                            ToastUtils.showToast(findProjectWorkerBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

}
