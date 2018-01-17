package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.tasks.activity.CronResultActivity;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class DeviceCycleTaskFargment extends BaseFragement {

    private final static int PROJECTREQUESTCODE = 1;
    private final static int DEVICEEQUESTCODE = 2;
    private final static int CRONREQUESTCODE = 3;

    CustomDatePicker startCustomDatePicker,endCustomDatePicker;
    @BindView(R.id.cycle_select_project)
    TextView cycleSelectProject;
    @BindView(R.id.cycle_select_project_device)
    TextView cycleSelectProjectDevice;
    @BindView(R.id.cycle_task_name)
    EditText cycleTaskName;
    @BindView(R.id.cycle_task_start_time)
    TextView cycleTaskStartTime;
    @BindView(R.id.cycle_task_end_time)
    TextView cycleTaskEndTime;
    @BindView(R.id.cycle_select_cron)
    TextView cycleSelectCron;
    @BindView(R.id.cycle_task_used_time)
    EditText cycleTaskUsedTime;
    @BindView(R.id.cycle_tasks_type)
    Switch cycleTasksType;
    @BindView(R.id.cycle_task_desc)
    EditText cycleTaskDesc;
    @BindView(R.id.cycle_task_templates)
    TextView cycleTaskTemplates;

    public String getCycleTaskStartTime() {
        return cycleTaskStartTime.getText().toString();
    }

    public String getCycleTaskEndTime() {
        return cycleTaskEndTime.getText().toString();
    }

    public static DeviceCycleTaskFargment newInstance() {
        return new DeviceCycleTaskFargment();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        initDatePicker();
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.device_cycle_task_fargment;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {

        cycleSelectProjectDevice.setOnClickListener(this);
        cycleSelectCron.setOnClickListener(this);
        cycleTaskEndTime.setOnClickListener(this);
        cycleTaskStartTime.setOnClickListener(this);
        cycleTaskTemplates.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.cycle_select_project:
                ToastUtils.showToast("选择项目");
                break;
            case R.id.cycle_select_project_device:
                ToastUtils.showToast("选择设备");
                break;
            case R.id.cycle_select_cron:
                Intent intent = new Intent(context, CronResultActivity.class);
                startActivityForResult(intent, CRONREQUESTCODE);
                break;
            case R.id.cycle_task_start_time:
                XLog.d(getCycleTaskStartTime());
                startCustomDatePicker.show(cycleTaskStartTime.getText().toString());
                break;
            case R.id.cycle_task_end_time:
                XLog.d(getCycleTaskEndTime());
                endCustomDatePicker.show(cycleTaskEndTime.getText().toString());
                break;
            case R.id.task_templates:
                ToastUtils.showToast("选择反馈项");
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CRONREQUESTCODE:
                if (resultCode == 1) {
                    cycleSelectCron.setText(data.getStringExtra("cron"));
                }
                break;
            case PROJECTREQUESTCODE:
                break;
            case DEVICEEQUESTCODE:
                break;
        }
    }
    public void initDatePicker(){
        String startDate = "2010-01-01 00:00";
        String pattern = "yyyy-MM-dd HH:mm";
        String startTime =DateTimeDialogUtils.getNewData(pattern,0);
        String endTime  =DateTimeDialogUtils.getNewData(pattern,1);
        cycleTaskStartTime.setText(startTime);
        cycleTaskEndTime.setText(endTime);

        startCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                    cycleTaskStartTime.setText(time);
            }
        }, startDate, startTime);
        startCustomDatePicker.showSpecificTime(true);
        endCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                cycleTaskEndTime.setText(time);
            }
        },startDate,endTime);
    }
}
