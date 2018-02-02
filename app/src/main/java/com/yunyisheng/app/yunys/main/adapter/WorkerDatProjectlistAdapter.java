package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.activity.ProjectDetailsActivity;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2017/12/26.
 */

public class WorkerDatProjectlistAdapter extends SimpleListAdapter<ProjectBean, WorkerDatProjectlistAdapter.ViewHolder> {


    public WorkerDatProjectlistAdapter(Context context, List<ProjectBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.maillist_project_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, ProjectBean item, int position) {
        ProjectBean projectBean = data.get(position);
        holder.teProjectTitle.setText(projectBean.getProjectName());
        String creationTime = projectBean.getProjectCreate();
        String endTime = projectBean.getProjectUpdate();
        holder.teProjectTime.setText(creationTime + " - " + endTime);
        ProjectBean.ProType proType = projectBean.getProType();
        String projectTypeName = proType.getProjectTypeName();
        holder.teProjectType.setText(projectTypeName);
        holder.teZhixingPeo.setText("执行人："+projectBean.getProjectLeader());
        holder.teProjectDetail.setText(projectBean.getProjectRemarks());
        final String projectId = projectBean.getProjectId();
        final String projectname=projectBean.getProjectName();
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProjectDetailsActivity.class);
                intent.putExtra("projectId",projectId);
                intent.putExtra("projectName",projectname);
                context.startActivity(intent);
            }
        });
    }

    class ViewHolder {

        @BindView(R.id.te_project_title)
        TextView teProjectTitle;
        @BindView(R.id.te_project_type)
        TextView teProjectType;
        @BindView(R.id.te_project_time)
        TextView teProjectTime;
        @BindView(R.id.te_project_detail)
        TextView teProjectDetail;
        @BindView(R.id.te_zhixing_peo)
        TextView teZhixingPeo;
        @BindView(R.id.cv_item)
        RelativeLayout cvItem;

        public ViewHolder(View view) {
            KnifeKit.bind(this,view);
        }
    }
}
