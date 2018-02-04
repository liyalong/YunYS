package com.yunyisheng.app.yunys.tasks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectActivity;
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


    public static NoneDeviceTemporaryTaskFargment newInstance() {
        return new NoneDeviceTemporaryTaskFargment();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        initDatePicker();
    }

    @Override
    public void initAfter() {

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
                ToastUtils.showToast("选择反馈项");
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
}
