package com.yunyisheng.app.yunys.main.activity;

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

public class SendNoticeActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_next)
    TextView teNext;
    @BindView(R.id.ed_noticetitle)
    EditText edNoticetitle;
    @BindView(R.id.rl_fujian)
    RelativeLayout rlFujian;
    @BindView(R.id.te_frangesize)
    TextView teFrangesize;
    @BindView(R.id.rl_senfrange)
    RelativeLayout rlSenfrange;
    @BindView(R.id.te_notice_deatil)
    EditText teNoticeDeatil;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_send_notice;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teNext.setOnClickListener(this);
        rlFujian.setOnClickListener(this);
        rlSenfrange.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.te_next:
                break;
            case R.id.rl_fujian:
                break;
            case R.id.rl_senfrange:
                break;
        }
    }

}
