package com.yunyisheng.app.yunys.main.fragement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.activity.NoticeDeatilActivity;
import com.yunyisheng.app.yunys.main.adapter.PublishNoticeListAdapter;
import com.yunyisheng.app.yunys.main.adapter.ReceiveNoticeListAdapter;
import com.yunyisheng.app.yunys.main.model.ReceiveMeMessageBean;
import com.yunyisheng.app.yunys.main.model.SendNoticeBean;
import com.yunyisheng.app.yunys.main.present.NoticePresent;
import com.yunyisheng.app.yunys.schedule.model.PositionMessageEvent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 作者：fuduo on 2018/1/18 17:38
 * 邮箱：duoendeavor@163.com
 * 用途：公告fragement
 */

public class NoticeFragement extends BaseFragement<NoticePresent> {
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    Unbinder unbinder;
    @BindView(R.id.pull_to_refresh_listview)
    PullToRefreshListView pullToRefreshListview;
    private int tabindex;
    private String sousuo_neirong;
    private int pageindex = 1;
    private List<SendNoticeBean.ListBean> sendlist = new ArrayList<>();
    private List<ReceiveMeMessageBean.ListBean> receivemelist=new ArrayList<>();
    private PublishNoticeListAdapter adapter;
    private ReceiveNoticeListAdapter adapter1;
    private MyReceiver receiver;

    public static NoticeFragement getInstance(int index) {
        NoticeFragement fragement = new NoticeFragement();
        Bundle bundle = new Bundle();
        bundle.putInt("tabindex", index);
        fragement.setArguments(bundle);
        return fragement;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        tabindex = bundle.getInt("tabindex", 0);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        if (tabindex == 0) {
            getP().getSendNoticelist(pageindex, 10, null);
        } else {
            getP().getReceiveNoticelist(pageindex, 10, null);
        }
        ScrowUtil.listViewConfig(pullToRefreshListview);
        pullToRefreshListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex = 1;
                if (tabindex == 0) {
                    sendlist.clear();
                    getP().getSendNoticelist(pageindex, 10, null);
                } else {
                    receivemelist.clear();
                    getP().getReceiveNoticelist(pageindex, 10, null);
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex++;
                if (tabindex == 0) {
                    getP().getSendNoticelist(pageindex, 10, null);
                } else {
                    getP().getReceiveNoticelist(pageindex, 10, null);
                }
            }
        });
        edSearch.addTextChangedListener(mTextWatcher);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sousuo_neirong = edSearch.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (sousuo_neirong == null || sousuo_neirong.equals("")) {
                        ToastUtils.showToast("搜索内容不能为空");
                    } else {
                        if (tabindex == 0) {
                            sendlist.clear();
                            pageindex = 1;
                            getP().getSendNoticelist(pageindex, 10, sousuo_neirong);
                        } else {
                            receivemelist.clear();
                            pageindex = 1;
                            getP().getReceiveNoticelist(pageindex, 10, sousuo_neirong);
                        }
                    }
                }
                return false;
            }
        });
        pullToRefreshListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tabindex==0) {
                    SendNoticeBean.ListBean listBean = sendlist.get(position - 1);
                    Intent intent = new Intent(mContext, NoticeDeatilActivity.class);
                    intent.putExtra("type", tabindex);
                    intent.putExtra("noticeid", listBean.getAnnouncementId());
                    startActivityForResult(intent, 2);
                }else {
                    ReceiveMeMessageBean.ListBean listBean = receivemelist.get(position - 1);
                    Intent intent = new Intent(mContext, NoticeDeatilActivity.class);
                    intent.putExtra("type", tabindex);
                    intent.putExtra("noticeid", listBean.getAnnouncementId());
                    startActivityForResult(intent, 2);
                }
            }
        });
    }

    @Override
    public void initAfter() {
        //广播接受者实例
        receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action");
        getActivity().registerReceiver(receiver, intentFilter);
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PositionMessageEvent messageEvent){
        String position = messageEvent.getPosition();
        if (position.equals("updatenotice")){
            sendlist.clear();
            pageindex = 1;
            getP().getSendNoticelist(pageindex, 10, null);
        }

    }

    class MyReceiver extends BroadcastReceiver {
        public MyReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("data");
            if ("deletemysend".equals(data)) {
                if (sendlist.size() == 1) {
                    sendlist.clear();
                    adapter = new PublishNoticeListAdapter(mContext, sendlist);
                    pullToRefreshListview.setAdapter(adapter);
                } else {
                    sendlist.clear();
                    getP().getSendNoticelist(pageindex, 10, null);
                }
            } else if ("deletesendme".equals(data)) {
                if (receivemelist.size() == 1) {
                    receivemelist.clear();
                    adapter1 = new ReceiveNoticeListAdapter(mContext, receivemelist);
                    pullToRefreshListview.setAdapter(adapter1);
                } else {
                    receivemelist.clear();
                    getP().getReceiveNoticelist(pageindex, 10, null);
                }

            }
        }

    }

    public void getPublishList(SendNoticeBean sendNoticeBean) {
        if (sendNoticeBean.getList().size() > 0) {
            if (pageindex == 1) {
                sendlist.addAll(sendNoticeBean.getList());
                adapter = new PublishNoticeListAdapter(mContext, sendlist);
                pullToRefreshListview.setAdapter(adapter);
            } else {
                sendlist.addAll(sendNoticeBean.getList());
                adapter.notifyDataSetChanged();
            }
        } else {
            if (pageindex == 1) {
                ToastUtils.showToast("暂无数据");
            } else {
                ToastUtils.showToast("暂无更多数据");
            }
        }
        stopRefresh();
    }

    public void stopRefresh(){
        pullToRefreshListview.onRefreshComplete();
    }

    public void getRecelveList(ReceiveMeMessageBean receiveMeMessageBean) {
        if (receiveMeMessageBean.getList().size() > 0) {
            if (pageindex == 1) {
                receivemelist.addAll(receiveMeMessageBean.getList());
                adapter1 = new ReceiveNoticeListAdapter(mContext, receivemelist);
                pullToRefreshListview.setAdapter(adapter1);
            } else {
                receivemelist.addAll(receiveMeMessageBean.getList());
                adapter1.notifyDataSetChanged();
            }
        } else {
            if (pageindex == 1) {
            } else {
                ToastUtils.showToast("暂无更多数据");
            }
        }
        stopRefresh();
    }

    //监听是否输入
    TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            if (s.length() > 0) {
                imgClear.setVisibility(View.VISIBLE);
            } else {
                imgClear.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public int bindLayout() {
        return R.layout.fragement_notice;
    }

    @Override
    public NoticePresent bindPresent() {
        return new NoticePresent();
    }

    @Override
    public void setListener() {
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSearch.setText("");
            }
        });
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
        EventBus.getDefault().unregister(this);
        getActivity().unregisterReceiver(receiver);
    }
}
