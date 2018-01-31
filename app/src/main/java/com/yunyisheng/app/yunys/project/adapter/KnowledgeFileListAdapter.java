package com.yunyisheng.app.yunys.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.project.bean.FileItem;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.FileCache;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/1/31.
 */

public class KnowledgeFileListAdapter extends SimpleListAdapter<FileItem, KnowledgeFileListAdapter.ViewHolder> implements View.OnClickListener {
    private Callback mCallback;
    public interface Callback{
        public void click(View view);
    }
    public KnowledgeFileListAdapter(Context context, List<FileItem> data,String projectId,Callback callback) {
        super(context, data);
        mCallback = callback;
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.knowledge_file_list_item;
    }

    @Override
    protected void convert(ViewHolder holder, FileItem item, int position) {
        holder.knowledgeFileName.setText(data.get(position).getFname().toString());
        holder.knowledgeFileCreateTime.setText(data.get(position).getCreatet().toString());
        if (CommonUtils.initDownPath(FileCache.path + data.get(position).getFname())){
            holder.download.setText(R.string.show);
        }else {
            holder.download.setText(R.string.download);
        }
        holder.download.setTag(position);
        holder.download.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mCallback.click(view);
    }

    public static class ViewHolder {
        @BindView(R.id.knowledge_file_name)
        TextView knowledgeFileName;
        @BindView(R.id.knowledge_file_create_time)
        TextView knowledgeFileCreateTime;
        @BindView(R.id.download)
        TextView download;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }
}
