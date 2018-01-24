package com.yunyisheng.app.yunys.main.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.adapter.PublishNoticeListAdapter;
import com.yunyisheng.app.yunys.main.model.ReciveNoticeBean;
import com.yunyisheng.app.yunys.main.model.SendNoticeBean;
import com.yunyisheng.app.yunys.main.present.NoticePresent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

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
    private List<ReciveNoticeBean.ListBean> receivelist = new ArrayList<>();
    private PublishNoticeListAdapter adapter;

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
    }

    @Override
    public void initView() {
        if (tabindex == 0) {
            getP().getSendNoticelist(10, pageindex, "");
        } else {
            getP().getReceiveNoticelist(10, pageindex, "");
        }
        ScrowUtil.listViewConfig(pullToRefreshListview);
        pullToRefreshListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex = 1;
                if (tabindex == 0) {
                    sendlist.clear();
                    getP().getSendNoticelist(10, pageindex, "");
                } else {
                    receivelist.clear();
                    getP().getReceiveNoticelist(10, pageindex, "");
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex++;
                if (tabindex == 0) {
                    getP().getSendNoticelist(10, pageindex, "");
                } else {
                    getP().getReceiveNoticelist(10, pageindex, "");
                }
            }
        });
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
                            getP().getSendNoticelist(10, pageindex, sousuo_neirong);
                        } else {
                            receivelist.clear();
                            getP().getReceiveNoticelist(10, pageindex, sousuo_neirong);
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initAfter() {

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
    }

    public void getRecelveList(ReciveNoticeBean reciveNoticeBean) {
        if (reciveNoticeBean.getList().size() > 0) {

        } else {
            if (pageindex == 1) {
                ToastUtils.showToast("暂无数据");
            } else {
                ToastUtils.showToast("暂无更多数据");
            }
        }
    }

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
    }
}
