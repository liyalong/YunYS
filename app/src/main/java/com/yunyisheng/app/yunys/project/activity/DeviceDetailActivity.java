package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.adapter.DeviceOrPCMAlarmListAdapter;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;
import com.yunyisheng.app.yunys.project.bean.DevicePLCValueBean;
import com.yunyisheng.app.yunys.project.bean.DeviceWarningBean;
import com.yunyisheng.app.yunys.project.model.DeviceInfoModel;
import com.yunyisheng.app.yunys.project.model.DevicePLCValueListModel;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.project.present.DeviceDetailPresent;
import com.yunyisheng.app.yunys.utils.SuperExpandableListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/18.
 * 设备详情页面
 */

public class DeviceDetailActivity extends BaseActivity<DeviceDetailPresent> implements DeviceOrPCMAlarmListAdapter.Callback {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.device_detail_title)
    TextView deviceDetailTitle;
    @BindView(R.id.info_drop_btn)
    ImageView infoDropBtn;
    @BindView(R.id.device_status)
    TextView deviceStatus;
    @BindView(R.id.device_bind_plc_status)
    TextView deviceBindPlcStatus;
    @BindView(R.id.to_alarm_rules)
    LinearLayout toAlarmRules;
    @BindView(R.id.to_knowledge)
    LinearLayout toKnowledge;
    @BindView(R.id.to_device_parts)
    LinearLayout toDeviceParts;
    @BindView(R.id.to_periodic_tasks)
    LinearLayout toPeriodicTasks;
    @BindView(R.id.jbxx_list)
    LinearLayout jbxxList;
    @BindView(R.id.jbxx_drop_btn)
    ImageView jbxxDropBtn;
    @BindView(R.id.device_detail_bjxx_list)
    ListView deviceDetailBjxxList;
    @BindView(R.id.sszb_drop_btn)
    ImageView sszbDropBtn;
    @BindView(R.id.device_detail_sszb_list)
    SuperExpandableListView deviceDetailSszbList;
    private String deviceId;
    private String projectId;
    private String deviceName;

    private List<String> groupList = new ArrayList<>();

    private List<DevicePLCValueBean> devicePLCValueList = new ArrayList<>();

    private boolean INFOISSHOW = true;
    private boolean BJXXISSHOW = true;
    private Timer timer;

    private DeviceOrPCMAlarmListAdapter warningAdapter;
    private List<DeviceWarningBean> warningDataList = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.deviceId = getIntent().getStringExtra("deviceId");
        this.deviceName = getIntent().getStringExtra("deviceName");
        this.projectId = getIntent().getStringExtra("projectId");
        deviceDetailTitle.setText(deviceName + "运行详情");

        getP().getDeviceInfo(projectId, deviceId);
        //实时报警列表
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getP().getDeviceWarningList(projectId, 1, 999, deviceId);
                getP().getDevicePlcValueList(projectId,deviceId);

            }
        }, 0, 10000);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.device_detail;
    }

    @Override
    public DeviceDetailPresent bindPresent() {
        return new DeviceDetailPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        toAlarmRules.setOnClickListener(this);
        toKnowledge.setOnClickListener(this);
        toDeviceParts.setOnClickListener(this);
        toPeriodicTasks.setOnClickListener(this);
        infoDropBtn.setOnClickListener(this);
        jbxxDropBtn.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                stopTimer();
                this.finish();
                break;
            case R.id.to_alarm_rules:
                Router.newIntent(context)
                        .to(DeviceAlarmRulesActivity.class)
                        .putString("projectId", projectId)
                        .putString("deviceId", deviceId)
                        .putString("deviceName", deviceName)
                        .launch();
                break;
            case R.id.to_knowledge:
                Router.newIntent(context)
                        .to(KnowledgeListActivity.class)
                        .putString("projectId", projectId)
                        .putString("deviceId", deviceId)
                        .putString("deviceName", deviceName)
                        .launch();
                break;
            case R.id.to_device_parts:
                Router.newIntent(context)
                        .to(DevicePartsListActivity.class)
                        .putString("projectId", projectId)
                        .putString("deviceId", deviceId)
                        .putString("deviceName", deviceName)
                        .launch();
                break;
            case R.id.to_periodic_tasks:
                Router.newIntent(context)
                        .to(PeriodicTaskListActivity.class)
                        .putString("projectId", projectId)
                        .putString("deviceId", deviceId)
                        .putString("deviceName", deviceName)
                        .launch();
                break;
            case R.id.info_drop_btn:
                if (INFOISSHOW) {
                    infoDropBtn.setImageResource(R.mipmap.icon_device_right);
                    jbxxList.setVisibility(View.GONE);
                    INFOISSHOW = false;
                } else {
                    infoDropBtn.setImageResource(R.mipmap.icon_device_down);
                    jbxxList.setVisibility(View.VISIBLE);
                    INFOISSHOW = true;
                }

                break;
            case R.id.jbxx_drop_btn:
                if (BJXXISSHOW){
                    jbxxDropBtn.setImageResource(R.mipmap.icon_device_right);
                    deviceDetailBjxxList.setVisibility(View.GONE);
                    BJXXISSHOW = false;
                }else {
                    jbxxDropBtn.setImageResource(R.mipmap.icon_device_down);
                    deviceDetailBjxxList.setVisibility(View.VISIBLE);
                    BJXXISSHOW = true;
                }
                break;
        }

    }

    /**
     * 处理基本信息中的设备状态和是否绑定plc
     *
     * @param deviceInfoModel
     */
    public void displayDeviceInfoList(DeviceInfoModel deviceInfoModel) {
        DeviceBean deviceBean = deviceInfoModel.getRespBody();
        if (deviceBean.getEquipmentStat() == 1) {
            deviceStatus.setText(R.string.device_status_1);
        } else if (deviceBean.getEquipmentStat() == 2){
            deviceStatus.setText(R.string.model_running_status_2);
        }else if (deviceBean.getEquipmentStat() == 3){
            deviceStatus.setText(R.string.device_status_2);
        }
        if (deviceBean.getBindPlcNum() == 0) {
            deviceBindPlcStatus.setText(R.string.no);
        } else {
            deviceBindPlcStatus.setText(R.string.yes);
        }
    }

    public void setDeviceWarningList(DeviceWarningListModel deviceWarningListModel) {
        if (deviceWarningListModel.getRespBody().size() > 0) {
            warningDataList.clear();
            warningDataList.addAll(deviceWarningListModel.getRespBody());
            warningAdapter = new DeviceOrPCMAlarmListAdapter(context, warningDataList, this);
            deviceDetailBjxxList.setAdapter(warningAdapter);
        }

    }

    public void setDevicePLCValueList(DevicePLCValueListModel devicePLCValueList) {
        if (devicePLCValueList.getRespBody().size() > 0){

        }
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void click(View v) {
        Integer position = (Integer) v.getTag();
    }

}
