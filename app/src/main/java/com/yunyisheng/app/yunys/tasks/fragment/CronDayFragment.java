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

public class CronDayFragment extends BaseFragement {
    DateTimeDialogUtils dateTimeDialogUtils = new DateTimeDialogUtils();
    @BindView(R.id.day_value)
    EditText dayValue;
    @BindView(R.id.day_first_runtime)
    TextView dayFirstRuntime;


    @Override
    public void initView() {
        ButterKnife.bind(this, context);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.crontab_day;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        dayFirstRuntime.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.day_first_runtime:
                dateTimeDialogUtils.showTimePickerDialog(context, dayFirstRuntime);
                break;
        }

    }

    public String getDayValue() {
        return dayValue.getText().toString();
    }

    public String getDayFirstRuntime() {
        return dayFirstRuntime.getText().toString();
    }
}
