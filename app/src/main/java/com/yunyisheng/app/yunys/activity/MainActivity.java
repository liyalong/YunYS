package com.yunyisheng.app.yunys.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.fragment.IndexFragment;
import com.yunyisheng.app.yunys.fragment.MyFragment;
import com.yunyisheng.app.yunys.fragment.ProjectFragment;
import com.yunyisheng.app.yunys.fragment.TaskFragment;
import com.yunyisheng.app.yunys.utils.Constans;

import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

public class MainActivity extends XActivity implements BottomNavigationBar.OnTabSelectedListener{
    BottomNavigationBar bottomNavigationBar;
    IndexFragment indexFragment;
    ProjectFragment projectFragment;
    TaskFragment taskFragment;
    MyFragment myFragment;

    @Override
    public void initData(Bundle savedInstanceState) {
        checkToken();
        bottomNavigationBar =(BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        initBottomBar();
        initTab();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void checkToken() {
        String token = SharedPref.getInstance(context).getString("TOKEN", "");
        if(token.length() == 0){
            Log.i("TOKEN","token is empty");
            Router.newIntent(context)
                    .to(LoginActivity.class)
                    .launch();
            this.finish();
        }
    }

    private void initTab() {
        indexFragment = new IndexFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,indexFragment);
        transaction.commit();
    }
    //初始化底部导航栏
    private void initBottomBar() {
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.activity_bar_index,R.string.bottom_bar_index))
                .addItem(new BottomNavigationItem(R.drawable.activity_bar_project,R.string.bottom_bar_project))
                .addItem(new BottomNavigationItem(R.drawable.activity_bar_center,null))
                .addItem(new BottomNavigationItem(R.drawable.activity_bar_task,R.string.bottom_bar_task))
                .addItem(new BottomNavigationItem(R.drawable.activity_bar_my,R.string.bottom_bar_my))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Object newP() {
        return null;
    }
    //底部导航栏切换fragment
    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position){
            case  0:
                if(indexFragment == null){
                    indexFragment = new IndexFragment();
                }
                transaction.replace(R.id.content_main,indexFragment);
                break;
            case 1:
                if(projectFragment == null){
                    projectFragment = new ProjectFragment();
                }
                transaction.replace(R.id.content_main,projectFragment);
                break;
            case 2:
                if(indexFragment == null){
                    indexFragment = new IndexFragment();
                }
                transaction.replace(R.id.content_main,indexFragment);
                break;
            case 3:
                if(taskFragment == null){
                    taskFragment = new TaskFragment();
                }
                transaction.replace(R.id.content_main,taskFragment);
                break;
            case 4:
                if(myFragment == null){
                    myFragment = new MyFragment();
                }
                transaction.replace(R.id.content_main,myFragment);
                break;
        }
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
