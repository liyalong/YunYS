package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.KnowledgeBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/24.
 */

public class KnowledgeListAdapter extends SimpleListAdapter<KnowledgeBean, KnowledgeListAdapter.ViewHolder> {
    private KnowledgeBean knowledgeBean;

    public KnowledgeListAdapter(Context context, List<KnowledgeBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.knowledge_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, KnowledgeBean item, int position) {
        knowledgeBean = data.get(position);
        holder.knowledgeName.setText(knowledgeBean.getKnowledgeName());
        holder.knowledgeCreateTime.setText(knowledgeBean.getCreatet().substring(0,16));
    }

    public static class ViewHolder {
        @BindView(R.id.knowledge_name)
        TextView knowledgeName;
        @BindView(R.id.knowledge_create_time)
        TextView knowledgeCreateTime;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }
}
