package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.tasks.adapter.SelectProjectAdapter;
import com.yunyisheng.app.yunys.tasks.present.SelectProjectPresen;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.XLog;

public class SelectProjectActivity extends BaseActivity<SelectProjectPresen> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.select_project_list)
    ListView selectProjectList;
    @BindView(R.id.search_text)
    EditText searchText;

    private int PAGE_NUM = 1;
    private int PAGE_SIZE = 999;
    private List<ProjectBean> dataList = new ArrayList<>();
    private SelectProjectAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        getP().getMyProjectList(PAGE_NUM,PAGE_SIZE,"");
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_select_project;
    }

    @Override
    public SelectProjectPresen bindPresent() {
        return new SelectProjectPresen();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    // 先隐藏键盘
                    ((InputMethodManager) searchText.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    // 搜索，进行自己要的操作...
                    String searchValue = searchText.getText().toString();
                    getP().getMyProjectList(PAGE_NUM,PAGE_SIZE,searchValue);//这里是我要做的操作！
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    public void widgetClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.img_back:
                setResult(2,intent);
                break;
            case R.id.submit:
                int selectPosition = adapter.getSelectPosition();
                if (selectPosition == -1){
                    setResult(2,intent);
                }else {
                    XLog.d("selectPosition",selectPosition);
                    ProjectBean selectProject = dataList.get(selectPosition);
                    intent.putExtra("selectProjectId", selectProject.getProjectId());
                    intent.putExtra("selectProjectName",selectProject.getProjectName());
                    setResult(1,intent);
                }
                break;
        }
        finish();
    }

    public void setAdapterData(ProjectListModel projectListModel){
        if (projectListModel.getRespBody().size() > 0){
            dataList.clear();
            dataList.addAll(projectListModel.getRespBody());
            adapter = new SelectProjectAdapter(context,dataList);
            selectProjectList.setAdapter(adapter);

        }else {
            ToastUtils.showToast("暂无参与项目！");
        }
    }


}
