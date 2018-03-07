package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.adapter.MemoListAdapter;
import com.yunyisheng.app.yunys.main.model.MemorandumBean;
import com.yunyisheng.app.yunys.main.present.MemorandumPresent;
import com.yunyisheng.app.yunys.schedule.model.PositionMessageEvent;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.SwipeListView;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

/**
 * @author fuduo
 * @time 2018/1/16  21:23
 * @describe 备忘录
 */
public class MemorandumActivity extends BaseActivity<MemorandumPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_add)
    TextView teAdd;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.lv_memarand)
    SwipeListView lvMemarand;
    @BindView(R.id.pull_to_refresh_scrollview)
    PullToRefreshScrollView pullToRefreshScrollview;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.img_quesheng)
    ImageView imgQuesheng;
    private int pageindex = 1;
    private int userid;
    private List<MemorandumBean.ListBean> beanList = new ArrayList<>();
    private List<MemorandumBean.ListBean> selectbeanList = new ArrayList<>();
    private MemoListAdapter adapter;
    private String sousuo_neirong;
    int position;
    private MemoListAdapter selectadapter;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        ScrowUtil.ScrollViewsetConfigAll(pullToRefreshScrollview);
        pullToRefreshScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageindex = 1;
                String sousuo_neirong = edSearch.getText().toString();
                if (sousuo_neirong != null && !sousuo_neirong.equals("")) {
                    selectbeanList.clear();
                    getP().selectMemoList(userid, pageindex, 10, sousuo_neirong);
                } else {
                    beanList.clear();
                    getP().getMemoList(pageindex, 10);
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageindex++;
                String sousuo_neirong = edSearch.getText().toString();
                if (sousuo_neirong != null && !sousuo_neirong.equals("")) {
                    getP().selectMemoList(userid, pageindex, 10, sousuo_neirong);
                } else {
                    getP().getMemoList(pageindex, 10);
                }
            }
        });
        edSearch.addTextChangedListener(mTextWatcher);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sousuo_neirong = edSearch.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(MemorandumActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (sousuo_neirong == null || sousuo_neirong.equals("")) {
                        ToastUtils.showToast("搜索内容不能为空");
                    } else {
                        selectbeanList.clear();
                        pageindex = 1;
                        getP().selectMemoList(userid, pageindex, 10, sousuo_neirong);
                    }
                }
                return false;
            }
        });
        lvMemarand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MemorandumBean.ListBean listBean = beanList.get(position);
                Intent intent = new Intent(MemorandumActivity.this, AddMemorandumActivity.class);
                intent.putExtra("memid", listBean.getMemoId());
                intent.putExtra("memovlue", listBean.getMemoVal());
                startActivity(intent);
            }
        });
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PositionMessageEvent messageEvent) {
        String position = messageEvent.getPosition();
        if (position.equals("updatebeiwanglu")) {
            beanList.clear();
            pageindex = 1;
            getP().getMemoList(pageindex, 10);
        }
    }

    @Override
    public void initAfter() {
        userid = SharedPref.getInstance(MemorandumActivity.this).getInt("userid", 0);
        getP().getMemoList(pageindex, 10);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_memorandum;
    }

    @Override
    public MemorandumPresent bindPresent() {
        return new MemorandumPresent();
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
                beanList.clear();
                pageindex = 1;
                getP().getMemoList(pageindex, 10);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        imgClear.setOnClickListener(this);
        imgQuesheng.setOnClickListener(this);
        teAdd.setOnClickListener(this);
    }

    public void getResult(MemorandumBean bean) {
        if (bean.getList().size() > 0) {
            if (pageindex == 1) {
                beanList.clear();
                beanList.addAll(bean.getList());
                adapter = new MemoListAdapter(MemorandumActivity.this, beanList);
                lvMemarand.setAdapter(adapter);
            } else {
                beanList.addAll(bean.getList());
                adapter.notifyDataSetChanged();
            }
        } else {
            if (pageindex == 1) {
                pullToRefreshScrollview.setVisibility(View.GONE);
                imgQuesheng.setVisibility(View.VISIBLE);
                imgQuesheng.setBackgroundResource(R.mipmap.no_data);
                ToastUtils.showToast("暂无数据");
            } else {
                ToastUtils.showToast("暂无更多数据");
            }
        }
        setListViewHeightBasedOnChildren(lvMemarand);
        stopRefresh();
    }

    public void getSelectResult(MemorandumBean memorandumBean) {
        if (memorandumBean.getList().size() > 0) {
            if (pageindex == 1) {
                beanList.clear();
                selectbeanList.clear();
                selectbeanList.addAll(memorandumBean.getList());
                selectadapter = new MemoListAdapter(MemorandumActivity.this, selectbeanList);
                lvMemarand.setAdapter(selectadapter);
            } else {
                selectbeanList.addAll(memorandumBean.getList());
                selectadapter.notifyDataSetChanged();
            }
        } else {
            if (pageindex == 1) {
                ToastUtils.showToast("暂无数据");
            } else {
                ToastUtils.showToast("暂无更多数据");
            }
        }
        setListViewHeightBasedOnChildren(lvMemarand);
        stopRefresh();
    }

    public void setGoneQuesheng(){
        pullToRefreshScrollview.setVisibility(View.VISIBLE);
        imgQuesheng.setVisibility(View.GONE);
    }

    public void setImgQuesheng(){
        pullToRefreshScrollview.setVisibility(View.GONE);
        imgQuesheng.setVisibility(View.VISIBLE);
        imgQuesheng.setBackgroundResource(R.mipmap.no_network);
    }

    public void stopRefresh() {
        pullToRefreshScrollview.onRefreshComplete();
    }

    public void deleteMemo(int position, int ids) {
        this.position = position;
        getP().deleteMemo(ids);
    }

    public void getDelete(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0) {
            String trim = edSearch.getText().toString().trim();
            if (trim != null && !trim.equals("")) {
                selectbeanList.remove(position);
                selectadapter.notifyDataSetChanged();
            } else {
                beanList.remove(position);
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 为了解决ListView在ScrollView中只能显示一行数据的问题
     *
     * @param listView
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_quesheng:
                pageindex = 1;
                getP().getMemoList(pageindex, 10);
                break;
            case R.id.img_clear:
                edSearch.setText("");
                break;
            case R.id.te_add:
                startActivity(new Intent(MemorandumActivity.this, AddMemorandumActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
