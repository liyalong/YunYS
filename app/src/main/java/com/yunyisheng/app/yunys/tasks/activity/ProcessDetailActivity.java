package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.bean.ProcessDetailBean;
import com.yunyisheng.app.yunys.tasks.present.ProcessDetailPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.io.Serializable;
import java.util.List;

import cn.droidlover.xdroidbase.cache.SharedPref;

public class ProcessDetailActivity extends BaseActivity<ProcessDetailPresent> {

    private String processDefinitionId;
    private int userId;
    private String taskId;
    private String taskType;
    private String state;
    private int type;
    private String taskid;
    private String processDefinitionName;

    @Override
    public void initView() {
        Intent intent = getIntent();
        taskId = intent.getStringExtra("taskId");
        userId = intent.getIntExtra("userId", 0);
        taskType = intent.getStringExtra("taskType");

        Button button=(Button)findViewById(R.id.but_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId ==0){
                   int userid = SharedPref.getInstance(ProcessDetailActivity.this).getInt("userid",0);
                   getP().getProcessTaskDetailByUser(taskId,taskType,userid+"");
               }else {
                   getP().getProcessTaskDetailByUser(taskId,taskType,userId+"");
               }
            }
        });
    }

    @Override
    public void initAfter() {

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

    }

    @Override
    public void widgetClick(View v) {

    }

    public void getProcessDetailResult(ProcessDetailBean processDetailBean) {
        ProcessDetailBean.YseNoEndBean yseNoEnd = processDetailBean.getRespBody().getYseNoEnd();
        processDefinitionId = yseNoEnd.getProcessDefinitionId();
        state = processDetailBean.getRespBody().getTask().getState();
        taskid = processDetailBean.getRespBody().getTask().getId();
        type = 2;
        List<ProcessDetailBean.SelectByIdAndUuid.DataListBean> dataList = processDetailBean.getRespBody().getSelectByIdAndUuid().getDataList();
        if (dataList.size()>0) {
            Intent intent = new Intent(ProcessDetailActivity.this, ProcessTaskFormActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("selectFormId", processDefinitionId);
            intent.putExtra("taskid", taskid);
            intent.putExtra("state", state);
            intent.putExtra("datalist", (Serializable) dataList);
            startActivity(intent);
        }else {
            ToastUtils.showToast("数据错误");
        }
    }
}
