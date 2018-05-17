package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/2/3.
 */

public class SelectProjectDeviceAdapter extends SimpleListAdapter<DeviceBean, SelectProjectDeviceAdapter.ViewHoler> {
    private int selectPosition = -1;
    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public int getSelectPosition() {
        return selectPosition;
    }
    public SelectProjectDeviceAdapter(Context context, List<DeviceBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHoler newViewHolder(View convertView) {
        return new ViewHoler(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.select_project_list_item;
    }

    @Override
    protected void convert(ViewHoler holder, DeviceBean item, final int position) {
        holder.projectTitle.setText(item.getEquipmentName() + "（"+item.getEquipmentUnicode()+"）");
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

    public static class ViewHoler {
        @BindView(R.id.m_radio_button)
        ImageView mRadioButton;
        @BindView(R.id.project_title)
        TextView projectTitle;
        public ViewHoler(View view){
            KnifeKit.bind(this,view);
        }
    }
}
