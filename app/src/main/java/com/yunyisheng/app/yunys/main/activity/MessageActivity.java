package com.yunyisheng.app.yunys.main.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.MessageAdapter;
import com.yunyisheng.app.yunys.main.adapter.SpinnerAdapter;
import com.yunyisheng.app.yunys.main.model.MessageBean;
import com.yunyisheng.app.yunys.main.model.SpinnerBean;
import com.yunyisheng.app.yunys.main.present.MessagePresent;
import com.yunyisheng.app.yunys.utils.ScreenUtils;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

/**
 * @author fuduo
 * @time 2018/1/11  14:24
 * @describe 消息activity
 */
public class MessageActivity extends BaseActivity<MessagePresent> {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.rl_allmsg)
    RelativeLayout rlAllmsg;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.sp_type)
    Spinner spType;
    @BindView(R.id.pull_to_list)
    PullToRefreshListView pullToList;
    private List<SpinnerBean> list = new ArrayList<>();
    private List<MessageBean.RespBodyBean> messagelist = new ArrayList<>();
    private int pageindex = 1;
    private int userid;
    private MessageAdapter messageAdapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        userid = SharedPref.getInstance(MessageActivity.this).getInt("userid", 0);
        ScrowUtil.listViewConfig(pullToList);
        teTitle.setText("消息");
        list.add(new SpinnerBean("全部任务", 48));
        list.add(new SpinnerBean("其他任务", 50));
        list.add(new SpinnerBean("热门任务", 60));
        spType.setDropDownWidth(ScreenUtils.getScreenHeight(MessageActivity.this));
        SpinnerAdapter adapter = new SpinnerAdapter(MessageActivity.this, list);
        //绑定 Adapter到控件
        spType.setAdapter(adapter);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                ToastUtils.showLongToast("你点击的是:" + list.get(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        messageAdapter = new MessageAdapter(MessageActivity.this, messagelist);
        pullToList.setAdapter(messageAdapter);
        pullToList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex = 1;
                messagelist.clear();
                getP().getMessageList(pageindex, userid);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex++;
                getP().getMessageList(pageindex, userid);
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getMessageList(pageindex, userid);
    }

    public void getMessageList(MessageBean messageBean) {
        List<MessageBean.RespBodyBean> respBody = messageBean.getRespBody();
        if (respBody.size() > 0) {
            messagelist.addAll(respBody);
            if (pageindex==1){

            }
            messageAdapter.setData(messagelist);
        } else {
            if (pageindex == 1) {
                ToastUtils.showToast("暂无消息");
            } else {
                ToastUtils.showToast("没有更多了");
            }
        }
        pullToList.onRefreshComplete();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_message;
    }

    @Override
    public MessagePresent bindPresent() {
        return new MessagePresent();
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
}
