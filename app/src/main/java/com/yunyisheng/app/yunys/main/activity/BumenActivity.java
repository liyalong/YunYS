package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.BumenAdapter;
import com.yunyisheng.app.yunys.main.model.BuMenBean;
import com.yunyisheng.app.yunys.main.present.BumenPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fuduo
 * @time 2018/1/28  14:22
 * @describe 部门activity
 */
public class BumenActivity extends BaseActivity<BumenPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.lv_bumen)
    ListView lvBumen;

    private List<BuMenBean.RespBodyBean> list = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("选择部门");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvBumen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                intent.putExtra("bumenname",list.get(position).getSectionName());
                intent.putExtra("bumenid",list.get(position).getSectionId());
                setResult(8,intent);
                finish();
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getBumenList();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bumen;
    }

    @Override
    public BumenPresent bindPresent() {
        return new BumenPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public void getBumenList(BuMenBean buMenBean) {
        list.addAll(buMenBean.getRespBody());
        BumenAdapter bumenAdapter = new BumenAdapter(BumenActivity.this, list);
        lvBumen.setAdapter(bumenAdapter);
    }

}
