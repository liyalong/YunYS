package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.DeviceWarningBean;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/2/5.
 */

public class AlarmListAdapter extends SimpleListAdapter<DeviceWarningBean, AlarmListAdapter.ViewHolder> {

    public AlarmListAdapter(Context context, List<DeviceWarningBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.alarm_history_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, DeviceWarningBean item, int position) {
        holder.warningName.setText(item.getAlarmName());
        holder.alarmHistoryTime.setText(item.getAlarmCreateDate().substring(0,16));

        if (item.getAlarmType() == 1){
            holder.alarmHistoryType.setText(R.string.alarm_history_type_1);
        }else if (item.getAlarmType() == 2){
            holder.alarmHistoryType.setText(R.string.alarm_history_type_2);
        }else {
            holder.alarmHistoryType.setText(R.string.alarm_history_type_3);
        }
        if (item.getAlarmRemark() != null){
            holder.alarmHistoryDesc.setText(item.getAlarmRemark());
        }else {
            holder.alarmHistoryDesc.setText("");
        }
        switch (item.getAlarmLevel()){
            case 1:
                holder.warningLeveal.setText(R.string.alarm_rules_level_1);
                holder.warningLeveal.setBackgroundResource(R.color.alarmrules_level_1);
                break;
            case 2:
                holder.warningLeveal.setText(R.string.alarm_rules_level_2);
                holder.warningLeveal.setBackgroundResource(R.color.alarmrules_level_2);
                break;
            case 3:
                holder.warningLeveal.setText(R.string.alarm_rules_level_3);
                holder.warningLeveal.setBackgroundResource(R.color.alarmrules_level_3);
                break;
            case 4:
                holder.warningLeveal.setText(R.string.alarm_rules_level_4);
                holder.warningLeveal.setBackgroundResource(R.color.alarmrules_level_4);
                break;
        }
        switch (item.getAlarmHandleType()){
            case 0:
                holder.alarm_status.setText(R.string.alarm_handle_type_1);
                holder.alarmUpdateTimeTitle.setVisibility(View.GONE);
                holder.alarmUpdateTime.setVisibility(View.GONE);
                break;
            case 1:
                holder.alarm_status.setText(R.string.alarm_handle_type_2);
                holder.alarmUpdateTimeTitle.setVisibility(View.VISIBLE);
                holder.alarmUpdateTime.setVisibility(View.VISIBLE);
                holder.alarmUpdateTime.setText(item.getAlarmUpdateDate().substring(0,16));
                break;
            case 2:
                holder.alarm_status.setText(R.string.alarm_handle_type_3);
                holder.alarmUpdateTimeTitle.setVisibility(View.VISIBLE);
                holder.alarmUpdateTime.setVisibility(View.VISIBLE);
                holder.alarmUpdateTime.setText(item.getAlarmUpdateDate().substring(0,16));
                break;
        }
    }

    public class ViewHolder {
        @BindView(R.id.warning_name)
        TextView warningName;
        @BindView(R.id.warning_leveal)
        TextView warningLeveal;
        @BindView(R.id.alarm_history_time)
        TextView alarmHistoryTime;
        @BindView(R.id.alarm_history_type)
        TextView alarmHistoryType;
        @BindView(R.id.alarm_history_desc)
        TextView alarmHistoryDesc;
        @BindView(R.id.alarm_status)
        TextView alarm_status;
        @BindView(R.id.alarm_update_time_title)
        TextView alarmUpdateTimeTitle;
        @BindView(R.id.alarm_update_time)
        TextView alarmUpdateTime;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }
}
