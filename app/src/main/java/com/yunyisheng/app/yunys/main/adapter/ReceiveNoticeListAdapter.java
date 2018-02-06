package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.model.ReceiveMeMessageBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/1/23 17:49
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ReceiveNoticeListAdapter extends SimpleListAdapter<ReceiveMeMessageBean.ListBean, ReceiveNoticeListAdapter.ViewHolder> {


    public ReceiveNoticeListAdapter(Context context, List<ReceiveMeMessageBean.ListBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.sendmy_notice_item;
    }

    @Override
    protected void convert(ViewHolder holder, ReceiveMeMessageBean.ListBean item, int position) {
        ReceiveMeMessageBean.ListBean.AnnouncementBean announcement = data.get(position).getAnnouncement();
        holder.teNoticetitle.setText(announcement.getTitle());
        holder.teNoticetime.setText(announcement.getCreateTime());
        holder.teNoticedetail.setText(announcement.getContent());
    }


    class ViewHolder {
        @BindView(R.id.te_noticetitle)
        TextView teNoticetitle;
        @BindView(R.id.te_noticetime)
        TextView teNoticetime;
        @BindView(R.id.te_noticedetail)
        TextView teNoticedetail;
        public ViewHolder(View view) {
            KnifeKit.bind(this,view);
        }
    }
}
