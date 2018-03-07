package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.adapter.KnowledgeListAdapter;
import com.yunyisheng.app.yunys.project.bean.KnowledgeBean;
import com.yunyisheng.app.yunys.project.model.KnowledgeListModel;
import com.yunyisheng.app.yunys.project.present.KnowledgeListPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/22.
 * 设备相关知识列表
 */

public class KnowledgeListActivity extends BaseActivity<KnowledgeListPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.knowledge_title)
    TextView knowledgeTitle;
    @BindView(R.id.include_title_name)
    TextView includeTitleName;
    @BindView(R.id.knowledge_list)
    PullToRefreshListView knowledgeList;
    @BindView(R.id.no_data_img)
    ImageView noDataImg;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private String projectId;
    private String deviceId;
    private String deviceName;

    private String modelId;
    private String modelName;

    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 10;

    KnowledgeListModel knowledgeListModel;
    List<KnowledgeBean> dataList = new ArrayList<>();

    KnowledgeListAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.deviceId = getIntent().getStringExtra("deviceId");
        this.deviceName = getIntent().getStringExtra("deviceName");
        this.modelId = getIntent().getStringExtra("modelId");
        this.modelName = getIntent().getStringExtra("modelName");
        ScrowUtil.listViewConfig(knowledgeList);
        knowledgeList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                if (deviceId != null) {
                    getP().getKnowledgeList(projectId, deviceId, PAGE_NUM, PAGE_SIZE);
                } else {
                    getP().getModelKnowledgeList(projectId, modelId, PAGE_NUM, PAGE_SIZE);
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                if (deviceId != null) {
                    getP().getKnowledgeList(projectId, deviceId, PAGE_NUM, PAGE_SIZE);
                } else {
                    getP().getModelKnowledgeList(projectId, modelId, PAGE_NUM, PAGE_SIZE);
                }
            }
        });
        knowledgeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                KnowledgeBean knowledgeBean = dataList.get(i - 1);
                Router.newIntent(context)
                        .to(KnowledgeDetailActivity.class)
                        .putString("projectId", projectId)
                        .putString("knowledgeId", String.valueOf(knowledgeBean.getKnowledgeId()))
                        .launch();
            }
        });

    }

    @Override
    public void initAfter() {

        if (deviceId != null) {
            knowledgeTitle.setText(deviceName + "相关知识");
            getP().getKnowledgeList(projectId, deviceId, PAGE_NUM, PAGE_SIZE);
        } else if (modelId != null) {
            knowledgeTitle.setText(modelName + "相关知识");
            getP().getModelKnowledgeList(projectId, modelId, PAGE_NUM, PAGE_SIZE);
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_knowledge;
    }

    @Override
    public KnowledgeListPresent bindPresent() {
        return new KnowledgeListPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        noDataImg.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.no_data_img:
                if (deviceId != null) {
                    getP().getKnowledgeList(projectId, deviceId, PAGE_NUM, PAGE_SIZE);
                } else {
                    getP().getModelKnowledgeList(projectId, modelId, PAGE_NUM, PAGE_SIZE);
                }
                break;
        }
    }

    public void setAdapter(KnowledgeListModel knowledgeListModel) {
        this.knowledgeListModel = knowledgeListModel;
        if (knowledgeListModel.getRespBody().size() > 0) {
            showList();
            if (PAGE_NUM == 1) {
                dataList.clear();
                dataList.addAll(knowledgeListModel.getRespBody());
                if (adapter == null) {
                    adapter = new KnowledgeListAdapter(context, dataList);
                }
                knowledgeList.setAdapter(adapter);
            } else {
                dataList.addAll(knowledgeListModel.getRespBody());
                adapter.setData(dataList);
            }
        } else {
            if (PAGE_NUM == 1) {
                setNoData();
                ToastUtils.showToast("暂无数据！");
            } else {
                PAGE_NUM -= 1;
                ToastUtils.showToast("暂无更多数据！");
            }
        }
        initRefresh();
    }

    public void initRefresh() {
        knowledgeList.onRefreshComplete();
        knowledgeList.computeScroll();
    }

    public void setNoData(){
        knowledgeList.setVisibility(View.GONE);
        noDataImg.setImageResource(R.mipmap.no_data);
        noData.setVisibility(View.VISIBLE);
    }
    public void setNoNetwork(){
        knowledgeList.setVisibility(View.GONE);
        noDataImg.setImageResource(R.mipmap.no_network);
        noData.setVisibility(View.VISIBLE);
    }
    public void showList(){
        noData.setVisibility(View.GONE);
        knowledgeList.setVisibility(View.VISIBLE);
    }
}
