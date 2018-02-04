package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import org.w3c.dom.Text;

import java.util.List;

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
    TextView processTaskEndTime;
    final static int FORMREQUESTCODE = 1;
    final static int USERREQUESTCODE = 2;

    private String selectFormId;
    private String selectFormName;
    CustomDatePicker startCustomDatePicker;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {
        initDatePicker();


    }
    public void initDatePicker(){
        String startDate = "2010-01-01 00:00";
        String pattern = "yyyy-MM-dd HH:mm";
        String startTime = DateTimeDialogUtils.getNewData(pattern, 1);
        processTaskEndTime.setText(startTime);
        startCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                processTaskEndTime.setText(time);
            }
        }, startDate, startTime);
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
        processTaskEndTime.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.submit:
                ToastUtils.showToast("提交表单");
                break;
            case R.id.select_process_task_form_list:
                ToastUtils.showToast("选择表单");
                Intent intent = new Intent(context,SelectProcessFormActivity.class);
                startActivityForResult(intent,FORMREQUESTCODE);
                break;
            case R.id.select_process_task_to_user:
                ToastUtils.showToast("选择审批人");
                Intent intent1 = new Intent(context,RadioSelectUserActivity.class);
                startActivityForResult(intent1,USERREQUESTCODE);
                break;
            case R.id.process_task_end_time:
                startCustomDatePicker.show(processTaskEndTime.getText().toString());
                break;
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FORMREQUESTCODE:
                if (resultCode == 1){
                    selectFormId = data.getStringExtra("procDefId");
                    selectFormName = data.getStringExtra("formName");
                    selectProcessTaskFormList.setText(selectFormName);
                }
                break;
            case USERREQUESTCODE:
                break;
        }
    }
}
