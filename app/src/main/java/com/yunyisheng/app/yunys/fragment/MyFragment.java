package com.yunyisheng.app.yunys.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.present.MyPresent;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2017/12/6.
 */

public class MyFragment extends XFragment {
    @BindView(R.id.logout)
    TextView logout;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fargment_my;
    }

    @Override
    public Object newP() {
        return new MyPresent();
    }


    @OnClick(R.id.logout)
    public void onViewClicked(){
        SharedPref.getInstance(context).remove("TOKEN");
        Router.newIntent(context)
                .to(LoginActivity.class)
                .launch();
        context.finish();

    }



}
