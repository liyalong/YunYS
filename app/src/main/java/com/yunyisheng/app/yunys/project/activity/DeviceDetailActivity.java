package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/18.
 * 设备详情页面
 */

public class DeviceDetailActivity extends BaseActivity<DeviceDetailPresent> {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.device_detail_title)
    TextView deviceDetailTitle;
    @BindView(R.id.device_detail_bjxx_list)
    SuperExpandableListView deviceDetailBjxxList;
    @BindView(R.id.device_detail_sszb_list)
    SuperExpandableListView deviceDetailSszbList;
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
    private String deviceId;
    private String projectId;
    private String deviceName;

    private List<String> groupList = new ArrayList<>();

    private List<DeviceWarningBean> deviceWarningList = new ArrayList<>();
    private List<DevicePLCValueBean> devicePLCValueList = new ArrayList<>();


    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.setDeviceId(getIntent().getStringExtra("deviceId"));
        this.setDeviceName(getIntent().getStringExtra("deviceName"));
        this.setProjectId(getIntent().getStringExtra("projectId"));
        XLog.d("deviceId:" + deviceId + ",deviceName:" + deviceName);
        deviceDetailTitle.setText(deviceName + "运行详情");

        getP().getDeviceInfo(projectId, deviceId);
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

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.to_alarm_rules:
                Router.newIntent(context)
                        .to(DeviceAlarmRulesActivity.class)
                        .putString("projectId",projectId)
                        .putString("deviceId",deviceId)
                        .putString("deviceName",deviceName)
                        .launch();
                break;
            case R.id.to_knowledge:
                Router.newIntent(context)
                        .to(KnowledgeListActivity.class)
                        .putString("projectId",projectId)
                        .putString("deviceId",deviceId)
                        .putString("deviceName",deviceName)
                        .launch();
                break;
            case R.id.to_device_parts:
                Router.newIntent(context)
                        .to(DevicePartsListActivity.class)
                        .putString("projectId",projectId)
                        .putString("deviceId",deviceId)
                        .putString("deviceName",deviceName)
                        .launch();
                break;
            case R.id.to_periodic_tasks:
                Router.newIntent(context)
                        .to(PeriodicTaskListActivity.class)
                        .putString("projectId",projectId)
                        .putString("deviceId",deviceId)
                        .putString("deviceName",deviceName)
                        .launch();
                break;
        }

    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {

        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 处理基本信息中的设备状态和是否绑定plc
     * @param deviceInfoModel
     */
    public void displayDeviceInfoList(DeviceInfoModel deviceInfoModel) {
        DeviceBean deviceBean = deviceInfoModel.getRespBody();
        if (deviceBean.getEquipmentStat() == 0){
            deviceStatus.setText(R.string.device_status_1);
        }else {
            deviceStatus.setText(R.string.device_status_2);
        }
        if (deviceBean.getBindPlcNum() == 0){
            deviceBindPlcStatus.setText(R.string.yes);
        }else {
            deviceBindPlcStatus.setText(R.string.no);
        }
    }

    public void setDeviceWarningList(DeviceWarningListModel deviceWarningListModel) {

    }

    public void setDevicePLCValueList(DevicePLCValueListModel devicePLCValueList) {

    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
