package com.yunyisheng.app.yunys.project.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/18.
 * 设备列表
 */

public class DeviceListFragment extends BaseFragement<DeviceListPresent> implements DeviceListAdapter.Callback{
    @BindView(R.id.device_list_view)
    PullToRefreshListView deviceListView;

    private List<DeviceBean> deviceBeanList = new ArrayList<>();
    private String projectId;
    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 10;
    DeviceListAdapter adapter;


    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        ProjectDetailsActivity projectDetailsActivity = (ProjectDetailsActivity) getActivity();
        projectId = projectDetailsActivity.getProjectId();
        getP().getProjectDeviceList(projectId,PAGE_NUM,PAGE_SIZE,"");
        ScrowUtil.listViewConfig(deviceListView);
        deviceListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getProjectDeviceList(projectId,PAGE_NUM,PAGE_SIZE,"");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getProjectDeviceList(projectId,PAGE_NUM,PAGE_SIZE,"");
            }
        });
        //点击列表中的设备进入设备详情
        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DeviceBean deviceBean= deviceBeanList.get(i-1);
                ToastUtils.showToast(deviceBean.getEquipmentName());
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
        return R.layout.fragment_device_list;
    }

    @Override
    public DeviceListPresent bindPresent() {
        return new DeviceListPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public DeviceListFragment newInstance(String projectId) {
        this.setProjectId(projectId);
        return new DeviceListFragment();
    }

    public void setAdapterData(DeviceListModel deviceListModel) {
        if (deviceListModel.getRespCode() == 1){
            ToastUtils.showToast(deviceListModel.getRespMsg());
            return;
        }
        if (deviceListModel.getList().size() > 0){
            if (PAGE_NUM == 1){
                deviceBeanList.clear();
                deviceBeanList.addAll(deviceListModel.getList());
                adapter = new DeviceListAdapter(context,deviceBeanList,this);
                deviceListView.setAdapter(adapter);
            }else {
                deviceBeanList.addAll(deviceListModel.getList());
                adapter.setData(deviceBeanList);
            }
        }else {
            if (PAGE_NUM == 1){
                ToastUtils.showToast("暂无数据！");
                return;
            }else {
                PAGE_NUM = deviceListModel.getLastPage();
                ToastUtils.showToast("暂无更多数据");
            }
        }
        deviceListView.computeScroll();

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

    private void createDeviceListBtnDialog(final Activity activity,Integer position){
        //当前点击按钮的设备
        final DeviceBean clickedDevice = deviceBeanList.get(position);

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
        XLog.d( "listview的内部的按钮被点击了！，位置是-->" + (Integer) v.getTag() +
                ",内容是-->" + deviceBeanList.get((Integer) v.getTag()).getEquipmentName());
        int position = (Integer) v.getTag();
        createDeviceListBtnDialog(context,position);
    }
}
