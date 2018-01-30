package com.yunyisheng.app.yunys.userset.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.userset.model.CompanyBean;
import com.yunyisheng.app.yunys.userset.present.EnterpriseinformationPresent;
import com.yunyisheng.app.yunys.utils.getapp.AppApplicationMgr;
import com.yunyisheng.app.yunys.utils.glide.GlideDownLoadImage;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fuduo
 * @time 2018/1/18  18:00
 * @describe 企业信息
 */
public class EnterpriseinformationActivity extends BaseActivity<EnterpriseinformationPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_name_code)
    TextView teNameCode;
    @BindView(R.id.te_company_name)
    TextView teCompanyName;
    @BindView(R.id.te_company_address)
    TextView teCompanyAddress;
    @BindView(R.id.te_company_num)
    TextView teCompanyNum;
    @BindView(R.id.img_company_logo)
    ImageView imgCompanyLogo;
    @BindView(R.id.te_top_companyname)
    TextView teTopCompanyname;

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
        getP().getCompanyinfo();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_enterpriseinformation;
    }

    @Override
    public EnterpriseinformationPresent bindPresent() {
        return new EnterpriseinformationPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public void getCompanyinfo(CompanyBean companyBean) {
        teCompanyName.setText(companyBean.getRespBody().getEnterpriseName());
        teCompanyAddress.setText(companyBean.getRespBody().getEnterpriseAddressProvince());
        teCompanyNum.setText(companyBean.getRespBody().getEnterpriseNumber());
        GlideDownLoadImage.getInstance().loadImage(EnterpriseinformationActivity.this, companyBean.getRespBody().getEnterpriseLogo(), imgCompanyLogo);
        teTopCompanyname.setText(companyBean.getRespBody().getEnterpriseName());
    }

}
