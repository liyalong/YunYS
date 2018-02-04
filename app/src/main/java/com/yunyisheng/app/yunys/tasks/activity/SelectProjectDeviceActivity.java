package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;
import com.yunyisheng.app.yunys.project.model.DeviceListModel;
import com.yunyisheng.app.yunys.tasks.adapter.SelectProjectDeviceAdapter;
import com.yunyisheng.app.yunys.tasks.present.SelectProjectDeviceListPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/18.
 * 选择设备列表
 */

public class SelectProjectDeviceActivity extends BaseActivity<SelectProjectDeviceListPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.select_project_device_list)
    ListView selectProjectDeviceList;

    private static int PAGE_NUM = 1;
    private static int PAGE_SIZE = 9999;
    private String projectId;
    private List<DeviceBean> dataList = new ArrayList<>();
    private SelectProjectDeviceAdapter adapter;
    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        getP().getProjectDeviceList(projectId,PAGE_NUM,PAGE_SIZE,"");

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_select_project_device;
    }

    @Override
    public SelectProjectDeviceListPresent bindPresent() {
        return new SelectProjectDeviceListPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
        searchText.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.img_back:
                setResult(2,intent);
                break;
            case R.id.submit:
                int selectPostion = adapter.getSelectPosition();
                if (selectPostion == -1){
                    setResult(2,intent);
                }else {
                    DeviceBean selectDevice = dataList.get(selectPostion);
                    intent.putExtra("selectDeviceId",selectDevice.getEquipmentId());
                    intent.putExtra("selectDeviceName",selectDevice.getEquipmentName());
                    setResult(1,intent);
                }
                break;
            case R.id.search_text:
                break;
        }
        if (v.getId() != R.id.search_text){
            finish();
        }
    }

    public void setAdapterData(DeviceListModel deviceListModel) {
            if (deviceListModel.getRespBody().size() > 0){
                dataList.clear();
                dataList.addAll(deviceListModel.getRespBody());
                adapter = new SelectProjectDeviceAdapter(context,dataList);
                selectProjectDeviceList.setAdapter(adapter);
            }else {
                ToastUtils.showToast("该项目下暂无设备！");
            }
    }
}
