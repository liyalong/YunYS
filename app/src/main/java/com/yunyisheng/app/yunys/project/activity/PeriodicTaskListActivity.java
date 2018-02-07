package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.adapter.PeriodicTaskListAdapter;
import com.yunyisheng.app.yunys.project.bean.PeriodicTaskBean;
import com.yunyisheng.app.yunys.project.model.PeriodicTaskListModel;
import com.yunyisheng.app.yunys.project.present.PeriodicTaskPresent;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/22.
 */

public class PeriodicTaskListActivity extends BaseActivity<PeriodicTaskPresent> implements PeriodicTaskListAdapter.Callback{
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.periodic_task_list_view)
    PullToRefreshListView periodicTaskListView;

    private int PAGE_NUM = 1;
    private  int PAGE_SIZE = 10;

    private String projectId;
    private String deviceId;
    private String deviceName;
    private List<PeriodicTaskBean> dataList = new ArrayList<>();

    private PeriodicTaskListAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.deviceId = getIntent().getStringExtra("deviceId");
        this.deviceName = getIntent().getStringExtra("deviceName");
        ScrowUtil.listViewConfig(periodicTaskListView);
        getP().getPeriodicTaskList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
        periodicTaskListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getPeriodicTaskList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_SIZE += 1;
                getP().getPeriodicTaskList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_periodic_task_list;
    }

    @Override
    public PeriodicTaskPresent bindPresent() {
        return new PeriodicTaskPresent();
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

    public void setAdapter(PeriodicTaskListModel periodicTaskListModel){
        if (periodicTaskListModel.getRespBody().size() > 0){
            if (PAGE_NUM == 1){
                dataList.clear();
                dataList.addAll(periodicTaskListModel.getRespBody());
                adapter = new PeriodicTaskListAdapter(context,dataList,this);
                periodicTaskListView.setAdapter(adapter);
            }else {
                dataList.addAll(periodicTaskListModel.getRespBody());
                adapter.setData(dataList);
            }
        }else {
            if (PAGE_NUM == 1){
                ToastUtils.showToast("暂无数据！");
            }else {
                ToastUtils.showToast("暂无更多数据！");
            }
        }
        initRefresh();

    }
    public void initRefresh(){
        periodicTaskListView.onRefreshComplete();
        periodicTaskListView.computeScroll();
    }

    @Override
    public void click(View v) {
        int tag = (int) v.getTag();
        PeriodicTaskBean clickTask = dataList.get(tag);
        Router.newIntent(context)
                .to(CreateDeviceTaskAcitvity.class)
                .putInt("fromPageType",2)
                .putString("taskId", String.valueOf(clickTask.getCycletaskId()))
                .putString("projectId",clickTask.getProjectId())
                .launch();
    }
}
