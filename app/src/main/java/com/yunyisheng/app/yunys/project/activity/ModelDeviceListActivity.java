package com.yunyisheng.app.yunys.project.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.adapter.DeviceListAdapter;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;
import com.yunyisheng.app.yunys.project.present.ModelDeviceListPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 工艺模块下的设备列表
 */
public class ModelDeviceListActivity extends BaseActivity<ModelDeviceListPresent> implements DeviceListAdapter.Callback {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.model_device_list_view)
    PullToRefreshListView modelDeviceListView;

    private String projectId;
    private String modelId;
    private String modelName;

    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 10;

    private DeviceListAdapter adapter;
    private List<DeviceBean> dataList = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.modelId = getIntent().getStringExtra("modelId");
        this.modelName = getIntent().getStringExtra("modelName");
        getP().getModelDeviceList(projectId,modelId,PAGE_NUM,PAGE_SIZE);
        ScrowUtil.listViewConfig(modelDeviceListView);
        modelDeviceListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getModelDeviceList(projectId,modelId,PAGE_NUM,PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getModelDeviceList(projectId,modelId,PAGE_NUM,PAGE_SIZE);
            }
        });
        modelDeviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DeviceBean deviceBean = dataList.get(i-1);
                Router.newIntent(context)
                        .to(DeviceDetailActivity.class)
                        .putString("projectId",projectId)
                        .putString("deviceId",deviceBean.getEquipmentId())
                        .putString("deviceName",deviceBean.getEquipmentName())
                        .launch();
            }
        });

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_model_device_list;
    }

    @Override
    public ModelDeviceListPresent bindPresent() {
        return new ModelDeviceListPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }

    public void setAdapter(DeviceListModel deviceListModel){
        if (deviceListModel.getList().size() > 0){
            if (PAGE_NUM == 1){
                dataList.clear();
                dataList.addAll(deviceListModel.getList());
                adapter = new DeviceListAdapter(context,dataList,this);
                modelDeviceListView.setAdapter(adapter);
            }else {
                dataList.addAll(deviceListModel.getList());
                adapter.setData(dataList);
            }
        }else {
            if (PAGE_NUM == 1){
                ToastUtils.showToast("暂无数据！");
            }else {
                PAGE_NUM -= 1;
                ToastUtils.showToast("暂无更多数据！");
            }
        }
        modelDeviceListView.onRefreshComplete();
        modelDeviceListView.computeScroll();
    }

    @Override
    public void click(View v) {
        int position = (Integer) v.getTag();
        createDeviceListBtnDialog(context,position);
    }
    private void createDeviceListBtnDialog(final Activity activity, Integer position){
        //当前点击按钮的设备
        final DeviceBean clickedDevice = dataList.get(position);

        final Dialog deviceListBtnDialog = new Dialog(activity,R.style.dialog_bottom_full);
        deviceListBtnDialog.setCanceledOnTouchOutside(true);
        deviceListBtnDialog.setCancelable(true);
        Window window = deviceListBtnDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View v = View.inflate(activity,R.layout.device_list_button_dialog,null);
        TextView toAlarmRules = v.findViewById(R.id.to_alarm_rules);
        TextView toKnowledge = v.findViewById(R.id.to_knowledge);
        TextView toDeviceParts = v.findViewById(R.id.to_device_parts);
        TextView toPeriodicTasks = v.findViewById(R.id.to_periodic_tasks);
        ImageView btnCZClose = v.findViewById(R.id.btn_cz_close);
        //报警规则列表
        toAlarmRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(DeviceAlarmRulesActivity.class)
                        .putString("deviceId",clickedDevice.getEquipmentId())
                        .putString("projectId",projectId)
                        .putString("deviceName",clickedDevice.getEquipmentName())
                        .launch();
            }
        });
        //相关知识列表
        toKnowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(KnowledgeListActivity.class)
                        .putString("deviceId",clickedDevice.getEquipmentId())
                        .putString("projectId",projectId)
                        .putString("deviceName",clickedDevice.getEquipmentName())
                        .launch();
            }
        });
        //设备备件列表
        toDeviceParts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(DevicePartsListActivity.class)
                        .putString("deviceId",clickedDevice.getEquipmentId())
                        .putString("projectId",projectId)
                        .putString("deviceName",clickedDevice.getEquipmentName())
                        .launch();
            }
        });
        //周期任务列表
        toPeriodicTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(PeriodicTaskListActivity.class)
                        .putString("deviceId",clickedDevice.getEquipmentId())
                        .putString("projectId",projectId)
                        .putString("deviceName",clickedDevice.getEquipmentName())
                        .launch();
            }
        });
        //关闭按钮
        btnCZClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deviceListBtnDialog.hide();
            }
        });

        window.setContentView(v);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        deviceListBtnDialog.show();

    }
}
