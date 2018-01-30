package com.yunyisheng.app.yunys.schedule.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/1/30 18:04
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProjectListAdapter extends SimpleListAdapter<ProjectBean, ProjectListAdapter.ViewHolder> {

    public ProjectListAdapter(Context context, List<ProjectBean> data) {
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
    protected void convert(ViewHolder holder, ProjectBean item, int position) {
        holder.teName.setText(data.get(position).getProjectName());
    }

    class ViewHolder {

        @BindView(R.id.te_name)
        TextView teName;

        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}