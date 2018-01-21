package com.yunyisheng.app.yunys.project.fragement;

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
import com.yunyisheng.app.yunys.project.adapter.MyProjectListAdapter;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.project.present.MyProjectPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/10.
 * 我参与的项目列表
 */

public class MyProjectFargment extends BaseFragement<MyProjectPresent> {
    @BindView(R.id.my_project_nums)
    TextView myProjectNums;
    @BindView(R.id.my_project_list)
    PullToRefreshListView myProjectList;

    private ProjectListModel projectListModel;
    private static int PAGE_NUM = 1;
    private static int PAGE_SIZE = 10;


    public static MyProjectFargment newInstance() {
        MyProjectFargment fargment = new MyProjectFargment();
        return fargment;

    }

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        getP().getMyProjectList(PAGE_NUM,PAGE_SIZE,"");
        ScrowUtil.listViewConfig(myProjectList);
        myProjectList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getMyProjectList(PAGE_NUM,PAGE_SIZE,"");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getMyProjectList(PAGE_NUM,PAGE_SIZE,"");
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

    }

    @Override
    public void widgetClick(View v) {

    }

    public void setProjectListModel(ProjectListModel projectListModel) {
        this.projectListModel = projectListModel;
        myProjectNums.setText("（"+projectListModel.getTotal()+"条）");
        if (projectListModel.getRespBody().size() > 0){
            MyProjectListAdapter mAdapter = new MyProjectListAdapter(context,projectListModel.getRespBody());
            myProjectList.setAdapter(mAdapter);
        }else {
            ToastUtils.showToast("暂无数据");
        }
        myProjectList.onRefreshComplete();

    }
}
