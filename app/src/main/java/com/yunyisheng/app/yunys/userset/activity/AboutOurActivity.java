package com.yunyisheng.app.yunys.userset.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.utils.getapp.AppApplicationMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/18  18:09
 * @describe 关于我们activity
 */
public class AboutOurActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_aboutour)
    TextView teAboutour;
    @BindView(R.id.te_name_code)
    TextView teNameCode;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teNameCode.setText(AppApplicationMgr.getAppName(AboutOurActivity.this) + AppApplicationMgr.getVersionCode(AboutOurActivity.this));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_about_our;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
