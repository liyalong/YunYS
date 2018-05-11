package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CronResultActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectForm;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectUserListActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateCycleTaskBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.cycle_task_used_time_m)
    EditText cycleTaskUsedTimeM;
    @BindView(R.id.cycle_task_used_time_s)
    EditText cycleTaskUsedTimeS;
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
    private String cycleSelectProjectId;
    private String cycleSelectProjectName;
    private List<ProjectUserBean> cycleSelectUsers = new ArrayList<>();

    private String cycleSelectFormId;
    private String cycleSelectFormName;

    UpdateCycleTaskBean cycleTaskForm;

    public static NoneDeviceCycleTaskFargment newInstance() {
        return new NoneDeviceCycleTaskFargment();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        initDatePicker();
        CreateNoneDeviceTaskAcitvity NoneDeviceTaskAcitvity = (CreateNoneDeviceTaskAcitvity) getActivity();

        List<WorkerBean> selectUsersFromWork = NoneDeviceTaskAcitvity.getSelectWorkList();
        if (selectUsersFromWork != null && selectUsersFromWork.size() > 0){
            String selectUserStr = "";
            for (int i=0;i<selectUsersFromWork.size();i++){
                selectUserStr += selectUsersFromWork.get(i).getName()+" ";
                ProjectUserBean projeceUser = new ProjectUserBean();
                projeceUser.setUserId(selectUsersFromWork.get(i).getUserId());
                projeceUser.setUserName(selectUsersFromWork.get(i).getName());
                projeceUser.setUserSex(selectUsersFromWork.get(i).getSex());
                cycleSelectUsers.add(projeceUser);
            }
            selectCycleAssignUsers.setText(selectUserStr);
        }
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
                Intent intent4 = new Intent(context, SelectProjectForm.class);
                startActivityForResult(intent4,TEMPLATEREQUESTCODE);
                break;
            case R.id.cycle_select_project:
                Intent intent1 = new Intent(context, SelectProjectActivity.class);
                startActivityForResult(intent1,PROJECTREQUESTCODE);
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

    public void initDatePicker() {
        String pattern = "yyyy-MM-dd HH:mm";
        String startTime = DateTimeDialogUtils.getNewData(pattern, 0);
        String endTime = DateTimeDialogUtils.getNewData(pattern, 1);
        String closeingData = DateTimeDialogUtils.getNewData(pattern,999);

        cycleTaskStartTime.setText(startTime);
        cycleTaskEndTime.setText(endTime);
        startCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                cycleTaskStartTime.setText(time);
            }
        }, startTime, closeingData);
        startCustomDatePicker.showSpecificTime(true);
        endCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                cycleTaskEndTime.setText(time);
            }
        }, startTime, closeingData);
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
            case TEMPLATEREQUESTCODE:
                if (resultCode == 1){
                    cycleSelectFormId = data.getStringExtra("selectFormId");
                    cycleSelectFormName = data.getStringExtra("selectFormName");
                    cycleTaskTemplates.setText(cycleSelectFormName);
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
    public Map<String,String> checkFormResult() {
        Map<String,String> checkStatus = new HashMap<>();
        cycleTaskForm = new UpdateCycleTaskBean();
        if (cycleSelectProjectId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择项目！");
            return checkStatus;
        }
        cycleTaskForm.setProjectId(cycleSelectProjectId);

        String cycletaskName = cycleTaskName.getText().toString().trim();
        if (cycletaskName.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务名称不能为空！");
            return checkStatus;
        }
        cycleTaskForm.setCycletaskName(cycletaskName);
        Boolean timeStatus = DateTimeDialogUtils.DateCompare(cycleTaskStartTime.getText().toString().trim()+":00",cycleTaskEndTime.getText().toString().trim()+":00");
        if (!timeStatus){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务结束时间不能小于开始时间！");
            return checkStatus;
        }
        cycleTaskForm.setCycletaskBegint(cycleTaskStartTime.getText().toString().trim()+":00");
        cycleTaskForm.setCycletaskEndt(cycleTaskEndTime.getText().toString().trim()+":00");
        String cron = cycleSelectCron.getText().toString().trim();
        if (cron == "*执行周期"){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择执行周期！");
            return checkStatus;
        }
        cycleTaskForm.setCorn(cron);

        int timeLength = cycleTaskUsedTime.getText().toString().isEmpty() ? 0 : Integer.parseInt(cycleTaskUsedTime.getText().toString());
        int timeLengthM = cycleTaskUsedTimeM.getText().toString().isEmpty() ? 0 : Integer.parseInt(cycleTaskUsedTimeM.getText().toString());
        int timeLengthS = cycleTaskUsedTimeS.getText().toString().isEmpty() ? 0 : Integer.parseInt(cycleTaskUsedTimeS.getText().toString());
        if (timeLength == 0 && timeLengthM == 0 && timeLengthS == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","请输入执行时长！");
            return checkStatus;
        }
        if (timeLengthM > 60 || timeLengthS > 60){
            checkStatus.put("status","error");
            checkStatus.put("msg","执行时长的分和秒不能超过60！");
            return checkStatus;
        }
        if (timeLength == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","请输入执行时长！");
            return checkStatus;
        }
        cycleTaskForm.setTimeLength(String.valueOf(timeLength));
        cycleTaskForm.setTimeLengthMin(String.valueOf(timeLengthM));
        cycleTaskForm.setTimeLengthSec(String.valueOf(timeLengthS));

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

        if (cycleSelectFormId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务反馈表单不能为空！");
            return checkStatus;
        }
        cycleTaskForm.setTemplateId(cycleSelectFormId);
        cycleTaskForm.setCycletaskType("3");
        checkStatus.put("status","success");
        checkStatus.put("msg","验证通过！");
        return checkStatus;
    }
    public UpdateCycleTaskBean getTaskFormData() {
        return cycleTaskForm;
    }
}
