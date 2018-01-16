package com.yunyisheng.app.yunys.main.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/12 10:15
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class OrganizationFragement extends BaseFragement {

    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.ck_allworker)
    CheckBox ckAllworker;
    @BindView(R.id.line_all)
    LinearLayout lineAll;
    Unbinder unbinder;
    private int tabindex;

    public static OrganizationFragement newInstance(int index) {
        OrganizationFragement fragement = new OrganizationFragement();
        Bundle bundle = new Bundle();
        bundle.putInt("tabIndex", index);
        fragement.setArguments(bundle);
        return fragement;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        tabindex = bundle.getInt("tabIndex");
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
