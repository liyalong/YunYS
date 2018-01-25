package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.ModelBean;

import java.util.List;

import cn.droidlover.xdroidmvp.base.SimpleListAdapter;

/**
 * Created by liyalong on 2018/1/22.
 */

public class ModelListAdapter extends SimpleListAdapter<ModelBean,ModelListAdapter.ViewHolder> {
    public ModelListAdapter(Context context, List<ModelBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void convert(ViewHolder holder, ModelBean item, int position) {

    }

    public class ViewHolder {
    }
}
