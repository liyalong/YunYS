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
import com.yunyisheng.app.yunys.main.adapter.MessageTypeAdapter;
import com.yunyisheng.app.yunys.main.model.MessageBean;
import com.yunyisheng.app.yunys.main.model.MessageTypeBean;
import com.yunyisheng.app.yunys.main.present.MessagePresent;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ScreenUtils;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.img_quesheng)
    ImageView imgQuesheng;
    private List<MessageTypeBean.ListBean> typelist = new ArrayList<>();
    private List<MessageBean.RespBodyBean> messagelist = new ArrayList<>();
    private int pageindex = 1;
    private int userid;
    private MessageAdapter messageAdapter;
    private String str = "";

    @Override
    public void initView() {
        ButterKnife.bind(this);
//        userid = SharedPref.getInstance(MessageActivity.this).getInt("userid", 0);
        teTitle.setText("消息");
        ScrowUtil.listViewConfig(pullToList);
        spType.setDropDownWidth(ScreenUtils.getScreenHeight(MessageActivity.this));
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                List<String> type = typelist.get(pos).getType();
                for (int i = 0; i < type.size(); i++) {
                    if (i != type.size() - 1) {
                        str += type.get(i) + ",";
                    } else {
                        str += type.get(i);
                    }
                }
                pageindex = 1;
                messagelist.clear();
                getP().getMessageList(str, 1);
                LogUtils.i("fdfsfdsfdsf", str);
                str="";
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
                getP().getMessageList(str, pageindex);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex++;
                getP().getMessageList(str, pageindex);
            }
        });
        pullToList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageBean.RespBodyBean respBodyBean = messagelist.get(position - 1);
                String messageStat = respBodyBean.getMessageStat();
                int messageId = respBodyBean.getMessageId();
                if (messageStat.equals("1")) {
                    getP().updateMessage(messageId, position - 1);
                }
            }
        });
    }

    public void setVoalGone(int position) {
        messagelist.get(position).setMessageStat("0");
        messageAdapter.notifyDataSetChanged();
    }

    @Override
    public void initAfter() {
        getP().getMessageTypeList();
    }

    public void getMessageType(MessageTypeBean messageTypeBean) {
        List<MessageTypeBean.ListBean> list = messageTypeBean.getList();
        if (list != null && list.size() > 0) {
            typelist.addAll(list);
            MessageTypeAdapter adapter = new MessageTypeAdapter(MessageActivity.this, typelist);
            //绑定 Adapter到控件
            spType.setAdapter(adapter);
        }
    }

    public void getMessageList(MessageBean messageBean) {
        List<MessageBean.RespBodyBean> respBody = messageBean.getRespBody();
        if (respBody.size() > 0) {
            imgQuesheng.setVisibility(View.GONE);
            pullToList.setVisibility(View.VISIBLE);
            messagelist.addAll(respBody);
            messageAdapter.setData(messagelist);
        } else {
            if (pageindex == 1) {
                imgQuesheng.setVisibility(View.VISIBLE);
                pullToList.setVisibility(View.GONE);
                ToastUtils.showToast("暂无消息");
            } else {
                ToastUtils.showToast("没有更多了");
            }
        }
        stopRefresh();
    }

    public void setimgBac(){
        pullToList.setVisibility(View.GONE);
        imgQuesheng.setVisibility(View.VISIBLE);
        imgQuesheng.setBackgroundResource(R.mipmap.no_network);
    }

    public void stopRefresh() {
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
