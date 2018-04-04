package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.model.SendNoticeBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/1/23 17:49
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class PublishNoticeListAdapter extends SimpleListAdapter<SendNoticeBean.ListBean, PublishNoticeListAdapter.ViewHolder> {


    public PublishNoticeListAdapter(Context context, List<SendNoticeBean.ListBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.notice_item;
    }

    @Override
    protected void convert(ViewHolder holder, SendNoticeBean.ListBean item, int position) {
        SendNoticeBean.ListBean listBean = data.get(position);
        holder.teNoticetitle.setText(listBean.getTitle());
        String subCreateTime = listBean.getCreateTime().substring(0, 16);
        holder.teNoticetime.setText(subCreateTime);
        holder.teNoticedetail.setText(listBean.getContent());
        String readStatistic=listBean.getReadStatistic();
        String[] strarray=readStatistic.split("[/]");
        holder.teReadedsize.setText("已读："+strarray[0]);
        holder.teNoreadsize.setText("未读："+strarray[1]);
    }


    class ViewHolder {
        @BindView(R.id.te_noticetitle)
        TextView teNoticetitle;
        @BindView(R.id.te_noticetime)
        TextView teNoticetime;
        @BindView(R.id.te_noticedetail)
        TextView teNoticedetail;
        @BindView(R.id.te_readedsize)
        TextView teReadedsize;
        @BindView(R.id.te_noreadsize)
        TextView teNoreadsize;
        public ViewHolder(View view) {
            KnifeKit.bind(this,view);
        }
    }
}
