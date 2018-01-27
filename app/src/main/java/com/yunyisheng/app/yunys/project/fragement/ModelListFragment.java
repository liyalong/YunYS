package com.yunyisheng.app.yunys.project.fragement;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.project.activity.ModelDetailActivity;
import com.yunyisheng.app.yunys.project.activity.ProjectDetailsActivity;
import com.yunyisheng.app.yunys.project.adapter.ModelListAdapter;
import com.yunyisheng.app.yunys.project.bean.ModelInfoBean;
import com.yunyisheng.app.yunys.project.model.ModelListModel;
import com.yunyisheng.app.yunys.project.present.ModelListPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/18.
 * 工艺模块列表
 */

public class ModelListFragment extends BaseFragement<ModelListPresent> {
    @BindView(R.id.model_list_view)
    PullToRefreshListView modelListView;

    private static int PAGE_NUM = 1;
    private static int PAGE_SIZE = 10;
    private String projectId;

    private List<ModelInfoBean> dataList = new ArrayList<>();

    private ModelListAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        ProjectDetailsActivity projectDetailsActivity = (ProjectDetailsActivity) getActivity();
        this.projectId = projectDetailsActivity.getProjectId();
        ScrowUtil.listViewConfig(modelListView);
        getP().getModelList(projectId,PAGE_NUM,PAGE_SIZE);
        modelListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getModelList(projectId,PAGE_NUM,PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_SIZE += 1;
                getP().getModelList(projectId,PAGE_NUM,PAGE_SIZE);
            }
        });
        modelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelInfoBean modelInfoBean = dataList.get(i-1);
                Router.newIntent(context)
                        .to(ModelDetailActivity.class)
                        .putString("projectId",projectId)
                        .putString("modelId",modelInfoBean.getPcmId())
                        .putString("modelName",modelInfoBean.getPcmName())
                        .launch();
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_device_model;
    }

    @Override
    public ModelListPresent bindPresent() {
        return new ModelListPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public static ModelListFragment newInstance() {
        return new ModelListFragment();
    }

    public void setAdapter(ModelListModel modelListModel){
        if (modelListModel.getRespBody().size() > 0){
            if (PAGE_NUM == 1){
                dataList.clear();
                dataList.addAll(modelListModel.getRespBody());
                adapter = new ModelListAdapter(context,dataList);
                modelListView.setAdapter(adapter);
            }else {
                dataList.addAll(modelListModel.getRespBody());
                adapter.setData(dataList);
            }
        }else {
            if (PAGE_NUM == 1){
                ToastUtils.showToast("暂无数据！");
            }else {
                ToastUtils.showToast("暂无更多数据！");
            }
        }
        modelListView.onRefreshComplete();
        modelListView.computeScroll();
    }
}
