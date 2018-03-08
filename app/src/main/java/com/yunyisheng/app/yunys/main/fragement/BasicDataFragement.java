package com.yunyisheng.app.yunys.main.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.activity.ChangeOtherUserinfoActivity;
import com.yunyisheng.app.yunys.main.activity.WorkerDataActivity;
import com.yunyisheng.app.yunys.main.model.GetOtherinfoBean;
import com.yunyisheng.app.yunys.main.present.BasicDataPresent;

import java.util.List;

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

    public void getResultInfo(GetOtherinfoBean getOtherinfoBean) {
        ((WorkerDataActivity) getActivity()).setInfodetail(getOtherinfoBean);
        if (getOtherinfoBean.getRespBody().getEnterpriseUser().getUserMailbox() != null
                && !getOtherinfoBean.getRespBody().getEnterpriseUser().getUserMailbox().equals("")
                && !getOtherinfoBean.getRespBody().getEnterpriseUser().getUserMailbox().equals("null")) {
            teEmail.setText(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserMailbox());
        }
        List<GetOtherinfoBean.RespBodyBean.SectionBean> section = getOtherinfoBean.getRespBody().getSection();
        String str = "";
        for (int i = 0; i < section.size(); i++) {
            String sectionName = section.get(i).getSectionName();
            if (i != section.size() - 1) {
                str += sectionName + ",";
            } else {
                str += sectionName;
            }
        }
        teZuzhibumen.setText(str);
        tePhonenum.setText(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPhone());
        teSex.setText(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserSex());
        this.getOtherinfoBean = getOtherinfoBean;
    }

    public void getinfo() {
        getP().getOtherInfo(userid);
    }

    public void editInfo() {
        Intent intent = new Intent((WorkerDataActivity)getActivity(), ChangeOtherUserinfoActivity.class);
        intent.putExtra("otherinfo", getOtherinfoBean);
        ((WorkerDataActivity)getActivity()).startActivityForResult(intent, 9);
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
