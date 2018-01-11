package com.yunyisheng.app.yunys.main.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.utils.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/10  18:11
 * @describe 报表activity
 */
public class ReportformActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    private boolean isshowmenu;
    private SlidingMenu menu;

    @Override
    public void initView() {
        // configure the SlidingMenu
        ButterKnife.bind(this);
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.distance_2);
        menu.setShadowDrawable(R.color.color_e7);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.distance_60);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        View view = LayoutInflater.from(mContext).inflate(R.layout.right_menu, null);
        MyGridView gvadded = (MyGridView) view.findViewById(R.id.gv_added);
        MyGridView gvadd = (MyGridView) view.findViewById(R.id.gv_add);

        //为侧滑菜单设置布局
        menu.setMenu(view);
        menu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {
                isshowmenu=true;
            }
        });

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
        imgBack.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                if (!isshowmenu){
                    menu.showMenu();
                }else {
                    menu.toggle();
                }
                break;
        }
    }
}
