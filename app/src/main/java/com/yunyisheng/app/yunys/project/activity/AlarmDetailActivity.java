package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.bean.PLCValueBean;
import com.yunyisheng.app.yunys.project.model.AlarmDetailModel;
import com.yunyisheng.app.yunys.project.model.AlarmPLCDataModel;
import com.yunyisheng.app.yunys.project.model.PLCListModel;
import com.yunyisheng.app.yunys.project.present.AlarmDetailPresent;
import com.yunyisheng.app.yunys.tasks.bean.AlarmChartUpdataBean;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 报警详情
 */
public class AlarmDetailActivity extends BaseActivity<AlarmDetailPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.warning_name)
    TextView warningName;
    @BindView(R.id.warning_leveal)
    TextView warningLeveal;
    @BindView(R.id.alarm_history_project)
    TextView alarmHistoryProject;
    @BindView(R.id.alarm_history_type)
    TextView alarmHistoryType;
    @BindView(R.id.alarm_status)
    TextView alarmStatus;
    @BindView(R.id.alarm_history_time)
    TextView alarmHistoryTime;
    @BindView(R.id.alarm_update_time_title)
    TextView alarmUpdateTimeTitle;
    @BindView(R.id.alarm_update_time)
    TextView alarmUpdateTime;
    @BindView(R.id.alarm_history_desc)
    TextView alarmHistoryDesc;
    @BindView(R.id.chart_data)
    LinearLayout chartData;
    @BindView(R.id.chart_web)
    WebView chartWeb;
    @BindView(R.id.no_chart_data)
    LinearLayout noChartData;
    private String projectId;
    private String alarmId;
    private String updataString;
    private List<AlarmPLCDataModel.PLCModel> dataList;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.alarmId = getIntent().getStringExtra("alarmId");
    }

    @Override
    public void initAfter() {
        try {
            LoadingDialog.show(context);
            getP().getAlarmDetail(projectId, alarmId);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_alarm_detail;
    }

    @Override
    public AlarmDetailPresent bindPresent() {
        return new AlarmDetailPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                this.finish();
                break;
        }
    }

    public void setDetail(AlarmDetailModel.AlarmDetail detail) {
        try{
            warningName.setText(detail.getAlarmName());
            alarmHistoryTime.setText(detail.getAlarmCreateDate().substring(0,16));
            alarmHistoryProject.setText(detail.getProject().getProjectName());


            if (detail.getAlarmType() == 1){
                alarmHistoryType.setText(R.string.alarm_history_type_1);
            }else if (detail.getAlarmType() == 2){
                alarmHistoryType.setText(R.string.alarm_history_type_2);
            }else {
                alarmHistoryType.setText(R.string.alarm_history_type_3);
            }
            if (detail.getAlarmRemark() != null){
                alarmHistoryDesc.setText(detail.getAlarmRemark());
            }else {
                alarmHistoryDesc.setText("");
            }
            switch (detail.getAlarmLevel()){
                case 1:
                    warningLeveal.setText(R.string.alarm_rules_level_1);
                    warningLeveal.setBackgroundResource(R.color.alarmrules_level_1);
                    break;
                case 2:
                    warningLeveal.setText(R.string.alarm_rules_level_2);
                    warningLeveal.setBackgroundResource(R.color.alarmrules_level_2);
                    break;
                case 3:
                    warningLeveal.setText(R.string.alarm_rules_level_3);
                    warningLeveal.setBackgroundResource(R.color.alarmrules_level_3);
                    break;
                case 4:
                    warningLeveal.setText(R.string.alarm_rules_level_4);
                    warningLeveal.setBackgroundResource(R.color.alarmrules_level_4);
                    break;
            }
            switch (detail.getAlarmHandleType()){
                case 0:
                    alarmStatus.setText(R.string.alarm_handle_type_1);
                    alarmUpdateTimeTitle.setVisibility(View.GONE);
                    alarmUpdateTime.setVisibility(View.GONE);
                    break;
                case 1:
                    alarmStatus.setText(R.string.alarm_handle_type_2);
                    alarmUpdateTimeTitle.setVisibility(View.VISIBLE);
                    alarmUpdateTime.setVisibility(View.VISIBLE);
                    alarmUpdateTime.setText(detail.getAlarmUpdateDate().substring(0,16));
                    break;
                case 2:
                    alarmStatus.setText(R.string.alarm_handle_type_3);
                    alarmUpdateTimeTitle.setVisibility(View.VISIBLE);
                    alarmUpdateTime.setVisibility(View.VISIBLE);
                    alarmUpdateTime.setText(detail.getAlarmUpdateDate().substring(0,16));
                    break;
            }
            chartWeb.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            if (detail.getAlarmType() != 4 && detail.getEquList() != null && detail.getEquList().size() > 0){
                noChartData.setVisibility(View.GONE);
                chartData.setVisibility(View.VISIBLE);

                AlarmChartUpdataBean updataBean = new AlarmChartUpdataBean();
                updataBean.setOpenTime(detail.getAlarmCreateDate());
                updataBean.setEndTime(detail.getAlarmRecoveryDate());
                updataBean.setTimeExpansion("10");
                List<AlarmChartUpdataBean.PropertyList> lists = new ArrayList<>();
                for(int i=0;i<detail.getEquList().size();i++){
                    if (detail.getEquList().get(i).getPropertyList() != null && detail.getEquList().get(i).getPropertyList().size() > 0){
                        for(int j=0;j<detail.getEquList().get(i).getPropertyList().size();j++){
                            AlarmChartUpdataBean.PropertyList propertyList = new AlarmChartUpdataBean.PropertyList();
                            propertyList.setEquipmentId(detail.getEquList().get(i).getEquipmentId());
                            propertyList.setProjectId(projectId);
                            propertyList.setDetail(detail.getEquList().get(i).getPropertyList().get(j).getDetail());
                            propertyList.setPlcName(detail.getEquList().get(i).getPropertyList().get(j).getPropertyName());
                            lists.add(propertyList);
                        }

                    }

                }
                updataBean.setList(lists);
                updataString = JSON.toJSONString(updataBean);

                WebSettings plcDetailSettings = chartWeb.getSettings();
                plcDetailSettings.setJavaScriptEnabled(true);
                plcDetailSettings.setDomStorageEnabled(true);

                chartWeb.loadUrl("file:///android_asset/alarmChart.html");


                chartWeb.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        getP().getAlarmChartData(projectId,updataString);

                    }
                });
            }else {
                chartData.setVisibility(View.GONE);
                noChartData.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void LoadChartData(AlarmPLCDataModel AlarmPLCDataModel) {
        if (AlarmPLCDataModel.getRespBody().size() > 0){
            dataList = AlarmPLCDataModel.getRespBody();
            chartWeb.post(new Runnable() {
                @Override
                public void run() {
                    LogUtils.v("datalist",JSON.toJSONString(dataList));
                    chartWeb.loadUrl("javascript:createCharts('"+ JSON.toJSONString(dataList)+"')");
                }
            });

        }else {
            ToastUtils.showToast("暂无PLC数据！");
        }
    }
}
