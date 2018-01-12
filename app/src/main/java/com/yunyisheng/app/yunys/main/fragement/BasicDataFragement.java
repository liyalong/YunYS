package com.yunyisheng.app.yunys.main.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/12 11:47
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class BasicDataFragement extends BaseFragement {

    @BindView(R.id.te_sex)
    TextView teSex;
    @BindView(R.id.te_phonenum)
    TextView tePhonenum;
    @BindView(R.id.te_zuzhibumen)
    TextView teZuzhibumen;
    @BindView(R.id.te_email)
    TextView teEmail;
    @BindView(R.id.btn_anpai_work)
    Button btnAnpaiWork;
    Unbinder unbinder;

    public static BasicDataFragement newInstance() {
        BasicDataFragement fragement = new BasicDataFragement();
        return fragement;
    }

    @Override
    public void initView() {
        btnAnpaiWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_basicdata;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
