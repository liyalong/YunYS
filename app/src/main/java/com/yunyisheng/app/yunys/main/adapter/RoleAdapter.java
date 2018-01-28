package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.model.RoleBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/1/28 14:28
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class RoleAdapter extends SimpleListAdapter<RoleBean.RespBodyBean, RoleAdapter.ViewHolder> {

    public RoleAdapter(Context context, List<RoleBean.RespBodyBean> data) {
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
    protected void convert(ViewHolder holder, RoleBean.RespBodyBean item, int position) {
        holder.teName.setText(data.get(position).getRoleName());
    }

    class ViewHolder {

        @BindView(R.id.te_name)
        TextView teName;

        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
