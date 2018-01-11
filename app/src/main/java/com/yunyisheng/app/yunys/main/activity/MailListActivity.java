package com.yunyisheng.app.yunys.main.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

public class MailListActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.rl_organizationframe)
    RelativeLayout rlOrganizationframe;
    @BindView(R.id.rl_projectframe)
    RelativeLayout rlProjectframe;
    @BindView(R.id.rl_arrangework)
    RelativeLayout rlArrangework;
    @BindView(R.id.rl_invite)
    RelativeLayout rlInvite;
    @BindView(R.id.elv_organizationframe)
    ExpandableListView elvOrganizationframe;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText(R.string.tongxunlu);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_mail_list;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        rlOrganizationframe.setOnClickListener(this);
        rlProjectframe.setOnClickListener(this);
        rlArrangework.setOnClickListener(this);
        rlInvite.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_organizationframe:
                break;
            case R.id.rl_projectframe:
                break;
            case R.id.rl_arrangework:
                break;
            case R.id.rl_invite:
                break;
        }
    }

}
