package com.yunyisheng.app.yunys.project.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.activity.DeviceDetailActivity;
import com.yunyisheng.app.yunys.project.bean.DeviceWarningBean;

import java.util.List;

/**
 * Created by liyalong on 2018/2/5.
 */

public class DeviceOrPCMAlarmListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groupList;
    private List<DeviceWarningBean> childList;
    DeviceDetailActivity deviceDetailActivity;

    public DeviceOrPCMAlarmListAdapter(Activity context, List<String> groupList, List<DeviceWarningBean> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.size();
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.device_detail_title_bar, null);
        TextView title = (TextView) view.findViewById(R.id.include_title_name);
        ImageView dropBtn = view.findViewById(R.id.drop_btn);
        title.setText(groupList.get(i));
        if (b) {
            dropBtn.setImageResource(R.mipmap.icon_device_down);
        } else {
            dropBtn.setImageResource(R.mipmap.icon_device_right);
        }
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.deviceinfo_baojing_item,null);
        TextView warning_name = view.findViewById(R.id.warning_name);
        TextView warningLeveal = view.findViewById(R.id.warning_leveal);
        TextView fuwei = view.findViewById(R.id.fuwei);
        if (childList.get(i1).getAlarmName() != null){
            warning_name.setText(childList.get(i1).getAlarmName().toString());
        }else {
            warning_name.setText(childList.get(i1).getAlarmName().toString());
        }
        switch (childList.get(i1).getAlarmLevel()){
            case 1:
                warningLeveal.setText(R.string.alarm_rules_level_1);
                warningLeveal.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_1));
                break;
            case 2:
                warningLeveal.setText(R.string.alarm_rules_level_2);
                warningLeveal.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_2));
                break;
            case 3:
                warningLeveal.setText(R.string.alarm_rules_level_3);
                warningLeveal.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_3));
                break;
            case 4:
                warningLeveal.setText(R.string.alarm_rules_level_4);
                warningLeveal.setBackgroundColor(context.getResources().getColor(R.color.alarmrules_level_4));
                break;
        }
        fuwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceWarningBean clickWarning = childList.get(i1);
                deviceDetailActivity = (DeviceDetailActivity) context;
                deviceDetailActivity.warningReset(Integer.valueOf(clickWarning.getAlarmId()));
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public String warningReset(String warningId){
        return warningId;
    }
}
