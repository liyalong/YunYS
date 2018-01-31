package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.bean.KnowledgeBean;
import com.yunyisheng.app.yunys.project.model.KnowledgDetailModel;
import com.yunyisheng.app.yunys.project.model.KnowledgeListModel;
import com.yunyisheng.app.yunys.project.present.KnowledgeDetailPresent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liyalong on 2018/1/18.
 * 设备的相关知识详情
 */

public class KnowledgeDetailActivity extends BaseActivity<KnowledgeDetailPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.knowledge_title)
    TextView knowledgeTitle;
    @BindView(R.id.knowledge_detail)
    TextView knowledgeDetail;

    private String projectId;
    private String knowledgeId;
    private KnowledgeBean knowledgeBean;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.knowledgeId = getIntent().getStringExtra("knowledgeId");
        getP().getKnowledgeDetail(projectId,knowledgeId);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_knowledge_detail;
    }

    @Override
    public KnowledgeDetailPresent bindPresent() {
        return new KnowledgeDetailPresent();
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

    public void setAdapter(KnowledgDetailModel knowledgDetailModel) {
        knowledgeBean = knowledgDetailModel.getRespBody();
        knowledgeTitle.setText(knowledgeBean.getKnowledgeName().toString());
        knowledgeDetail.setText(Html.fromHtml(knowledgeBean.getKnowledgeBase()));
    }

}
