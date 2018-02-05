package com.yunyisheng.app.yunys.project.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.adapter.ModelAlarmRulesAdapter;
import com.yunyisheng.app.yunys.project.bean.ModelAlarmRulesBean;
import com.yunyisheng.app.yunys.project.model.ModelAlarmRulesListModel;
import com.yunyisheng.app.yunys.project.present.ModelAlarmRulesPresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModelAlarmRulesActivity extends BaseActivity<ModelAlarmRulesPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.model_alarm_rules_list)
    PullToRefreshListView modelAlarmRulesList;

    private int PAGE_NUM = 1;
    private final int PAGE_SIZE = 10;
    private String projectId;
    private String modelId;
    private String modelName;

    private ModelAlarmRulesAdapter adapter;
    private List<ModelAlarmRulesBean> dataList = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ScrowUtil.listViewConfig(modelAlarmRulesList);
        this.projectId = getIntent().getStringExtra("projectId");
        this.modelId = getIntent().getStringExtra("modelId");
        this.modelName = getIntent().getStringExtra("modelName");
        getP().getModelAlarmRulesList(projectId, modelId, PAGE_NUM, PAGE_SIZE);
        modelAlarmRulesList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM = 1;
                getP().getModelAlarmRulesList(projectId, modelId, PAGE_NUM, PAGE_SIZE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PAGE_NUM += 1;
                getP().getModelAlarmRulesList(projectId, modelId, PAGE_NUM, PAGE_SIZE);
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_model_alarm_rules;
    }

    @Override
    public ModelAlarmRulesPresent bindPresent() {
        return new ModelAlarmRulesPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }

    }

    public void setAdapter(ModelAlarmRulesListModel modelAlarmRulesListModel) {
        if (modelAlarmRulesListModel.getRespBody().size() > 0) {
            if (PAGE_NUM == 1) {
                dataList.clear();
                dataList.addAll(modelAlarmRulesListModel.getRespBody());
                adapter = new ModelAlarmRulesAdapter(context, dataList);
                modelAlarmRulesList.setAdapter(adapter);
            } else {
                dataList.addAll(modelAlarmRulesListModel.getRespBody());
                adapter.setData(dataList);
            }
        } else {
            if (PAGE_NUM == 1) {
                ToastUtils.showToast("暂无数据！");
            } else {
                PAGE_NUM -= 1;
                ToastUtils.showToast("暂无更多数据！");
            }
        }
        initRefresh();
    }
    public void initRefresh(){
        modelAlarmRulesList.onRefreshComplete();
        modelAlarmRulesList.computeScroll();
    }
}
