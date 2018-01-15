package com.yunyisheng.app.yunys.tasks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class NoneDeviceCycleTaskFargment extends BaseFragement {
    @BindView(R.id.task_name)
    EditText taskName;
    @BindView(R.id.task_start_time)
    EditText taskStartTime;
    @BindView(R.id.task_end_time)
    EditText taskEndTime;
    @BindView(R.id.select_cron)
    TextView selectCron;
    @BindView(R.id.task_used_time)
    EditText taskUsedTime;
    @BindView(R.id.tasks_type)
    Switch tasksType;
    @BindView(R.id.task_desc)
    EditText taskDesc;
    @BindView(R.id.task_templates)
    TextView taskTemplates;


    public static NoneDeviceCycleTaskFargment newInstance() {
        return new NoneDeviceCycleTaskFargment();
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
        return R.layout.none_device_cycle_task;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        selectCron.setOnClickListener(this);
        taskStartTime.setOnClickListener(this);
        taskEndTime.setOnClickListener(this);
        taskTemplates.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.select_cron:
                ToastUtils.showToast("选择cron");
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
