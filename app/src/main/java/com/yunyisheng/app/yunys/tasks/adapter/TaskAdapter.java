package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.TaskBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/30.
 */

public class TaskAdapter extends SimpleListAdapter<TaskBean, TaskAdapter.ViewHolder> implements View.OnClickListener {
    TaskBean taskBean;
    private int SELECT_TYPE;

    private TaskAdapter.Callback mCallback;
    public interface Callback{
        public void click(View v);
    }

    public TaskAdapter(Context context, List<TaskBean> data,int selectType,Callback callback) {
        super(context, data);
        this.SELECT_TYPE = selectType;
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
        taskBean = data.get(position);
        holder.taskName.setText(taskBean.getReleaseName().toString());
        holder.taskStartTime.setText(taskBean.getReleaseBegint().toString());
        holder.taskEndTime.setText(taskBean.getReleaseEndt().toString());
        holder.createUser.setText(taskBean.getReleaseUsername().toString());
        if (SELECT_TYPE == 1 || SELECT_TYPE == 2){
            //待认领任务设置待认领状态隐藏，认领人信息隐藏
            //待完成任务状态，设置待认领状态隐藏,认领人信息隐藏
            holder.taskDoUserInfo.setVisibility(View.GONE);
            holder.takeDoUser.setVisibility(View.GONE);
            holder.taskStatusBox.setVisibility(View.GONE);
        }else if(SELECT_TYPE == 3){
            //已发布任务
            holder.userInfoBox.setVisibility(View.GONE);
            holder.releaseStatFinish.setText(String.valueOf(taskBean.getReleaseStatFinish()));
            holder.releaseStatClaim.setText(String.valueOf(taskBean.getReleaseStatClaim()));
            holder.releaseStatBack.setText(String.valueOf(taskBean.getReleaseStatBack()));
            holder.releaseStatUnclaim.setText(String.valueOf(taskBean.getReleaseStatUnclaim()));
        }
        if (taskBean.getTaskSubmitTime() == null){
            //判断超时，
            String now = DateTimeDialogUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
            if (DateTimeDialogUtils.DateCompare(taskBean.getReleaseEndt(),now)){
                holder.taskStatusIstimeout.setVisibility(View.VISIBLE);
            }
        }
        holder.taskBtn.setTag(position);
        holder.taskBtn.setOnClickListener(this);

    }

    public static class ViewHolder {
        @BindView(R.id.task_name)
        TextView taskName;
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
