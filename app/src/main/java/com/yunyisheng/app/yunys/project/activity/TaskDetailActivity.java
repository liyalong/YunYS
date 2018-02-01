package com.yunyisheng.app.yunys.project.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.bean.TaskBean;
import com.yunyisheng.app.yunys.tasks.model.TaskDetailModel;
import com.yunyisheng.app.yunys.tasks.present.TaskDetailPresent;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    TextView taskBackInfo;
    @BindView(R.id.task_child_list)
    TextView taskChildList;
    private String taskId;
    private String userId;
    private String taskType;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.taskId = getIntent().getStringExtra("taskId");
        this.userId = getIntent().getStringExtra("userId");
        this.taskType = getIntent().getStringExtra("taskType");
        getP().getTask(taskId,taskType,userId);
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
        taskBackInfo.setOnClickListener(this);
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
                break;
            case R.id.remove_task:
                //撤销任务
                break;
            case R.id.assign_task:
                //分派任务
                break;
            case R.id.claim_task:
                //认领任务
                break;
            case R.id.do_task:
                //执行任务
                Router.newIntent(context)
                        .to(RenwuFankuiFormActivity.class)
                        .putInt("taskid", Integer.parseInt(taskId))
                        .launch();
                break;
            case R.id.back_task:
                //回退任务
                break;
            case R.id.task_back_info:
                //查看执行情况
                break;
            case R.id.task_child_list:
                //已发布任务查看子任务列表
                break;
        }

    }

    public void setDetail(TaskDetailModel taskDetailModel) {
        if (taskDetailModel.getRespBody() != null) {
            TaskBean task = taskDetailModel.getRespBody().getTask();
            //任务名称
            taskName.setText(task.getReleaseName().toString());
//            createTime.setText();
            //任务创建人
            createUser.setText(task.getReleaseName().toString());
            //任务描述
            taskDesc.setText(task.getReleaseRemark().toString());
            //任务开始时间
            beginTime.setText(task.getReleaseBegint().toString());
            //任务结束时间
            endTime.setText(task.getReleaseEndt().toString());

            initTaskBtn(task);


            //项目设备 显示对应的项目和设备名称
            if (task.getReleaseTaskType() == 1) {

            } else {
                deviceTaskBox.setVisibility(View.GONE);
            }
            if (task.getTaskSubmitTime() == null) {
                //判断超时，
                String now = DateTimeDialogUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
                if (DateTimeDialogUtils.DateCompare(task.getReleaseEndt(), now)) {
                    taskStatusIstimeout.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    /**
     * 根据任务的不同来源进行显示不同的按钮
     *
     * @param task
     */
    public void initTaskBtn(TaskBean task) {
        //未认领、已认领任务详情处理
        if (task.getTaskStat() == 0) {
            //未认领
            taskStatus.setText(R.string.task_status_1);
            doUserLayout.setVisibility(View.GONE);

            editTask.setVisibility(View.GONE);
            removeTask.setVisibility(View.GONE);
            assignTask.setVisibility(View.GONE);
            doTask.setVisibility(View.GONE);
            //claimTask.setVisibility(View.GONE); 认领
            backTask.setVisibility(View.GONE);
            taskBackInfo.setVisibility(View.GONE);
            taskChildList.setVisibility(View.GONE);
        } else if (task.getTaskStat() == 1) {
            //已认领
            taskStatus.setText(R.string.task_status_2);
            doUser.setText(task.getTaskUserName().toString());

            editTask.setVisibility(View.GONE);
            removeTask.setVisibility(View.GONE);
            assignTask.setVisibility(View.GONE);
            claimTask.setVisibility(View.GONE);
            //doTask.setVisibility(View.GONE); 执行
            //backTask.setVisibility(View.GONE); 回退
            taskBackInfo.setVisibility(View.GONE);
            taskChildList.setVisibility(View.GONE);
        } else if (task.getTaskStat() == 2) {
            //已完成
            taskStatus.setText(R.string.task_status_3);
            doUser.setText(task.getTaskUserName().toString());

            editTask.setVisibility(View.GONE);
            removeTask.setVisibility(View.GONE);
            assignTask.setVisibility(View.GONE);
            claimTask.setVisibility(View.GONE);
            doTask.setVisibility(View.GONE);
            backTask.setVisibility(View.GONE);
            //taskBackInfo.setVisibility(View.GONE);查看执行情况
            taskChildList.setVisibility(View.GONE);
        }
    }
}
