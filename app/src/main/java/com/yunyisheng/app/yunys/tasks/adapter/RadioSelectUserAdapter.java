package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/2/5.
 */

public class RadioSelectUserAdapter extends SimpleListAdapter<ProjectUserBean, RadioSelectUserAdapter.ViewHolder> {

    private int selectPosition = -1;
    private String selectUserId;

    public int getSelectPosition() {
        return selectPosition;
    }

    public RadioSelectUserAdapter(Context context, List<ProjectUserBean> data,String userId) {
        super(context, data);
        selectUserId = userId;
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
    protected void convert(ViewHolder holder, ProjectUserBean item, final int position) {
        if (item.getName() != null && item.getUserName() == null){
            holder.projectTitle.setText(item.getName().toString());
        }
        if (item.getUserName() != null && item.getName() == null){
            holder.projectTitle.setText(item.getUserName().toString());
        }

        holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPosition = position;
                notifyDataSetChanged();
            }
        });

        if (selectPosition == position && selectPosition != -1){
            holder.mRadioButton.setBackgroundResource(R.mipmap.icon_radio_checked);
        }else {
            holder.mRadioButton.setBackgroundResource(R.mipmap.icon_radio);
//            if (selectUserId != null){
//                if (Integer.valueOf(selectUserId) == item.getUserId()){
//                    holder.mRadioButton.setBackgroundResource(R.mipmap.icon_radio_checked);
//                }else {
//                    holder.mRadioButton.setBackgroundResource(R.mipmap.icon_radio);
//                }
//            }
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
