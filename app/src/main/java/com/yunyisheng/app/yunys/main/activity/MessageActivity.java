package com.yunyisheng.app.yunys.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.SpinnerAdapter;
import com.yunyisheng.app.yunys.main.model.SpinnerBean;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/11  14:24
 * @describe 消息activity
 */
public class MessageActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
//    @BindView(R.id.te_msgsize)
//    TextView teMsgsize;
    @BindView(R.id.rl_allmsg)
    RelativeLayout rlAllmsg;
    @BindView(R.id.lv_message)
    ListView lvMessage;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.sp_type)
    Spinner spType;
    private List<SpinnerBean> list=new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("消息");
        list.add(new SpinnerBean("全部任务",48));
        list.add(new SpinnerBean("其他任务",50));
        list.add(new SpinnerBean("热门任务",60));
        SpinnerAdapter adapter=new SpinnerAdapter(MessageActivity.this,list);
        //绑定 Adapter到控件
        spType .setAdapter(adapter);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                ToastUtils.showLongToast("你点击的是:"+list.get(pos));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_message;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        rlAllmsg.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_allmsg:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
