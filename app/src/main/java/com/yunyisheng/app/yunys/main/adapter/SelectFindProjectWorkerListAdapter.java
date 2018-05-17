package com.yunyisheng.app.yunys.main.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.activity.SelectPeopleActivity;
import com.yunyisheng.app.yunys.main.model.FindProjectWorkerBean;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.bean.UploadDynamicFormImageBean;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleListAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/27 14:28
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SelectFindProjectWorkerListAdapter extends SimpleListAdapter<FindProjectWorkerBean.RespBodyBean, SelectFindProjectWorkerListAdapter.ViewHolder> {

    private List<FindProjectWorkerBean.RespBodyBean> list = new ArrayList<>();

    public SelectFindProjectWorkerListAdapter(Context context, List<FindProjectWorkerBean.RespBodyBean> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    /**
     * @author fuduo
     * @time 2018/1/27  19:51
     * @describe 搜索选中的人
     */
    public List<FindProjectWorkerBean.RespBodyBean> getSelectlist() {
        if (list.size() > 0) {
            list.clear();
        }
        for (int i = 0; i < data.size(); i++) {
            FindProjectWorkerBean.RespBodyBean respBodyBean = data.get(i);
            if (respBodyBean.isIscheck()) {
                if (!checkIsSelected(list,data.get(i))){
                    list.add(respBodyBean);
                }

            }
        }
        return list;
    }
    public Boolean checkIsSelected(List<FindProjectWorkerBean.RespBodyBean> lists, FindProjectWorkerBean.RespBodyBean item){
        Boolean in = false;
        if (lists.size() > 0){
            for (int i=0;i<lists.size();i++){
                if (lists.get(i).getUserId() == item.getUserId()){
                    in = true;
                    break;
                }
            }
        }
        return in;
    }
    /**
     * @author fuduo
     * @time 2018/1/27  19:50
     * @describe 设置全选
     */
    public void setSelectall(boolean ischeck) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setIscheck(ischeck);
        }
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.selectpeople_exp_child_item;
    }

    @Override
    protected void convert(ViewHolder holder, FindProjectWorkerBean.RespBodyBean item, int position) {
        final FindProjectWorkerBean.RespBodyBean respBodyBean = data.get(position);
        holder.teName.setText(respBodyBean.getUserName());
        holder.teZhiwei.setText(respBodyBean.getUserJobTitle());
        if (respBodyBean.getUserPicture() != null && !respBodyBean.getUserPicture().equals("") && !respBodyBean.getUserPicture().equals("null")) {
//            Bitmap bitmap = CommonUtils.stringtoBitmap(respBodyBean.getUserPicture());
//            holder.imgWokerHead.setImageBitmap(bitmap);
            getFormImage(holder.imgWokerHead,respBodyBean.getUserPicture());
        } else {
            String sex = respBodyBean.getUserSex();
            if (sex != null && !sex.equals("") && !sex.equals("null")) {
                if (sex.equals("男")) {
                    holder.imgWokerHead.setBackgroundResource(R.mipmap.maillist_man);
                } else {
                    holder.imgWokerHead.setBackgroundResource(R.mipmap.maillist_woman);
                }

            } else {
                holder.imgWokerHead.setBackgroundResource(R.mipmap.maillist_man);
            }
        }

        if (respBodyBean.isIscheck()) {
            holder.ckSelect.setBackgroundResource(R.mipmap.select_yes);
        } else {
            holder.ckSelect.setBackgroundResource(R.mipmap.select_no);
        }

        holder.imgCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri
                        .parse("tel:" + respBodyBean.getUserPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(intent);
            }
        });
        holder.imgSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsToUri = Uri.parse("smsto:" + respBodyBean.getUserPhone());
                Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                context.startActivity(intent);
            }
        });
    }

    /**
     * 获取图片
     */
    public void getFormImage(final ImageView imageView, String fileurl) {
        Api.scheduleService().getFormImage(fileurl)
                .compose(XApi.<UploadDynamicFormImageBean>getApiTransformer())
                .compose(XApi.<UploadDynamicFormImageBean>getScheduler())
                .compose(((SelectPeopleActivity) context).<UploadDynamicFormImageBean>bindToLifecycle())
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
                            imageView.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    class ViewHolder {
        @BindView(R.id.ck_select)
        ImageView ckSelect;
        @BindView(R.id.img_woker_head)
        RoundedImageView imgWokerHead;
        @BindView(R.id.te_name)
        TextView teName;
        @BindView(R.id.te_type)
        TextView teType;
        @BindView(R.id.te_zhiwei)
        TextView teZhiwei;
        @BindView(R.id.rel)
        RelativeLayout rel;
        @BindView(R.id.img_send_msg)
        ImageView imgSendMsg;
        @BindView(R.id.img_call_phone)
        ImageView imgCallPhone;

        public ViewHolder(View view) {
            KnifeKit.bind(this, view);
        }
    }
}
