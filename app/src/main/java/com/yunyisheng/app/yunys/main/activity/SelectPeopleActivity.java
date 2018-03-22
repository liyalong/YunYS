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

/**
 * @author fuduo
 * @time 2018/1/16  21:25
 * @describe 架构选人
 */
public class SelectPeopleActivity extends BaseActivity{

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tablayout_jiagou)
    TabLayout tablayoutJiagou;
    @BindView(R.id.vp_jiagou)
    ViewPager vpJiagou;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    public int type;//区分从那样的页面跳转（1：通讯录；其他：需要选人）

    @Override
    public void initView() {
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", 0);
        mTitleList.add("组织架构");
        mTitleList.add("项目架构");
//        //注册成为订阅者
//        EventBus.getDefault().register(this);
        for (int i = 0; i < 2; i++) {
            fragmentList.add(OrganizationFragement.newInstance(i));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        vpJiagou.setAdapter(adapter);
        tablayoutJiagou.setupWithViewPager(vpJiagou);
        setIndicator(this, tablayoutJiagou, 5, 5);
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
//    //订阅方法，当接收到事件的时候，会调用该方法
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent(ViewListBean viewListBean){
//        Log.d("cylog","receive it");
//        if (selectlist.size()>0){
//            selectlist.clear();
//        }
//        selectlist.addAll(viewListBean.getList());
//       if (selectlist.size()>0){
//           rlBottom.setVisibility(View.VISIBLE);
//       }else {
//           rlBottom.setVisibility(View.GONE);
//       }
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //解除注册
//        EventBus.getDefault().unregister(this);
//    }
}
