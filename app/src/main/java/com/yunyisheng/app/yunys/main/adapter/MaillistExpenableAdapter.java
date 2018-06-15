package com.yunyisheng.app.yunys.main.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.activity.MailListActivity;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.main.model.WorkerListBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.bean.UploadDynamicFormImageBean;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.glide.GlideDownLoadImage;

import java.util.List;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/24 18:35
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MaillistExpenableAdapter extends BaseExpandableListAdapter {

    private List<WorkerListBean> list;
    private Context context;
    private LayoutInflater mInflater;

    public MaillistExpenableAdapter(Context context, List<WorkerListBean> list) {
        this.list = list;
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (list.get(groupPosition).getWorkerBeanList() == null) {
            return 0;
        }
        return list.get(groupPosition).getWorkerBeanList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getWorkerBeanList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.maillist_exp_group_item, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if (groupPosition == 0) {
            groupViewHolder.viewwhite.setVisibility(View.GONE);
        } else {
            groupViewHolder.viewwhite.setVisibility(View.VISIBLE);
        }
        groupViewHolder.te_groupname.setText(list.get(groupPosition).getGroupname()+"（"+list.get(groupPosition).getGroupSize()+"人）");
        if (isExpanded) {
            groupViewHolder.img_zhedie.setImageResource(R.mipmap.downsanjiao);
        } else {
            groupViewHolder.img_zhedie.setImageResource(R.mipmap.rightsanjiao);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View view = mInflater.inflate(R.layout.maillist_exp_child_item, parent, false);
        TextView te_zhiwei = (TextView) view.findViewById(R.id.te_zhiwei);
        TextView te_name = (TextView) view.findViewById(R.id.te_name);
        RoundedImageView img_woker_head = (RoundedImageView) view.findViewById(R.id.img_woker_head);
        ImageView img_send_msg = (ImageView) view.findViewById(R.id.img_send_msg);
        ImageView img_call_phone = (ImageView) view.findViewById(R.id.img_call_phone);
        View view1 = view.findViewById(R.id.view1);
        final WorkerBean workerBean = list.get(groupPosition).getWorkerBeanList().get(childPosition);
        te_name.setText(workerBean.getName());
        te_zhiwei.setText(workerBean.getUserJobTitle());
        int size = list.get(groupPosition).getWorkerBeanList().size();
        if (childPosition == size - 1) {
            view1.setVisibility(View.GONE);
        } else {
            view1.setVisibility(View.VISIBLE);
        }
        if (workerBean.getIcon() != null && !workerBean.getIcon().equals("") && !workerBean.getIcon().equals("null")) {
//            Bitmap bitmap = CommonUtils.stringtoBitmap(workerBean.getIcon());
//            img_woker_head.setImageBitmap(bitmap);
//            GlideDownLoadImage.getInstance().loadBitmapCircleImageRole(context, img_woker_head, bitmap);
            if (img_woker_head.getDrawable() == null) {
                getFormImage(img_woker_head, workerBean.getIcon());
            }
        } else {
            img_woker_head.setBackground(null);
            String sex = workerBean.getSex();
            if (sex != null && !sex.equals("") && !sex.equals("null")) {
//                if (sex.equals("男")) {
                    img_woker_head.setBackgroundResource(R.mipmap.maillist_man);
//                } else {
//                    img_woker_head.setBackgroundResource(R.mipmap.maillist_woman);
//                }

            } else {
                img_woker_head.setBackgroundResource(R.mipmap.maillist_man);
            }
        }
        img_call_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri
                        .parse("tel:" + workerBean.getUserPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(intent);
            }
        });
        img_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsToUri = Uri.parse("smsto:" + workerBean.getUserPhone());
                Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 获取图片
     */
    public void getFormImage(final ImageView imageView, String fileurl) {
        Api.scheduleService().getFormImage(fileurl)
                .compose(XApi.<UploadDynamicFormImageBean>getApiTransformer())
                .compose(XApi.<UploadDynamicFormImageBean>getScheduler())
                .compose(((MailListActivity) context).<UploadDynamicFormImageBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<UploadDynamicFormImageBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("NET ERROR :" + error.toString());
                        return;
                    }

                    @Override
                    public void onNext(UploadDynamicFormImageBean uploadDynamicFormImageBean) {
                        try {
                            if (uploadDynamicFormImageBean.getRespCode() == 1) {
                                ToastUtils.showToast(uploadDynamicFormImageBean.getRespMsg());
                                return;
                            }
                            String respBody = uploadDynamicFormImageBean.getRespBody();
                            Bitmap bitmap = CommonUtils.stringtoBitmap(respBody);
                            GlideDownLoadImage.getInstance().loadBitmapCircleImageRole(context, imageView, bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    class GroupViewHolder {
        ImageView img_zhedie;
        TextView te_groupname;
        View viewwhite;

        public GroupViewHolder(View view) {
            img_zhedie = (ImageView) view.findViewById(R.id.img_zhedie);
            te_groupname = (TextView) view.findViewById(R.id.te_groupname);
            viewwhite = view.findViewById(R.id.view);
        }
    }

    class ChildViewHolder {
        TextView te_zhiwei, te_name;
        ImageView img_woker_head, img_send_msg, img_call_phone;
        View view1;

        public ChildViewHolder(View view) {
            te_zhiwei = (TextView) view.findViewById(R.id.te_zhiwei);
            te_name = (TextView) view.findViewById(R.id.te_name);
            img_woker_head = (ImageView) view.findViewById(R.id.img_woker_head);
            img_send_msg = (ImageView) view.findViewById(R.id.img_send_msg);
            img_call_phone = (ImageView) view.findViewById(R.id.img_call_phone);
            view1 = view.findViewById(R.id.view1);
        }
    }
}
