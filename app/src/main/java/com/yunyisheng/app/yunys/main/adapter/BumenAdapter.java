package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.model.BuMenBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/1/28 14:28
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class BumenAdapter extends SimpleListAdapter<BuMenBean.RespBodyBean, BumenAdapter.ViewHolder> {

    public BumenAdapter(Context context, List<BuMenBean.RespBodyBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.bumenandrole_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, BuMenBean.RespBodyBean item, int position) {
        holder.teName.setText(data.get(position).getSectionName());
    }

    class ViewHolder {

        @BindView(R.id.te_name)
        TextView teName;

        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
