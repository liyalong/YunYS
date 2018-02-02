package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;

/**
 * 作者：fuduo on 2018/1/15 12:19
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SelectFankuiTypeSpinnerAdapter extends BaseAdapter {

    Context context;
    String[] str = new String[]{"单选", "照片", "文本", "多选"};

    public SelectFankuiTypeSpinnerAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int position) {
        return str[position];
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
            tetype.setText(str[position]);
        }
        return convertView;
    }
}
