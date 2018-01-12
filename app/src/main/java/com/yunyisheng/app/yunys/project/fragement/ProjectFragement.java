package com.yunyisheng.app.yunys.project.fragement;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/10 09:25
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProjectFragement extends BaseFragement {
    @BindView(R.id.get_my_projects)
    TabItem getMyProjects;
    @BindView(R.id.get_company_projects)
    TabItem getCompanyProjects;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.project_list_viewpage)
    ViewPager projectListViewpage;

    MyProjectFargment myProjectFargment;
    CompanyProjectFargment companyProjectFargment;
    @Override
    public void initView() {
        ButterKnife.bind(this, context);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fargment_project;
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
