package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.adapter.RadioSelectUserAdapter;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;
import com.yunyisheng.app.yunys.tasks.model.ProjectUserListModel;
import com.yunyisheng.app.yunys.tasks.present.RadioSelectUserPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioSelectUserActivity extends BaseActivity<RadioSelectUserPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.select_check_user_list)
    ListView selectCheckUserList;
    @BindView(R.id.page_title)
    TextView pageTitle;

    RadioSelectUserAdapter adapter;
    List<ProjectUserBean> dataList = new ArrayList<>();

    private String projectId;
    private String fromPageTitle;
    private String selectUserId;
    @Override
    public void initView() {
        ButterKnife.bind(this);
        projectId = getIntent().getStringExtra("projectId");
        fromPageTitle = getIntent().getStringExtra("fromPageTitle");
        selectUserId = getIntent().getStringExtra("selectUserId");
        if (fromPageTitle != null){
            pageTitle.setText(fromPageTitle);
        }else {
            pageTitle.setText("请选择审批人员");
        }
    }

    @Override
    public void initAfter() {
        if (projectId != null){
            getP().getProjectUserList(projectId);
        }else {
            getP().getAllUserLists();
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_radio_select_user;
    }

    @Override
    public RadioSelectUserPresent bindPresent() {
        return new RadioSelectUserPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.submit:
                Intent intent = new Intent();
                int selectPosition = adapter.getSelectPosition();
                if (selectPosition == -1){
                    setResult(2,intent);
                }else {
                    ProjectUserBean selectUser = dataList.get(selectPosition);
                    String username = "";
                    if (selectUser.getName() != null){
                         username = selectUser.getName();
                    }
                    if (selectUser.getUserName() != null){
                        username = selectUser.getUserName();
                    }
                    intent.putExtra("selectUserId",selectUser.getUserId());
                    intent.putExtra("selectUserName",username);
                    setResult(1,intent);
                }
                finish();
                break;
        }
    }

    public void setAdapterData(ProjectUserListModel projectUserListModel) {
        if (projectUserListModel.getRespBody().size() > 0){
            dataList.clear();
            dataList.addAll(projectUserListModel.getRespBody());
            adapter = new RadioSelectUserAdapter(context,dataList,selectUserId);
            selectCheckUserList.setAdapter(adapter);
        }else {
            ToastUtils.showToast("暂无人员！");
        }
    }
}
