package com.yunyisheng.app.yunys.project.fragement;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.project.activity.ProjectDetailsActivity;
import com.yunyisheng.app.yunys.project.adapter.MyProjectListAdapter;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.project.present.MyProjectPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/10.
 * 我参与的项目列表
 */

public class MyProjectFargment extends BaseFragement<MyProjectPresent> {
    @BindView(R.id.my_project_nums)
    TextView myProjectNums;
    @BindView(R.id.my_project_list)
    PullToRefreshListView myProjectList;
    @BindView(R.id.no_data_img)
    ImageView noDataImg;
    @BindView(R.id.no_data)
    LinearLayout noData;
    Unbinder unbinder;

    private ProjectListModel projectListModel;
    private static int PAGE_NUM = 1;
    private static int PAGE_SIZE = 10;
    private List<ProjectBean> mList = new ArrayList<>();
    MyProjectListAdapter mAdapter;

    public static MyProjectFargment newInstance() {
        MyProjectFargment fargment = new MyProjectFargment();
        return fargment;

    }

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        getP().getMyProjectList(PAGE_NUM, PAGE_SIZE, "");
        ScrowUtil.listViewConfig(myProjectList);
        myProjectList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getMyProjectList(PAGE_NUM, PAGE_SIZE, "");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getMyProjectList(PAGE_NUM, PAGE_SIZE, "");
            }
        });
        myProjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProjectBean item = mList.get(i - 1);
                XLog.d(item.toString());
                Router.newIntent(context)
                        .to(ProjectDetailsActivity.class)
                        .putString("projectId", item.getProjectId())
                        .putString("projectName", item.getProjectName())
                        .launch();
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_project_list_my;
    }

    @Override
    public MyProjectPresent bindPresent() {
        return new MyProjectPresent();
    }

    @Override
    public void setListener() {
        noDataImg.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.no_data_img:
                getP().getMyProjectList(PAGE_NUM, PAGE_SIZE, "");
                break;
        }
    }

    public void setProjectListModel(ProjectListModel projectListModel) {
        this.projectListModel = projectListModel;
        myProjectNums.setText("（" + projectListModel.getTotal() + "条）");
        if (projectListModel.getRespBody().size() > 0) {
            if (PAGE_NUM == 1) {
                mList.clear();
                mList.addAll(projectListModel.getRespBody());
                mAdapter = new MyProjectListAdapter(context, projectListModel.getRespBody());
                myProjectList.setAdapter(mAdapter);
            } else {
                mList.addAll(projectListModel.getRespBody());
                mAdapter.setData(mList);
            }
            myProjectList.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
        } else {
            if (PAGE_NUM == 1){
                setNoData();
                ToastUtils.showToast("暂无数据");
            }else {
                ToastUtils.showToast("暂无更多数据");
            }
            PAGE_NUM -= 1;
        }
        initRefresh();
    }

    public void initRefresh() {
        myProjectList.onRefreshComplete();
        myProjectList.computeScroll();
    }
    public void setNoData(){
        myProjectList.setVisibility(View.GONE);
        noDataImg.setImageResource(R.mipmap.no_data);
        noData.setVisibility(View.VISIBLE);
    }
    public void setNoNetWork(){
        myProjectList.setVisibility(View.GONE);
        noDataImg.setImageResource(R.mipmap.no_network);
        noData.setVisibility(View.VISIBLE);
    }
}
