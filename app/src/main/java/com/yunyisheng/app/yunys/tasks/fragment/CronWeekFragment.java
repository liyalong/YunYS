package com.yunyisheng.app.yunys.tasks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

public class CronWeekFragment extends BaseFragement {
    @BindView(R.id.checkBox1)
    CheckBox checkBox1;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.checkBox3)
    CheckBox checkBox3;
    @BindView(R.id.checkBox4)
    CheckBox checkBox4;
    @BindView(R.id.checkBox5)
    CheckBox checkBox5;
    @BindView(R.id.checkBox6)
    CheckBox checkBox6;
    @BindView(R.id.checkBox7)
    CheckBox checkBox7;
    @BindView(R.id.week_first_runtime)
    TextView weekFirstRuntime;


    private String checkedValue;

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
        return R.layout.crontab_week;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        weekFirstRuntime.setOnClickListener(this);

    }
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.week_first_runtime:
                dateTimeDialogUtils.showTimePickerDialog(context, weekFirstRuntime);
                break;
        }

    }



    public String getCheckedValue() {
        checkedValue = "";
        if (checkBox1.isChecked()) {
            checkedValue += "2,";
        }
        if (checkBox2.isChecked()) {
            checkedValue += "3,";
        }
        if (checkBox3.isChecked()) {
            checkedValue += "4,";
        }
        if (checkBox4.isChecked()) {
            checkedValue += "5,";
        }
        if (checkBox5.isChecked()) {
            checkedValue += "6,";
        }
        if (checkBox6.isChecked()) {
            checkedValue += "7,";
        }
        if (checkBox7.isChecked()) {
            checkedValue += "1,";
        }
        return checkedValue;
    }

    public String getWeekFirstRuntime() {
        return weekFirstRuntime.getText().toString();
    }
}
