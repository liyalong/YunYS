package com.yunyisheng.app.yunys.tasks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class DeviceTemporaryTaskFargment extends BaseFragement {
    @BindView(R.id.select_project)
    TextView selectProject;
    @BindView(R.id.select_project_device)
    TextView selectProjectDevice;
    @BindView(R.id.task_name)
    EditText taskName;
    @BindView(R.id.task_start_time)
    EditText taskStartTime;
    @BindView(R.id.task_end_time)
    EditText taskEndTime;
    @BindView(R.id.tasks_type)
    Switch tasksType;
    @BindView(R.id.task_templates)
    TextView taskTemplates;

    public static DeviceTemporaryTaskFargment newInstance() {
        return new DeviceTemporaryTaskFargment();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.device_temporary_task_fargment;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        selectProject.setOnClickListener(this);
        selectProjectDevice.setOnClickListener(this);
        taskStartTime.setOnClickListener(this);
        taskEndTime.setOnClickListener(this);
        taskTemplates.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.select_project:
                ToastUtils.showToast("选择项目");
                break;
            case R.id.select_project_device:
                ToastUtils.showToast("选择设备");
                break;
            case R.id.task_start_time:
                ToastUtils.showToast("选择开始时间");
                break;
            case R.id.task_end_time:
                ToastUtils.showToast("选择结束时间");
                break;
            case R.id.task_templates:
                ToastUtils.showToast("选择反馈项");
                break;
        }

    }

}
