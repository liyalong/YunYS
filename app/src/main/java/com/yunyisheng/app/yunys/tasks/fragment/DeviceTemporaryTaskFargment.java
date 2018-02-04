package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;
import com.yunyisheng.app.yunys.tasks.activity.ProjectTemplateActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectDeviceActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectUserListActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/13.
 */

public class DeviceTemporaryTaskFargment extends BaseFragement {

    private final static int PROJECTREQUESTCODE = 1;
    private final static int DEVICEEQUESTCODE = 2;
    private final static int CRONREQUESTCODE = 3;
    private final static int TEMPLATEREQUESTCODE = 4;
    private final static int PROJECTUSERREQUESTCODE = 5;

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
    @BindView(R.id.task_templates)
    TextView taskTemplates;
    @BindView(R.id.select_assign_users)
    TextView selectAssignUsers;

    CustomDatePicker startTimeCustomDatePicker,endTimeCustomDatePicker;

    private String selectProjectId;
    private String selectProjectName;

    private String selectDeviceId;
    private String selectDeviceName;
    private List<ProjectUserBean> selectUsers = new ArrayList<>();


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
        String closeingData = DateTimeDialogUtils.getNewData(patten,999);
        taskStartTime.setText(startTime);
        taskEndTime.setText(endTime);

        startTimeCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                taskStartTime.setText(time);
            }
        },startDate,closeingData);
        endTimeCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                taskEndTime.setText(time);
            }
        },startDate,closeingData);

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
        selectAssignUsers.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.select_project:
                //选择项目
                Intent intent1 = new Intent(context, SelectProjectActivity.class);
                startActivityForResult(intent1,PROJECTREQUESTCODE);
                break;
            case R.id.select_project_device:
                //选择设备
                if (selectProjectId == null){
                    ToastUtils.showToast("请先选择项目！");
                    return;
                }
                Intent intent2 = new Intent(context, SelectProjectDeviceActivity.class);
                intent2.putExtra("projectId",selectProjectId);
                startActivityForResult(intent2,DEVICEEQUESTCODE);
                break;
            case R.id.task_start_time:
                startTimeCustomDatePicker.show(taskStartTime.getText().toString());
                break;
            case R.id.task_end_time:
                endTimeCustomDatePicker.show(taskEndTime.getText().toString());
                break;
            case R.id.task_templates:
                Intent intent4 = new Intent(context, ProjectTemplateActivity.class);
                startActivityForResult(intent4,TEMPLATEREQUESTCODE);
                break;
            case R.id.select_assign_users:
                if (selectProjectId == null){
                    ToastUtils.showToast("请先选择项目！");
                    return;
                }
                Intent intent5 = new Intent(context, SelectProjectUserListActivity.class);
                intent5.putExtra("projectId",selectProjectId);
                startActivityForResult(intent5,PROJECTUSERREQUESTCODE);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CRONREQUESTCODE:

                break;
            case PROJECTREQUESTCODE:
                if (resultCode == 1){
                    selectProjectId = data.getStringExtra("selectProjectId");
                    selectProjectName = data.getStringExtra("selectProjectName");
                    selectProject.setText(selectProjectName.toString());
                }
                break;
            case DEVICEEQUESTCODE:
                if (resultCode == 1){
                    selectDeviceId = data.getStringExtra("selectDeviceId");
                    selectDeviceName = data.getStringExtra("selectDeviceName");
                    selectProjectDevice.setText(selectDeviceName);
                }
                break;
            case TEMPLATEREQUESTCODE:
                if (resultCode==5){
                    String fankuijson = data.getStringExtra("fankuijson");//任务反馈项json
                }
                break;
            case PROJECTUSERREQUESTCODE:
                if (resultCode == 1){
                    selectUsers.clear();
                    selectUsers =(List<ProjectUserBean>) data.getSerializableExtra("selectlist");
                    String selectUserNames = "";
                    for (int i=0;i<selectUsers.size();i++){
                        selectUserNames += selectUsers.get(i).getUserName() + " ";
                    }
                    selectAssignUsers.setText(selectUserNames);
                }
                break;
        }
    }


    public static DeviceTemporaryTaskFargment newInstance() {
        return new DeviceTemporaryTaskFargment();
    }
}
