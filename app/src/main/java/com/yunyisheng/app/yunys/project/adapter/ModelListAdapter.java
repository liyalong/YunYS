package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.ModelInfoBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/22.
 */

public class ModelListAdapter extends SimpleListAdapter<ModelInfoBean, ModelListAdapter.ViewHolder> {
    private ModelInfoBean modelInfoBean;

    public ModelListAdapter(Context context, List<ModelInfoBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.device_model_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, ModelInfoBean item, int position) {
        modelInfoBean = data.get(position);
        holder.modelName.setText(modelInfoBean.getPcmName().toString());
        if (modelInfoBean.getPcmBindnum() == null){
            holder.modelBindDeviceNums.setText(0+"");
        }else {
            holder.modelBindDeviceNums.setText(modelInfoBean.getPcmBindnum().toString());
        }

        if (modelInfoBean.getPcmIsWarning() == 0){
            holder.modelRunStatus.setText(R.string.model_running_status_1);
            holder.modelRunStatus.setBackgroundColor(context.getResources().getColor(R.color.device_status_success));
        }else {
            holder.modelRunStatus.setText(R.string.model_running_status_2);
            holder.modelRunStatus.setBackgroundColor(context.getResources().getColor(R.color.device_status_error));
        }
        if (modelInfoBean.getPcmStat() == 1){
            holder.modelUsedStatus.setText(R.string.enable);
        }else if (modelInfoBean.getPcmStat() == 3){
            holder.modelUsedStatus.setText(R.string.disable);
        }
    }

    public static class ViewHolder {
        @BindView(R.id.model_name)
        TextView modelName;
        @BindView(R.id.model_run_status)
        TextView modelRunStatus;
        @BindView(R.id.model_bind_device_nums)
        TextView modelBindDeviceNums;
        @BindView(R.id.model_used_status)
        TextView modelUsedStatus;
        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
