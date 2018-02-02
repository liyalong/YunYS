package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.tasks.activity.ProjectTemplateActivity;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class DeviceTemporaryTaskFargment extends BaseFragement {

    private final static int PROJECTREQUESTCODE = 1;
    private final static int DEVICEEQUESTCODE = 2;
    private final static int CRONREQUESTCODE = 3;
    private final static int TEMPLATEREQUESTCODE = 4;

    @BindView(R.id.select_project)
    TextView selectProject;
    @BindView(R.id.select_project_device)
    TextView selectProjectDevice;
    @BindView(R.id.task_name)
    EditText taskName;
    @BindView(R.id.task_start_time)
    TextView taskStartTime;
    @BindView(R.id.task_end_time)
    TextView taskEndTime;
    @BindView(R.id.tasks_type)
    Switch tasksType;
    @BindView(R.id.task_templates)
    TextView taskTemplates;

    CustomDatePicker startTimeCustomDatePicker,endTimeCustomDatePicker;


    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        initDatePicker();
    }
    //初始化日期时间选择插件
    private void initDatePicker() {
        String startDate = "2010-01-01 00:00";
        String patten = "yyyy-MM-dd HH:mm";

        String startTime = DateTimeDialogUtils.getNewData(patten,0);
        String endTime = DateTimeDialogUtils.getNewData(patten,1);

        startTimeCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                taskStartTime.setText(time);
            }
        },startDate,startTime);
        endTimeCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                taskEndTime.setText(time);
            }
        },startDate,endTime);

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
                Intent intent4 = new Intent(context, ProjectTemplateActivity.class);
                startActivityForResult(intent4,TEMPLATEREQUESTCODE);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CRONREQUESTCODE:

                break;
            case PROJECTREQUESTCODE:
                break;
            case DEVICEEQUESTCODE:
                break;
            case TEMPLATEREQUESTCODE:
                if (resultCode==5){
                    String fankuijson = data.getStringExtra("fankuijson");//任务反馈项json
                }
                break;
        }
    }


    public static DeviceTemporaryTaskFargment newInstance() {
        return new DeviceTemporaryTaskFargment();
    }
}
