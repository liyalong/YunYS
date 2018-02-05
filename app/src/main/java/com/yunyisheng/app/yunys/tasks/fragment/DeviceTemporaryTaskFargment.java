package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.ProjectTemplateActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectDeviceActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectUserListActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateTemporaryTaskBean;
import com.yunyisheng.app.yunys.tasks.present.DeviceTemporaryTaskPresent;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class DeviceTemporaryTaskFargment extends BaseFragement<DeviceTemporaryTaskPresent> {

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
    @BindView(R.id.task_desc)
    EditText taskDesc;

    CustomDatePicker startTimeCustomDatePicker,endTimeCustomDatePicker;

    private String selectProjectId;
    private String selectProjectName;

    private String selectDeviceId;
    private String selectDeviceName;
    private List<ProjectUserBean> selectUsers = new ArrayList<>();

    private UpdateTemporaryTaskBean taskForm;

    private String feedbackJSON;
    private String taskId;
    private String projectId;

    @Override
    public void initView() {
        ButterKnife.bind(this, context);

        initDatePicker();

        CreateDeviceTaskAcitvity createDeviceTaskAcitvity = (CreateDeviceTaskAcitvity) getActivity();
        this.taskId = createDeviceTaskAcitvity.getTaskEditId();
        this.projectId = createDeviceTaskAcitvity.getProjectId();
        if (taskId != null){
            getP().getTexporaryTaskInfo(projectId,taskId);
        }
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
    public DeviceTemporaryTaskPresent bindPresent() {
        return new DeviceTemporaryTaskPresent();
    }

    public void initEditTaskInfo(){

    };
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
                    feedbackJSON = data.getStringExtra("fankuijson");//任务反馈项json
                    taskTemplates.setText("任务反馈项（已添加）");
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
    public Map<String,String> checkFormResult(){
        Map<String,String> checkStatus = new HashMap<>();
        taskForm = new UpdateTemporaryTaskBean();

        if (selectProjectId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择项目！");
            return checkStatus;
        }
        taskForm.setProjectId(selectProjectId);
        if (selectDeviceId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择设备！");
            return checkStatus;
        }
        taskForm.setEquipmentId(selectDeviceId);

        String releaseName = taskName.getText().toString().trim();
        if (releaseName.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务名称不能为空！");
            return checkStatus;
        }
        taskForm.setReleaseName(releaseName);
        taskForm.setReleaseBegint(taskStartTime.getText().toString()+":00");
        taskForm.setReleaseEndt(taskEndTime.getText().toString()+":00");

        String releaseRemark = taskDesc.getText().toString().trim();
        if (releaseRemark.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务备注不能为空！");
            return checkStatus;
        }
        taskForm.setReleaseRemark(releaseRemark);

        if (selectUsers.size() > 0){
            List<Map<String,String>> listStr = new ArrayList<>();
            for (int i=0;i<selectUsers.size();i++){
                Map<String,String> user = new HashMap<>();
                user.put("userId", String.valueOf(selectUsers.get(i).getUserId()));
                listStr.add(user);
            }
            taskForm.setListStr(JSON.toJSONString(listStr));
        }
        if (feedbackJSON == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务反馈项不能为空！");
            return checkStatus;
        }
        taskForm.setFeedbackJSON(feedbackJSON);
        taskForm.setReleaseTaskType(1);
        checkStatus.put("status","success");
        checkStatus.put("msg","完成验证！");
        return checkStatus;
    }
    public UpdateTemporaryTaskBean getFormData() {
        return taskForm;
    }


    public void setDetail(ScheduleDetailBean.RespBodyBean.TaskBean task,
                          List<ScheduleDetailBean.RespBodyBean.TaskBackBean> taskback,
                          ScheduleDetailBean.RespBodyBean.FormBean form) {
        XLog.d(task.toString());
        selectProjectId = task.getProjectId();

        selectDeviceId = String.valueOf(task.getEquipmentId());

        selectProject.setText(task.getProjectName());
        selectProject.setClickable(false);
        selectProjectDevice.setText(task.getEquipmentName());
        selectProjectDevice.setClickable(false);
        taskName.setText(task.getReleaseName());
        taskStartTime.setText(task.getReleaseBegint().substring(0,16));
        taskEndTime.setText(task.getReleaseEndt().substring(0,16));
        if (task.getReleaseRemark() != null){
            taskDesc.setText(task.getReleaseRemark().toString());
        }
        taskTemplates.setClickable(false);
        selectAssignUsers.setClickable(false);


    }
}
