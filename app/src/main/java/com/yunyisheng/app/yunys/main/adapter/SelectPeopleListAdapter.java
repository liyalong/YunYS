package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/3/26 12:16
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SelectPeopleListAdapter extends SimpleListAdapter<String, SelectPeopleListAdapter.ViewHolder> {

    public SelectPeopleListAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.maillist_selectpeople_item;
    }

    @Override
    protected void convert(ViewHolder holder, String item, int position) {
        holder.teSelectName.setText(item);
    }

    class ViewHolder {
        @BindView(R.id.te_select_name)
        TextView teSelectName;

        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
