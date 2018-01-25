package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
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
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.SwipeListView;
import com.yunyisheng.app.yunys.utils.ToastUtils;

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
    private int pageindex = 1;
    private int userid;
    private List<MemorandumBean.ListBean> beanList = new ArrayList<>();
    private MemoListAdapter adapter;
    private String sousuo_neirong;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ScrowUtil.ScrollViewsetConfigAll(pullToRefreshScrollview);
        pullToRefreshScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                beanList.clear();
                pageindex = 1;
                getP().getMemoList(pageindex, 10);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageindex++;
                getP().getMemoList(pageindex, 10);
            }
        });
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sousuo_neirong = edSearch.getText().toString();
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(MemorandumActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (sousuo_neirong == null || sousuo_neirong.equals("")) {
                       ToastUtils.showToast("搜索内容不能为空");
                    } else {

                    }
                }
                return false;
            }
        });
        lvMemarand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MemorandumBean.ListBean listBean = beanList.get(position);
                Intent intent=new Intent(MemorandumActivity.this,AddMemorandumActivity.class);
                intent.putExtra("memid",listBean.getMemoId());
                intent.putExtra("memovlue",listBean.getMemoVal());
                startActivity(intent);
            }
        });
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

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        imgClear.setOnClickListener(this);
        teAdd.setOnClickListener(this);
    }

    public void getResult(MemorandumBean bean) {
        if (bean.getList().size() > 0) {
            if (pageindex == 1) {
                beanList.addAll(bean.getList());
                adapter = new MemoListAdapter(MemorandumActivity.this, beanList);
                lvMemarand.setAdapter(adapter);
            } else {
                beanList.addAll(bean.getList());
                adapter.notifyDataSetChanged();
            }
        } else {
            if (pageindex == 1) {
                ToastUtils.showToast("暂无数据");
            } else {
                ToastUtils.showToast("暂无更多数据");
            }
        }
        setListViewHeightBasedOnChildren(lvMemarand);
        pullToRefreshScrollview.onRefreshComplete();
    }

    public void deleteMemo(int ids) {
        getP().deleteMemo(ids);
    }

    public void getDelete(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0) {
            adapter.notifyDataSetChanged();
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
            case R.id.img_clear:
                edSearch.setText("");
            case R.id.te_add:
                startActivity(new Intent(MemorandumActivity.this, AddMemorandumActivity.class));
                break;
        }
    }
}
