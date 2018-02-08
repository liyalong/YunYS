package com.yunyisheng.app.yunys.main.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.activity.ChangeOtherUserinfoActivity;
import com.yunyisheng.app.yunys.main.activity.WorkerDataActivity;
import com.yunyisheng.app.yunys.main.model.GetOtherinfoBean;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.main.present.BasicDataPresent;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateProcessTaskAcitvity;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
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
    @BindView(R.id.btn_anpai_work)
    Button btnAnpaiWork;
    Unbinder unbinder;
    int userid;
    private GetOtherinfoBean getOtherinfoBean;
    private List<WorkerBean> selectlist = new ArrayList<>();//选中的人员
    private String workername;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        userid = arguments.getInt("userid", 0);
    }

    @Override
    public void initView() {
        boolean canArrangeWork = ((WorkerDataActivity) getActivity()).canArrangeWork;
        workername = ((WorkerDataActivity) getActivity()).workername;
        if (canArrangeWork){
            btnAnpaiWork.setVisibility(View.VISIBLE);
        }
        btnAnpaiWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workername!=null&&!workername.equals("")){
                    WorkerBean workerBean=new WorkerBean();
                    workerBean.setName(workername);
                    workerBean.setUserId(userid);
                    selectlist.add(workerBean);
                    createSelectTaskDialog(getActivity());
                }else {
                    ToastUtils.showToast("获取员工信息失败");
                }

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
        Intent intent = new Intent(getActivity(), ChangeOtherUserinfoActivity.class);
        intent.putExtra("otherinfo", getOtherinfoBean);
        getActivity().startActivityForResult(intent, 9);
    }

    /**
     * 选择任务对话框
     *
     * @param activity
     * @return
     */
    public void createSelectTaskDialog(final Activity activity) {
        final Dialog mSelectTask = new Dialog(activity, R.style.dialog_bottom_full);
        mSelectTask.setCanceledOnTouchOutside(true);
        mSelectTask.setCancelable(true);
        Window window = mSelectTask.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View view1 = View.inflate(activity, R.layout.dialog_select_task, null);
        RelativeLayout rl_shebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_shebei_task);
        RelativeLayout rl_wrongshebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_wrongshebei_task);

        RelativeLayout rl_liucheng_task = (RelativeLayout) view1
                .findViewById(R.id.rl_liucheng_task);
        RelativeLayout rl_close = (RelativeLayout) view1
                .findViewById(R.id.rl_close);
        rl_shebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(mContext, CreateDeviceTaskAcitvity.class);
                intent.putExtra("selectlist",(Serializable)selectlist);
                startActivity(intent);
            }
        });
        rl_wrongshebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(mContext, CreateNoneDeviceTaskAcitvity.class);
                intent.putExtra("selectlist",(Serializable)selectlist);
                startActivity(intent);
            }
        });
        rl_liucheng_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, CreateProcessTaskAcitvity.class);
                intent.putExtra("selectlist",(Serializable)selectlist);
                startActivity(intent);
            }
        });

        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectTask.dismiss();
            }
        });

        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mSelectTask.show();
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
