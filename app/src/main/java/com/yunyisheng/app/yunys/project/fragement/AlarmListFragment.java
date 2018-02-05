package com.yunyisheng.app.yunys.project.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.project.activity.ProjectDetailsActivity;
import com.yunyisheng.app.yunys.project.adapter.AlarmListAdapter;
import com.yunyisheng.app.yunys.project.bean.DeviceWarningBean;
import com.yunyisheng.app.yunys.project.model.DeviceWarningListModel;
import com.yunyisheng.app.yunys.project.present.AlarmListPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/18.
 * 报警记录列表
 */

public class AlarmListFragment extends BaseFragement<AlarmListPresent> {


    @BindView(R.id.alarm_history_list)
    PullToRefreshListView alarmHistoryList;
    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 10;
    private List<DeviceWarningBean> dataList = new ArrayList<>();
    private AlarmListAdapter adapter;
    private String projectId;

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        ProjectDetailsActivity projectDetailsActivity = (ProjectDetailsActivity) getActivity();
        this.projectId = projectDetailsActivity.getProjectId();

        ScrowUtil.listViewConfig(alarmHistoryList);
        alarmHistoryList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getAlarmLists(projectId,PAGE_NUM,PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getAlarmLists(projectId,PAGE_NUM,PAGE_SIZE);
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getAlarmLists(projectId,PAGE_NUM,PAGE_SIZE);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_alarm_history;
    }

    @Override
    public AlarmListPresent bindPresent() {
        return new AlarmListPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public static AlarmListFragment newInstance() {
        return new AlarmListFragment();
    }

    public void setAdapterData(DeviceWarningListModel deviceWarningListModel) {
        if (deviceWarningListModel.getRespBody().size() > 0){
            if (PAGE_NUM == 1){
                dataList.clear();
                dataList.addAll(deviceWarningListModel.getRespBody());
                adapter = new AlarmListAdapter(context,dataList);
                alarmHistoryList.setAdapter(adapter);
            }else {
                dataList.addAll(deviceWarningListModel.getRespBody());
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
        alarmHistoryList.onRefreshComplete();
        alarmHistoryList.computeScroll();
    }

}
