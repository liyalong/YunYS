package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.bean.ProcessDetailBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/3/1.
 */

public class ProcessTaskApprovalInfoAdapter extends SimpleListAdapter<ProcessDetailBean.HistoryCommnetsBean, ProcessTaskApprovalInfoAdapter.ViewHolder> {
    private String taskApprovalState;
    private Integer countSize;


    public ProcessTaskApprovalInfoAdapter(Context context, List<ProcessDetailBean.HistoryCommnetsBean> data, String taskApprovalState, int countSize) {
        super(context, data);
        this.taskApprovalState = taskApprovalState;
        this.countSize = countSize;
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.process_approval_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, ProcessDetailBean.HistoryCommnetsBean item, int position) {
        holder.processTaskApprovalUser.setText(item.getUserName());
        holder.processTaskApprovalTime.setText(item.getTime().substring(0,16));
        holder.processApprovalInfo.setText(item.getFullMessage());
        if ((position+1) == countSize){
            holder.processApprovalInfoTitle.setText(R.string.process_approval_info);
            holder.processTaskApprovalTimeTitle.setText(R.string.process_approval_time);
            holder.processTaskApprovalUserTitle.setText(R.string.process_approval_user);
        }else {
            holder.processApprovalInfoTitle.setText(R.string.process_turn_info);
            holder.processTaskApprovalTimeTitle.setText(R.string.process_turn_time);
            holder.processTaskApprovalUserTitle.setText(R.string.process_turn_user);
        }
    }

    public class ViewHolder {
        @BindView(R.id.process_task_approval_user)
        TextView processTaskApprovalUser;
        @BindView(R.id.process_task_approval_time)
        TextView processTaskApprovalTime;
        @BindView(R.id.process_approval_info)
        TextView processApprovalInfo;
        @BindView(R.id.process_task_approval_user_title)
        TextView processTaskApprovalUserTitle;
        @BindView(R.id.process_task_approval_time_title)
        TextView processTaskApprovalTimeTitle;
        @BindView(R.id.process_approval_info_title)
        TextView processApprovalInfoTitle;

        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
