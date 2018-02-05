package com.yunyisheng.app.yunys.tasks.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import com.yunyisheng.app.yunys.tasks.fragment.NoneDeviceCycleTaskFargment;
import com.yunyisheng.app.yunys.tasks.fragment.NoneDeviceTemporaryTaskFargment;
import com.yunyisheng.app.yunys.tasks.present.NoneDeviceTaskPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class CreateNoneDeviceTaskAcitvity extends BaseActivity<NoneDeviceTaskPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.none_device_tablayout)
    TabLayout noneDeviceTablayout;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.none_device_tasks_viewpage)
    ViewPager noneDeviceTasksViewpage;
    private List<Fragment> fragmentLists = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();
    private int selectTabPostion = 0;

    NoneDeviceTemporaryTaskFargment noneDeviceTemporaryTaskFargment;
    NoneDeviceCycleTaskFargment noneDeviceCycleTaskFargment;


    private  List<WorkerBean> selectWorkList; //安排工作跳转来的人员列表

    private String editTaskId;          //编辑页面跳转过来的任务id
    private int fromPageType;           //编辑页面跳转的对应任务类型，0,新建任务，1、临时任务，2、周期任务

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.editTaskId = getIntent().getStringExtra("editTaskId");
        this.fromPageType = getIntent().getIntExtra("fromPageType",0);
        if (fromPageType == 0){
            mTitles.add("临时任务");
            mTitles.add("周期任务");

            noneDeviceTemporaryTaskFargment = new NoneDeviceTemporaryTaskFargment();
            noneDeviceCycleTaskFargment = new NoneDeviceCycleTaskFargment();
            fragmentLists.add(noneDeviceTemporaryTaskFargment);
            fragmentLists.add(noneDeviceCycleTaskFargment);
        }else if (fromPageType == 1){
            mTitles.add("临时任务");
            noneDeviceTemporaryTaskFargment = new NoneDeviceTemporaryTaskFargment();
            fragmentLists.add(noneDeviceTemporaryTaskFargment);
            selectTabPostion = 0;
        }else if (fromPageType == 2){
            mTitles.add("周期任务");
            noneDeviceCycleTaskFargment = new NoneDeviceCycleTaskFargment();
            fragmentLists.add(noneDeviceCycleTaskFargment);
            selectTabPostion = 1;
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentLists,mTitles);
        noneDeviceTasksViewpage.setAdapter(adapter);
        noneDeviceTablayout.setupWithViewPager(noneDeviceTasksViewpage);
        noneDeviceTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        selectWorkList = (List<WorkerBean>) getIntent().getSerializableExtra("selectlist");//选中的人
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_none_device_task;
    }

    @Override
    public NoneDeviceTaskPresent bindPresent() {
        return new NoneDeviceTaskPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
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

    private void updateCycleTask() {
        Map<String,String> checked = noneDeviceCycleTaskFargment.checkFormResult();
        if (checked.get("status") == "error"){
            ToastUtils.showToast(checked.get("msg"));
            return;
        }
        UpdateCycleTaskBean taskValue = noneDeviceCycleTaskFargment.getTaskFormData();
        getP().updateCycleTask(taskValue);
    }

    private void updateTemporaryTask() {
        Map<String,String> checked = noneDeviceTemporaryTaskFargment.checkFormResult();
        if (checked.get("status") == "error"){
            ToastUtils.showToast(checked.get("msg"));
            return;
        }
        UpdateTemporaryTaskBean taskValue = noneDeviceTemporaryTaskFargment.getFormData();
        getP().updateDeviceTemporaryTask(taskValue);
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
    public List<WorkerBean> getSelectWorkList() {
        return selectWorkList;
    }

    public String getEditTaskId() {
        return editTaskId;
    }

    public int getFromPageType() {
        return fromPageType;
    }
}
