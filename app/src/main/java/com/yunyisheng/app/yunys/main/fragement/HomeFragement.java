package com.yunyisheng.app.yunys.main.fragement;

import android.app.Activity;
import android.view.View;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/10 09:24
 * 邮箱：duoendeavor@163.com
 * 用途：首页fragement
 */

public class HomeFragement extends BaseFragement {
    @Override
    public void initView() {
        ButterKnife.bind((Activity) mContext);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fargment_home;
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
}
