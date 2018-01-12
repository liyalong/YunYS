package com.yunyisheng.app.yunys.project.fragement;

import android.view.View;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/10.
 */

public class CompanyProjectFragment extends BaseFragement {
    public static CompanyProjectFragment newInstance(){
        CompanyProjectFragment fragment = new CompanyProjectFragment();
        return  fragment;
    }
    @Override
    public void initView() {

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fargment_project_list_company;
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
