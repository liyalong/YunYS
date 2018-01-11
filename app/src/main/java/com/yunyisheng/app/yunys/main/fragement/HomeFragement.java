package com.yunyisheng.app.yunys.main.fragement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.activity.ReportformActivity;
import com.yunyisheng.app.yunys.utils.RecyclerBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/10 09:24
 * 邮箱：duoendeavor@163.com
 * 用途：首页fragement
 */

public class HomeFragement extends BaseFragement {
    @BindView(R.id.rcy_banner)
    RecyclerBanner rcyBanner;
    @BindView(R.id.img_baobiao)
    ImageView imgBaobiao;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.te_columntitle)
    TextView teColumntitle;
    @BindView(R.id.te_columnsize)
    TextView teColumnsize;
    @BindView(R.id.te_seeall)
    TextView teSeeall;
    @BindView(R.id.rl_todaycolumn)
    RelativeLayout rlTodaycolumn;
    @BindView(R.id.pull_to_refresh_scrollview)
    PullToRefreshListView pullToRefreshScrollview;
    Unbinder unbinder;
    @BindView(R.id.line_notice)
    LinearLayout lineNotice;
    @BindView(R.id.line_tongxunlu)
    LinearLayout lineTongxunlu;
    @BindView(R.id.line_beiwanglu)
    LinearLayout lineBeiwanglu;
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
    int i;

    @Override
    public void initView() {
        rcyBanner.setContext((Activity) mContext);
    }

    @Override
    public void initAfter() {

        i++;
        urls.clear();
        if (i % 2 == 0) {
            urls.add(new Entity("http://pic.58pic.com/58pic/12/46/13/03B58PICXxE.jpg"));
            urls.add(new Entity("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg"));
            urls.add(new Entity("http://pic34.nipic.com/20131025/2531170_132447503000_2.jpg"));
            urls.add(new Entity("http://img5.imgtn.bdimg.com/it/u=3462610901,3870573928&fm=206&gp=0.jpg"));
        } else {
            urls.add(new Entity("http://img0.imgtn.bdimg.com/it/u=726278301,2143262223&fm=11&gp=0.jpg"));
            urls.add(new Entity("http://pic51.nipic.com/file/20141023/2531170_115622554000_2.jpg"));
            urls.add(new Entity("http://img3.imgtn.bdimg.com/it/u=2968209827,470106340&fm=21&gp=0.jpg"));
        }
        long t = System.currentTimeMillis();
        rcyBanner.setDatas(urls);
        Log.w("---", System.currentTimeMillis() - t + "");
    }

    @Override
    public int bindLayout() {
        return R.layout.fargment_home;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBaobiao.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        teSeeall.setOnClickListener(this);
        lineNotice.setOnClickListener(this);
        lineTongxunlu.setOnClickListener(this);
        lineBeiwanglu.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_baobiao:
                startActivity(new Intent(mContext, ReportformActivity.class));
                break;
            case R.id.img_message:
                break;
            case R.id.te_seeall:
                break;
            case R.id.line_notice:
                break;
            case R.id.line_tongxunlu:
                break;
            case R.id.line_beiwanglu:
                break;
        }
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

    private class Entity implements RecyclerBanner.BannerEntity {

        String url;

        public Entity(String url) {
            this.url = url;
        }

        @Override
        public String getUrl() {
            return url;
        }
    }
}
