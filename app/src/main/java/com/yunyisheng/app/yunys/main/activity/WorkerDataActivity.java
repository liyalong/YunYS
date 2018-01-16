package com.yunyisheng.app.yunys.main.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.main.fragement.BasicDataFragement;
import com.yunyisheng.app.yunys.main.fragement.ParticipateinFragement;
import com.yunyisheng.app.yunys.main.fragement.ScheduleFragement;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

import static com.yunyisheng.app.yunys.utils.ScreenUtils.setIndicator;

/**
 * @author fuduo
 * @time 2018/1/16  21:26
 * @describe 员工详情
 */
public class WorkerDataActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_edit)
    TextView teEdit;
    @BindView(R.id.img_worker_head)
    ImageView imgWorkerHead;
    @BindView(R.id.te_name_zhize)
    TextView teNameZhize;
    @BindView(R.id.tablayout_information)
    TabLayout tablayoutInformation;
    @BindView(R.id.vp_information)
    ViewPager vpInformation;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private int tabindex;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mTitleList.add("基本资料");
        mTitleList.add("日程安排");
        mTitleList.add("参与项目");
        BasicDataFragement basicDataFragement=new BasicDataFragement();
        ScheduleFragement scheduleFragement=new ScheduleFragement();
        ParticipateinFragement participateinFragement=new ParticipateinFragement();
        fragmentList.add(basicDataFragement);
        fragmentList.add(scheduleFragement);
        fragmentList.add(participateinFragement);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        vpInformation.setAdapter(adapter);
        tablayoutInformation.setupWithViewPager(vpInformation);
        setIndicator(this, tablayoutInformation, 45, 45);

        vpInformation.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabindex=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_worker_data;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teEdit.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.te_edit:
                ToastUtils.showLongToast(tabindex+"");
                break;
        }
    }

}
