package com.yunyisheng.app.yunys.utils;

import android.view.View;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.yunyisheng.app.yunys.ConstantManager;

/**
 * 作者：fuduo
 * 时间 2018/1/12
 * 邮箱：mengchong.55@163.com
 * 类的意图：
 */

public class ScrowUtil {

    /**
     * @Author :付铎
     * @DATE :2017/9/22 15:10
     * @Params 上拉下拉
     */
    public static void ScrollViewsetConfigAll(PullToRefreshScrollView mScrollView){
        mScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        mScrollView.getLoadingLayoutProxy(true,false).setPullLabel(ConstantManager.PullDownLabel);
        mScrollView.getLoadingLayoutProxy(true,false).setRefreshingLabel(ConstantManager.RefreshingDownLabel);
        mScrollView.getLoadingLayoutProxy(true,false).setReleaseLabel(ConstantManager.ReleaseDownLabel);

        mScrollView.getLoadingLayoutProxy(false,true).setPullLabel(ConstantManager.PullUpLabel);
        mScrollView.getLoadingLayoutProxy(false,true).setRefreshingLabel(ConstantManager.RefreshingUpLabel);
        mScrollView.getLoadingLayoutProxy(false,true).setReleaseLabel(ConstantManager.ReleaseUpLabel);

        final ScrollView refreshableView = mScrollView.getRefreshableView();
        refreshableView.post(new Runnable() {
            @Override
            public void run() {
                refreshableView.fullScroll(View.FOCUS_UP);
            }
        });
        refreshableView.smoothScrollTo(0,20);//ScrollView
    }
    /**
     * @Author :付铎
     * @DATE :2017/9/22 15:10
     * @Params 仅有下拉
     */

    public static void ScrollViewsetConfig(PullToRefreshScrollView mScrollView){
        mScrollView.getLoadingLayoutProxy(true,false).setPullLabel(ConstantManager.PullDownLabel);
        mScrollView.getLoadingLayoutProxy(true,false).setRefreshingLabel(ConstantManager.RefreshingDownLabel);
        mScrollView.getLoadingLayoutProxy(true,false).setReleaseLabel(ConstantManager.ReleaseDownLabel);

        mScrollView.getLoadingLayoutProxy(false,true).setPullLabel(ConstantManager.PullUpLabel);
        mScrollView.getLoadingLayoutProxy(false,true).setRefreshingLabel(ConstantManager.RefreshingUpLabel);
        mScrollView.getLoadingLayoutProxy(false,true).setReleaseLabel(ConstantManager.ReleaseUpLabel);

       final ScrollView refreshableView = mScrollView.getRefreshableView();
        refreshableView.post(new Runnable() {
            @Override
            public void run() {
                refreshableView.fullScroll(View.FOCUS_UP);
            }
        });
        refreshableView.smoothScrollTo(0,20);//ScrollView
    }

    /**
     * @Author :付铎
     * @DATE :2017/9/22 15:10
     * @Params PullToRefreshListView 上拉下拉
     */

    public static void listViewConfig(PullToRefreshListView mlistview){
        mlistview.setMode(PullToRefreshBase.Mode.BOTH);
        mlistview.getLoadingLayoutProxy(true, false).setPullLabel(ConstantManager.PullDownLabel);
        mlistview.getLoadingLayoutProxy(true, false).setRefreshingLabel(ConstantManager.RefreshingDownLabel);
        mlistview.getLoadingLayoutProxy(true, false).setReleaseLabel(ConstantManager.ReleaseDownLabel);

        mlistview.getLoadingLayoutProxy(false, true).setPullLabel(ConstantManager.PullUpLabel);
        mlistview.getLoadingLayoutProxy(false, true).setRefreshingLabel(ConstantManager.RefreshingUpLabel);
        mlistview.getLoadingLayoutProxy(false, true).setReleaseLabel(ConstantManager.ReleaseUpLabel);
    }

    public static void listViewUpConfig(PullToRefreshListView mlistview){
        mlistview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mlistview.getLoadingLayoutProxy(true, false).setPullLabel(ConstantManager.PullDownLabel);
        mlistview.getLoadingLayoutProxy(true, false).setRefreshingLabel(ConstantManager.RefreshingDownLabel);
        mlistview.getLoadingLayoutProxy(true, false).setReleaseLabel(ConstantManager.ReleaseDownLabel);

        mlistview.getLoadingLayoutProxy(false, true).setPullLabel(ConstantManager.PullUpLabel);
        mlistview.getLoadingLayoutProxy(false, true).setRefreshingLabel(ConstantManager.RefreshingUpLabel);
        mlistview.getLoadingLayoutProxy(false, true).setReleaseLabel(ConstantManager.ReleaseUpLabel);
    }
}
