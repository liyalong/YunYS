package com.yunyisheng.app.yunys.project.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.adapter.DeviceOrPCMAlarmListAdapter;
import com.yunyisheng.app.yunys.project.adapter.DeviceOrPcmPLCValueListAdapter;
import com.yunyisheng.app.yunys.project.bean.DevicePLCValueBean;
import com.yunyisheng.app.yunys.project.bean.DeviceWarningBean;
import com.yunyisheng.app.yunys.project.bean.ModelInfoBean;
import com.yunyisheng.app.yunys.project.model.DevicePLCValueListModel;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.project.model.ModelDetailModel;
import com.yunyisheng.app.yunys.project.present.ModelDetailPresent;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.SuperExpandableListView;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.glide.GlideDownLoadImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/25.
 */

public class ModelDetailActivity extends BaseActivity<ModelDetailPresent> implements DeviceOrPCMAlarmListAdapter.Callback {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.model_detail_title)
    TextView modelDetailTitle;
    @BindView(R.id.model_pic)
    ImageView modelPic;
    @BindView(R.id.model_detail_jbxx_drop)
    ImageView modelDetailJbxxDrop;
    @BindView(R.id.device_model_detail_status)
    TextView deviceModelDetailStatus;
    @BindView(R.id.to_model_device_list)
    LinearLayout toModelDeviceList;
    @BindView(R.id.to_model_alarm_rules)
    LinearLayout toModelAlarmRules;
    @BindView(R.id.model_detail_jbxx_box)
    LinearLayout modelDetailJbxxBox;
    @BindView(R.id.to_model_knowledge)
    LinearLayout toModelKnowledge;
    @BindView(R.id.model_pic_drop)
    ImageView modelPicDrop;
    @BindView(R.id.model_pic_box)
    RelativeLayout modelPicBox;
    @BindView(R.id.bjxx_list)
    SuperExpandableListView bjxxList;
    @BindView(R.id.sszb_list)
    SuperExpandableListView sszbList;


    private List<String> PLCGroupList = new ArrayList<>();
    private List<String> warningGrouptList = new ArrayList<>();

    private boolean jbxxBoxIsshow = true;
    private boolean MODELPICISSHOW = true;

    private String projectId;
    private String modelId;
    private String modelName;
    private Timer timer;
    private DeviceOrPCMAlarmListAdapter warningAdapter;
    private List<DeviceWarningBean> warningDataList = new ArrayList<>();

    private DeviceOrPcmPLCValueListAdapter PLCAdapter;
    private List<DevicePLCValueBean> devicePLCValueList = new ArrayList<>();
    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.modelId = getIntent().getStringExtra("modelId");
        this.modelName = getIntent().getStringExtra("modelName");

        PLCGroupList.add("实时指标");
        warningGrouptList.add("实时报警");

        getP().getModelDetail(projectId, modelId);
        //实时报警列表
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getP().getModelWarningList(projectId, 1, 10, modelId);
                getP().getModelPlcValueList(projectId, modelId);

            }
        }, 0, 10000);
        sszbList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                DevicePLCValueBean clickPlc = devicePLCValueList.get(i1);
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
        return R.layout.activity_model_detail;
    }

    @Override
    public ModelDetailPresent bindPresent() {
        return new ModelDetailPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        modelDetailJbxxDrop.setOnClickListener(this);
        modelPicDrop.setOnClickListener(this);
        toModelDeviceList.setOnClickListener(this);
        toModelAlarmRules.setOnClickListener(this);
        toModelKnowledge.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                stopTimer();
                finish();
                break;
            case R.id.model_detail_jbxx_drop:
                if (jbxxBoxIsshow == true) {
                    modelDetailJbxxBox.setVisibility(View.GONE);
                    modelDetailJbxxDrop.setImageResource(R.mipmap.icon_device_right);
                    jbxxBoxIsshow = false;
                } else {
                    modelDetailJbxxBox.setVisibility(View.VISIBLE);
                    modelDetailJbxxDrop.setImageResource(R.mipmap.icon_device_down);
                    jbxxBoxIsshow = true;
                }
                break;
            case R.id.model_pic_drop:
                if (MODELPICISSHOW){
                    modelPicBox.setVisibility(View.GONE);
                    modelPicDrop.setImageResource(R.mipmap.icon_device_right);
                    MODELPICISSHOW = false;
                }else {
                    modelPicBox.setVisibility(View.VISIBLE);
                    modelPicDrop.setImageResource(R.mipmap.icon_device_down);
                    MODELPICISSHOW = true;
                }
                break;
            case R.id.to_model_alarm_rules:
                Router.newIntent(context)
                        .to(ModelAlarmRulesActivity.class)
                        .putString("projectId",projectId)
                        .putString("modelId",modelId)
                        .putString("modelName",modelName)
                        .launch();
                break;
            case R.id.to_model_device_list:
                Router.newIntent(context)
                        .to(ModelDeviceListActivity.class)
                        .putString("projectId",projectId)
                        .putString("modelId",modelId)
                        .putString("modelName",modelName)
                        .launch();
                break;
            case R.id.to_model_knowledge:
                Router.newIntent(context)
                        .to(KnowledgeListActivity.class)
                        .putString("projectId",projectId)
                        .putString("modelId",modelId)
                        .putString("modelName",modelName)
                        .launch();
                break;
        }
    }

    public void setModelInfo(ModelDetailModel modelDetailModel) {
        ModelInfoBean modelInfoBean = modelDetailModel.getRespBody();
        String modelPicValue = modelInfoBean.getPcmBlueprint();
        if (modelPicValue != null && modelPicValue != "") {
            Bitmap bitmap = CommonUtils.stringtoBitmap(modelPicValue);
            GlideDownLoadImage.getInstance().loadBitmapImageRole(mContext, modelPic, bitmap);
        }
    }

    public void setModelWarningList(DeviceWarningListModel deviceWarningListModel) {
        if (deviceWarningListModel.getRespBody() != null) {
            warningDataList.clear();
            warningDataList.addAll(deviceWarningListModel.getRespBody());
            warningAdapter = new DeviceOrPCMAlarmListAdapter(context,warningGrouptList, warningDataList,this);
            bjxxList.setAdapter(warningAdapter);
            bjxxList.expandGroup(0);
        }
    }

    public void setModelPLCValueList(DevicePLCValueListModel devicePLCValueListModel) {
        if (devicePLCValueListModel.getRespBody() != null) {
            devicePLCValueList.clear();
            devicePLCValueList.addAll(devicePLCValueListModel.getRespBody());
            PLCAdapter = new DeviceOrPcmPLCValueListAdapter(context,PLCGroupList, devicePLCValueList);
            sszbList.setAdapter(PLCAdapter);
            sszbList.expandGroup(0);

        }
    }

    public void warningReset(Integer warningId){
        getP().warningReset(warningId);
    }
    public void checkWarningReset(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0){
            ToastUtils.showToast(baseModel.getRespMsg());
            getP().getModelWarningList(projectId, 1, 999, modelId);
        }
    }
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void click(View v) {
        Integer clickPostion = (Integer) v.getTag();
        warningReset(Integer.valueOf(warningDataList.get(clickPostion).getAlarmId()));
    }
}
