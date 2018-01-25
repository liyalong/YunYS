package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.model.DeviceInfoModel;

import java.util.List;

/**
 * Created by liyalong on 2018/1/23.
 */

public class DeviceDetailInfoAdapterback extends BaseExpandableListAdapter {
    private List<String> groupList;
    private List<List<DeviceInfoModel>> childList;
    private Context context;


    public DeviceDetailInfoAdapterback(Context context, List<String> groupList, List<List<DeviceInfoModel>> childList) {
        this.groupList = groupList;
        this.childList = childList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
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
        title.setText(groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.device_detail_info_status,null);
        TextView left_title = (TextView) view.findViewById(R.id.device_Left_title);
        TextView device_detail_status = (TextView)view.findViewById(R.id.device_detail_status);
        ImageView icon_right = (ImageView) view.findViewById(R.id.icon_right);
        //left_title.setText(childList.get(groupPosition).get(childPosition).getTitle());
//        if (childPosition == 0 || childPosition == 1){
//            icon_right.setVisibility(View.INVISIBLE);
//        }else {
//            device_detail_status.setVisibility(View.INVISIBLE);
//        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
