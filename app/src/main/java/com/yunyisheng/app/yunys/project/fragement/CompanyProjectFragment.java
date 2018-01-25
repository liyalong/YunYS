package com.yunyisheng.app.yunys.project.fragement;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.project.adapter.ProjectListAdapter;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.project.present.CompanyProjectPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;

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
    List<ProjectBean> projectBeanList=new ArrayList<>();
    ProjectListAdapter adapter;


    public static CompanyProjectFragment newInstance() {
        CompanyProjectFragment fragment = new CompanyProjectFragment();
        return fragment;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        getP().getCompanyProjectList(PAGE_NUM, PAGE_SIZE);
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

    public ProjectListModel getProjectListModel() {
        return projectListModel;
    }

    public void setProjectListModel(ProjectListModel projectListModel) {
        this.projectListModel = projectListModel;
        if (projectListModel.getRespCode() == 1){
            ToastUtils.showToast(projectListModel.getRespMsg());
            return;
        }
        //设置总数
        companyProjectNums.setText("（"+projectListModel.getTotal()+"条）");
        if (projectListModel.getRespBody().size() > 0){
            if (PAGE_NUM==1){
                projectBeanList.clear();
                projectBeanList.addAll(projectListModel.getRespBody());
                adapter= new ProjectListAdapter(context,projectBeanList);
                companyProjectList.setAdapter(adapter);
            }else {
                projectBeanList.addAll(projectListModel.getRespBody());
//                ProjectListAdapter adapter = new ProjectListAdapter(context,projectBeanList);
//                companyProjectList.setAdapter(adapter);
                adapter.setData(projectBeanList);
            }
        }else {
            PAGE_NUM = projectListModel.getLastPage();
            if (PAGE_NUM == 1){
                ToastUtils.showToast("暂无数据！");
            }else {
                ToastUtils.showToast("暂无更多数据！");
            }

        }
        companyProjectList.onRefreshComplete();
        companyProjectList.computeScroll();
    }

}
