package com.yunyisheng.app.yunys.schedule.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.activity.TaskDetailActivity;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.tasks.activity.ProcessDetailActivity;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by liyalong on 2017/12/26.
 */

public class TaskAdapter extends RecyclerAdapter<MyScheduleBean.RespBodyBean.DataListBean, TaskAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<MyScheduleBean.RespBodyBean.DataListBean> list = new ArrayList<>();
    int scheduletype;

    public TaskAdapter(Context context, List<MyScheduleBean.RespBodyBean.DataListBean> list) {
        super(context);
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setType(int type){
        this.scheduletype=type;
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.task_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyScheduleBean.RespBodyBean.DataListBean bean = list.get(position);
        holder.te_schedule_title.setText(bean.getTheme());
        String creationTime = bean.getCreationTime();
        String endTime = bean.getEndTime();
        if (position==list.size()-1){
            holder.view1.setVisibility(View.GONE);
        }
        holder.te_schedule_time.setText(creationTime + "-" + endTime);
        final String type = bean.getType();
        if (type==null)return;
        if (type.equals("1")) {
            holder.te_liucheng_type.setText("设备");
        } else if (type.equals("2")) {
            holder.te_liucheng_type.setText("非设备");
        } else {
            holder.te_liucheng_type.setText("流程");
        }
        String state = bean.getState();
        if (state.equals("3")||state.equals("102")) {
            holder.img_isok.setVisibility(View.VISIBLE);
        } else {
            holder.img_isok.setVisibility(View.GONE);
        }
        holder.cv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("1")||type.equals("2")) {
                    Intent intent = new Intent(context, TaskDetailActivity.class);
                    intent.putExtra("taskType", type);
                    intent.putExtra("taskId", bean.getTaskId());
                    intent.putExtra("projectId",bean.getProjectsId());
                    if (scheduletype==5){
                        intent.putExtra("fromPage",4);
                    }else if (scheduletype==6){
                        intent.putExtra("fromPage",5);
                    }
                    context.startActivity(intent);
                }else {
                    Intent intent=new Intent(context, ProcessDetailActivity.class);
                    intent.putExtra("taskType", type);
                    intent.putExtra("taskId", bean.getTaskId());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView te_schedule_title, te_schedule_time, te_liucheng_type;
        RelativeLayout cv_item;
        ImageView img_isok;
        View view1;

        ViewHolder(View view) {
            super(view);
            te_liucheng_type = (TextView) view.findViewById(R.id.te_liucheng_type);
            img_isok = (ImageView) view.findViewById(R.id.img_isok);
            cv_item = (RelativeLayout) view.findViewById(R.id.cv_item);
            te_schedule_title = (TextView) view.findViewById(R.id.te_schedule_title);
            te_schedule_time = (TextView) view.findViewById(R.id.te_schedule_time);
            view1=view.findViewById(R.id.view);
        }
    }
}
