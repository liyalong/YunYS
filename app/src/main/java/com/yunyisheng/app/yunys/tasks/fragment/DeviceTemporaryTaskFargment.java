package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.project.bean.FeedbackJSONBean;
import com.yunyisheng.app.yunys.schedule.model.RenWuFanKuiDetailBean;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.ProjectTemplateActivity;
import com.yunyisheng.app.yunys.tasks.activity.RadioSelectUserActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectDeviceActivity;
import com.yunyisheng.app.yunys.tasks.bean.FeedBackItemBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateTemporaryTaskBean;
import com.yunyisheng.app.yunys.tasks.model.CycleTaskDetailModel;
import com.yunyisheng.app.yunys.tasks.model.ReleaseTaskDetailModel;
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
    private String selectUserId;
    private String selectUserName;
    private String selectAssignUserId;

    private UpdateTemporaryTaskBean taskForm;

    private String feedbackJSON;
    private String releaseTaskId;
    private String projectId;

    private String feedbackBacknum;

    @Override
    public void initView() {
        ButterKnife.bind(this, context);

        initDatePicker();

        CreateDeviceTaskAcitvity createDeviceTaskAcitvity = (CreateDeviceTaskAcitvity) getActivity();
        this.releaseTaskId = createDeviceTaskAcitvity.getTaskEditId();
        this.projectId = createDeviceTaskAcitvity.getProjectId();

        List<WorkerBean> selectUsersFromWork = createDeviceTaskAcitvity.getSelectWorkList();
        if (selectUsersFromWork!=null&&selectUsersFromWork.size() > 0){

            selectUserId = String.valueOf(selectUsersFromWork.get(0).getUserId());
            selectUserName = selectUsersFromWork.get(0).getName();

            selectAssignUsers.setText(selectUserName);
        }
        //从设备页面过来的创建任务
        if (createDeviceTaskAcitvity.getFromPageType() == 3){
            selectProjectId = createDeviceTaskAcitvity.getProjectId();
            selectDeviceId = createDeviceTaskAcitvity.getDeviceId();
            selectProject.setText(createDeviceTaskAcitvity.getProjectName());
            selectProjectDevice.setText(createDeviceTaskAcitvity.getDeviceName());
        }
        if (releaseTaskId != null && releaseTaskId != "null"){
            getP().getReleaseTaskDetail(projectId,releaseTaskId);
        }
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

                Intent intent1 = new Intent(context, SelectProjectActivity.class);
                intent1.putExtra("selectUserIdFromTXL",selectUserId);
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
                if (feedbackJSON != null){
//                    if (releaseTaskId == null){
                        intent4.putExtra("fankuijson_create",feedbackJSON);
//                    }else {
//                        intent4.putExtra("fankuijson_edit",feedbackJSON);
//                    }

                }
                startActivityForResult(intent4,TEMPLATEREQUESTCODE);
                break;
            case R.id.select_assign_users:
                if (selectProjectId == null){
                    ToastUtils.showToast("请先选择项目！");
                    return;
                }
                Intent intent5 = new Intent(context, RadioSelectUserActivity.class);
                intent5.putExtra("projectId",selectProjectId);
                intent5.putExtra("fromPageTitle","选择分派人员");
                intent5.putExtra("selectUserId",selectUserId);
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
                    selectDeviceId = null;
                    selectProjectDevice.setText("*选择设备");
                    selectUserId = null;
                    selectAssignUsers.setText("请选择分配人员");
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

                    selectAssignUserId = data.getStringExtra("selectUserId");
                    selectUserName = data.getStringExtra("selectUserName");
                    if (selectAssignUserId.equals("-1")){
                        selectAssignUserId = null;
                        selectAssignUsers.setText("选择分配人员列表");
                    }else {
                        selectAssignUsers.setText(selectUserName);
                    }

                }
                break;
        }
    }
    public Map<String,String> checkFormResult(){
        Map<String,String> checkStatus = new HashMap<>();
        taskForm = new UpdateTemporaryTaskBean();

        if (selectProjectId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择项目");
            return checkStatus;
        }
        taskForm.setProjectId(selectProjectId);
        if (selectDeviceId == null){
            checkStatus.put("status","error");
            checkStatus.put("msg","请选择设备");
            return checkStatus;
        }
        taskForm.setEquipmentId(selectDeviceId);

        String releaseName = taskName.getText().toString().trim();
        if (releaseName.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务名称不能为空");
            return checkStatus;
        }
        taskForm.setReleaseName(releaseName);
        Boolean timeStatus = DateTimeDialogUtils.DateCompare(taskStartTime.getText().toString()+":00",taskEndTime.getText().toString()+":00");
        if (!timeStatus){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务结束时间不能小于开始时间");
            return checkStatus;
        }
        taskForm.setReleaseBegint(taskStartTime.getText().toString()+":00");
        taskForm.setReleaseEndt(taskEndTime.getText().toString()+":00");

        String releaseRemark = taskDesc.getText().toString().trim();
        if (releaseRemark.length() == 0){
            checkStatus.put("status","error");
            checkStatus.put("msg","任务备注不能为空");
            return checkStatus;
        }
        taskForm.setReleaseRemark(releaseRemark);

        if (selectAssignUserId != null){
            List<Map<String,String>> listStr = new ArrayList<>();
            Map<String,String> user = new HashMap<>();
            user.put("userId", selectAssignUserId);
            listStr.add(user);
            taskForm.setListStr(JSON.toJSONString(listStr));
            if (releaseTaskId != null){
                taskForm.setUserlist(JSON.toJSONString(listStr));
            }
        }
        if (releaseTaskId == null){
            if (feedbackJSON == null){
                checkStatus.put("status","error");
                checkStatus.put("msg","任务反馈项不能为空");
                return checkStatus;
            }
            taskForm.setFeedbackJSON(feedbackJSON);
        }else {
            taskForm.setFeedbackBacknum(feedbackBacknum);
            taskForm.setReleaseId(releaseTaskId);
            taskForm.setFeedbackJSON(feedbackJSON);
        }

        taskForm.setReleaseTaskType(1);
        checkStatus.put("status","success");
        checkStatus.put("msg","完成验证");
        return checkStatus;
    }
    public UpdateTemporaryTaskBean getFormData() {
        return taskForm;
    }


    public void setDetail(ReleaseTaskDetailModel releaseTaskDetailModel) {
        ReleaseTaskDetailModel.ReleaseTask task = releaseTaskDetailModel.getRespBody();

        selectProjectId = task.getProjectId();

        selectDeviceId = String.valueOf(task.getEquipmentId());

        feedbackBacknum = task.getFeedbackBacknum();

        selectProject.setText(task.getProjectName()+"(不可编辑)");
        selectProject.setClickable(false);
        selectProjectDevice.setText(task.getEquipmentName()+"(不可编辑)");
        selectProjectDevice.setClickable(false);
        taskName.setText(task.getReleaseName());
        taskStartTime.setText(task.getReleaseBegint().substring(0,16));
        taskEndTime.setText(task.getReleaseEndt().substring(0,16));
        if (task.getReleaseRemark() != null){
            taskDesc.setText(task.getReleaseRemark().toString());
        }
        if (task.getItemList() != null && task.getItemList().size() > 0){
            taskTemplates.setText("任务反馈项（已添加）");
            try {
                List<CycleTaskDetailModel.taskDetail.FeedbackItemBean> itemlist = task.getItemList();
                List<FeedbackJSONBean> feedbackJSONBeans = new ArrayList<>();
                for (int i=0;i<itemlist.size();i++){
                    FeedbackJSONBean feedbackJSONBean = new FeedbackJSONBean();
                    feedbackJSONBean.setFeedbackName(itemlist.get(i).getFeedbackName());
                    feedbackJSONBean.setFeedbackType(itemlist.get(i).getFeedbackType());
                    List<RenWuFanKuiDetailBean.RespBodyBean.Valueitem> modelArray = itemlist.get(i).getModelArray();
                    List<FeedbackJSONBean.BackModel> modelList = new ArrayList<>();
                    for(int j=0;j<modelArray.size();j++){
                        FeedbackJSONBean.BackModel model = new FeedbackJSONBean.BackModel();
                        model.setDynamicTypeName(modelArray.get(j).getDynamic_type_name());
                        model.setIndex(modelArray.get(j).getIndex());
                        modelList.add(model);
                    }
                    feedbackJSONBeans.add(feedbackJSONBean);
                    feedbackJSONBean.setModel(modelList);
                }
                feedbackJSON = JSON.toJSONString(feedbackJSONBeans);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            taskTemplates.setText("任务反馈项（未添加）");
        }
    }
}
