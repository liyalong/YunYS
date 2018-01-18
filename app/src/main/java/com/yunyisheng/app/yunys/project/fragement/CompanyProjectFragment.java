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
import com.yunyisheng.app.yunys.project.adapter.ProjectListAdapter;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.project.present.CompanyProjectPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liyalong on 2018/1/10.
 */

public class CompanyProjectFragment extends BaseFragement<CompanyProjectPresent> {

    @BindView(R.id.company_project_nums)
    TextView companyProjectNums;
    @BindView(R.id.company_project_list)
    PullToRefreshListView companyProjectList;

    private ProjectListModel projectListModel;
    private static int PAGE_NUM = 1;
    private static int PAGE_SIZE = 10;

    public static CompanyProjectFragment newInstance() {
        CompanyProjectFragment fragment = new CompanyProjectFragment();
        return fragment;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        ScrowUtil.listViewConfig(companyProjectList);

        companyProjectList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getCompanyProjectList(PAGE_NUM, PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getCompanyProjectList(PAGE_NUM, PAGE_SIZE);

            }
        });
        getP().getCompanyProjectList(PAGE_NUM, PAGE_SIZE);
        //设置总数

//        companyProjectNums.setText(projectListModel.getTotal());
//        ProjectListAdapter adapter = new ProjectListAdapter(context,projectListModel.getList());
//        companyProjectList.setAdapter(adapter);
    }


    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fargment_project_list_company;
    }

    @Override
    public CompanyProjectPresent bindPresent() {
        return new CompanyProjectPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public ProjectListModel getProjectListModel() {
        return projectListModel;
    }

    public void setProjectListModel(ProjectListModel projectListModel) {
        this.projectListModel = projectListModel;
    }

}
