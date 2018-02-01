package com.yunyisheng.app.yunys.project.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.activity.ProjectDetailsActivity;
import com.yunyisheng.app.yunys.project.activity.RenwuFankuiFormActivity;
import com.yunyisheng.app.yunys.project.activity.TaskDetailActivity;
import com.yunyisheng.app.yunys.project.adapter.SpinnerAdapter;
import com.yunyisheng.app.yunys.main.model.SpinnerBean;
import com.yunyisheng.app.yunys.tasks.bean.TaskBean;
import com.yunyisheng.app.yunys.project.model.TaskListModel;
import com.yunyisheng.app.yunys.tasks.adapter.TaskAdapter;
import com.yunyisheng.app.yunys.tasks.present.TaskListPresent;
import com.yunyisheng.app.yunys.utils.ScreenUtils;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/18.
 */

public class TaskPoolFragment extends BaseFragement<TaskListPresent> implements TaskAdapter.Callback{
    @BindView(R.id.tasks_type)
    Spinner tasksType;
    @BindView(R.id.task_list_view)
    PullToRefreshListView taskListView;

    private List<SpinnerBean> sList = new ArrayList<>();

    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 10;
    private String projectId;
    private int SELECT_TYPE = 1;  //选择项：1待认领，2待完成，3已发布

    private TaskAdapter adapter;
    private List<TaskBean> dataList = new ArrayList<>();

    private Dialog taskListBtnDialog;

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        ProjectDetailsActivity projectDetailsActivity = (ProjectDetailsActivity) getActivity();
        this.projectId = projectDetailsActivity.getProjectId();
        sList.clear();
        sList.add(new SpinnerBean("待认领",0));
        sList.add(new SpinnerBean("待完成",0));
        sList.add(new SpinnerBean("已发布",0));
        tasksType.setDropDownWidth(ScreenUtils.getScreenHeight(TaskPoolFragment.super.context));
        SpinnerAdapter adapter = new SpinnerAdapter(TaskPoolFragment.super.context,sList);
        tasksType.setAdapter(adapter);
        tasksType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtils.showLongToast("你点击的是:"+sList.get(i).getSptype());
                XLog.d("selectedId",String.valueOf(i));
                PAGE_NUM = 1;
                switch (i){
                    case 0:
                        SELECT_TYPE = 1;
                        break;
                    case 1:
                        SELECT_TYPE = 2;
                        break;
                    case 2:
                        SELECT_TYPE = 3;
                        break;
                }
                getP().getTaskList(SELECT_TYPE,projectId,PAGE_NUM,PAGE_SIZE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ScrowUtil.listViewConfig(taskListView);
        taskListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getTaskList(SELECT_TYPE,projectId,PAGE_NUM,PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getTaskList(SELECT_TYPE,projectId,PAGE_NUM,PAGE_SIZE);
            }
        });
        //跳转任务详情
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskBean task = dataList.get(i-1);
                if (SELECT_TYPE == 3){
                    ToastUtils.showToast("子任务详情");
                }else {
                    Router.newIntent(context)
                            .to(TaskDetailActivity.class)
                            .putString("taskId",task.getTaskId())
                            .putString("taskType", String.valueOf(task.getReleaseTaskType()))
                            .launch();
                }
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getTaskList(SELECT_TYPE,projectId,PAGE_NUM,PAGE_SIZE);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_task_pool;
    }

    @Override
    public TaskListPresent bindPresent() {
        return new TaskListPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public void setAdapter(TaskListModel taskListModel){
        if (taskListModel.getRespBody().size() > 0){
            if (PAGE_NUM == 1){
                dataList.clear();
                dataList.addAll(taskListModel.getRespBody());
                adapter = new TaskAdapter(context,dataList,SELECT_TYPE,this);
                taskListView.setAdapter(adapter);
            }else {
                dataList.addAll(taskListModel.getRespBody());
                adapter.setData(dataList);
            }
        }else {
            if (PAGE_NUM == 1){
                adapter.clearData();
                ToastUtils.showToast("暂无数据！");
            }else {
                ToastUtils.showToast("暂无更多数据");
            }
        }
        taskListView.onRefreshComplete();
        taskListView.computeScroll();

    }

    public static TaskPoolFragment newInstance() {
        return new TaskPoolFragment();
    }

    @Override
    public void click(View v) {
        int position = (Integer) v.getTag();
        createTaskListBtnDialog(context,position);
    }
    //任务列表操作按钮点击弹框
    private void createTaskListBtnDialog(final Activity activity, int position) {
        final TaskBean clickTask = dataList.get(position);

        taskListBtnDialog = new Dialog(activity,R.style.dialog_bottom_full);
        taskListBtnDialog.setCanceledOnTouchOutside(true);
        taskListBtnDialog.setCancelable(true);
        Window window = taskListBtnDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        View v =View.inflate(activity,R.layout.tasks_list_button_clicked,null);
        TextView editTask = v.findViewById(R.id.edit_task);
        TextView removeTask = v.findViewById(R.id.remove_task);
        TextView assignTask = v.findViewById(R.id.assign_task);
        TextView claimTask = v.findViewById(R.id.claim_task);
        TextView doTask = v.findViewById(R.id.do_task);
        TextView backTask = v.findViewById(R.id.back_task);
        TextView lookTaskInfo = v.findViewById(R.id.look_task_info);
        TextView lookTaskChildInfo = v.findViewById(R.id.look_task_child_info);
        ImageView btn_cz_close = v.findViewById(R.id.btn_cz_close);


        switch (SELECT_TYPE){
            case 1:
                //待认领状态下进行认领操作显示
                editTask.setVisibility(View.GONE);
                removeTask.setVisibility(View.GONE);
                assignTask.setVisibility(View.GONE);
                doTask.setVisibility(View.GONE);
                backTask.setVisibility(View.GONE);
                lookTaskInfo.setVisibility(View.GONE);
                lookTaskChildInfo.setVisibility(View.GONE);
                break;
            case 2:
                //待完成状态下进行执行和回退操作
                editTask.setVisibility(View.GONE);
                removeTask.setVisibility(View.GONE);
                assignTask.setVisibility(View.GONE);
                claimTask.setVisibility(View.GONE);
                lookTaskInfo.setVisibility(View.GONE);
                lookTaskChildInfo.setVisibility(View.GONE);
                break;
            case 3:
                //已发布状态下进行编辑，查看认领情况，分配，撤销
                //未认领状态下可进行编辑，分配，撤销(已完成和已认领数为0时)
                if (clickTask.getReleaseStatFinish() == 0 && clickTask.getReleaseStatClaim() == 0){
                    claimTask.setVisibility(View.GONE);
                    doTask.setVisibility(View.GONE);
                    backTask.setVisibility(View.GONE);
                }else if (clickTask.getReleaseStatFinish() > 0 || clickTask.getReleaseStatClaim() > 0){
                    //已认领状态下可进行查看认领情况（已完成或已认领数大于0）
                    editTask.setVisibility(View.GONE);
                    removeTask.setVisibility(View.GONE);
                    assignTask.setVisibility(View.GONE);
                    claimTask.setVisibility(View.GONE);
                    doTask.setVisibility(View.GONE);
                    backTask.setVisibility(View.GONE);
                }
                break;
        }
        //编辑任务
        editTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //撤销任务
        removeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //分配任务
        assignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //认领任务
        claimTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getP().claimTask(clickTask.getTaskId());
            }
        });
        //执行任务
        doTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(RenwuFankuiFormActivity.class)
                        .putInt("taskid", Integer.parseInt(clickTask.getTaskId()))
                        .putString("projectId",projectId)
                        .launch();
            }
        });
        //退回任务
        backTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //已发布任务，查看任务执行情况列表
        lookTaskInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //已完成任务，查看任务反馈项
        lookTaskChildInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //关闭按钮
        btn_cz_close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                taskListBtnDialog.hide();
            }
        });

        window.setContentView(v);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        taskListBtnDialog.show();
    }

    public void checkClaimTaskStatus(BaseModel baseModel){
        if (baseModel.getRespCode() == 0){
            taskListBtnDialog.hide();
            ToastUtils.showToast("认领成功！");
            PAGE_NUM = 1;
            getP().getTaskList(SELECT_TYPE,projectId,PAGE_NUM,PAGE_SIZE);
        }
    }
}
