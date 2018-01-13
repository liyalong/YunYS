package com.yunyisheng.app.yunys.userset.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

public class MyInformationActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_ok)
    TextView teOk;
    @BindView(R.id.te_username)
    TextView teUsername;
    @BindView(R.id.rl_ed_username)
    RelativeLayout rlEdUsername;
    @BindView(R.id.te_userphone)
    TextView teUserphone;
    @BindView(R.id.rl_ed_userphone)
    RelativeLayout rlEdUserphone;
    @BindView(R.id.te_useremail)
    TextView teUseremail;
    @BindView(R.id.rl_ed_usereamil)
    RelativeLayout rlEdUsereamil;
    @BindView(R.id.te_userbumen)
    TextView teUserbumen;
    @BindView(R.id.te_userzhiwei)
    TextView teUserzhiwei;
    @BindView(R.id.te_userjiaose)
    TextView teUserjiaose;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_information;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teOk.setOnClickListener(this);
        rlEdUsername.setOnClickListener(this);
        rlEdUserphone.setOnClickListener(this);
        rlEdUsereamil.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.te_ok:
                break;
            case R.id.rl_ed_username:
                break;
            case R.id.rl_ed_userphone:
                break;
            case R.id.rl_ed_usereamil:
                break;
        }
    }

}
