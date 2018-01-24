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
    private DeviceBean deviceBean;

    private List<String> groupList = new ArrayList<>();


    private List<DeviceWarningBean> deviceWarningList = new ArrayList<>();
    private List<DevicePLCValueBean> devicePLCValueList = new ArrayList<>();
    private List<DeviceInfoModel> deviceInfoList = new ArrayList<>();
    private DeviceInfoModel deviceInfoModel;


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

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                this.finish();
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

    public void displayDeviceInfoList(DeviceBean deviceBean) {
        this.deviceBean = deviceBean;

    }

    public void setDeviceWarningList(DeviceWarningListModel deviceWarningListModel) {

    }

    public void setDevicePLCValueList(DevicePLCValueListModel devicePLCValueList) {

    }

    public void setDeviceInfoList(List<DeviceInfoModel> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
