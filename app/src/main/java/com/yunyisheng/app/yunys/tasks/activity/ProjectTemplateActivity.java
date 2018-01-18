package com.yunyisheng.app.yunys.tasks.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.adapter.MyAdapter;
import com.yunyisheng.app.yunys.tasks.model.ChildBean;
import com.yunyisheng.app.yunys.tasks.model.GroupBean;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/18.
 * 任务反馈项
 */

public class ProjectTemplateActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.lv_all)
    MyListView lvAll;
    @BindView(R.id.scro_all)
    ScrollView scroAll;
    @BindView(R.id.bottom)
    RelativeLayout bottom;
    private List<GroupBean> stringList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {
        adapter = new MyAdapter(ProjectTemplateActivity.this, stringList);
        lvAll.setAdapter(adapter);
    }

    @Override
    public int bindLayout() {
        return R.layout.task_field_template;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
       submit.setOnClickListener(this);
       bottom.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
       switch (v.getId()){
           case R.id.submit:
               adapter.notifyDataSetChanged();
               List<GroupBean> groupBeanList = adapter.getStrList();
               LogUtils.i("str", groupBeanList.get(0).toString());
               break;
           case R.id.bottom:
               List<ChildBean> childBeans = new ArrayList<>();
               ChildBean childBean = new ChildBean();
               childBeans.add(childBean);
               GroupBean bean = new GroupBean();
               bean.setChilddata(childBeans);
               stringList.add(bean);
               adapter.notifyDataSetChanged();
               break;
       }
    }

}
