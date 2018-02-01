package com.yunyisheng.app.yunys.userset.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.userset.present.FankuiPresent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FanKuiActivity extends BaseActivity<FankuiPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.ed_put)
    EditText edPut;
    @BindView(R.id.btn_queren)
    Button btnQueren;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("反馈建议");
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fan_kui;
    }

    @Override
    public FankuiPresent bindPresent() {
        return new FankuiPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        btnQueren.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_queren:
                String fankui = edPut.getText().toString().trim();
                break;
        }
    }
}
