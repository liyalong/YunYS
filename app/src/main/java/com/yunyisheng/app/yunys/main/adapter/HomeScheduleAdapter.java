package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.schedule.activity.ScheduleDeatilActivity;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.base.SimpleListAdapter;

/**
 * Created by liyalong on 2017/12/26.
 */

public class HomeScheduleAdapter extends SimpleListAdapter<MyScheduleBean.RespBodyBean.DataListBean, HomeScheduleAdapter.ViewHolder> {

    private final Context context;
    private List<MyScheduleBean.RespBodyBean.DataListBean> list = new ArrayList<>();

    public HomeScheduleAdapter(Context context, List<MyScheduleBean.RespBodyBean.DataListBean> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.task_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, MyScheduleBean.RespBodyBean.DataListBean item, int position) {
        final MyScheduleBean.RespBodyBean.DataListBean bean = list.get(position);
        holder.te_schedule_title.setText(bean.getTheme());
        String creationTime = bean.getCreationTime();
        String endTime = bean.getEndTime();

        if (position==list.size()-1){
            holder.view1.setVisibility(View.GONE);
        }

        holder.te_schedule_time.setText(creationTime + "-" + endTime);
        String type = bean.getType();
        if (type.equals("1")) {
            holder.te_liucheng_type.setText("设备");
        } else if (type.equals("2")) {
            holder.te_liucheng_type.setText("非设备");
        } else {
            holder.te_liucheng_type.setText("流程");
        }
        String state = bean.getState();
        if (state.equals("3")) {
            holder.img_isok.setVisibility(View.VISIBLE);
        } else {
            holder.img_isok.setVisibility(View.GONE);
        }
        holder.cv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScheduleDeatilActivity.class);
                intent.putExtra("scheduleid", bean.getTaskId());
                context.startActivity(intent);
            }
        });
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
