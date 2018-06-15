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
import com.yunyisheng.app.yunys.main.activity.SelectPeopleActivity;
import com.yunyisheng.app.yunys.main.model.ProjectFromWorkBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.bean.UploadDynamicFormImageBean;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.glide.GlideDownLoadImage;

import java.util.ArrayList;
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

public class FromWorkListExpenableAdapter extends BaseExpandableListAdapter {

    private List<ProjectFromWorkBean.ListBean> list;
    private Context context;
    private LayoutInflater mInflater;
    private List<ProjectFromWorkBean.ListBean.UserListBean> selectpeople=new ArrayList<>();
    private proOnclicklisttener myOnclicklisttener;

    public FromWorkListExpenableAdapter(Context context, List<ProjectFromWorkBean.ListBean> list) {
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
        if (list.get(groupPosition).getUserList() == null) {
            return 0;
        }
        return list.get(groupPosition).getUserList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getUserList().get(childPosition);
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

    /**
     * @author fuduo
     * @time 2018/1/25  11:19
     * @describe 设置全选反选
     */
    public void setAllCheckandNocheck(boolean isselect) {
        for (int i = 0; i < list.size(); i++) {
            ProjectFromWorkBean.ListBean listBean = list.get(i);
            List<ProjectFromWorkBean.ListBean.UserListBean> userList = listBean.getUserList();
            if (userList==null||userList.size()==0){
                continue;
            }else {
                if (isselect) {
                    if (!listBean.isIscheckgroup()) {
                        listBean.setIscheckgroup(true);
                    }
                    for (int j = 0; j < userList.size(); j++) {
                        ProjectFromWorkBean.ListBean.UserListBean userListBean = userList.get(j);
                        if (!userListBean.isIscheckchild()) {
                            userListBean.setIscheckchild(true);
                        }
                    }
                } else {
                    if (listBean.isIscheckgroup()) {
                        listBean.setIscheckgroup(false);
                    }
                    for (int j = 0; j < userList.size(); j++) {
                        ProjectFromWorkBean.ListBean.UserListBean userListBean = userList.get(j);
                        if (userListBean.isIscheckchild()) {
                            userListBean.setIscheckchild(false);
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    /**
     * @author fuduo
     * @time 2018/1/25  11:31
     * @describe 获取选中的人员
     */
    public List<ProjectFromWorkBean.ListBean.UserListBean> getSelectPeopleList(){
        for (int i = 0; i < list.size(); i++) {
            ProjectFromWorkBean.ListBean listBean = list.get(i);
            List<ProjectFromWorkBean.ListBean.UserListBean> userList = listBean.getUserList();

            if (userList!=null&&userList.size()>0) {
                for (int j = 0; j < userList.size(); j++) {
                    ProjectFromWorkBean.ListBean.UserListBean userListBean = userList.get(j);
                    if (userListBean.isIscheckchild() && !checkIsSelected(selectpeople,userListBean)) {
                        selectpeople.add(userListBean);
                    }
                }
            }
        }
        return selectpeople;
    }
    public Boolean checkIsSelected(List<ProjectFromWorkBean.ListBean.UserListBean> lists, ProjectFromWorkBean.ListBean.UserListBean item){
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
    public void setOnceSelect(ProjectFromWorkBean.ListBean.UserListBean userListBean){
        int userId = userListBean.getUserId();
        boolean ischeckchild = userListBean.isIscheckchild();
        for (int i = 0; i < list.size(); i++) {
            ProjectFromWorkBean.ListBean listBean = list.get(i);
            List<ProjectFromWorkBean.ListBean.UserListBean> workerBeanList = listBean.getUserList();
            if (workerBeanList!=null&&workerBeanList.size()>0) {
                for (int j = 0; j < workerBeanList.size(); j++) {
                    ProjectFromWorkBean.ListBean.UserListBean worker = workerBeanList.get(j);
                    if (worker.getUserId()==userId){
                        worker.setIscheckchild(ischeckchild);
                        notifyDataSetChanged();
                        return;
                    }
                }
            }
        }
    }


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.selectpeople_exp_group_item, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if (groupPosition==0){
            groupViewHolder.viewwhite.setVisibility(View.GONE);
        }else {
            groupViewHolder.viewwhite.setVisibility(View.VISIBLE);
        }
        if (list.get(groupPosition).isIscheckgroup()) {
            groupViewHolder.ck_select.setBackgroundResource(R.mipmap.select_yes);
        } else {
            groupViewHolder.ck_select.setBackgroundResource(R.mipmap.select_no);
        }
        groupViewHolder.te_groupname.setText(list.get(groupPosition).getProjectName());
        groupViewHolder.ck_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischeckgroup = list.get(groupPosition).isIscheckgroup();
                List<ProjectFromWorkBean.ListBean.UserListBean> userList = list.get(groupPosition).getUserList();
                if (userList!=null&&userList.size()>0) {
                    if (ischeckgroup) {
                        for (int i = 0; i < userList.size(); i++) {
                            ProjectFromWorkBean.ListBean.UserListBean userListBean = userList.get(i);
                            if (userListBean.isIscheckchild()) {
                                userListBean.setIscheckchild(false);
                            }
                        }
                    } else {
                        for (int i = 0; i < userList.size(); i++) {
                            ProjectFromWorkBean.ListBean.UserListBean userListBean = userList.get(i);
                            if (!userListBean.isIscheckchild()) {
                                userListBean.setIscheckchild(true);
                            }
                        }
                    }
                    myOnclicklisttener.Onclicklistener(groupPosition);
                }else {
                    ToastUtils.showToast("该项目未添加人员");
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.selectpeople_exp_child_item, parent, false);
        TextView te_zhiwei = (TextView) view.findViewById(R.id.te_zhiwei);
        TextView te_name = (TextView) view.findViewById(R.id.te_name);
        RoundedImageView img_woker_head = (RoundedImageView) view.findViewById(R.id.img_woker_head);
        ImageView img_send_msg = (ImageView) view.findViewById(R.id.img_send_msg);
        ImageView img_call_phone = (ImageView) view.findViewById(R.id.img_call_phone);
        ImageView ck_select = (ImageView) view.findViewById(R.id.ck_select);
        View view1=view.findViewById(R.id.view1);

        final ProjectFromWorkBean.ListBean.UserListBean userListBean = list.get(groupPosition).getUserList().get(childPosition);
        te_name.setText(userListBean.getUserName());
        int size=list.get(groupPosition).getUserList().size();
        if (childPosition==size-1){
            view1.setVisibility(View.GONE);
        }else {
            view1.setVisibility(View.VISIBLE);
        }
        if (userListBean.isIscheckchild()) {
            ck_select.setBackgroundResource(R.mipmap.select_yes);
        } else {
            ck_select.setBackgroundResource(R.mipmap.select_no);
        }
        if (userListBean.getUserPicture() != null &&
                !userListBean.getUserPicture().equals("")
                && !userListBean.getUserPicture().equals("null")) {
//            Bitmap bitmap = CommonUtils.stringtoBitmap(userListBean.getUserPicture());
//            img_woker_head.setImageBitmap(bitmap);
            getFormImage(img_woker_head,userListBean.getUserPicture());
        }else {
            String sex = userListBean.getUserSex();
            if (sex!= null && !sex.equals("")&& !sex.equals("null")){
//                if (sex.equals("男")){
                    img_woker_head.setBackgroundResource(R.mipmap.maillist_man);
//                }else {
//                    img_woker_head.setBackgroundResource(R.mipmap.maillist_woman);
//                }

            }else {
                img_woker_head.setBackgroundResource(R.mipmap.maillist_man);
            }
        }
        img_call_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri
                        .parse("tel:" + userListBean.getUserPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(intent);
            }
        });
        img_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsToUri = Uri.parse("smsto:" + userListBean.getUserPhone());
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

    public interface proOnclicklisttener{
        void Onclicklistener(int position);
    }
    public void setMyOnclicklisttener(proOnclicklisttener onclicklisttener){
        myOnclicklisttener=onclicklisttener;
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
                            GlideDownLoadImage.getInstance().loadBitmapCircleImageRole(context, imageView, bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    class GroupViewHolder {
        TextView te_groupname;
        View viewwhite;
        ImageView ck_select;

        public GroupViewHolder(View view) {
            te_groupname = (TextView) view.findViewById(R.id.te_groupname);
            ck_select = (ImageView) view.findViewById(R.id.ck_select);
            viewwhite=view.findViewById(R.id.view);
        }
    }

    class ChildViewHolder {
        TextView te_zhiwei, te_name;
        ImageView img_woker_head, img_send_msg, img_call_phone;
        ImageView ck_select;
        View view1;

        public ChildViewHolder(View view) {
            te_zhiwei = (TextView) view.findViewById(R.id.te_zhiwei);
            te_name = (TextView) view.findViewById(R.id.te_name);
            img_woker_head = (ImageView) view.findViewById(R.id.img_woker_head);
            img_send_msg = (ImageView) view.findViewById(R.id.img_send_msg);
            img_call_phone = (ImageView) view.findViewById(R.id.img_call_phone);
            ck_select = (ImageView) view.findViewById(R.id.ck_select);
            view1=view.findViewById(R.id.view1);
        }
    }
}
