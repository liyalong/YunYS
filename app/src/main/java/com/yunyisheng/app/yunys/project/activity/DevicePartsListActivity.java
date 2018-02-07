package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.adapter.DevicePartsListAdapter;
import com.yunyisheng.app.yunys.project.bean.DevicePartsBean;
import com.yunyisheng.app.yunys.project.model.DevicePartsListModel;
import com.yunyisheng.app.yunys.project.present.DevicePartsListPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/22.
 */

public class DevicePartsListActivity extends BaseActivity<DevicePartsListPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.device_parts_list_view)
    PullToRefreshListView devicePartsListView;

    private List<DevicePartsBean> dataList = new ArrayList<>();

    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 10;

    private String projectId;
    private String deviceId;
    private String deviceName;

    private DevicePartsListAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.deviceId = getIntent().getStringExtra("deviceId");
        this.deviceName = getIntent().getStringExtra("deviceName");
        ScrowUtil.listViewConfig(devicePartsListView);
        getP().getDevicePartsList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
        devicePartsListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getDevicePartsList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getDevicePartsList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_device_parts_list;
    }

    @Override
    public DevicePartsListPresent bindPresent() {
        return new DevicePartsListPresent();
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
    public void setAdapter(DevicePartsListModel devicePartsListModel){

        if (devicePartsListModel.getRespBody().size() > 0){
            if (PAGE_NUM == 1){
                dataList.clear();
                dataList.addAll(devicePartsListModel.getRespBody());
                adapter = new DevicePartsListAdapter(context,dataList);
                devicePartsListView.setAdapter(adapter);
            }else {
                dataList.addAll(devicePartsListModel.getRespBody());
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
        initRefresh();
    }
    public void initRefresh(){
        devicePartsListView.onRefreshComplete();
        devicePartsListView.computeScroll();
    }
}
