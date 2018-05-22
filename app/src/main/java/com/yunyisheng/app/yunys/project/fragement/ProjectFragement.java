package com.yunyisheng.app.yunys.project.fragement;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

import static com.yunyisheng.app.yunys.utils.ScreenUtils.setIndicator;

/**
 * 作者：fuduo on 2018/1/10 09:25
 * 邮箱：duoendeavor@163.com
 * 用途：项目Fragment
 */

public class ProjectFragement extends BaseFragement {

    @BindView(R.id.project_tablayout)
    TabLayout projectTablayout;
    @BindView(R.id.project_list_viewpage)
    ViewPager projectListViewpage;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        if (mTitle.size()>0){
            mTitle.clear();
        }
        mTitle.add("我的项目");
        mTitle.add("公司项目");
        if (fragmentList.size()>0){
            fragmentList.clear();
        }
        fragmentList.add(MyProjectFargment.newInstance());
        fragmentList.add(CompanyProjectFragment.newInstance());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(),fragmentList,mTitle);
        projectListViewpage.setAdapter(adapter);
        projectTablayout.setupWithViewPager(projectListViewpage);
        setIndicator(getActivity(), projectTablayout, 5, 5);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_project;
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
