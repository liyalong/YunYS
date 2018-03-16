package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.PeriodicTaskBean;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/25.
 */

public class PeriodicTaskListAdapter extends SimpleListAdapter<PeriodicTaskBean, PeriodicTaskListAdapter.ViewHolder> implements View.OnClickListener {
    private Callback mCallback;
    public interface Callback{
        public void click(View v);
    }
    private PeriodicTaskBean periodicTaskBean;

    public PeriodicTaskListAdapter(Context context, List<PeriodicTaskBean> data,Callback callback) {
        super(context, data);
        mCallback = callback;
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
    protected void convert(ViewHolder holder, final PeriodicTaskBean item, int position) {
        periodicTaskBean = data.get(position);
        holder.taskName.setText(periodicTaskBean.getCycletaskName());
        holder.addtime.setText(periodicTaskBean.getCreateTime().substring(0,16));
        holder.cronValue.setText(periodicTaskBean.getCorn());
        if(periodicTaskBean.getCycletaskStat() == 1){
            holder.deviceTasksStatus.setText(R.string.device_cycle_task_status_1);
        }else {
            holder.deviceTasksStatus.setText(R.string.device_cycle_task_status_2);
        }
        holder.toEditTask.setTag(position);
        holder.toEditTask.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        mCallback.click(view);
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
        @BindView(R.id.to_edit_task)
        TextView toEditTask;
        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
