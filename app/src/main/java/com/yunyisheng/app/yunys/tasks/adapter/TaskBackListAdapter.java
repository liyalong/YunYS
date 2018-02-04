package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/2/2.
 */

public class TaskBackListAdapter extends SimpleListAdapter<ScheduleDetailBean.RespBodyBean.TaskBackBean, TaskBackListAdapter.ViewHolder> {
    public TaskBackListAdapter(Context context, List<ScheduleDetailBean.RespBodyBean.TaskBackBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.back_info_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, ScheduleDetailBean.RespBodyBean.TaskBackBean item, int position) {
        if (data.get(position).getTaskbackVal() != null){
            holder.taskbackVal.setText(item.getTaskbackVal().toString());
        }else {
            holder.taskbackVal.setText("无");
        }
        holder.taskBackUser.setText(item.getTaskbackUsername().toString());
        holder.taskBackCreatet.setText(item.getTaskbackCreatet().toString());
    }

    public static class ViewHolder {
        @BindView(R.id.taskback_val)
        TextView taskbackVal;
        @BindView(R.id.task_back_user)
        TextView taskBackUser;
        @BindView(R.id.task_back_createt)
        TextView taskBackCreatet;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }
}
