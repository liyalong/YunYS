package com.yunyisheng.app.yunys.schedule.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XPresent;

public class ScheduleSetActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.cb_isshowchexiao)
    CheckBox cbIsshowchexiao;
    @BindView(R.id.cb_isshowwancheng)
    CheckBox cbIsshowwancheng;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("设置");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        boolean isshowchexiao = SharedPref.getInstance(ScheduleSetActivity.this).getBoolean("isshowchexiao", true);
        boolean isshowwancheng = SharedPref.getInstance(ScheduleSetActivity.this).getBoolean("isshowwancheng", true);
        cbIsshowwancheng.setChecked(isshowwancheng);
        cbIsshowchexiao.setChecked(isshowchexiao);

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_schedule_set;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        cbIsshowchexiao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPref.getInstance(ScheduleSetActivity.this).putBoolean("isshowchexiao", true);
                } else {
                    SharedPref.getInstance(ScheduleSetActivity.this).putBoolean("isshowchexiao", false);
                }
            }
        });
        cbIsshowwancheng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPref.getInstance(ScheduleSetActivity.this).putBoolean("isshowwancheng", true);
                } else {
                    SharedPref.getInstance(ScheduleSetActivity.this).putBoolean("isshowwancheng", false);
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }
}
