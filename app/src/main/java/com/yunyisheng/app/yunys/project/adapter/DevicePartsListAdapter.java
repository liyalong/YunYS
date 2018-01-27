package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.DevicePartsBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/25.
 */

public class DevicePartsListAdapter extends SimpleListAdapter<DevicePartsBean, DevicePartsListAdapter.ViewHolder> {
    private DevicePartsBean devicePartsBean;

    private String[] cycleArray = {"小时","周","月","季","年"};

    public DevicePartsListAdapter(Context context, List<DevicePartsBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.device_parts_item;
    }

    @Override
    protected void convert(ViewHolder holder, DevicePartsBean item, int position) {
        devicePartsBean = data.get(position);
        holder.devicePartsName.setText(devicePartsBean.getSpareName().toString());
        if (devicePartsBean.getSpareModel() == null){
            holder.devicePartsCode.setText("");
        }else {
            holder.devicePartsCode.setText(devicePartsBean.getSpareModel().toString());
        }
        holder.addtime.setText(devicePartsBean.getCreatet().toString());
        holder.beijianGhzq.setText(devicePartsBean.getReplaceVal()+""+cycleArray[devicePartsBean.getReplaceCycle()]);
    }

    public static class ViewHolder {
        @BindView(R.id.device_parts_name)
        TextView devicePartsName;
        @BindView(R.id.device_parts_code)
        TextView devicePartsCode;
        @BindView(R.id.addtime)
        TextView addtime;
        @BindView(R.id.beijian_ghzq)
        TextView beijianGhzq;
        public ViewHolder(View view) {
            KnifeKit.bind(this,view);
        }
    }
}
