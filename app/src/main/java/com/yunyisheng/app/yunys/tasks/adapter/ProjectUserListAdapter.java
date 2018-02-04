package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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
    protected void convert(final ViewHolder holder, final ProjectUserBean item, int position) {
            holder.text.setText(item.getUserName().toString());

        holder.checkedView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final boolean check = item.isCheck();
                    if (check){
                        holder.checkedView.setBackgroundResource(R.mipmap.select_no);
                        item.setCheck(false);
                    }else {
                        holder.checkedView.setBackgroundResource(R.mipmap.select_yes);
                        item.setCheck(true);
                    }
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
