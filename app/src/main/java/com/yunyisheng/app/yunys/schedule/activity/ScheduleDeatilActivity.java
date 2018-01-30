package com.yunyisheng.app.yunys.schedule.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/18  18:22
 * @describe 日程详情
 */
public class ScheduleDeatilActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.te_columntitle)
    TextView teColumntitle;
    @BindView(R.id.te_columnsize)
    TextView teColumnsize;
    @BindView(R.id.te_schedule_time)
    TextView teScheduleTime;
    @BindView(R.id.te_schedule_detail)
    TextView teScheduleDetail;
    @BindView(R.id.te_zhixing_peo)
    TextView teZhixingPeo;
    private String scheduleid;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("日程详情");
        Intent intent = getIntent();
        scheduleid = intent.getStringExtra("scheduleid");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_schedule_deatil;
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
