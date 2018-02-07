package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.adapter.DeviceOrPCMAlarmListAdapter;
import com.yunyisheng.app.yunys.project.adapter.DeviceOrPcmPLCValueListAdapter;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;
import com.yunyisheng.app.yunys.project.bean.DevicePLCValueBean;
import com.yunyisheng.app.yunys.project.bean.DeviceWarningBean;
import com.yunyisheng.app.yunys.project.model.DeviceInfoModel;
import com.yunyisheng.app.yunys.project.model.DevicePLCValueListModel;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.project.present.DeviceDetailPresent;
import com.yunyisheng.app.yunys.utils.SuperExpandableListView;
import com.yunyisheng.app.yunys.utils.ToastUtils;

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

public class DeviceDetailActivity extends BaseActivity<DeviceDetailPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.device_detail_title)
    TextView deviceDetailTitle;
    @BindView(R.id.line)
    LinearLayout line;
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

    private List<String> PLCGroupList = new ArrayList<>();
    private List<String> warningGrouptList = new ArrayList<>();


    private boolean INFOISSHOW = true;
    private Timer timer;

    private DeviceOrPCMAlarmListAdapter warningAdapter;
    private List<DeviceWarningBean> warningDataList = new ArrayList<>();

    private DeviceOrPcmPLCValueListAdapter PLCAdapter;
    private List<DevicePLCValueBean> devicePLCValueList = new ArrayList<>();

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
                getP().getDevicePlcValueList(projectId, deviceId);

            }
        }, 0, 10000);
       PLCGroupList.add("实时指标");
       warningGrouptList.add("实时报警");
        deviceDetailSszbList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                DevicePLCValueBean clickPlc = devicePLCValueList.get(i1);
                XLog.d(String.valueOf(clickPlc.getPropertyId()));
                Router.newIntent(context)
                        .to(PLCDetailActivity.class)
                        .putString("plcName", String.valueOf(clickPlc.getPropertyId()))
                        .putString("plcUnits",clickPlc.getUnits())
                        .putString("plcDesc",clickPlc.getDetail())
                        .launch();
                return true;
            }
        });

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
        } else if (deviceBean.getEquipmentStat() == 2) {
            deviceStatus.setText(R.string.model_running_status_2);
        } else if (deviceBean.getEquipmentStat() == 3) {
            deviceStatus.setText(R.string.device_status_2);
        }
        if (deviceBean.getBindPlcNum() == 0) {
            deviceBindPlcStatus.setText(R.string.no);
        } else {
            deviceBindPlcStatus.setText(R.string.yes);
        }
    }

    public void setDeviceWarningList(DeviceWarningListModel deviceWarningListModel) {
        if (deviceWarningListModel.getRespBody() != null) {
            warningDataList.clear();
            warningDataList.addAll(deviceWarningListModel.getRespBody());
            warningAdapter = new DeviceOrPCMAlarmListAdapter(context,warningGrouptList, warningDataList);
            deviceDetailBjxxList.setAdapter(warningAdapter);
            deviceDetailBjxxList.expandGroup(0);
        }

    }

    public void setDevicePLCValueList(DevicePLCValueListModel devicePLCValueListModel) {
        if (devicePLCValueListModel.getRespBody() != null) {
            devicePLCValueList.clear();
            devicePLCValueList.addAll(devicePLCValueListModel.getRespBody());
            PLCAdapter = new DeviceOrPcmPLCValueListAdapter(context,PLCGroupList, devicePLCValueList);
            deviceDetailSszbList.setAdapter(PLCAdapter);
            deviceDetailSszbList.expandGroup(0);

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
    public void warningReset(Integer warningId){
        getP().warningReset(warningId);
    }
    public void checkWarningReset(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0){
            ToastUtils.showToast(baseModel.getRespMsg());
            getP().getDeviceWarningList(projectId, 1, 999, deviceId);
        }
    }
}
