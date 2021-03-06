package com.yunyisheng.app.yunys.project.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.project.activity.DeviceAlarmRulesActivity;
import com.yunyisheng.app.yunys.project.activity.DeviceDetailActivity;
import com.yunyisheng.app.yunys.project.activity.DevicePartsListActivity;
import com.yunyisheng.app.yunys.project.activity.KnowledgeListActivity;
import com.yunyisheng.app.yunys.project.activity.PeriodicTaskListActivity;
import com.yunyisheng.app.yunys.project.activity.ProjectDetailsActivity;
import com.yunyisheng.app.yunys.project.adapter.DeviceListAdapter;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;
import com.yunyisheng.app.yunys.project.present.DeviceListPresent;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/18.
 * 设备列表
 */

public class DeviceListFragment extends BaseFragement<DeviceListPresent> implements DeviceListAdapter.Callback {
    @BindView(R.id.device_list_view)
    PullToRefreshListView deviceListView;
    @BindView(R.id.no_data_img_devicd)
    ImageView noDataImgDevicd;
    @BindView(R.id.no_data_device)
    LinearLayout noDataDevice;
    Unbinder unbinder;

    private List<DeviceBean> deviceBeanList = new ArrayList<>();
    private String projectId;
    private String projectName;
    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 10;
    DeviceListAdapter adapter;


    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        ProjectDetailsActivity projectDetailsActivity = (ProjectDetailsActivity) getActivity();
        projectId = projectDetailsActivity.getProjectId();
        projectName = projectDetailsActivity.getProjectName();
        getP().getProjectDeviceList(projectId, PAGE_NUM, PAGE_SIZE, "");
        ScrowUtil.listViewConfig(deviceListView);
        deviceListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getProjectDeviceList(projectId, PAGE_NUM, PAGE_SIZE, "");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getProjectDeviceList(projectId, PAGE_NUM, PAGE_SIZE, "");
            }
        });
        //点击列表中的设备进入设备详情
        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DeviceBean deviceBean = deviceBeanList.get(i - 1);
                Router.newIntent(context)
                        .to(DeviceDetailActivity.class)
                        .putString("projectId", projectId)
                        .putString("deviceId", deviceBean.getEquipmentId())
                        .putString("deviceName", deviceBean.getEquipmentName())
                        .launch();
            }
        });

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_device_list;
    }

    @Override
    public DeviceListPresent bindPresent() {
        return new DeviceListPresent();
    }

    @Override
    public void setListener() {
        noDataImgDevicd.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.no_data_img_devicd:
                PAGE_NUM = 1;
                getP().getProjectDeviceList(projectId, PAGE_NUM, PAGE_SIZE, "");
                break;
        }
    }

    public DeviceListFragment newInstance(String projectId) {
        this.setProjectId(projectId);
        return new DeviceListFragment();
    }

    public void setAdapterData(DeviceListModel deviceListModel) {
        if (deviceListModel.getList().size() > 0) {
            showList();
            if (PAGE_NUM == 1) {
                deviceBeanList.clear();
                deviceBeanList.addAll(deviceListModel.getList());
                adapter = new DeviceListAdapter(context, deviceBeanList, this);
                deviceListView.setAdapter(adapter);
            } else {
                deviceBeanList.addAll(deviceListModel.getList());
                adapter.setData(deviceBeanList);
            }
        } else {
            if (PAGE_NUM == 1) {
                setNodata();
            } else {
                PAGE_NUM -= 1;
                ToastUtils.showToast("暂无更多数据");
            }
        }
        initRefresh();
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public static DeviceListFragment newInstance() {
        return new DeviceListFragment();
    }

    private void createDeviceListBtnDialog(final Activity activity, Integer position) {
        //当前点击按钮的设备
        final DeviceBean clickedDevice = deviceBeanList.get(position);

        final Dialog deviceListBtnDialog = new Dialog(activity, R.style.dialog_bottom_full);
        deviceListBtnDialog.setCanceledOnTouchOutside(true);
        deviceListBtnDialog.setCancelable(true);
        Window window = deviceListBtnDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View v = View.inflate(activity, R.layout.device_list_button_dialog, null);
        TextView toAlarmRules = v.findViewById(R.id.to_alarm_rules);
        TextView toKnowledge = v.findViewById(R.id.to_knowledge);
        TextView toDeviceParts = v.findViewById(R.id.to_device_parts);
        TextView toPeriodicTasks = v.findViewById(R.id.to_periodic_tasks);
        TextView toCreateTask = v.findViewById(R.id.to_create_task);
        ImageView btnCZClose = v.findViewById(R.id.btn_cz_close);
        //报警规则列表
        toAlarmRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(DeviceAlarmRulesActivity.class)
                        .putString("deviceId", clickedDevice.getEquipmentId())
                        .putString("projectId", projectId)
                        .putString("deviceName", clickedDevice.getEquipmentName())
                        .launch();
            }
        });
        //相关知识列表
        toKnowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(KnowledgeListActivity.class)
                        .putString("deviceId", clickedDevice.getEquipmentId())
                        .putString("projectId", projectId)
                        .putString("deviceName", clickedDevice.getEquipmentName())
                        .launch();
            }
        });
        //设备备件列表
        toDeviceParts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(DevicePartsListActivity.class)
                        .putString("deviceId", clickedDevice.getEquipmentId())
                        .putString("projectId", projectId)
                        .putString("deviceName", clickedDevice.getEquipmentName())
                        .launch();
            }
        });
        //周期任务列表
        toPeriodicTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(PeriodicTaskListActivity.class)
                        .putString("deviceId", clickedDevice.getEquipmentId())
                        .putString("projectId", projectId)
                        .putString("deviceName", clickedDevice.getEquipmentName())
                        .launch();
            }
        });
        toCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.newIntent(context)
                        .to(CreateDeviceTaskAcitvity.class)
                        .putString("projectName", projectName)
                        .putString("projectId", projectId)
                        .putInt("fromPageType", 3)
                        .putString("deviceName", clickedDevice.getEquipmentName())
                        .putString("deviceId", clickedDevice.getEquipmentId())
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

    @Override
    public void click(View v) {
        int position = (Integer) v.getTag();
        createDeviceListBtnDialog(context, position);
    }

    public void initRefresh() {
        deviceListView.onRefreshComplete();
        deviceListView.computeScroll();
    }
    public void setNodata(){
        deviceListView.setVisibility(View.GONE);
        noDataImgDevicd.setImageResource(R.mipmap.no_data);
        noDataDevice.setVisibility(View.VISIBLE);
    }
    public void setNoNetwork(){
        deviceListView.setVisibility(View.GONE);
        noDataImgDevicd.setImageResource(R.mipmap.no_network);
        noDataDevice.setVisibility(View.VISIBLE);
    }
    public void showList(){
        noDataDevice.setVisibility(View.GONE);
        deviceListView.setVisibility(View.VISIBLE);
    }

}
