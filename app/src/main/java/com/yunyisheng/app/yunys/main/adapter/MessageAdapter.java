package com.yunyisheng.app.yunys.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.model.MessageBean;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：fuduo on 2018/1/30 16:36
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MessageAdapter extends SimpleListAdapter<MessageBean.RespBodyBean, MessageAdapter.ViewHolder> {

    public MessageAdapter(Context context, List<MessageBean.RespBodyBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.message_item;
    }

    @Override
    protected void convert(ViewHolder holder, MessageBean.RespBodyBean item, int position) {
        MessageBean.RespBodyBean respBodyBean = data.get(position);

        holder.teMsgdetail.setText(respBodyBean.getMessageRemark());
        holder.teMsgtime.setText(respBodyBean.getMessageCreateTime()+" - "+respBodyBean.getMessageUpdateTime());
        String messageType = respBodyBean.getMessageType();
        if (messageType.equals("3")){
            holder.teMsgtitle.setVisibility(View.VISIBLE);
            holder.imgMsgtype.setVisibility(View.VISIBLE);
            holder.imgMsgtype.setBackgroundResource(R.mipmap.noticeimg);
            holder.teMsgtitle.setText("公告消息");
        }else if (messageType.equals("1")){
            holder.teMsgtitle.setVisibility(View.VISIBLE);
            holder.imgMsgtype.setVisibility(View.VISIBLE);
            holder.imgMsgtype.setBackgroundResource(R.mipmap.taskimg);
            holder.teMsgtitle.setText("任务消息");
        }else if (messageType.equals("2")){
            holder.teMsgtitle.setVisibility(View.VISIBLE);
            holder.imgMsgtype.setVisibility(View.VISIBLE);
            holder.imgMsgtype.setBackgroundResource(R.mipmap.waringimg);
            holder.teMsgtitle.setText("报警消息");
        }else {
            holder.imgMsgtype.setVisibility(View.GONE);
            holder.teMsgtitle.setVisibility(View.GONE);
        }
        String messageStat = respBodyBean.getMessageStat();
        if (messageStat.equals("0")){
            holder.imgOval.setVisibility(View.GONE);
        }else {
            holder.imgOval.setVisibility(View.VISIBLE);
        }
    }

    class ViewHolder {

        @BindView(R.id.te_msgtime)
        TextView teMsgtime;
        @BindView(R.id.te_msgdetail)
        TextView teMsgdetail;
        @BindView(R.id.img_msgtype)
        ImageView imgMsgtype;
        @BindView(R.id.te_msgtitle)
        TextView teMsgtitle;
        @BindView(R.id.img_oval)
        ImageView imgOval;

        public ViewHolder(View view) {
            KnifeKit.bind(this,view);
        }
    }
}
