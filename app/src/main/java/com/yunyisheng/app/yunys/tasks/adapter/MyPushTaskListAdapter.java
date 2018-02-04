package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.bean.TaskBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/30.
 */

public class MyPushTaskListAdapter extends SimpleListAdapter<TaskBean, MyPushTaskListAdapter.ViewHolder> implements View.OnClickListener {
    TaskBean taskBean;

    private MyPushTaskListAdapter.Callback mCallback;
    public interface Callback{
        public void click(View v);
    }

    public MyPushTaskListAdapter(Context context, List<TaskBean> data, Callback callback) {
        super(context, data);
        mCallback = callback;
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.task_pool_item;
    }

    @Override
    protected void convert(ViewHolder holder, TaskBean item, int position) {
        holder.taskStatusBox.setVisibility(View.GONE);

        holder.taskName.setText(item.getReleaseName().toString());
        holder.taskStartTime.setText(item.getReleaseBegint().toString());
        holder.taskEndTime.setText(item.getReleaseEndt().toString());
        holder.createUser.setText(item.getReleaseUsername().toString());

        //待认领任务设置待认领状态隐藏，认领人信息隐藏
        //待完成任务状态，设置待认领状态隐藏,认领人信息隐藏
        holder.taskStat.setVisibility(View.VISIBLE);
        holder.taskStat.setBackgroundColor(context.getResources().getColor(R.color.device_status_success));
        holder.taskStat.setTextColor(context.getResources().getColor(R.color.white));
        if (item.getTaskStat() == 0){
            holder.taskStat.setText(R.string.task_status_1);
        }else if (item.getTaskStat() == 1){
            holder.taskStat.setText(R.string.task_status_2);
        }else if (item.getTaskStat() == 2){
            holder.taskStat.setText(R.string.task_status_3);
        }else if (item.getTaskStat() == 3){
            holder.taskStat.setText(R.string.task_status_6);
//            holder.taskStat.setTextColor(context.getResources().getColor(R.color.color_2F));
//            holder.taskStat.setBackgroundColor(context.getResources().getColor(R.color.color_green_main_disabled));
        }
        if (item.getTaskSubmitTime() == null){
            //判断超时，
            String now = DateTimeDialogUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
            if (DateTimeDialogUtils.DateCompare(item.getReleaseEndt(),now) == true){
                holder.taskStatusIstimeout.setVisibility(View.VISIBLE);
            }else {
                holder.taskStatusIstimeout.setVisibility(View.GONE);
            }
        }
        holder.taskBtn.setVisibility(View.GONE);
        holder.taskBtn.setTag(position);
        //holder.taskBtn.setOnClickListener(this);

    }

    public static class ViewHolder {
        @BindView(R.id.task_name)
        TextView taskName;
        @BindView(R.id.task_stat)
        TextView taskStat;
        @BindView(R.id.task_status_istimeout)
        TextView taskStatusIstimeout;
        @BindView(R.id.task_start_time)
        TextView taskStartTime;
        @BindView(R.id.task_end_time)
        TextView taskEndTime;
        @BindView(R.id.create_user)
        TextView createUser;
        @BindView(R.id.task_do_user_info)
        TextView taskDoUserInfo;
        @BindView(R.id.take_do_user)
        TextView takeDoUser;
        @BindView(R.id.task_btn)
        TextView taskBtn;
        @BindView(R.id.task_status_box)
        LinearLayout taskStatusBox;
        @BindView(R.id.user_info_box)
        LinearLayout userInfoBox;
        @BindView(R.id.releaseStatFinish)
        TextView releaseStatFinish;
        @BindView(R.id.releaseStatClaim)
        TextView releaseStatClaim;
        @BindView(R.id.releaseStatBack)
        TextView releaseStatBack;
        @BindView(R.id.releaseStatUnclaim)
        TextView releaseStatUnclaim;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }

    @Override
    public void onClick(View view){
        mCallback.click(view);
    }
}
