package com.yunyisheng.app.yunys.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.yunyisheng.app.yunys.R;

import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * Created by liyalong on 2017/12/6.
 */

public class ProjectFragment extends XFragment {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fargment_project;
    }

    @Override
    public Object newP() {
        return null;
    }

    public static Fragment newInstance() {
        return null;
    }
}
