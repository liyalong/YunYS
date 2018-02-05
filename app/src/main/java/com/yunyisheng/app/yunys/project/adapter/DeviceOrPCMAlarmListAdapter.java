package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.DeviceWarningBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/2/5.
 */

public class DeviceOrPCMAlarmListAdapter extends SimpleListAdapter<DeviceWarningBean, DeviceOrPCMAlarmListAdapter.ViewHolder> implements View.OnClickListener {

    private Callback mCallback;

    @Override
    public void onClick(View view) {
        mCallback.click(view);
    }

    public interface Callback{
        public void click(View v);
    }
    public DeviceOrPCMAlarmListAdapter(Context context, List<DeviceWarningBean> data,Callback callback) {
        super(context, data);
        mCallback = callback;
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.deviceinfo_baojing_item;
    }

    @Override
    protected void convert(final ViewHolder holder, DeviceWarningBean item, final int position) {
        holder.baojingName.setText(item.getAlarmName().toString());
        holder.fuwei.setTag(position);
        holder.fuwei.setOnClickListener(this);
    }

    public class ViewHolder {
        @BindView(R.id.baojing_name)
        TextView baojingName;
        @BindView(R.id.fuwei)
        TextView fuwei;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }
}
