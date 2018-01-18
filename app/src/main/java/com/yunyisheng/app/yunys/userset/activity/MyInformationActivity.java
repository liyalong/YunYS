package com.yunyisheng.app.yunys.userset.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/18  18:01
 * @describe 个人信息activity
 */
public class MyInformationActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_ok)
    TextView teOk;
    @BindView(R.id.rl_ed_username)
    RelativeLayout rlEdUsername;
    @BindView(R.id.rl_ed_userphone)
    RelativeLayout rlEdUserphone;
    @BindView(R.id.rl_ed_usereamil)
    RelativeLayout rlEdUsereamil;
    @BindView(R.id.te_userbumen)
    TextView teUserbumen;
    @BindView(R.id.te_userzhiwei)
    TextView teUserzhiwei;
    @BindView(R.id.te_userjiaose)
    TextView teUserjiaose;
    @BindView(R.id.te_username)
    EditText teUsername;
    @BindView(R.id.te_userphone)
    EditText teUserphone;
    @BindView(R.id.te_useremail)
    EditText teUseremail;

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
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.te_ok:
                break;
        }
    }
}
