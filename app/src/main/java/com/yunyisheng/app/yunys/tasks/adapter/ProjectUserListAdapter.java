package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by liyalong on 2018/2/2.
 */

public class ProjectUserListAdapter extends SimpleListAdapter<ProjectUserBean, ProjectUserListAdapter.ViewHolder> {


    public ProjectUserListAdapter(Context context, List<ProjectUserBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.select_list_item;
    }

    @Override
    protected void convert(final ViewHolder holder, final ProjectUserBean item, final int position) {
        holder.text.setText(data.get(position).getUserName());
        if (!data.get(position).isCheck()){
            holder.checkedView.setBackgroundResource(R.mipmap.select_no);
        }else {
            holder.checkedView.setBackgroundResource(R.mipmap.select_yes);
        }
        holder.checkedView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean check = data.get(position).isCheck();
                    if (check){
                        data.get(position).setCheck(false);
                    }else {
                        data.get(position).setCheck(true);
                    }
                    notifyDataSetChanged();
                }
            });

    }
    public List<ProjectUserBean> getSelectList(){
        List<ProjectUserBean> users = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            if (data.get(i).isCheck()){
                users.add(data.get(i));
            }
        }
        return users;
    }

    public class ViewHolder {
        @BindView(R.id.ck_select)
        ImageView checkedView;
        @BindView(R.id.text)
        TextView text;
        public ViewHolder(View view){
            KnifeKit.bind(this,view);
        }
    }
}
