package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/22.
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
        if (deviceId != null){
            knowledgeTitle.setText(deviceName+"相关知识");
            getP().getKnowledgeList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
            knowledgeList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    PAGE_NUM = 1;
                    getP().getKnowledgeList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    PAGE_NUM += 1;
                    getP().getKnowledgeList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
                }
            });
        }else if (modelId != null){
            knowledgeTitle.setText(modelName+"相关知识");
            getP().getKnowledgeList(projectId,modelId,PAGE_NUM,PAGE_SIZE);

            knowledgeList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    PAGE_NUM = 1;
                    getP().getKnowledgeList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    PAGE_NUM += 1;
                    getP().getKnowledgeList(projectId,deviceId,PAGE_NUM,PAGE_SIZE);
                }
            });
        }

    }

    @Override
    public void initAfter() {

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
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }

    public void setAdapter(KnowledgeListModel knowledgeListModel){
        this.knowledgeListModel = knowledgeListModel;
        //TODO 调试知识内容
    }
}
