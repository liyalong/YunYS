package com.yunyisheng.app.yunys.main.present;

import android.util.Log;

import com.yunyisheng.app.yunys.main.activity.MailListActivity;
import com.yunyisheng.app.yunys.main.service.HomeService;
import com.yunyisheng.app.yunys.net.RetrofitManager;
import com.yunyisheng.app.yunys.utils.LoadingDialog;

import cn.droidlover.xdroidmvp.mvp.XPresent;
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
}
