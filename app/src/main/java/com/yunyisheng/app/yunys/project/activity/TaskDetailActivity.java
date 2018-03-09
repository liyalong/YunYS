package com.yunyisheng.app.yunys.project.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.model.TaskMessageEvent;
import com.yunyisheng.app.yunys.schedule.model.PositionMessageEvent;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.SelectProjectUserListActivity;
import com.yunyisheng.app.yunys.tasks.adapter.TaskBackListAdapter;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.tasks.present.TaskDetailPresent;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/31.
 */

public class TaskDetailActivity extends BaseActivity<TaskDetailPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.banner_title)
    TextView bannerTitle;
    @BindView(R.id.include_title_name)
    TextView includeTitleName;
    @BindView(R.id.task_name)
    TextView taskName;
    @BindView(R.id.task_status)
    TextView taskStatus;
    @BindView(R.id.task_status_istimeout)
    TextView taskStatusIstimeout;
    @BindView(R.id.create_time)
    TextView createTime;
    @BindView(R.id.create_user)
    TextView createUser;
    @BindView(R.id.do_user)
    TextView doUser;
    @BindView(R.id.do_user_layout)
    LinearLayout doUserLayout;
    @BindView(R.id.task_desc)
    TextView taskDesc;
    @BindView(R.id.project_name)
    TextView projectName;
    @BindView(R.id.device_name)
    TextView deviceName;
    @BindView(R.id.device_task_box)
    LinearLayout deviceTaskBox;
    @BindView(R.id.edit_task)
    TextView editTask;
    @BindView(R.id.remove_task)
    TextView removeTask;
    @BindView(R.id.assign_task)
    TextView assignTask;
    @BindView(R.id.claim_task)
    TextView claimTask;
    @BindView(R.id.do_task)
    TextView doTask;
    @BindView(R.id.back_task)
    TextView backTask;
    @BindView(R.id.begin_time)
    TextView beginTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.task_back_info)
    TextView lookTaskBackInfo;
    @BindView(R.id.task_child_list)
    TextView taskChildList;
    @BindView(R.id.caozuo_box)
    LinearLayout caozuoBox;
    @BindView(R.id.back_info_box)
    LinearLayout backInfoBox;
    @BindView(R.id.back_info_list)
    ListView backInfoList;
    private String taskId;
    private int userId;
    private String taskType;
    private Dialog taskBackInfoDialog;
    private String projectId;
    private ScheduleDetailBean.RespBodyBean.TaskBean task;

    private int fromPage;  //从哪个页面跳转过来的，1。任务池待认领列表，2任务池待完成列表，3我发布任务的子列表,4我的日程，5项目日程，6通讯录日程

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent=getIntent();
        taskId = intent.getStringExtra("taskId");
        userId = intent.getIntExtra("userId",0);
        taskType = intent.getStringExtra("taskType");
        int fromPage = intent.getIntExtra("fromPage",1);
        this.fromPage=fromPage;
        projectId = intent.getStringExtra("projectId");
        getP().getTask(projectId,taskId,taskType,userId);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_task_detail;
    }

    @Override
    public TaskDetailPresent bindPresent() {
        return new TaskDetailPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        editTask.setOnClickListener(this);
        removeTask.setOnClickListener(this);
        assignTask.setOnClickListener(this);
        claimTask.setOnClickListener(this);
        doTask.setOnClickListener(this);
        backTask.setOnClickListener(this);
        lookTaskBackInfo.setOnClickListener(this);
        taskChildList.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.edit_task:
                //编辑任务
                Router.newIntent(context)
                        .to(CreateDeviceTaskAcitvity.class)
                        .putInt("taskType",1)
                        .putString("editTaskId", String.valueOf(task.getTaskId()))
                        .putString("projectId",projectId)
                        .launch();
                break;
            case R.id.remove_task:
                //撤销任务
                getP().repealTask(projectId, String.valueOf(task.getReleaseId()));
                break;
            case R.id.assign_task:
                //分派任务
                Intent intent = new Intent(context, SelectProjectUserListActivity.class);
                intent.putExtra("projectId",projectId);
                intent.putExtra("releaseId",task.getReleaseId());
                startActivityForResult(intent,5);
                break;
            case R.id.claim_task:
                //认领任务
                getP().claimTask(String.valueOf(task.getTaskId()));
                break;
            case R.id.do_task:
                //执行任务
                if (task.getReleaseFormId() == null){
                    Router.newIntent(context)
                            .to(RenwuFankuiFormActivity.class)
                            .putInt("taskid", Integer.parseInt(String.valueOf(task.getTaskId())))
                            .putString("projectId",projectId)
                            .putInt("type",1)
                            .launch();
                }else {
                    Router.newIntent(context)
                            .to(DynamicFormActivity.class)
                            .putInt("type", task.getReleaseTaskType())
                            .putString("scheduleid", String.valueOf(task.getTaskId()))
                            .putInt("userId",userId)
                            .putInt("othertype",1)
                            .launch();
                }
                break;
            case R.id.back_task:
                //回退任务
                createTaskBackInfoDialog(context,task);
                break;
            case R.id.task_back_info:
                if (task.getReleaseFormId() == null){
                    Router.newIntent(context)
                            .to(RenwuFankuiFormActivity.class)
                            .putInt("taskid", Integer.parseInt(String.valueOf(task.getTaskId())))
                            .putString("projectId",projectId)
                            .putInt("type",2)
                            .launch();
                }else {
                    Router.newIntent(context)
                            .to(DynamicFormActivity.class)
                            .putInt("type", task.getReleaseTaskType())
                            .putString("scheduleid", String.valueOf(task.getTaskId()))
                            .putInt("userId",userId)
                            .putInt("othertype",2)
                            .launch();
                }
                //查看执行情况
                break;
            case R.id.task_child_list:
                //已发布任务查看子任务列表
                break;
        }

    }
    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TaskMessageEvent messageEvent){
        String position = messageEvent.getPosition();
        if (position.equals("updateOK")){
            getP().getTask(projectId,taskId,taskType,userId);
        }

    }
    public void setDetail(ScheduleDetailBean.RespBodyBean.TaskBean task,
                          List<ScheduleDetailBean.RespBodyBean.TaskBackBean> taskback,
                          ScheduleDetailBean.RespBodyBean.FormBean form) {
            this.task = task;
            initTaskBtn(task,fromPage);
            //任务名称
            taskName.setText(task.getReleaseName().toString());
            if (task.getTaskCreatet() != null){
                createTime.setText(task.getTaskCreatet().toString());
            }else {
                createTime.setText("");
            }
            //任务创建人
            createUser.setText(task.getTaskUserName().toString());
            //任务描述
            if (task.getReleaseRemark() != null){
                taskDesc.setText(task.getReleaseRemark().toString());
            }else {
                taskDesc.setText("");
            }

            //任务开始时间
            beginTime.setText(task.getReleaseBegint().toString());
            //任务结束时间
            endTime.setText(task.getReleaseEndt().toString());



            if (taskback.size() > 0){
                //有回退意见时进行显示
                backInfoBox.setVisibility(View.VISIBLE);
                TaskBackListAdapter backAdapter = new TaskBackListAdapter(context,taskback);
                backInfoList.setAdapter(backAdapter);
            }
            //项目设备 显示对应的项目和设备名称
            if (task.getReleaseTaskType() == 1) {
                projectName.setText(task.getProjectName().toString());
                deviceName.setText(task.getEquipmentName().toString());
                deviceTaskBox.setVisibility(View.VISIBLE);
            } else {
                deviceTaskBox.setVisibility(View.GONE);
            }
            if (task.getTaskStat() != 2) {
                //判断超时，
                String now = DateTimeDialogUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
                if (DateTimeDialogUtils.DateCompare(task.getReleaseEndt(), now) == true) {
                    taskStatusIstimeout.setVisibility(View.VISIBLE);
                }else {
                    taskStatusIstimeout.setVisibility(View.GONE);
                }
            }else {
                String now = DateTimeDialogUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
                if (DateTimeDialogUtils.DateCompare(task.getTaskSubmitTime(), now) == true) {
                    taskStatusIstimeout.setVisibility(View.VISIBLE);
                }else {
                    taskStatusIstimeout.setVisibility(View.GONE);
                }
            }


    }

    /**
     * 根据任务的不同来源进行显示不同的按钮
     *
     * @param task
     */
    public void initTaskBtn(ScheduleDetailBean.RespBodyBean.TaskBean task ,int fromPage) {
        Integer thisUserid = SharedPref.getInstance(context).getInt("userid",0);
        switch (fromPage){
            case 1:
                taskStatus.setText(R.string.task_status_1);
                doUserLayout.setVisibility(View.GONE);
                claimTask.setVisibility(View.VISIBLE);
                break;
            case 2:
            case 4:
                if (task.getTaskStat() == 0) {
                    taskStatus.setText(R.string.task_status_1);
                    doUserLayout.setVisibility(View.GONE);
                    claimTask.setVisibility(View.VISIBLE);
                }else if (task.getTaskStat() == 1){
                    taskStatus.setText(R.string.task_status_2);
                    doUser.setText(task.getTaskUserName().toString());
                    doTask.setVisibility(View.VISIBLE);
                    backTask.setVisibility(View.VISIBLE);
                }else if (task.getTaskStat() == 3){
                    caozuoBox.setVisibility(View.GONE);
                }else if (task.getTaskStat() == 2){
                    taskStatus.setText(R.string.task_status_3);
                    doUserLayout.setVisibility(View.VISIBLE);
                    doUser.setText(task.getTaskUserName().toString());
                    caozuoBox.setVisibility(View.VISIBLE);
                    lookTaskBackInfo.setVisibility(View.VISIBLE);
                }
                break;
            case 3:
            case 9:
                if (task.getTaskStat() == 0){
                    taskStatus.setText(R.string.task_status_1);
                    caozuoBox.setVisibility(View.GONE);
                    doUserLayout.setVisibility(View.GONE);

                }else if (task.getTaskStat() == 1){
                    taskStatus.setText(R.string.task_status_2);
                    caozuoBox.setVisibility(View.GONE);
                    doUserLayout.setVisibility(View.VISIBLE);
                    doUser.setText(task.getTaskUserName().toString());
                }else if (task.getTaskStat() == 2){
                    taskStatus.setText(R.string.task_status_3);
                    doUserLayout.setVisibility(View.VISIBLE);
                    doUser.setText(task.getTaskUserName().toString());
                    caozuoBox.setVisibility(View.VISIBLE);
                    lookTaskBackInfo.setVisibility(View.VISIBLE);
                }else if (task.getTaskStat() == 3){
                    taskStatus.setText(R.string.task_status_6);
                    caozuoBox.setVisibility(View.GONE);
                }
                break;
//            case 4:
//                taskStatus.setText(R.string.task_status_2);
//                doUser.setText(task.getTaskUserName().toString());
//                doTask.setVisibility(View.VISIBLE);
//                backTask.setVisibility(View.VISIBLE);
//                break;
            case 5:
                if (task.getTaskStat() == 0) {
                    taskStatus.setText(R.string.task_status_1);
                    doUserLayout.setVisibility(View.GONE);
                    claimTask.setVisibility(View.VISIBLE);
                }else if (task.getTaskStat() == 1){
                    taskStatus.setText(R.string.task_status_2);
                    doUser.setText(task.getTaskUserName().toString());
                    doTask.setVisibility(View.VISIBLE);
                    backTask.setVisibility(View.VISIBLE);
                }else if (task.getTaskStat() == 3){
                    caozuoBox.setVisibility(View.GONE);
                }else if (task.getTaskStat() == 2){
                    taskStatus.setText(R.string.task_status_3);
                    doUserLayout.setVisibility(View.VISIBLE);
                    doUser.setText(task.getTaskUserName().toString());
                    caozuoBox.setVisibility(View.VISIBLE);
                    lookTaskBackInfo.setVisibility(View.VISIBLE);
                }
                break;
            case 6:
                caozuoBox.setVisibility(View.GONE);
                break;
        }
    }
    //回退意见表单
    private void createTaskBackInfoDialog(final Activity activity, final ScheduleDetailBean.RespBodyBean.TaskBean task){
        if (taskBackInfoDialog == null){
            taskBackInfoDialog = new Dialog(activity,R.style.dialog_bottom_full);
        }
        taskBackInfoDialog.setCanceledOnTouchOutside(true);
        taskBackInfoDialog.setCancelable(true);
        Window window = taskBackInfoDialog.getWindow();
        window.setGravity(Gravity.CENTER);

        View v =View.inflate(activity,R.layout.task_back_info,null);
        final EditText taskBackInfo = v.findViewById(R.id.task_back_info);
        TextView backTaskSubmit = v.findViewById(R.id.back_task_submit);
        TextView backTaskClose = v.findViewById(R.id.back_task_close);

        backTaskSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String backInfo = taskBackInfo.getText().toString().trim();
                if (backInfo.length() == 0){
                    ToastUtils.showToast("回退意见不能为空！");
                    return;
                }
                getP().backTask(String.valueOf(task.getTaskId()),backInfo);
            }
        });
        backTaskClose.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                taskBackInfoDialog.hide();
            }
        });
        window.setContentView(v);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        taskBackInfoDialog.show();
    }
    public void checkTaskBack(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0){
            taskBackInfoDialog.hide();
            ToastUtils.showToast("退回成功！");
            getP().getTask(projectId,taskId,taskType,userId);
        }
    }
    public void checkClaimTaskStatus(BaseModel baseModel){
        if (baseModel.getRespCode() == 0){
            ToastUtils.showToast("认领成功！");
            getP().getTask(projectId,taskId,taskType,userId);
        }
    }
    /**
     * 检查撤销结果
     * @param baseModel
     */
    public void checkRepealTaskStatus(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0){
            ToastUtils.showToast("撤销成功！");
            getP().getTask(projectId,taskId,taskType,userId);
        }
    }
    /**
     * 选择人员接收结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode,int resultCode ,Intent data){
        if (requestCode == 5){
            if (resultCode == 1){
                List<ProjectUserBean> list= (List<ProjectUserBean>) data.getSerializableExtra("selectlist");
                String releaseId = data.getStringExtra("releaseId");
                if (list.size() > 0){
                    Gson gson = new Gson();
                    List<Map<String,Integer>> selectUsers = new ArrayList<>();
                    for (int i=0;i<list.size();i++){
                        Map<String,Integer> map= new HashMap<String,Integer>();
                        map.put("userId",list.get(i).getUserId());
                        selectUsers.add(map);
                    }
                    getP().assingTaskByUser(projectId,gson.toJson(selectUsers).toString(),releaseId);
                }else {
                    ToastUtils.showToast("未选择分配人员！");
                }
            }
        }
    }
    /**
     * 检查任务分派结果
     * @param baseModel
     */
    public void checkAssignTaskResult(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0){
            ToastUtils.showToast("分派成功！");
            getP().getTask(projectId,taskId,taskType,userId);
        }
    }
    public void goFinish(){
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
