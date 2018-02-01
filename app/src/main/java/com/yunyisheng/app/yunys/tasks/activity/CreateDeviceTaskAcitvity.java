package com.yunyisheng.app.yunys.tasks.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.tasks.fragment.DeviceCycleTaskFargment;
import com.yunyisheng.app.yunys.tasks.fragment.DeviceTemporaryTaskFargment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class CreateDeviceTaskAcitvity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tablayout)
    TabLayout ablayout;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.device_tasks_viewpage)
    ViewPager deviceTasksViewpage;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> mtitle = new ArrayList<>();
    public List<WorkerBean> selectlist;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mtitle.add("临时任务");
        mtitle.add("周期任务");
        fragments.add(DeviceTemporaryTaskFargment.newInstance());
        fragments.add(DeviceCycleTaskFargment.newInstance());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, mtitle);
        deviceTasksViewpage.setAdapter(adapter);
        ablayout.setupWithViewPager(deviceTasksViewpage);
    }

    @Override
    public void initAfter() {
        selectlist = (List<WorkerBean>) getIntent().getSerializableExtra("selectlist");//选中的人
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_device_task;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.submit:
                //TODO 提交表单
                break;
        }

    }

}
