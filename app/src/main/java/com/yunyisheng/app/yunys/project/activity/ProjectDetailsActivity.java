package com.yunyisheng.app.yunys.project.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.project.fragement.AlarmListFragment;
import com.yunyisheng.app.yunys.project.fragement.DeviceListFragment;
import com.yunyisheng.app.yunys.project.fragement.ModelListFragment;
import com.yunyisheng.app.yunys.project.fragement.TaskPoolFragment;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

import static com.yunyisheng.app.yunys.utils.ScreenUtils.setIndicator;

/**
 * Created by liyalong on 2018/1/18.
 */

public class ProjectDetailsActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.project_detail_name)
    TextView projectDetailName;
    @BindView(R.id.detail_layout)
    TabLayout detailLayout;
    @BindView(R.id.project_detail_viewpage)
    ViewPager projectDetailViewpage;

    List<String> mTitle = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    private String projectId;
    private String projectName;
    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.setProjectId(getIntent().getStringExtra("projectId"));
        this.setProjectName(getIntent().getStringExtra("projectName"));
        projectDetailName.setText(projectName);
        mTitle.add("设备");
        mTitle.add("工艺模块");
        mTitle.add("报警记录");
        mTitle.add("任务池");

        fragments.add(DeviceListFragment.newInstance());
        fragments.add(ModelListFragment.newInstance());
        fragments.add(AlarmListFragment.newInstance());
        fragments.add(TaskPoolFragment.newInstance());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,mTitle);
        projectDetailViewpage.setAdapter(viewPagerAdapter);
        detailLayout.setupWithViewPager(projectDetailViewpage);
        setIndicator(context, detailLayout, 15, 15);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_project_details;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                this.finish();
                break;
        }

    }

}
