package com.yunyisheng.app.yunys.main.present;

import android.util.Log;

import com.yunyisheng.app.yunys.main.activity.MailListActivity;
import com.yunyisheng.app.yunys.main.model.FindWorkerBean;
import com.yunyisheng.app.yunys.main.service.HomeService;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.TokenHeaderInterceptor;

import java.util.concurrent.TimeUnit;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：fuduo on 2018/1/24 15:42
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MaillistPresent extends XPresent<MailListActivity> {

    private Call<String> call;

    /**
     * @author fuduo
     * @time 2018/1/24  15:42
     * @describe 获取通讯录列表
     */
    public void getMaillist() {
        LoadingDialog.show(getV());
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).
                writeTimeout(60, TimeUnit.SECONDS).
                addNetworkInterceptor(new TokenHeaderInterceptor()).
                build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(Api.BASE_PATH)
                .build();
        HomeService mMallRequest = retrofit.create(HomeService.class);
        call = mMallRequest.getUserFromwork();
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
                    getV().setImgQueshengng();
                    LoadingDialog.dismiss(getV());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void cancle(){
        if (call!=null)
        call.cancel();
    }


    /**
     * @author fuduo
     * @time 2018/1/23  20:05
     * @describe 获取搜索人员列表
     */
    public void getFindList(String title) {
        LoadingDialog.show(getV());
        Api.homeService().getfindworkerlist(title)
                .compose(XApi.<FindWorkerBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<FindWorkerBean>getScheduler()) //线程调度
                .compose(getV().<FindWorkerBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<FindWorkerBean>() {
                    @Override
                    public void onNext(FindWorkerBean FindWorkerBean) {
                        LoadingDialog.dismiss(getV());
                        if (FindWorkerBean.getRespCode() == 0) {
                            getV().getFindList(FindWorkerBean);
                        } else {
                            ToastUtils.showToast(FindWorkerBean.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        if (error.getType() == 5) {
                            getV().setImgQueshengng();
                        }
                        ToastUtils.showToast("请求数据失败！");
                    }
                });
    }

}
