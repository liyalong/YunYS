package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.bean.ProcessFormBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/2/4.
 */

public class ProcessFormListAdapter extends SimpleListAdapter<ProcessFormBean, ProcessFormListAdapter.ViewHolder> {
    private int selectPosition = -1;
    public ProcessFormListAdapter(Context context, List<ProcessFormBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.select_project_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, ProcessFormBean item, final int position) {
        if (item.getFormBasicName() != null){
            holder.projectTitle.setText(item.getFormBasicName());
        }
        holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPosition = position;
                notifyDataSetChanged();
            }
        });
        if (selectPosition == position){
            holder.mRadioButton.setBackgroundResource(R.mipmap.icon_radio_checked);
        }else {
            holder.mRadioButton.setBackgroundResource(R.mipmap.icon_radio);
        }
    }

    public class ViewHolder {
        @BindView(R.id.m_radio_button)
        ImageView mRadioButton;
        @BindView(R.id.project_title)
        TextView projectTitle;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }
    public int getSelectPosition(){
        return selectPosition;
    }
}
