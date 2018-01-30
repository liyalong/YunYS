package com.yunyisheng.app.yunys.main.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.activity.ChangeOtherUserinfoActivity;
import com.yunyisheng.app.yunys.main.activity.WorkerDataActivity;
import com.yunyisheng.app.yunys.main.model.GetOtherinfoBean;
import com.yunyisheng.app.yunys.main.present.BasicDataPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：fuduo on 2018/1/12 11:47
 * 邮箱：duoendeavor@163.com
 * 用途：员工个人信息详情fragement
 */

public class BasicDataFragement extends BaseFragement<BasicDataPresent> {

    @BindView(R.id.te_sex)
    TextView teSex;
    @BindView(R.id.te_phonenum)
    TextView tePhonenum;
    @BindView(R.id.te_zuzhibumen)
    TextView teZuzhibumen;
    @BindView(R.id.te_email)
    TextView teEmail;
    @BindView(R.id.btn_anpai_work)
    Button btnAnpaiWork;
    Unbinder unbinder;
    int userid;
    private GetOtherinfoBean getOtherinfoBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        userid = arguments.getInt("userid", 0);
    }

    @Override
    public void initView() {
        btnAnpaiWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initAfter() {
       getP().getOtherInfo(userid);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_basicdata;
    }

    @Override
    public BasicDataPresent bindPresent() {
        return new BasicDataPresent();
    }

    public void getResultInfo(GetOtherinfoBean getOtherinfoBean){
        ((WorkerDataActivity)getActivity()).setInfodetail(getOtherinfoBean);
        if (getOtherinfoBean.getRespBody().getUserMailbox()!=null
                &&!getOtherinfoBean.getRespBody().getUserMailbox().equals("")
                &&!getOtherinfoBean.getRespBody().getUserMailbox().equals("null")){
            teEmail.setText(getOtherinfoBean.getRespBody().getUserMailbox());
        }
        tePhonenum.setText(getOtherinfoBean.getRespBody().getUserPhone());
        teSex.setText(getOtherinfoBean.getRespBody().getUserSex());
        this.getOtherinfoBean=getOtherinfoBean;
    }

    public void getinfo(){
        getP().getOtherInfo(userid);
    }

    public void editInfo(){
        Intent intent=new Intent(getActivity(), ChangeOtherUserinfoActivity.class);
        intent.putExtra("otherinfo",getOtherinfoBean);
        getActivity().startActivityForResult(intent,9);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

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
}
