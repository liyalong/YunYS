package com.yunyisheng.app.yunys.project.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.activity.DeviceAlarmRulesActivity;
import com.yunyisheng.app.yunys.project.bean.DeviceAlarmRulesBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/24.
 */

public class DeviceAlarmRulesAdapter extends SimpleListAdapter<DeviceAlarmRulesBean, DeviceAlarmRulesAdapter.ViewHolder> {
    private List<DeviceAlarmRulesBean> dataList;

    public DeviceAlarmRulesAdapter(Context context, List<DeviceAlarmRulesBean> data) {
        super(context, data);
        this.dataList = data;
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.alarmrules_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, DeviceAlarmRulesBean item, int position) {
        DeviceAlarmRulesBean deviceAlarmRulesBean = dataList.get(position);
            holder.alramRulesName.setText(deviceAlarmRulesBean.getEquwarnName().toString());
            switch (deviceAlarmRulesBean.getEquwarnLevel()){
                case 1:
                    holder.alarmRulesLevel.setText(R.string.alarm_rules_level_1);
                    holder.alarmRulesLevel.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_1));
                    break;
                case 2:
                    holder.alarmRulesLevel.setText(R.string.alarm_rules_level_2);
                    holder.alarmRulesLevel.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_2));
                    break;
                case 3:
                    holder.alarmRulesLevel.setText(R.string.alarm_rules_level_3);
                    holder.alarmRulesLevel.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_3));
                    break;
                case  4:
                    holder.alarmRulesLevel.setText(R.string.alarm_rules_level_4);
                    holder.alarmRulesLevel.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_4));
                    break;
            }
            holder.addtime.setText(deviceAlarmRulesBean.getCreatet().toString());
            holder.alarmrulesSet.setText(deviceAlarmRulesBean.getEquwarnCompare().toString());
            holder.alarmrulesDesc.setText(deviceAlarmRulesBean.getDescription().toString());
            if (deviceAlarmRulesBean.getEquwarnStat() == 1){
                holder.alarmrulesStatus.setText(R.string.alarm_rules_status_1);
            }else {
                holder.alarmrulesStatus.setText(R.string.alarm_rules_status_2);
            }
            holder.alarmrulesValue.setText(deviceAlarmRulesBean.getEquwarnVal().toString());
    }

    public static class ViewHolder {
        @BindView(R.id.alram_rules_name)
        TextView alramRulesName;
        @BindView(R.id.alarm_rules_level)
        TextView alarmRulesLevel;
        @BindView(R.id.addtime)
        TextView addtime;
        @BindView(R.id.alarmrules_set)
        TextView alarmrulesSet;
        @BindView(R.id.alarmrules_value)
        TextView alarmrulesValue;
        @BindView(R.id.alarmrules_status)
        TextView alarmrulesStatus;
        @BindView(R.id.alarmrules_desc)
        TextView alarmrulesDesc;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }
}
