package com.yunyisheng.app.yunys.project.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/10.
 */

public class MyProjectFargment extends BaseFragement {
    public static MyProjectFargment newInstance(){
        MyProjectFargment fargment = new MyProjectFargment();
        return  fargment;

    }

    @Override
    public void initView() {

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fargment_project_list_my;
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
