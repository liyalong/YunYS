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

public class CronMouthFragment extends BaseFragement {

    DateTimeDialogUtils dateTimeDialogUtils = new DateTimeDialogUtils();
    @BindView(R.id.month_first_value)
    EditText monthFirstValue;
    @BindView(R.id.month_secend_value)
    EditText monthSecendValue;
    @BindView(R.id.month_first_runtime)
    TextView monthFirstRuntime;

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.crontab_mouth;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        monthFirstRuntime.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.month_first_runtime:
                dateTimeDialogUtils.showTimePickerDialog(context, monthFirstRuntime);
                break;
        }

    }

    public String getMonthFirstValue() {
        return monthFirstValue.getText().toString();
    }

    public String getMonthSecendValue() {
        return monthSecendValue.getText().toString();
    }

    public String getMonthFirstRuntime() {
        return monthFirstRuntime.getText().toString();
    }
}
