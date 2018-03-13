package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.bean.TaskBean;
import com.yunyisheng.app.yunys.utils.DateTimeDialogUtils;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/30.
 */

public class TaskAdapter extends SimpleListAdapter<TaskBean, TaskAdapter.ViewHolder> implements View.OnClickListener {
    private int SELECT_TYPE;
    private int thisUserId = SharedPref.getInstance(context).getInt("userid",0);

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
        holder.taskName.setText(item.getReleaseName().toString());
        holder.taskStartTime.setText(item.getReleaseBegint().toString());
        holder.taskEndTime.setText(item.getReleaseEndt().toString());
        holder.createUser.setText(item.getReleaseUsername().toString());
        if (SELECT_TYPE == 1){
            //待认领任务设置待认领状态隐藏，认领人信息隐藏
            holder.taskDoUserInfo.setVisibility(View.GONE);
            holder.takeDoUser.setVisibility(View.GONE);
            holder.taskStatusBox.setVisibility(View.GONE);
            if (item.getTaskLook() == 0){
                holder.taskName.getPaint().setFakeBoldText(true);
            }else {
                holder.taskName.getPaint().setFakeBoldText(false);
            }

        }else if(SELECT_TYPE == 2 || SELECT_TYPE == 9){
            //待认领任务设置待认领状态隐藏，认领人信息隐藏
            //待完成任务状态，设置待认领状态隐藏,认领人信息隐藏
            if (String.valueOf(thisUserId) == item.getTaskUserId()){
                holder.taskDoUserInfo.setVisibility(View.GONE);
                holder.takeDoUser.setVisibility(View.GONE);
                holder.taskStatusBox.setVisibility(View.GONE);
            }else {
                holder.takeDoUser.setText(item.getTaskUserName());
                holder.taskDoUserInfo.setVisibility(View.VISIBLE);
                holder.takeDoUser.setVisibility(View.VISIBLE);
            }
            holder.taskStatusBox.setVisibility(View.GONE);
            if (item.getTaskLook() == 0){
                holder.taskName.getPaint().setFakeBoldText(true);
            }else {
                holder.taskName.getPaint().setFakeBoldText(false);
            }
        }else if(SELECT_TYPE == 3){
            //已发布任务
            holder.userInfoBox.setVisibility(View.GONE);
            holder.releaseStatFinish.setText(String.valueOf(item.getReleaseStatFinish()));
            holder.releaseStatClaim.setText(String.valueOf(item.getReleaseStatClaim()));
            holder.releaseStatBack.setText(String.valueOf(item.getReleaseStatBack()));
            holder.releaseStatUnclaim.setText(String.valueOf(item.getReleaseStatUnclaim()));
        }
        if (item.getTaskStat() == 3){
            holder.taskStat.setVisibility(View.VISIBLE);
            holder.taskBtn.setEnabled(false);
            holder.taskBtn.setBackgroundColor(context.getResources().getColor(R.color.color_c8c8c8));
        }else {
            holder.taskStat.setVisibility(View.GONE);
            holder.taskBtn.setEnabled(true);
            holder.taskBtn.setBackground(context.getResources().getDrawable(R.mipmap.button_cz));
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
        if (SELECT_TYPE == 2){
            if (thisUserId == Integer.valueOf(item.getTaskUserId())){
                holder.taskBtn.setEnabled(true);
                holder.taskBtn.setBackground(context.getResources().getDrawable(R.mipmap.button_cz));
                holder.taskBtn.setTag(position);
                holder.taskBtn.setOnClickListener(this);
            }else {
                holder.taskBtn.setEnabled(false);
                holder.taskBtn.setBackgroundColor(context.getResources().getColor(R.color.color_c8c8c8));
            }
        }else {
            holder.taskBtn.setTag(position);
            holder.taskBtn.setOnClickListener(this);
        }


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
