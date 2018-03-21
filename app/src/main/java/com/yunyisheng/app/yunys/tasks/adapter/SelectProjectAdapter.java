package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/2/3.
 */

public class SelectProjectAdapter extends SimpleListAdapter<ProjectBean, SelectProjectAdapter.ViewHolder> {
    private int selectPosition = -1;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public SelectProjectAdapter(Context context, List<ProjectBean> data) {
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
    protected void convert(final ViewHolder holder, ProjectBean item, final int position) {
        holder.projectTitle.setText(item.getProjectName());
        holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectPosition(position);
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
}
