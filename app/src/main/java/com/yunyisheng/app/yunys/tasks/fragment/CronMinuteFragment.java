package com.yunyisheng.app.yunys.tasks.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/15.
 */

public class CronMinuteFragment extends BaseFragement {
    @BindView(R.id.minute_first_runtime)
    TextView minute_first_runtime;
    @BindView(R.id.minute_value)
    EditText minute_value;
    DateTimeDialogUtils dateTimeDialogUtils = new DateTimeDialogUtils();

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.crontab_minute;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        minute_first_runtime.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.minute_first_runtime:
                dateTimeDialogUtils.showTimePickerDialog(context,minute_first_runtime);
                break;
        }
    }

    public String getMinute_first_runtime() {
        return minute_first_runtime.getText().toString();
    }

    public String getMinute_value() {
        return minute_value.getText().toString();
    }
}
