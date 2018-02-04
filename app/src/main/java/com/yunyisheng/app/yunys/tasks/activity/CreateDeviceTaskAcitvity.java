package com.yunyisheng.app.yunys.tasks.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateCycleTaskBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateTemporaryTaskBean;
import com.yunyisheng.app.yunys.tasks.fragment.CreateDeviceTaskPresent;
import com.yunyisheng.app.yunys.tasks.fragment.DeviceCycleTaskFargment;
import com.yunyisheng.app.yunys.tasks.fragment.DeviceTemporaryTaskFargment;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class CreateDeviceTaskAcitvity extends BaseActivity<CreateDeviceTaskPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.device_tasks_viewpage)
    ViewPager deviceTasksViewpage;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> mtitle = new ArrayList<>();
    public List<WorkerBean> selectlist;
    private int selectTabPostion = 0;
    DeviceTemporaryTaskFargment deviceTemporaryTaskFargment;
    DeviceCycleTaskFargment deviceCycleTaskFargment;

    private String taskId;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.taskId = getIntent().getStringExtra("taskId");


        mtitle.add("临时任务");
        mtitle.add("周期任务");
        deviceTemporaryTaskFargment = new DeviceTemporaryTaskFargment();
        deviceCycleTaskFargment = new DeviceCycleTaskFargment();
        fragments.add(deviceTemporaryTaskFargment);
        fragments.add(deviceCycleTaskFargment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, mtitle);
        deviceTasksViewpage.setAdapter(adapter);
        tablayout.setupWithViewPager(deviceTasksViewpage);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectTabPostion = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
    public CreateDeviceTaskPresent bindPresent() {
        return new CreateDeviceTaskPresent();
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
                if (selectTabPostion == 0){
                    updateTemporaryTask();
                }else {
                    updateCycleTask();
                }
                break;

        }

    }
    //提交临时任务
    public void updateTemporaryTask(){
        Map<String,String> checked = deviceTemporaryTaskFargment.checkFormResult();
        if (checked.get("status") == "error"){
            ToastUtils.showToast(checked.get("msg"));
            return;
        }
        UpdateTemporaryTaskBean taskValue = deviceTemporaryTaskFargment.getFormData();
        getP().updateDeviceTemporaryTask(taskValue);
    }
    public void updateCycleTask(){
        Map<String,String> checked = deviceCycleTaskFargment.checkFormResult();
        if (checked.get("status") == "error"){
            ToastUtils.showToast(checked.get("msg"));
            return;
        }
        UpdateCycleTaskBean taskValue = deviceCycleTaskFargment.getTaskFormData();
        getP().updateCycleTask(taskValue);

    }
    public void checkUpdateDeviceTemporaryTaskStatus(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0){
            ToastUtils.showToast("提交成功！");
            finish();
        }
    }

    public void checkCycleTaskStatus(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0){
            ToastUtils.showToast("提交成功！");
            finish();
        }
    }
}
