package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.adapter.ProcessFormListAdapter;
import com.yunyisheng.app.yunys.tasks.bean.ProcessFormBean;
import com.yunyisheng.app.yunys.tasks.model.ProcessFormListModel;
import com.yunyisheng.app.yunys.tasks.present.ProcessFormListPresent;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/18.
 * 选择流程表单
 */

public class SelectProcessFormActivity extends BaseActivity<ProcessFormListPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.select_process_form_list)
    ListView selectProcessFormList;
    ProcessFormListAdapter adapter;
    private List<ProcessFormBean> dataList = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {
        getP().getProcessFormList();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_select_process_form;
    }

    @Override
    public ProcessFormListPresent bindPresent() {
        return new ProcessFormListPresent();
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
                this.finish();
                break;
            case R.id.submit:
                Intent intent = new Intent();
                int selectPosition = adapter.getSelectPosition();
                if (selectPosition == -1){
                    setResult(2,intent);
                }else {
                    ProcessFormBean selectForm = dataList.get(selectPosition);
                    intent.putExtra("procDefId",selectForm.getProcDefId());
                    intent.putExtra("formName",selectForm.getFormBasicName());
                    setResult(1,intent);
                }
                finish();
                break;
        }

    }

    public void setAdapterList(ProcessFormListModel processFormListModel) {
        if (processFormListModel.getRespBody().size() > 0){
            dataList.clear();
            dataList.addAll(processFormListModel.getRespBody());
            adapter = new ProcessFormListAdapter(context,dataList);
            selectProcessFormList.setAdapter(adapter);
        }else {
            ToastUtils.showToast("暂无流程表单！");
        }
    }
}
