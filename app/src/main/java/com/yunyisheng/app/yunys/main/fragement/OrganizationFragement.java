package com.yunyisheng.app.yunys.main.fragement;

import android.view.View;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/12 10:15
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class OrganizationFragement extends BaseFragement {

    public static OrganizationFragement newInstance(int index){
        OrganizationFragement fragement=new OrganizationFragement();
        return fragement;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_organization;
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
