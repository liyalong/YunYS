package com.yunyisheng.app.yunys.userset.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

public class AccountSetActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.ed_phonenum)
    EditText edPhonenum;
    @BindView(R.id.ed_yanzhengnum)
    EditText edYanzhengnum;
    @BindView(R.id.get_yzm)
    Button getYzm;
    @BindView(R.id.btn_queren)
    Button btnQueren;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("账号设置");
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_account_set;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        getYzm.setOnClickListener(this);
        btnQueren.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.get_yzm:
                break;
            case R.id.btn_queren:
                break;
        }
    }

}
