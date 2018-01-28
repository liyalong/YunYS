package com.yunyisheng.app.yunys.main.present;

import android.util.Log;

import com.yunyisheng.app.yunys.main.activity.MailListActivity;
import com.yunyisheng.app.yunys.main.model.FindWorkerBean;
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

public class MaillistPresent extends XPresent<MailListActivity> {

    /**
     *  @author fuduo
     *  @time 2018/1/24  15:42
     *  @describe 获取通讯录列表
     */
    public void getMaillist(){
        LoadingDialog.show(getV());
        HomeService mMallRequest = RetrofitManager.getInstance().getRetrofit().create(HomeService.class);
        Call<String> call = mMallRequest.getUserFromwork();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                Log.d("debug", response.body());
                LoadingDialog.dismiss(getV());
                getV().getResultList(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LoadingDialog.dismiss(getV());
            }
        });
    }
    /**
     * @author fuduo
     * @time 2018/1/23  20:05
     * @describe 获取搜索人员列表
     */
    public void getFindList(String title){
        LoadingDialog.show(getV());
        Api.homeService().getfindworkerlist(title)
                .compose(XApi.<FindWorkerBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<FindWorkerBean>getScheduler()) //线程调度
                .compose(getV().<FindWorkerBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<FindWorkerBean>() {
                    @Override
                    public void onNext(FindWorkerBean FindWorkerBean) {
                        LoadingDialog.dismiss(getV());
                        if (FindWorkerBean.getRespCode()==0){
                            getV().getFindList(FindWorkerBean);
                        }else {
                            ToastUtils.showToast(FindWorkerBean.getRespMsg());
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
