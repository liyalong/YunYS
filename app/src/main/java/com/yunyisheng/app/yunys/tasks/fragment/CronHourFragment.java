package com.yunyisheng.app.yunys.tasks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/15.
 */

public class CronHourFragment extends BaseFragement {
    @BindView(R.id.hour_value)
    EditText hourValue;
    @BindView(R.id.hour_first_runtime)
    TextView hourFirstRuntime;
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
        return R.layout.crontab_hour;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        hourFirstRuntime.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.hour_first_runtime:
                dateTimeDialogUtils.showTimePickerDialog(context,hourFirstRuntime);
                break;
        }

    }

    public String getHourValue() {
        return hourValue.getText().toString();
    }

    public String getHourFirstRuntime() {
        return hourFirstRuntime.getText().toString();
    }
}
