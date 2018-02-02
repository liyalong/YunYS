package com.yunyisheng.app.yunys.main.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.activity.WorkerDataActivity;
import com.yunyisheng.app.yunys.main.adapter.HomeScheduleAdapter;
import com.yunyisheng.app.yunys.main.present.WorkerSchedulePresent;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.yunyisheng.app.yunys.utils.CommonUtils.getDayEndTime;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getDayStartTime;

/**
 * 作者：fuduo on 2018/1/12 12:12
 * 邮箱：duoendeavor@163.com
 * 用途：员工日程fragement
 */

public class ScheduleFragement extends BaseFragement<WorkerSchedulePresent> {
    @BindView(R.id.te_columnsize)
    TextView teColumnsize;
    Unbinder unbinder;
    @BindView(R.id.pull_to_list_schudle)
    PullToRefreshListView pullToListSchudle;
    private int userid;
    private long dayStartTime;
    private long dayEndTime;
    private int pageindex = 1;
    private List<MyScheduleBean.RespBodyBean.DataListBean> dataListBeans = new ArrayList<>();
    private HomeScheduleAdapter adapter;
    private boolean isfirst = true;

    @Override
    public void initView() {
        ScrowUtil.listViewConfig(pullToListSchudle);
        userid =((WorkerDataActivity) getActivity()).userid;
        dayStartTime = getDayStartTime();
        dayEndTime = getDayEndTime();
        adapter = new HomeScheduleAdapter(mContext, dataListBeans);
        pullToListSchudle.setAdapter(adapter);
        pullToListSchudle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex = 1;
                dataListBeans.clear();
                getP().getWorkerScheduleList(pageindex, userid, dayStartTime, dayEndTime);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex++;
                getP().getWorkerScheduleList(pageindex, userid, dayStartTime, dayEndTime);
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getWorkerScheduleList(pageindex, userid, dayStartTime, dayEndTime);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_schedule;
    }

    @Override
    public WorkerSchedulePresent bindPresent() {
        return new WorkerSchedulePresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getWorkerSchedule(MyScheduleBean myScheduleBean) {
        if (isfirst) {
            isfirst = false;
            int total = myScheduleBean.getRespBody().getTotal();
            teColumnsize.setText("(" + total + ")");
        }

        List<MyScheduleBean.RespBodyBean.DataListBean> dataList = myScheduleBean.getRespBody().getDataList();
        if (dataList != null && dataList.size() > 0) {
            dataListBeans.addAll(dataList);
            adapter.setData(dataListBeans);
            adapter.setOtheruserid(userid);
        } else {
            if (pageindex == 1) {
                ToastUtils.showToast("暂无日程");
            } else {
                ToastUtils.showToast("没有更多了");
            }
        }
        stopRefresh();
    }

    public void stopRefresh(){
        pullToListSchudle.onRefreshComplete();
    }

}
