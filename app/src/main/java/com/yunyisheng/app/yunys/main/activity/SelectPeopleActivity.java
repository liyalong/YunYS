package com.yunyisheng.app.yunys.main.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.main.fragement.OrganizationFragement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

import static com.yunyisheng.app.yunys.utils.ScreenUtils.setIndicator;

public class SelectPeopleActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tablayout_jiagou)
    TabLayout tablayoutJiagou;
    @BindView(R.id.vp_jiagou)
    ViewPager vpJiagou;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mTitleList.add("组织架构");
        mTitleList.add("项目架构");
        for (int i = 0; i < 2; i++) {
            fragmentList.add(OrganizationFragement.newInstance(i));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        vpJiagou.setAdapter(adapter);
        tablayoutJiagou.setupWithViewPager(vpJiagou);
        setIndicator(this, tablayoutJiagou, 15, 15);
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
        return R.layout.activity_select_people;
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
