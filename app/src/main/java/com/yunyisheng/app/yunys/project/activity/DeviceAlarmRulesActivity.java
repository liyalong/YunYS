package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.adapter.DeviceAlarmRulesAdapter;
import com.yunyisheng.app.yunys.project.bean.DeviceAlarmRulesBean;
import com.yunyisheng.app.yunys.project.model.DeviceAlarmRulesModel;
import com.yunyisheng.app.yunys.project.present.DeviceAlarmRulesPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liyalong on 2018/1/18.
 * 设备的报警规则列表
 */

public class DeviceAlarmRulesActivity extends BaseActivity<DeviceAlarmRulesPresent> {
    @BindView(R.id.alarm_rules_list)
    PullToRefreshListView alarmRulesList;
    @BindView(R.id.img_back)
    ImageView imgBack;

    DeviceAlarmRulesModel deviceAlarmRulesModel;
    @BindView(R.id.no_data_img)
    ImageView noDataImg;
    @BindView(R.id.no_data)
    LinearLayout noData;
    private int PAGE_NUM = 1;
    private final int PAGE_SIZE = 10;
    private String projectId;
    private String deviceId;
    private String deviceName;
    DeviceAlarmRulesAdapter adapter;

    List<DeviceAlarmRulesBean> dataLists = new ArrayList<>();


    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.deviceId = getIntent().getStringExtra("deviceId");
        this.deviceName = getIntent().getStringExtra("deviceName");
        getP().getDeviceAlarmRulesList(projectId, deviceId, PAGE_NUM, PAGE_SIZE);
        ScrowUtil.listViewConfig(alarmRulesList);
        alarmRulesList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getDeviceAlarmRulesList(projectId, deviceId, PAGE_NUM, PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getDeviceAlarmRulesList(projectId, deviceId, PAGE_NUM, PAGE_SIZE);
            }
        });

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.alarm_rules_activity;
    }

    @Override
    public DeviceAlarmRulesPresent bindPresent() {
        return new DeviceAlarmRulesPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        noDataImg.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.no_data_img:
                getP().getDeviceAlarmRulesList(projectId, deviceId, PAGE_NUM, PAGE_SIZE);
                break;
        }

    }

    public void setDeviceAlarmRulesModel(DeviceAlarmRulesModel deviceAlarmRulesModel) {
        this.deviceAlarmRulesModel = deviceAlarmRulesModel;
        if (deviceAlarmRulesModel.getRespBody().size() > 0) {
            showList();
            if (PAGE_NUM == 1) {
                dataLists.clear();
                dataLists.addAll(deviceAlarmRulesModel.getRespBody());
                adapter = new DeviceAlarmRulesAdapter(context, dataLists);
                alarmRulesList.setAdapter(adapter);
            } else {
                dataLists.addAll(deviceAlarmRulesModel.getRespBody());
                adapter.setData(dataLists);
            }
        } else {
            if (PAGE_NUM == 1) {
                setNoData();
                ToastUtils.showToast("暂无数据！");
            } else {
                PAGE_NUM -= 1;
                ToastUtils.showToast("暂无更多数据");
            }
        }
        initRefresh();
    }

    public void initRefresh() {
        alarmRulesList.onRefreshComplete();
        alarmRulesList.computeScroll();
    }
    public void setNoData(){
        alarmRulesList.setVisibility(View.GONE);
        noDataImg.setImageResource(R.mipmap.no_data);
        noData.setVisibility(View.VISIBLE);
    }
    public void setNoNetwork(){
        alarmRulesList.setVisibility(View.GONE);
        noDataImg.setImageResource(R.mipmap.no_network);
        noData.setVisibility(View.VISIBLE);
    }
    public void showList(){
        noData.setVisibility(View.GONE);
        alarmRulesList.setVisibility(View.VISIBLE);
    }

}
