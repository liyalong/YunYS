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
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectUserListActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class NoneDeviceCycleTaskFargment extends BaseFragement {
    @BindView(R.id.cycle_select_project)
    TextView cycleSelectProject;
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
    @BindView(R.id.select_cycle_assign_users)
    TextView selectCycleAssignUsers;

    private final static int PROJECTREQUESTCODE = 1;
    private final static int CRONREQUESTCODE = 3;
    private final static int TEMPLATEREQUESTCODE = 4;
    private final static int PROJECTUSERCODE = 5;
    CustomDatePicker startCustomDatePicker, endCustomDatePicker;
    private String selectProjectId;
    private String selectProjectName;
    private List<ProjectUserBean> selectUsers = new ArrayList<>();

    public static NoneDeviceCycleTaskFargment newInstance() {
        return new NoneDeviceCycleTaskFargment();
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
        return R.layout.none_device_cycle_task;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        cycleSelectCron.setOnClickListener(this);
        cycleTaskStartTime.setOnClickListener(this);
        cycleTaskEndTime.setOnClickListener(this);
        cycleTaskTemplates.setOnClickListener(this);
        cycleSelectProject.setOnClickListener(this);
        selectCycleAssignUsers.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.cycle_select_cron:
                Intent intent = new Intent(context, CronResultActivity.class);
                startActivityForResult(intent, CRONREQUESTCODE);
                break;
            case R.id.cycle_task_start_time:
                startCustomDatePicker.show(cycleTaskStartTime.getText().toString());
                break;
            case R.id.cycle_task_end_time:
                endCustomDatePicker.show(cycleTaskEndTime.getText().toString());
                break;
            case R.id.cycle_task_templates:
                ToastUtils.showToast("选择反馈项");
                break;
            case R.id.cycle_select_project:
                Intent intent1 = new Intent(context, SelectProjectActivity.class);
                startActivityForResult(intent1,PROJECTREQUESTCODE);
                break;
            case R.id.select_cycle_assign_users:
                if (selectProjectId == null){
                    ToastUtils.showToast("请先选择项目！");
                    return;
                }
                Intent intent5 = new Intent(context, SelectProjectUserListActivity.class);
                intent5.putExtra("projectId",selectProjectId);
                startActivityForResult(intent5,PROJECTUSERCODE);
                break;
        }

    }

    public void initDatePicker() {
        String startDate = "2010-01-01 00:00";
        String pattern = "yyyy-MM-dd HH:mm";
        String startTime = DateTimeDialogUtils.getNewData(pattern, 0);
        String endTime = DateTimeDialogUtils.getNewData(pattern, 1);
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
        }, startDate, endTime);
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
                if (resultCode == 1){
                    selectProjectId = data.getStringExtra("selectProjectId");
                    selectProjectName = data.getStringExtra("selectProjectName");
                    cycleSelectProject.setText(selectProjectName.toString());
                }
                break;
            case TEMPLATEREQUESTCODE:
                //TODO 选择表单
                break;
            case PROJECTUSERCODE:
                if (resultCode == 1){
                    selectUsers.clear();
                    selectUsers =(List<ProjectUserBean>) data.getSerializableExtra("selectlist");
                    String selectUserNames = "";
                    for (int i=0;i<selectUsers.size();i++){
                        selectUserNames += selectUsers.get(i).getUserName() + " ";
                    }
                    selectCycleAssignUsers.setText(selectUserNames);
                }
                break;
        }
    }
}
