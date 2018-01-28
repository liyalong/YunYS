package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.model.ReportFormBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/1/26 19:31
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class CanAddReportformListAdapter extends SimpleListAdapter<ReportFormBean.ListBean, CanAddReportformListAdapter.ViewHolder> {

    public CanAddReportformListAdapter(Context context, List<ReportFormBean.ListBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.baobiao_add_item;
    }

    @Override
    protected void convert(ViewHolder holder, ReportFormBean.ListBean item, int position) {
        holder.teBaobiaoName.setText(data.get(position).getInstanceName());
    }

    class ViewHolder {

        @BindView(R.id.te_baobiao_name)
        TextView teBaobiaoName;

        public ViewHolder(View view) {
            KnifeKit.bind(this,view);
        }
    }
}
