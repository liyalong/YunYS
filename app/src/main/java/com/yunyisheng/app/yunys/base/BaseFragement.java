package com.yunyisheng.app.yunys.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/9 14:09
 * 邮箱：duoendeavor@163.com
 * 用途：基类fragement
 */

public abstract class BaseFragement<M extends XPresent> extends XFragment<M> implements View.OnClickListener {

    protected final String TAG = this.getClass().getSimpleName();
    private View mContextView = null;
    protected Context mContext = null;

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = getContext();
        initView();
        initAfter();
        setListener();
    }

    @Override
    public int getLayoutId() {
        return bindLayout();
    }

    @Override
    public M newP() {
        return bindPresent();
    }

    /**
     * [找控件]
     *
     * @return
     */
    public abstract void initView();

    /**
     * [找完控件后执行]
     *
     * @return
     */
    public abstract void initAfter();

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract M bindPresent();

    /**
     * [设置监听]
     */
    public abstract void setListener();

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View view) {
        if (fastClick())
            widgetClick(view);
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }
}
