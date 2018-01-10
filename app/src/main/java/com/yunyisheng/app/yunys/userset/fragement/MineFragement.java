package com.yunyisheng.app.yunys.userset.fragement;

import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 作者：fuduo on 2018/1/10 09:22
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MineFragement extends BaseFragement {

    @BindView(R.id.logout)
    TextView logout;

    @Override
    public void initView() {

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

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
