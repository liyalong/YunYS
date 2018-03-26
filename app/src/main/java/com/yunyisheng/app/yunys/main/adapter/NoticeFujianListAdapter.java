package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.activity.SendNoticeActivity;
import com.yunyisheng.app.yunys.main.model.AnnexBean;
import com.yunyisheng.app.yunys.utils.FileUtil;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/1/26 15:33
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NoticeFujianListAdapter extends SimpleListAdapter<AnnexBean, NoticeFujianListAdapter.ViewHolder> {

    private int type;

    public NoticeFujianListAdapter(Context context, List<AnnexBean> data, int i) {
        super(context, data);
        type = i;
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.notice_fujian_listitem;
    }

    @Override
    protected void convert(ViewHolder holder, AnnexBean item, final int position) {
        AnnexBean annexBean = data.get(position);
        String annexName = annexBean.getAnnexName();
        String fileType = FileUtil.getFileType(annexName);
        if (fileType.equals("image")) {
            holder.imgFujianImg.setBackgroundResource(R.mipmap.imgbac);
        } else {
            holder.imgFujianImg.setBackgroundResource(R.mipmap.wordimg);
        }
        if (type==2){
            holder.imgdeletefujian.setVisibility(View.VISIBLE);
        }
        holder.imgdeletefujian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SendNoticeActivity)context).deleteFilepos(position);
            }
        });
        holder.teFilename.setText(annexName);
    }

    class ViewHolder {

        @BindView(R.id.img_fujian_img)
        ImageView imgFujianImg;
        @BindView(R.id.img_delete_fujian)
        ImageView imgdeletefujian;
        @BindView(R.id.te_filename)
        TextView teFilename;

        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
