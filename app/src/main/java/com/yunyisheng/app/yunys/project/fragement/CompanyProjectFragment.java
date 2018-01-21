package com.yunyisheng.app.yunys.project.fragement;

import android.view.View;
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
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liyalong on 2018/1/10.
 * 公司项目列表
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
        getP().getCompanyProjectList(PAGE_NUM, PAGE_SIZE);


    }


    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_project_list_company;
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

    public void setProjectListAdapter(ProjectListModel projectListModel) {

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

        //设置总数

        //companyProjectNums.setText(projectListModel.getTotal());
        if (projectListModel.getRespBody().size() > 0){
            ProjectListAdapter adapter = new ProjectListAdapter(context,projectListModel.getRespBody());
            companyProjectList.setAdapter(adapter);
        }else {
            ToastUtils.showToast("暂无数据！");
        }

    }

}
