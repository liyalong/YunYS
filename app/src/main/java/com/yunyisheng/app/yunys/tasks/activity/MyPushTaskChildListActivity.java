package com.yunyisheng.app.yunys.tasks.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.activity.RenwuFankuiFormActivity;
import com.yunyisheng.app.yunys.project.activity.TaskDetailActivity;
import com.yunyisheng.app.yunys.project.model.TaskListModel;
import com.yunyisheng.app.yunys.tasks.adapter.MyPushTaskListAdapter;
import com.yunyisheng.app.yunys.tasks.adapter.TaskAdapter;
import com.yunyisheng.app.yunys.tasks.bean.TaskBean;
import com.yunyisheng.app.yunys.tasks.present.MyPushTaskChildPersen;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.router.Router;

public class MyPushTaskChildListActivity extends BaseActivity<MyPushTaskChildPersen> implements MyPushTaskListAdapter.Callback{

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.file_title)
    TextView fileTitle;
    @BindView(R.id.task_child_list)
    PullToRefreshListView taskChildList;

    MyPushTaskListAdapter adapter;
    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 10;
    private int SELECT_TYPE = 1;
    private String projectId;
    private String releaseId;

    private List<TaskBean> dataList = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.releaseId = getIntent().getStringExtra("releaseId");
        ScrowUtil.listViewConfig(taskChildList);
        getP().getMyPushTaskChildList(projectId,releaseId,PAGE_NUM,PAGE_SIZE);

        taskChildList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getMyPushTaskChildList(projectId,releaseId,PAGE_NUM,PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getMyPushTaskChildList(projectId,releaseId,PAGE_NUM,PAGE_SIZE);
            }
        });
        taskChildList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskBean task = dataList.get(i-1);
                Router.newIntent(context)
                        .to(TaskDetailActivity.class)
                        .putString("taskId",task.getTaskId())
                        .putInt("fromPage",3)
                        .putString("projectId",projectId)
                        .putString("taskType", String.valueOf(task.getReleaseTaskType()))
                        .launch();
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_push_task_child_list;
    }

    @Override
    public MyPushTaskChildPersen bindPresent() {
        return new MyPushTaskChildPersen();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }

    public void setDataList(TaskListModel taskListModel){
        if (taskListModel.getRespBody().size() > 0){
            if (PAGE_NUM == 1){
                dataList.clear();
                dataList = taskListModel.getRespBody();
                adapter = new MyPushTaskListAdapter(context,dataList,this);
                taskChildList.setAdapter(adapter);
            }else {
                dataList = taskListModel.getRespBody();
                adapter.setData(dataList);
            }
        }else {
            if (PAGE_NUM == 1){
                ToastUtils.showToast("暂无数据！");
            }else {
                PAGE_NUM -= 1;
                ToastUtils.showToast("暂无更多数据！");
            }
        }
        stopRefresh();
    }

    @Override
    public void click(View v) {
        int position = (Integer) v.getTag();
        //createTaskListBtnDialog(context,position);
    }

    public void stopRefresh(){
        taskChildList.onRefreshComplete();
        taskChildList.computeScroll();
    }
    //任务列表操作按钮点击弹框
    private void createTaskListBtnDialog(final Activity activity, int position) {

    }




}
