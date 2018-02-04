package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.adapter.ProjectUserListAdapter;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.tasks.model.ProjectUserListModel;
import com.yunyisheng.app.yunys.tasks.present.ProjectUserListPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectProjectUserListActivity extends BaseActivity<ProjectUserListPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.select_project_user_list)
    ListView selectProjectUserList;
    ProjectUserListAdapter adapter;
    private List<ProjectUserBean> dataList = new ArrayList<>();
    private String projectId;
    private String releaseId;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.releaseId = getIntent().getStringExtra("releaseId");
        getP().getProjectUserList(projectId);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_select_project_user_list;
    }

    @Override
    public ProjectUserListPresent bindPresent() {
        return new ProjectUserListPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.img_back:
                setResult(2,intent);
                break;
            case R.id.submit:
                List<ProjectUserBean> lists = adapter.getSelectList();
                if (lists.size() == 0){
                    setResult(2,intent);
                }else {
                    intent.putExtra("selectlist",(Serializable)lists);
                    intent.putExtra("releaseId",releaseId);
                    setResult(1,intent);
                }
//                Intent intent1 = getIntent();
//                List<ProjectUserBean> list = (List<ProjectUserBean>) intent1.getSerializableExtra("selectlist");
                break;
            case R.id.search_text:
                break;
        }
        if (v.getId() != R.id.search_text){
            finish();
        }
    }

    public void setAdapterData(ProjectUserListModel projectUserListModel) {
        if (projectUserListModel.getRespBody().size() > 0){
            dataList.clear();
            dataList = projectUserListModel.getRespBody();
            adapter = new ProjectUserListAdapter(context,projectUserListModel.getRespBody());
            selectProjectUserList.setAdapter(adapter);
        }else {
            ToastUtils.showToast("获取人员列表失败！");
        }
    }

}