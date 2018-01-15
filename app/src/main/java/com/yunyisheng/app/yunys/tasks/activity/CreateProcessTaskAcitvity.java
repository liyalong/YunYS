package com.yunyisheng.app.yunys.tasks.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/13.
 */

public class CreateProcessTaskAcitvity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.select_process_task_form_list)
    TextView selectProcessTaskFormList;
    @BindView(R.id.select_process_task_to_user)
    TextView selectProcessTaskToUser;
    @BindView(R.id.process_task_end_time)
    EditText processTaskEndTime;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_process_task;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
        selectProcessTaskFormList.setOnClickListener(this);
        selectProcessTaskToUser.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                this.finish();
                break;
            case R.id.submit:
                ToastUtils.showToast("提交表单");
                break;
            case R.id.select_process_task_form_list:
                ToastUtils.showToast("选择表单");
                break;
            case R.id.select_process_task_to_user:
                ToastUtils.showToast("选择审批人");
                break;
        }

    }
}
