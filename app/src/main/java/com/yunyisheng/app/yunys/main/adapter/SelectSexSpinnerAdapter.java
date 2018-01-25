package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.model.SexBean;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/15 12:19
 * 邮箱：duoendeavor@163.com
 * 用途：筛选消息类型spinner适配器
 */

public class SelectSexSpinnerAdapter extends BaseAdapter {

    Context context;
    List<SexBean> mlist;

    public SelectSexSpinnerAdapter(Context context, List<SexBean> list) {
        this.context=context;
        this.mlist=list;

    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.select_sex_spiner_list_item,null);
        if (convertView!=null){
            TextView tetype=(TextView) convertView.findViewById(R.id.te_type);
            tetype.setText(mlist.get(position).getSexname());
        }
        return convertView;
    }
}
