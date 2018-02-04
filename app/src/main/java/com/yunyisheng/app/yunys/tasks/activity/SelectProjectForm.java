package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.adapter.ProjectFormListAdapter;
import com.yunyisheng.app.yunys.tasks.bean.ProjectFormBean;
import com.yunyisheng.app.yunys.tasks.model.ProjectFormListModel;
import com.yunyisheng.app.yunys.tasks.present.ProjectFormPresent;
import com.yunyisheng.app.yunys.tasks.present.SelectProjectPresen;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/18.
 * 选择工单列表
 */

public class SelectProjectForm extends BaseActivity<ProjectFormPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.select_project_form_list)
    ListView selectProjectFormList;

    private List<ProjectFormBean> dataLists = new ArrayList<>();
    private ProjectFormListAdapter adapter;
    private String projectId = "";

    @Override
    public void initView() {
        ButterKnife.bind(this);
        getP().getProjectFormList(projectId);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_select_project_form;
    }

    @Override
    public ProjectFormPresent bindPresent() {
        return  new ProjectFormPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.img_back:
                setResult(2,intent);
                break;
            case R.id.submit:
                int selectPostion = adapter.getSelectPosition();
                if (selectPostion == -1){
                    setResult(2,intent);
                }else {
                    String selectFormId = String.valueOf(dataLists.get(selectPostion).getId());
                    String selectFormName = dataLists.get(selectPostion).getBaseName();
                    intent.putExtra("selectFormId",selectFormId);
                    intent.putExtra("selectFormName",selectFormName);
                    setResult(1,intent);
                }
                break;
        }
        finish();
    }

    public void setAdapterData(ProjectFormListModel projectFormListModel) {
        if (projectFormListModel.getRespBody().size() > 0){
            dataLists.clear();
            dataLists.addAll(projectFormListModel.getRespBody());
            adapter = new ProjectFormListAdapter(context,dataLists);
            selectProjectFormList.setAdapter(adapter);
        }else {
            ToastUtils.showToast("暂无表单！");
        }

    }
}
