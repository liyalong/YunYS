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
import com.yunyisheng.app.yunys.project.bean.DevicePLCValueBean;

import java.util.List;

/**
 * Created by liyalong on 2018/2/5.
 */

public class DeviceOrPcmPLCValueListAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;
    private List<DevicePLCValueBean> childrenList;
    private Context context;

    public DeviceOrPcmPLCValueListAdapter(Activity context, List<String> groupList, List<DevicePLCValueBean> childrenList) {
        this.context = context;
        this.groupList = groupList;
        this.childrenList = childrenList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childrenList.size();
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenList.get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.device_detail_title_bar,null);
        TextView title = (TextView) convertView.findViewById(R.id.include_title_name);
        ImageView dropBtn = convertView.findViewById(R.id.drop_btn);
        title.setText(groupList.get(groupPosition));
        if (isExpanded){
            dropBtn.setImageResource(R.mipmap.icon_device_down);
        }else {
            dropBtn.setImageResource(R.mipmap.icon_device_right);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.deviceinfo_zhibiao_item,null);
        TextView plc_name = view.findViewById(R.id.plc_name);
        TextView plc_value = view.findViewById(R.id.plc_value);
        if (childrenList.get(childPosition).getPropertyName() != null){
            plc_name.setText(childrenList.get(childPosition).getPropertyName());
        }
        if (childrenList.get(childPosition).getPropertyVal() != null){
            plc_value.setText(childrenList.get(childPosition).getPropertyVal() + childrenList.get(childPosition).getUnits());
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
