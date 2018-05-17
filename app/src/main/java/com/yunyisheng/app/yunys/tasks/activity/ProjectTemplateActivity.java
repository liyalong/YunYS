package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.tasks.adapter.MyAdapter;
import com.yunyisheng.app.yunys.tasks.model.ChildBean;
import com.yunyisheng.app.yunys.tasks.model.GroupBean;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.MyListView;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/18.
 * 生成任务反馈项
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
        Intent intent=getIntent();
        String fankuijsoncreate = intent.getStringExtra("fankuijson_create");
        if (fankuijsoncreate!=null&&!fankuijsoncreate.equals("")){
            getCreateProjectTemplateDetail(fankuijsoncreate);
        }else {
            addOnce();
        }
    }

    @Override
    public void initAfter() {

    }

    private void addOnce(){
        //首先生成一个反馈项展示
        List<ChildBean> childBeans = new ArrayList<>();
        ChildBean childBean = new ChildBean();
        childBeans.add(childBean);
        GroupBean bean = new GroupBean();
        bean.setModel(childBeans);
        stringList.add(bean);
        adapter = new MyAdapter(ProjectTemplateActivity.this, stringList);
        lvAll.setAdapter(adapter);
    }

    private void getEditProjectTemplateDetail(String json){
        try {
           JSONArray feedbackItem=new JSONArray(json);
            for (int i=0;i<feedbackItem.length();i++){
                GroupBean bean = new GroupBean();
                List<ChildBean> modellist=new ArrayList<>();
                org.json.JSONObject object= new JSONObject(feedbackItem.get(i).toString());
                String feedbackName = object.getString("feedbackName");
                int feedbackType = object.getInt("feedbackType");
                bean.setfeedbackType(feedbackType);
                bean.setfeedbackName(feedbackName);
                JSONArray model = object.getJSONArray("model");
                for (int j=0;j<model.length();j++){
                    ChildBean childBean=new ChildBean();
                    org.json.JSONObject modelobject= new JSONObject(model.get(j).toString());
                    String dynamicTypeName = modelobject.getString("dynamicTypeName");
                    int index = modelobject.getInt("index");
                    childBean.setDynamicTypeName(dynamicTypeName);
                    childBean.setIndex(index);
                    modellist.add(childBean);
                }
                bean.setModel(modellist);
                stringList.add(bean);
            }
            adapter = new MyAdapter(ProjectTemplateActivity.this, stringList);
            lvAll.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getCreateProjectTemplateDetail(String json){
        try {
            JSONArray feedbackItem=new JSONArray(json);
            for (int i=0;i<feedbackItem.length();i++){
                GroupBean bean = new GroupBean();
                List<ChildBean> modellist=new ArrayList<>();
                String string = feedbackItem.get(i).toString();
//                String modelstr = string.replaceAll("\\\\", "");
                org.json.JSONObject object= new JSONObject(string);
                String feedbackName = object.getString("feedbackName");
                int feedbackType = object.getInt("feedbackType");
                bean.setfeedbackType(feedbackType);
                bean.setfeedbackName(feedbackName);
                JSONArray model = object.getJSONArray("modelArray");
                for (int j=0;j<model.length();j++){
                    ChildBean childBean=new ChildBean();
                    String string1 = model.get(j).toString();
                    org.json.JSONObject modelobject= new JSONObject(string1);
                    String dynamicTypeName = modelobject.getString("dynamic_type_name");
                    int index = modelobject.getInt("index");
                    childBean.setDynamicTypeName(dynamicTypeName);
                    childBean.setIndex(index);
                    modellist.add(childBean);
                }
                bean.setModel(modellist);
                stringList.add(bean);
            }
            adapter = new MyAdapter(ProjectTemplateActivity.this, stringList);
            lvAll.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
        bottom.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.submit:
                List<GroupBean> groupBeanList = adapter.getStrList();
                if (groupBeanList.size()>0) {
                    for (int i = 0; i < groupBeanList.size(); i++) {
                        GroupBean groupBean = groupBeanList.get(i);
                        int type = groupBean.getfeedbackType();
                        String s = groupBean.getfeedbackName();
                        //判断是否有未填写的外层内容
                        if (s == null || s.equals("")) {
                            ToastUtils.showToast("您还有未填写的项");
                            return;
                        } else {
                            if (type != 4) {
                                for (int m = 0; m < groupBean.getModel().size(); m++) {
                                    ChildBean childBean = groupBean.getModel().get(m);
                                    String dynamicTypeName = childBean.getDynamicTypeName();
                                    //判断是否有未填写的里层内容
                                    if (dynamicTypeName == null || dynamicTypeName.equals("")) {
                                        ToastUtils.showToast("您还有未填写的项");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    String string = JSON.toJSONString(groupBeanList);
                    LogUtils.i("str", string);
                    Intent intent = getIntent();
                    intent.putExtra("fankuijson",string);
                    setResult(5,intent);
                    finish();
                }else {
                    ToastUtils.showToast("请添加任务反馈项");
                }
                break;
            case R.id.bottom:
                //添加任务反馈项
                List<GroupBean> list = adapter.getStrList();
                List<ChildBean> childBeans = new ArrayList<>();
                ChildBean childBean = new ChildBean();
                childBeans.add(childBean);
                GroupBean bean = new GroupBean();
                bean.setModel(childBeans);
                list.add(bean);
                stringList=list;
                adapter.notifyDataSetChanged();
                break;
        }
    }



}
