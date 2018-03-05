package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.adapter.ProcessTaskApprovalInfoAdapter;
import com.yunyisheng.app.yunys.tasks.bean.ProcessDetailBean;
import com.yunyisheng.app.yunys.tasks.present.ProcessDetailPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

public class ProcessDetailActivity extends BaseActivity<ProcessDetailPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.process_task_name)
    TextView processTaskName;
    @BindView(R.id.task_start_user)
    TextView taskStartUser;
    @BindView(R.id.task_start_time)
    TextView taskStartTime;
    @BindView(R.id.task_start_state)
    TextView taskStartState;
    @BindView(R.id.process_approval_list)
    ListView processApprovalList;
    @BindView(R.id.approval_box)
    LinearLayout approvalBox;
    @BindView(R.id.do_process_task)
    TextView doProcessTask;
    @BindView(R.id.to_process_task_detail)
    TextView toProcessTaskDetail;
    @BindView(R.id.caozuo_box)
    LinearLayout caozuoBox;
    private int userId;
    private String taskId;
    private String taskType;
    private ProcessDetailBean processDetail;
    private ProcessTaskApprovalInfoAdapter approvalAdapter;

    @Override
    public void initView() {
        Intent intent = getIntent();
        ButterKnife.bind(this);

        taskId = intent.getStringExtra("taskId");
        userId = intent.getIntExtra("userId", 0);
        taskType = intent.getStringExtra("taskType");

    }

    @Override
    public void initAfter() {
        if (userId == 0) {
            int userid = SharedPref.getInstance(ProcessDetailActivity.this).getInt("userid", 0);
            getP().getProcessTaskDetailByUser(taskId, taskType, userid + "");
        } else {
            getP().getProcessTaskDetailByUser(taskId, taskType, userId + "");
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_process_detail;
    }

    @Override
    public ProcessDetailPresent bindPresent() {
        return new ProcessDetailPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        doProcessTask.setOnClickListener(this);
        toProcessTaskDetail.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        try{

            switch (v.getId()){
                case R.id.img_back:
                    this.finish();
                    break;
                case R.id.do_process_task:
                    String state = processDetail.getRespBody().getTask().getState();
                    String taskid = processDetail.getRespBody().getTask().getId();
                    Integer type = 2;
                    List<ProcessDetailBean.SelectByIdAndUuid.DataListBean> dataList = processDetail.getRespBody().getSelectByIdAndUuid().getDataList();
                    List<ProcessDetailBean.SelectByIdAndUuid.FormBean.DataBean> dataBeans = processDetail.getRespBody().getSelectByIdAndUuid().getForm().getData();
                    if (dataList.size()>0 && dataBeans.size() > 0) {
                        Intent intent = new Intent(ProcessDetailActivity.this, ProcessTaskFormActivity.class);
                        intent.putExtra("type", type);
                        intent.putExtra("taskid", taskid);
                        intent.putExtra("state", state);
                        intent.putExtra("processDetail",processDetail);
                        startActivity(intent);
                    }else {
                        ToastUtils.showToast("表单数据错误");
                    }
                    break;
                case R.id.to_process_task_detail:
                    String state2 = processDetail.getRespBody().getTask().getState();
                    String taskid2 = processDetail.getRespBody().getTask().getId();
                    Integer type2 = 2;
                    List<ProcessDetailBean.SelectByIdAndUuid.DataListBean> dataList2 = processDetail.getRespBody().getSelectByIdAndUuid().getDataList();
                    List<ProcessDetailBean.SelectByIdAndUuid.FormBean.DataBean> dataBeans2 = processDetail.getRespBody().getSelectByIdAndUuid().getForm().getData();
                    if (dataList2.size()>0 && dataBeans2.size() > 0) {
                        Intent intent = new Intent(ProcessDetailActivity.this, ProcessTaskFormActivity.class);
                        intent.putExtra("type", type2);
                        intent.putExtra("taskid", taskid2);
                        intent.putExtra("state", state2);
                        intent.putExtra("processDetail",processDetail);
                        startActivityForResult(intent,1);
                    }else {
                        ToastUtils.showToast("表单数据错误");
                    }
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case 1:
                if (resultCode == 1){
                    initAfter();
                }
                break;
        }
    }
    public void getProcessDetailResult(ProcessDetailBean processDetailBean) {
        this.processDetail = processDetailBean;
        try{
            String taskName = processDetail.getRespBody().getSelectByIdAndUuid().getForm().getName().toString();
            String startUser = processDetail.getRespBody().getStartUserId().getUserName().toString();
            String taskStartTimeValue = processDetail.getRespBody().getTask().getCreationTime();
            String taskState = processDetail.getRespBody().getTask().getState();
            String taskApprovalState = processDetail.getRespBody().getTask().getYesOrNoApproval();
            processTaskName.setText(taskName);
            taskStartUser.setText(startUser);
            taskStartTime.setText(taskStartTimeValue);

            if (taskApprovalState.equals("1")){
                taskStartState.setText("待审批");
                if (taskState.equals("101")){
                    doProcessTask.setVisibility(View.VISIBLE);
                    toProcessTaskDetail.setVisibility(View.GONE);
                }else {
                    doProcessTask.setVisibility(View.GONE);
                    toProcessTaskDetail.setVisibility(View.VISIBLE);
                }
            }else if (taskApprovalState.equals("0")){
                taskStartState.setText("已结束");
                doProcessTask.setVisibility(View.GONE);
                toProcessTaskDetail.setVisibility(View.VISIBLE);
            }
            //审批意见列表
            List<ProcessDetailBean.HistoryCommnetsBean> taskApprovalDetailList = processDetail.getRespBody().getHistoryCommnets();
            if (taskApprovalDetailList.size() == 0){
                approvalBox.setVisibility(View.GONE);
            }else {
                approvalBox.setVisibility(View.VISIBLE);
                approvalAdapter = new ProcessTaskApprovalInfoAdapter(context,taskApprovalDetailList,taskApprovalState,taskApprovalDetailList.size());
                processApprovalList.setAdapter(approvalAdapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
