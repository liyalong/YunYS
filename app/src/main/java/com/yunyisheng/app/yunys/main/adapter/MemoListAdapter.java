package com.yunyisheng.app.yunys.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.activity.MemorandumActivity;
import com.yunyisheng.app.yunys.main.model.MemorandumBean;
import com.yunyisheng.app.yunys.utils.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：fuduo on 2018/1/23 15:05
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MemoListAdapter extends BaseAdapter {

    private MemorandumActivity context;
    private List<MemorandumBean.ListBean> list = new ArrayList<>();

    public MemoListAdapter(MemorandumActivity context, List<MemorandumBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = null;
        if (convertView == null) {
            viewholder = new Viewholder();
            View view1 = LayoutInflater.from(context).inflate(R.layout.memarandum_list_item, null);
            View view2 = LayoutInflater.from(context).inflate(R.layout.delete_memo_item, null);
            viewholder.te_dateil = (TextView) view1.findViewById(R.id.te_dateil);
            viewholder.te_time = (TextView) view1.findViewById(R.id.te_time);
            viewholder.te_delete = (TextView) view2.findViewById(R.id.te_delete);
            convertView = new SwipeItemLayout(view1, view2, null, null);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }
        viewholder.te_dateil.setText(list.get(position).getMemoVal());
        viewholder.te_time.setText(list.get(position).getMemoDate());
        viewholder.te_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteMemo(list.get(position).getMemoId());
            }
        });
        return convertView;
    }

    class Viewholder {
        private TextView te_dateil, te_time, te_delete;
    }
}
