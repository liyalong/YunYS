package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.main.fragement.NoticeFragement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

import static com.yunyisheng.app.yunys.utils.ScreenUtils.setIndicator;

/**
 * @author fuduo
 * @time 2018/1/11  15:33
 * @describe 公告activity
 */
public class NoticeActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_sendnotice)
    TextView teSendnotice;
    @BindView(R.id.vp_notice)
    ViewPager vpNotice;
    @BindView(R.id.tablayout_notice)
    TabLayout tablayoutNotice;
    private List<String> stringList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    public void initView() {
        ButterKnife.bind(this);
        stringList.add("我发布的");
        stringList.add("发布的给我的");
        for (int i = 0; i < stringList.size(); i++) {
            fragmentList.add(NoticeFragement.getInstance(i));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, stringList);
        vpNotice.setAdapter(adapter);
        tablayoutNotice.setupWithViewPager(vpNotice);
        setIndicator(this, tablayoutNotice, 8, 8);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_notice;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teSendnotice.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.te_sendnotice:
                startActivity(new Intent(this, SendNoticeActivity.class));
                break;
        }
    }
}
