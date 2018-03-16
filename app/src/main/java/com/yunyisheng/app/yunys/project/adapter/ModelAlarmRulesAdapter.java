package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.DeviceAlarmRulesBean;
import com.yunyisheng.app.yunys.project.bean.ModelAlarmRulesBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/24.
 */

public class ModelAlarmRulesAdapter extends SimpleListAdapter<ModelAlarmRulesBean, ModelAlarmRulesAdapter.ViewHolder> {
    private List<ModelAlarmRulesBean> dataList;

    public ModelAlarmRulesAdapter(Context context, List<ModelAlarmRulesBean> data) {
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
    protected void convert(ViewHolder holder, ModelAlarmRulesBean item, int position) {
        ModelAlarmRulesBean modelAlarmRulesBean = dataList.get(position);
        holder.alramRulesName.setText(modelAlarmRulesBean.getPcmwarnName());
        switch (modelAlarmRulesBean.getPcmwarnLevel()) {
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
            case 4:
                holder.alarmRulesLevel.setText(R.string.alarm_rules_level_4);
                holder.alarmRulesLevel.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_4));
                break;
        }
        holder.addtime.setText(modelAlarmRulesBean.getCreatet().substring(0,16));
        holder.alarmrulesSet.setText(modelAlarmRulesBean.getCompare());
        if (modelAlarmRulesBean.getRemark() != null) {
            holder.alarmrulesDesc.setText(modelAlarmRulesBean.getRemark());
        } else {
            holder.alarmrulesDesc.setText("");
        }

        if (modelAlarmRulesBean.getPcmwarnStat() == 1) {
            holder.alarmrulesStatus.setText(R.string.alarm_rules_status_1);
        } else {
            holder.alarmrulesStatus.setText(R.string.alarm_rules_status_2);
        }
        holder.alarmrulesValue.setVisibility(View.GONE);
        holder.alarmRulesLevelTitle.setVisibility(View.GONE);
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
        @BindView(R.id.alarm_rules_level_title)
        TextView alarmRulesLevelTitle;

        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
