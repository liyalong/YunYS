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

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.model.ProjectFromWorkBean;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.glide.GlideDownLoadImage;

import java.util.ArrayList;
import java.util.List;

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
                    if (userListBean.isIscheckchild()) {
                        selectpeople.add(userListBean);
                    }
                }
            }
        }
        return selectpeople;
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
                if (ischeckgroup){
                    for (int i = 0; i < userList.size(); i++) {
                        ProjectFromWorkBean.ListBean.UserListBean userListBean = userList.get(i);
                        if (userListBean.isIscheckchild()) {
                            userListBean.setIscheckchild(false);
                        }
                    }
                }else {
                    for (int i = 0; i < userList.size(); i++) {
                        ProjectFromWorkBean.ListBean.UserListBean userListBean = userList.get(i);
                        if (!userListBean.isIscheckchild()) {
                            userListBean.setIscheckchild(true);
                        }
                    }
                }
                myOnclicklisttener.Onclicklistener(groupPosition);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.selectpeople_exp_child_item, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        final ProjectFromWorkBean.ListBean.UserListBean userListBean = list.get(groupPosition).getUserList().get(childPosition);
        childViewHolder.te_name.setText(userListBean.getUserName());
        int size=list.get(groupPosition).getUserList().size();
        if (childPosition==size-1){
            childViewHolder.view1.setVisibility(View.GONE);
        }else {
            childViewHolder.view1.setVisibility(View.VISIBLE);
        }
        if (userListBean.isIscheckchild()) {
            childViewHolder.ck_select.setBackgroundResource(R.mipmap.select_yes);
        } else {
            childViewHolder.ck_select.setBackgroundResource(R.mipmap.select_no);
        }
        if (userListBean.getUserPicture() != null &&
                !userListBean.getUserPicture().equals("")
                && !userListBean.getUserPicture().equals("null")) {
            Bitmap bitmap = CommonUtils.stringtoBitmap(userListBean.getUserPicture());
            GlideDownLoadImage.getInstance().loadBitmapCircleImageRole(context, childViewHolder.img_woker_head, bitmap);
        }else {
            String sex = userListBean.getUserSex();
            if (sex!= null && !sex.equals("")&& !sex.equals("null")){
                if (sex.equals("男")){
                    childViewHolder.img_woker_head.setBackgroundResource(R.mipmap.maillist_man);
                }else {
                    childViewHolder.img_woker_head.setBackgroundResource(R.mipmap.maillist_woman);
                }

            }else {
                childViewHolder.img_woker_head.setBackgroundResource(R.mipmap.maillist_man);
            }
        }
        childViewHolder.img_call_phone.setOnClickListener(new View.OnClickListener() {
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
        childViewHolder.img_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsToUri = Uri.parse("smsto:" + userListBean.getUserPhone());
                Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                context.startActivity(intent);
            }
        });
        return convertView;
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
