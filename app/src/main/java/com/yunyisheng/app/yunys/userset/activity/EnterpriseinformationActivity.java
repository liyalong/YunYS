package com.yunyisheng.app.yunys.userset.activity;

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
 * @time 2018/1/18  18:00
 * @describe 企业信息
 */
public class EnterpriseinformationActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_company_info)
    TextView teCompanyInfo;
    @BindView(R.id.te_name_code)
    TextView teNameCode;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teNameCode.setText(AppApplicationMgr.getAppName(EnterpriseinformationActivity.this) + AppApplicationMgr.getVersionCode(EnterpriseinformationActivity.this));
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
        return R.layout.activity_enterpriseinformation;
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
