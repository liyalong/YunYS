package com.yunyisheng.app.yunys.userset.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.userset.activity.AboutOurActivity;
import com.yunyisheng.app.yunys.userset.activity.AccountSetActivity;
import com.yunyisheng.app.yunys.userset.activity.ClearCatchActivity;
import com.yunyisheng.app.yunys.userset.activity.EnterpriseinformationActivity;
import com.yunyisheng.app.yunys.userset.activity.MimaManagerActivity;
import com.yunyisheng.app.yunys.userset.activity.MyInformationActivity;
import com.yunyisheng.app.yunys.utils.DialogManager;
import com.yunyisheng.app.yunys.utils.getapp.AppApplicationMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidbase.router.Router;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/10 09:22
 * 邮箱：duoendeavor@163.com
 * 用途：我的fragement
 */
public class MineFragement extends BaseFragement {

    Unbinder unbinder;
    @BindView(R.id.img_worker_head)
    ImageView imgWorkerHead;
    @BindView(R.id.te_name_zhize)
    TextView teNameZhize;
    @BindView(R.id.te_phonenum)
    TextView tePhonenum;
    @BindView(R.id.my_info)
    RelativeLayout myInfo;
    @BindView(R.id.soft_version)
    RelativeLayout softVersion;
    @BindView(R.id.user_setting)
    RelativeLayout userSetting;
    @BindView(R.id.mimamanager)
    RelativeLayout mimamanager;
    @BindView(R.id.about_us)
    RelativeLayout aboutUs;
    @BindView(R.id.company_info)
    RelativeLayout companyInfo;
    @BindView(R.id.clean_cache)
    RelativeLayout cleanCache;
    @BindView(R.id.logout)
    TextView logout;
    @BindView(R.id.img_carm)
    ImageView imgCarm;
    @BindView(R.id.te_versioncode)
    TextView teVersioncode;

    @Override
    public void initView() {
        teVersioncode.setText(AppApplicationMgr.getVersionCode(mContext));
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgCarm.setOnClickListener(this);
        myInfo.setOnClickListener(this);
        softVersion.setOnClickListener(this);
        userSetting.setOnClickListener(this);
        mimamanager.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        companyInfo.setOnClickListener(this);
        cleanCache.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_carm:
                DialogManager.createPickImageDialog(getActivity());
                break;
            case R.id.my_info:
                startActivity(new Intent(mContext, MyInformationActivity.class));
                break;
            case R.id.soft_version:
                break;
            case R.id.user_setting:
                startActivity(new Intent(mContext, AccountSetActivity.class));
                break;
            case R.id.mimamanager:
                startActivity(new Intent(mContext, MimaManagerActivity.class));
                break;
            case R.id.about_us:
                startActivity(new Intent(mContext, AboutOurActivity.class));
                break;
            case R.id.company_info:
                startActivity(new Intent(mContext, EnterpriseinformationActivity.class));
                break;
            case R.id.clean_cache:
                startActivity(new Intent(mContext, ClearCatchActivity.class));
                break;
            case R.id.logout:
                SharedPref.getInstance(context).remove("TOKEN");
                Router.newIntent(context)
                        .to(LoginActivity.class)
                        .launch();
                context.finish();
                break;
        }
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
