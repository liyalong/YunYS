package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectForm;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectUserListActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateCycleTaskBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateTemporaryTaskBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class NoneDeviceTemporaryTaskFargment extends BaseFragement {
    @BindView(R.id.task_name)
    EditText taskName;
    @BindView(R.id.task_start_time)
    TextView taskStartTime;
    @BindView(R.id.task_end_time)
    TextView taskEndTime;
    @BindView(R.id.tasks_desc)
    EditText tasksDesc;
    @BindView(R.id.select_assign_users)
    TextView selectAssignUsers;
    @BindView(R.id.task_templates)
    TextView taskTemplates;
    @BindView(R.id.select_project)
    TextView selectProject;

    CustomDatePicker startTimeCustomDatePicker,endTimeCustomDatePicker;

    private final static int PROJECTREQUESTCODE = 1;
    private final static int DEVICEEQUESTCODE = 2;
    private final static int CRONREQUESTCODE = 3;
    private final static int TEMPLATEREQUESTCODE = 4;
    private final static int PROJECTUSERREQUESTCODE = 5;

    private String selectProjectId;
    private String selectProjectName;
    private List<ProjectUserBean> selectUsers = new ArrayList<>();
    private String selectFormId;
    private String selectFormName;
    private UpdateTemporaryTaskBean taskForm;

    private String editTemporaryTaskId;


    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        initDatePicker();
        CreateNoneDeviceTaskAcitvity createNoneDeviceTaskAcitvity = (CreateNoneDeviceTaskAcitvity) getActivity();
        this.editTemporaryTaskId = createNoneDeviceTaskAcitvity.getEditTaskId();

        List<WorkerBean> selectUsersFromWork = createNoneDeviceTaskAcitvity.getSelectWorkList();
        if (selectUsersFromWork != null && selectUsersFromWork.size() > 0){
            String selectUserStr = "";
            for (int i=0;i<selectUsersFromWork.size();i++){
                selectUserStr += selectUsersFromWork.get(i).getName()+" ";
                ProjectUserBean projeceUser = new ProjectUserBean();
                projeceUser.setUserId(selectUsersFromWork.get(i).getUserId());
                projeceUser.setUserName(selectUsersFromWork.get(i).getName());
                projeceUser.setUserSex(selectUsersFromWork.get(i).getSex());
                selectUsers.add(projeceUser);
            }
            selectAssignUsers.setText(selectUserStr);
        }
    }

    @Override
    public void initAfter() {

    }
    //初始化日期时间选择插件
    private void initDatePicker() {
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
        },startTime,closeingData);
        endTimeCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                taskEndTime.setText(time);
            }
        },startTime,closeingData);

    }
    @Override
    public int bindLayout() {
        return R.layout.none_device_temporary_task;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        taskStartTime.setOnClickListener(this);
        taskEndTime.setOnClickListener(this);
        taskTemplates.setOnClickListener(this);
        selectProject.setOnClickListener(this);
        selectAssignUsers.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.task_start_time:
                startTimeCustomDatePicker.show(taskStartTime.getText().toString());
                break;
            case R.id.task_end_time:
                endTimeCustomDatePicker.show(taskEndTime.getText().toString());
                break;
            case R.id.task_templates:
                Intent intent4 = new Intent(context, SelectProjectForm.class);
                startActivityForResult(intent4,TEMPLATEREQUESTCODE);
                break;
            case R.id.select_project:
                //选择项目
                Intent intent1 = new Intent(context, SelectProjectActivity.class);
                startActivityForResult(intent1,PROJECTREQUESTCODE);
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
            case TEMPLATEREQUESTCODE:
                if (resultCode == 1){
                    selectFormId = data.getStringExtra("selectFormId");
                    selectFormName = data.getStringExtra("selectFormName");
                    taskTemplates.setText(selectFormName);
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
        if (selectProjectId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择项目！");
            return checkStatus;
        }
        taskForm.setProjectId(selectProjectId);
        String releaseName = taskName.getText().toString().trim();
        if (releaseName.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务名称不能为空！");
            return checkStatus;
        }
        taskForm.setReleaseName(releaseName);
        Boolean timeStatus = DateTimeDialogUtils.DateCompare(taskStartTime.getText().toString()+":00",taskEndTime.getText().toString()+":00");
        if (!timeStatus){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务结束时间不能小于开始时间！");
            return checkStatus;
        }
        taskForm.setReleaseBegint(taskStartTime.getText().toString()+":00");
        taskForm.setReleaseEndt(taskEndTime.getText().toString()+":00");

        String releaseRemark = tasksDesc.getText().toString().trim();
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
        if (selectFormId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务反馈表单不能为空！");
            return checkStatus;
        }
        taskForm.setReleaseBaseformId(selectFormId);
        taskForm.setReleaseTaskType(2);
        checkStatus.put("status","success");
        checkStatus.put("msg","完成验证！");
        return checkStatus;
    }


    public UpdateTemporaryTaskBean getFormData() {
        return taskForm;
    }
}
