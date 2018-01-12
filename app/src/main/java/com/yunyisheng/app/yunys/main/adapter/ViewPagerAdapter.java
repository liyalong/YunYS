package com.yunyisheng.app.yunys.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者:fuDuo
 * 时间：2017/8/23 14:16
 * 邮箱:18610922052@163.com
 * 类的意图:我的订单 viewpager适配器
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> title;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}