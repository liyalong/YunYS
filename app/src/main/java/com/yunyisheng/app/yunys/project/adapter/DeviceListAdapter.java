package com.yunyisheng.app.yunys.project.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.log.XLog;

/**
 * Created by liyalong on 2018/1/22.
 */

public class DeviceListAdapter extends SimpleListAdapter<DeviceBean, DeviceListAdapter.ViewHolder> implements View.OnClickListener {
    private Callback mCallback;
    public interface Callback{
        public void click(View v);
    }
    public DeviceListAdapter(Context context, List<DeviceBean> data,Callback callback) {
        super(context, data);
        mCallback = callback;
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.device_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, DeviceBean item, int position) {
        final DeviceBean deviceBean = data.get(position);
        holder.deviceName.setText(deviceBean.getEquipmentName());
        holder.deviceCreateTime.setText(deviceBean.getEquipmentCreate());
        if (deviceBean.getIsWarning() == 1){
            holder.deviceStatus.setBackgroundColor(context.getResources().getColor(R.color.device_status_error));
            holder.deviceStatus.setText(context.getResources().getString(R.string.device_status_error));
        }else {
            holder.deviceStatus.setBackgroundColor(context.getResources().getColor(R.color.device_status_success));
            holder.deviceStatus.setText(context.getResources().getString(R.string.device_status_success));
        }
        holder.itemButton.setTag(position);
        holder.itemButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        mCallback.click(view);
    }

    public static class ViewHolder {
        @BindView(R.id.device_name)
        TextView deviceName;
        @BindView(R.id.device_status)
        TextView deviceStatus;
        @BindView(R.id.item_button)
        TextView itemButton;
        @BindView(R.id.device_create_time)
        TextView deviceCreateTime;
        public ViewHolder(View itemView) {
            KnifeKit.bind(this, itemView);
        }
    }

}
