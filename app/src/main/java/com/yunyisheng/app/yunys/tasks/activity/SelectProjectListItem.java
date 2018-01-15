package com.yunyisheng.app.yunys.tasks.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Checkable;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseFragement;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/15.
 */

public class SelectProjectListItem extends BaseFragement implements Checkable {
    @BindView(R.id.m_radio_button)
    RadioButton mRadioButton;
    @BindView(R.id.project_title)
    TextView projectTitle;

    @Override
    public void setChecked(boolean checked) {
        mRadioButton.setChecked(checked);
    }

    @Override
    public boolean isChecked() {
        return mRadioButton.isChecked();
    }
    public void setProjectTitle(String title){
        projectTitle.setText(title);
    }
    @Override
    public void toggle() {
        mRadioButton.toggle();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,context);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.select_project_list_item;
    }


    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }
}
