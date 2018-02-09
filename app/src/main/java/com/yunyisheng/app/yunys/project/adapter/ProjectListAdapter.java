package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/12.
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
        return R.layout.project_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, ProjectBean item, int position) {
            final ProjectBean projectBean = data.get(position);
            holder.projectName.setText(projectBean.getProjectName());
            holder.projectCreateUser.setText(projectBean.getProjectLeaderName());
            holder.projectErrorLayout.setVisibility(View.GONE);
            holder.projectNewtaskLayout.setVisibility(View.GONE);
            holder.projectCreateTime.setText(projectBean.getProjectCreate());
            holder.projectDesc.setText(projectBean.getProjectRemarks());
    }



    public static class ViewHolder{
        @BindView(R.id.project_name)
        TextView projectName;
        @BindView(R.id.project_error_nums)
        TextView projectErrorNums;
        @BindView(R.id.project_error_layout)
        LinearLayout projectErrorLayout;
        @BindView(R.id.project_newtask_nums)
        TextView projectNewtaskNums;
        @BindView(R.id.project_newtask_layout)
        LinearLayout projectNewtaskLayout;
        @BindView(R.id.project_create_time)
        TextView projectCreateTime;
        @BindView(R.id.project_desc)
        TextView projectDesc;
        @BindView(R.id.project_create_user)
        TextView projectCreateUser;
        public ViewHolder(View itemView) {
            KnifeKit.bind(this, itemView);
        }
    }
}
