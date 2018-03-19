package com.yunyisheng.app.yunys.main.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.activity.WorkerDataActivity;
import com.yunyisheng.app.yunys.main.adapter.WorkerDatProjectlistAdapter;
import com.yunyisheng.app.yunys.main.present.ParticpateinPresent;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：fuduo on 2018/1/12 12:12
 * 邮箱：duoendeavor@163.com
 * 用途：员工参与项目fragement
 */

public class ParticipateinFragement extends BaseFragement<ParticpateinPresent> {

    @BindView(R.id.te_columnsize)
    TextView teColumnsize;
    @BindView(R.id.lv_participatein)
    ListView lvParticipatein;
    Unbinder unbinder;
    @BindView(R.id.img_quesheng)
    ImageView imgQuesheng;
    private List<ProjectBean> respBodylist = new ArrayList<>();
    private WorkerDatProjectlistAdapter adapter;
    private int userid;

    @Override
    public void initView() {
        userid = ((WorkerDataActivity) getActivity()).userid;
        adapter = new WorkerDatProjectlistAdapter(mContext, respBodylist);
        lvParticipatein.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            getP().getOtherProjectList(userid, "");
        }
    }
    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_participatein;
    }

    @Override
    public ParticpateinPresent bindPresent() {
        return new ParticpateinPresent();
    }

    @Override
    public void setListener() {
       imgQuesheng.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
       switch (v.getId()){
           case R.id.img_quesheng:
               getP().getOtherProjectList(userid, "");
               break;
       }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setProjectListModel(ProjectListModel projectListModel) {
        teColumnsize.setText("("+projectListModel.getTotal()+")");
        respBodylist.clear();
        List<ProjectBean> respBody = projectListModel.getRespBody();
        if (respBody != null && respBody.size() > 0) {
            respBodylist.addAll(respBody);
            adapter.setData(respBodylist);
            setGoneQuesheng();
        }else {
            lvParticipatein.setVisibility(View.GONE);
            imgQuesheng.setVisibility(View.VISIBLE);
            imgQuesheng.setBackgroundResource(R.mipmap.no_data);
        }
    }

    public void setGoneQuesheng(){
        lvParticipatein.setVisibility(View.VISIBLE);
        imgQuesheng.setVisibility(View.GONE);
    }

    public void setImgQuesheng(){
        lvParticipatein.setVisibility(View.GONE);
        imgQuesheng.setVisibility(View.VISIBLE);
        imgQuesheng.setBackgroundResource(R.mipmap.no_network);
    }


}
