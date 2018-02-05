package com.yunyisheng.app.yunys.tasks.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.model.ProcessTaskFormDetailBean;
import com.yunyisheng.app.yunys.tasks.present.ProcessTaskPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：fuduo on 2018/2/5 17:11
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProcessTaskFormActivity extends BaseActivity<ProcessTaskPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.line_all)
    LinearLayout lineAll;

    private  List<ProcessTaskFormDetailBean.RespBodyBean.DataBean> dataBeanList=new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_processtask;
    }

    @Override
    public ProcessTaskPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public void getProcessTaskForm(ProcessTaskFormDetailBean processTaskFormDetailBean) {
        List<ProcessTaskFormDetailBean.RespBodyBean.DataBean> data = processTaskFormDetailBean.getRespBody().getData();
    }
}
