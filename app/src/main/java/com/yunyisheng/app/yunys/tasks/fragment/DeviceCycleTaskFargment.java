package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.tasks.activity.CronResultActivity;
import com.yunyisheng.app.yunys.tasks.activity.ProjectTemplateActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectDeviceActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectUserListActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class DeviceCycleTaskFargment extends BaseFragement {

    private final static int PROJECTREQUESTCODE = 1;
    private final static int DEVICEEQUESTCODE = 2;
    private final static int CRONREQUESTCODE = 3;
    private final static int TEMPLATEREQUESTCODE = 4;
    private final static int PROJECTUSERCODE = 5;

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
    @BindView(R.id.select_cycle_assign_users)
    TextView selectCycleAssignUsers;

    private String selectProjectId;
    private String selectProjectName;

    private String selectDeviceId;
    private String selectDeviceName;
    private List<ProjectUserBean> selectUsers = new ArrayList<>();

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
        cycleSelectProject.setOnClickListener(this);
        cycleSelectProjectDevice.setOnClickListener(this);
        cycleSelectCron.setOnClickListener(this);
        cycleTaskEndTime.setOnClickListener(this);
        cycleTaskStartTime.setOnClickListener(this);
        cycleTaskTemplates.setOnClickListener(this);
        selectCycleAssignUsers.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.cycle_select_project:
                Intent intent1 = new Intent(context, SelectProjectActivity.class);
                startActivityForResult(intent1,PROJECTREQUESTCODE);
                break;
            case R.id.cycle_select_project_device:
                //选择设备
                if (selectProjectId == null){
                    ToastUtils.showToast("请先选择项目！");
                    return;
                }
                Intent intent2 = new Intent(context, SelectProjectDeviceActivity.class);
                intent2.putExtra("projectId",selectProjectId);
                startActivityForResult(intent2,DEVICEEQUESTCODE);
                break;
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
                Intent intent4 = new Intent(context, ProjectTemplateActivity.class);
                startActivityForResult(intent4,TEMPLATEREQUESTCODE);
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
            case DEVICEEQUESTCODE:
                if (resultCode == 1){
                    selectDeviceId = data.getStringExtra("selectDeviceId");
                    selectDeviceName = data.getStringExtra("selectDeviceName");
                    cycleSelectProjectDevice.setText(selectDeviceName);
                }
                break;
            case TEMPLATEREQUESTCODE:
                if (resultCode==5){
                    String fankuijson = data.getStringExtra("fankuijson");//任务反馈项json
                }
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
    public void initDatePicker(){
        String startDate = "2010-01-01 00:00";
        String pattern = "yyyy-MM-dd HH:mm";
        String startTime =DateTimeDialogUtils.getNewData(pattern,0);
        String endTime  =DateTimeDialogUtils.getNewData(pattern,1);
        String closeingData = DateTimeDialogUtils.getNewData(pattern,999);
        cycleTaskStartTime.setText(startTime);
        cycleTaskEndTime.setText(endTime);

        startCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                    cycleTaskStartTime.setText(time);
            }
        }, startDate, closeingData);
        endCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                cycleTaskEndTime.setText(time);
            }
        },startDate,closeingData);
    }
}
