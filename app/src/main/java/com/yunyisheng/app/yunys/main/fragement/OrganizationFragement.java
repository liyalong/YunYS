package com.yunyisheng.app.yunys.main.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/12 10:15
 * 邮箱：duoendeavor@163.com
 * 用途：选人fragement
 */

public class OrganizationFragement extends BaseFragement {

    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.ck_allworker)
    CheckBox ckAllworker;
    @BindView(R.id.line_all)
    LinearLayout lineAll;
    Unbinder unbinder;
    @BindView(R.id.line_selecall)
    LinearLayout lineSelecall;
    @BindView(R.id.btn_queren)
    Button btnQueren;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.scro_all)
    HorizontalScrollView scroAll;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private int tabindex;
    private List<String> selectlist = new ArrayList<>();

    public static OrganizationFragement newInstance(int index) {
        OrganizationFragement fragement = new OrganizationFragement();
        Bundle bundle = new Bundle();
        bundle.putInt("tabIndex", index);
        fragement.setArguments(bundle);
        return fragement;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        tabindex = bundle.getInt("tabIndex");
    }

    @Override
    public void initView() {
        selectlist.add("冯绍峰的事");
        selectlist.add("冯绍峰的");
        selectlist.add("冯绍峰");
        selectlist.add("冯绍");
        btnQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < selectlist.size(); i++) {
                    addChildViewLineLayout(selectlist.get(i));
                }

            }
        });
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSearch.setText("");
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_organization;
    }

    @Override
    public XPresent bindPresent() {
        return null;
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

    private void addChildViewLineLayout(String str) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.maillist_selectpeople_item, null);
        TextView te_select_name = (TextView) view.findViewById(R.id.te_select_name);
        te_select_name.setText(str);
//        view.setTag(infoBean.getDescription());
        lineSelecall.addView(view);
    }
}
