package com.yunyisheng.app.yunys.schedule.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.schedule.activity.ScheduleSetActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

import static com.yunyisheng.app.yunys.utils.ScreenUtils.setIndicator;

/**
 * 作者：fuduo on 2018/1/13 11:32
 * 邮箱：duoendeavor@163.com
 * 用途：日程fragement
 */
public class ScheduleTaskFragement extends BaseFragement {

    Unbinder unbinder;
    @BindView(R.id.tablayout_task)
    TabLayout tablayoutTask;
    @BindView(R.id.img_set)
    ImageView imgSet;
    @BindView(R.id.vp_task)
    ViewPager vpTask;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    @Override
    public void initView() {
        if (mTitleList.size()>0){
            mTitleList.clear();
        }
        mTitleList.add("我的日程");
        mTitleList.add("项目日程");
        if (fragmentList.size()>0){
            fragmentList.clear();
        }
        for (int i=0;i<mTitleList.size();i++){
            fragmentList.add(OurProjeceScheduleFragement.getInstance(i));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList, mTitleList);
        vpTask.setAdapter(adapter);
        tablayoutTask.setupWithViewPager(vpTask);
        setIndicator(getActivity(), tablayoutTask, 10, 10);
    }

    @Override
    public void initAfter() {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_scheduletask;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgSet.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_set:
                startActivity(new Intent(mContext, ScheduleSetActivity.class));
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
