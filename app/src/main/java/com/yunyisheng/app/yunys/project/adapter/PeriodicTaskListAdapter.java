package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.PeriodicTaskBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/25.
 */

public class PeriodicTaskListAdapter extends SimpleListAdapter<PeriodicTaskBean, PeriodicTaskListAdapter.ViewHolder> {

    private PeriodicTaskBean periodicTaskBean;

    public PeriodicTaskListAdapter(Context context, List<PeriodicTaskBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.periodic_tasks_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, PeriodicTaskBean item, int position) {
        periodicTaskBean = data.get(position);
        holder.taskName.setText(periodicTaskBean.getCycletaskName().toString());
        holder.addtime.setText(periodicTaskBean.getCreateTime().toString());
        holder.cronValue.setText(periodicTaskBean.getCorn().toString());
        if(periodicTaskBean.getCycletaskStat() == 1){
            holder.deviceTasksStatus.setText(R.string.device_cycle_task_status_1);
        }else {
            holder.deviceTasksStatus.setText(R.string.device_cycle_task_status_2);
        }

    }

    public static class ViewHolder {
        @BindView(R.id.task_name)
        TextView taskName;
        @BindView(R.id.addtime)
        TextView addtime;
        @BindView(R.id.device_tasks_status)
        TextView deviceTasksStatus;
        @BindView(R.id.cron_value)
        TextView cronValue;
        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
