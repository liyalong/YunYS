package com.yunyisheng.app.yunys.main.activity;

import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/10  18:11
 * @describe 报表activity
 */
public class ReportformActivity extends BaseActivity {


    @Override
    public void initView() {
        // configure the SlidingMenu
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.distance_15);
        menu.setShadowDrawable(R.color.colorAccent);

        // 设置滑动菜单视图的宽度
        int width = getWindowManager().getDefaultDisplay().getWidth();
        menu.setBehindOffsetRes(width);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.right_menu);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_reportform;
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
