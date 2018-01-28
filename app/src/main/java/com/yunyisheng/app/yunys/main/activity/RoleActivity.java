package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.RoleAdapter;
import com.yunyisheng.app.yunys.main.model.RoleBean;
import com.yunyisheng.app.yunys.main.present.RolePresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fuduo
 * @time 2018/1/28  14:22
 * @describe 部门activity
 */
public class RoleActivity extends BaseActivity<RolePresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.lv_bumen)
    ListView lvBumen;

    private List<RoleBean.RespBodyBean> list = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("选择角色");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvBumen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RoleBean.RespBodyBean respBodyBean = list.get(position);
                Intent intent = getIntent();
                intent.putExtra("rolename", respBodyBean.getRoleName());
                intent.putExtra("roleid", respBodyBean.getRoleId());
                setResult(6, intent);
                finish();
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getRoleList();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bumen;
    }

    @Override
    public RolePresent bindPresent() {
        return new RolePresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public void getBumenList(RoleBean roleBean) {
        list.addAll(roleBean.getRespBody());
        RoleAdapter bumenAdapter = new RoleAdapter(RoleActivity.this, list);
        lvBumen.setAdapter(bumenAdapter);
    }

}
