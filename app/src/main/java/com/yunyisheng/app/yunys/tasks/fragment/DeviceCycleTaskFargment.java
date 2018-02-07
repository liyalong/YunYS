package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CronResultActivity;
import com.yunyisheng.app.yunys.tasks.activity.ProjectTemplateActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectDeviceActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectUserListActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateCycleTaskBean;
import com.yunyisheng.app.yunys.tasks.model.TaskDetailModel;
import com.yunyisheng.app.yunys.tasks.present.DeviceCycleTaskPresent;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liyalong on 2018/1/13.
 */

public class DeviceCycleTaskFargment extends BaseFragement<DeviceCycleTaskPresent> {

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

    private String cycleSelectProjectId;
    private String cycleSelectProjectName;

    private String cycleSelectDeviceId;
    private String cycleSelectDeviceName;
    private List<ProjectUserBean> cycleSelectUsers = new ArrayList<>();

    private String cycleFeedbackJSON;

    UpdateCycleTaskBean cycleTaskForm;
    private String cycleReleaseTaskId;
    private String cycleProjectId;

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        initDatePicker();
        CreateDeviceTaskAcitvity DeviceTaskAcitvity = (CreateDeviceTaskAcitvity) getActivity();
        this.cycleReleaseTaskId = String.valueOf(DeviceTaskAcitvity.getTaskEditId());
        this.cycleProjectId = DeviceTaskAcitvity.getProjectId();
        //从通讯录安排工作跳转来的人员
        List<WorkerBean> selectUsersFromWork = DeviceTaskAcitvity.getSelectWorkList();
        if (selectUsersFromWork!=null&&selectUsersFromWork.size() > 0){
            String selectCycleUserStr = "";
            for (int i=0;i<selectUsersFromWork.size();i++){
                selectCycleUserStr += selectUsersFromWork.get(i).getName()+" ";
                ProjectUserBean projeceUser = new ProjectUserBean();
                projeceUser.setUserId(selectUsersFromWork.get(i).getUserId());
                projeceUser.setUserName(selectUsersFromWork.get(i).getName());
                projeceUser.setUserSex(selectUsersFromWork.get(i).getSex());
                cycleSelectUsers.add(projeceUser);
            }
            selectCycleAssignUsers.setText(selectCycleUserStr);
        }

        if (cycleReleaseTaskId != null && cycleReleaseTaskId != "null"){
            getP().getCycleTaskInfo(cycleProjectId,cycleReleaseTaskId);
        }
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.device_cycle_task_fargment;
    }

    @Override
    public DeviceCycleTaskPresent bindPresent() {
        return new DeviceCycleTaskPresent();
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
                if (cycleSelectProjectId == null){
                    ToastUtils.showToast("请先选择项目！");
                    return;
                }
                Intent intent2 = new Intent(context, SelectProjectDeviceActivity.class);
                intent2.putExtra("projectId",cycleSelectProjectId);
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
                if (cycleSelectProjectId == null){
                    ToastUtils.showToast("请先选择项目！");
                    return;
                }
                Intent intent5 = new Intent(context, SelectProjectUserListActivity.class);
                intent5.putExtra("projectId",cycleSelectProjectId);
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
                    cycleSelectProjectId = data.getStringExtra("selectProjectId");
                    cycleSelectProjectName = data.getStringExtra("selectProjectName");
                    cycleSelectProject.setText(cycleSelectProjectName.toString());
                }
                break;
            case DEVICEEQUESTCODE:
                if (resultCode == 1){
                    cycleSelectDeviceId = data.getStringExtra("selectDeviceId");
                    cycleSelectDeviceName = data.getStringExtra("selectDeviceName");
                    cycleSelectProjectDevice.setText(cycleSelectDeviceName);
                }
                break;
            case TEMPLATEREQUESTCODE:
                if (resultCode==5){
                    cycleFeedbackJSON = data.getStringExtra("fankuijson");//任务反馈项json
                    cycleTaskTemplates.setText("任务反馈项（已添加）");
                }
                break;
            case PROJECTUSERCODE:
                if (resultCode == 1){
                    cycleSelectUsers.clear();
                    cycleSelectUsers =(List<ProjectUserBean>) data.getSerializableExtra("selectlist");
                    String selectUserNames = "";
                    for (int i=0;i<cycleSelectUsers.size();i++){
                        selectUserNames += cycleSelectUsers.get(i).getUserName() + " ";
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

    public Map<String,String> checkFormResult() {
        Map<String,String> checkStatus = new HashMap<>();
        cycleTaskForm = new UpdateCycleTaskBean();
        if (cycleSelectProjectId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择项目！");
            return checkStatus;
        }
        cycleTaskForm.setProjectId(cycleSelectProjectId);
        if (cycleSelectDeviceId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择设备！");
            return checkStatus;
        }
        cycleTaskForm.setEquipmentId(cycleSelectDeviceId);
        String cycletaskName = cycleTaskName.getText().toString().trim();
        if (cycletaskName.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务名称不能为空！");
            return checkStatus;
        }
        cycleTaskForm.setCycletaskName(cycletaskName);
        cycleTaskForm.setCycletaskBegint(cycleTaskStartTime.getText().toString().trim()+":00");
        cycleTaskForm.setCycletaskEndt(cycleTaskEndTime.getText().toString().trim()+":00");
        String cron = cycleSelectCron.getText().toString().trim();
        if (cron == "*执行周期"){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择执行周期！");
            return checkStatus;
        }
        cycleTaskForm.setCorn(cron);
        String timeLength = cycleTaskUsedTime.getText().toString().trim();
        if (timeLength.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","请输入执行时长！");
            return checkStatus;
        }
        cycleTaskForm.setTimeLength(timeLength);
        if (cycleTasksType.isChecked()){
            cycleTaskForm.setCycletaskStat("1");
        }else {
            cycleTaskForm.setCycletaskStat("2");
        }
        String cycletaskRemark = cycleTaskDesc.getText().toString().trim();
        if (cycletaskRemark.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务备注不能为空！");
            return checkStatus;
        }
        cycleTaskForm.setCycletaskRemark(cycletaskRemark);
        if (cycleSelectUsers.size() > 0){
            List<Integer> listStr = new ArrayList<>();
            //List<Map<String,String>> listStr = new ArrayList<>();
            for (int i=0;i<cycleSelectUsers.size();i++){
                listStr.add(cycleSelectUsers.get(i).getUserId());
//                Map<String,String> user = new HashMap<>();
//                user.put("userId", String.valueOf(cycleSelectUsers.get(i).getUserId()));
//                listStr.add(user);
            }
            cycleTaskForm.setUserIdStr(JSON.toJSONString(listStr));
        }

        if (cycleFeedbackJSON == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务反馈项不能为空！");
            return checkStatus;
        }
        cycleTaskForm.setFeedbackJSON(cycleFeedbackJSON.toString());
        cycleTaskForm.setCycletaskType("1");
        checkStatus.put("status","success");
        checkStatus.put("msg","验证通过！");
        return checkStatus;
    }
    public UpdateCycleTaskBean getTaskFormData() {
        return cycleTaskForm;
    }

    public void setDetailData(TaskDetailModel taskDetailModel) {

    }
}
