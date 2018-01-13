package com.yunyisheng.app.yunys.adapter;

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
import com.yunyisheng.app.yunys.schedule.activity.ScheduleDeatilActivity;

/**
 * Created by liyalong on 2017/12/26.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private String[] titles;

    public TaskAdapter(Context context) {
        titles = context.getResources().getStringArray(R.array.titles);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.task_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ScheduleDeatilActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView te_schedule_title, te_schedule_time, te_schedule_detail, te_zhixing_peo,te_liucheng_type;
        RelativeLayout cv_item;
        ImageView img_isok;

        ViewHolder(View view) {
            super(view);
            te_liucheng_type = (TextView) view.findViewById(R.id.te_liucheng_type);
            img_isok = (ImageView) view.findViewById(R.id.img_isok);
            cv_item = (RelativeLayout) view.findViewById(R.id.cv_item);
            te_schedule_title = (TextView) view.findViewById(R.id.te_schedule_title);
            te_schedule_time = (TextView) view.findViewById(R.id.te_schedule_time);
            te_schedule_detail = (TextView) view.findViewById(R.id.te_schedule_detail);
            te_zhixing_peo = (TextView) view.findViewById(R.id.te_zhixing_peo);
        }
    }
}
