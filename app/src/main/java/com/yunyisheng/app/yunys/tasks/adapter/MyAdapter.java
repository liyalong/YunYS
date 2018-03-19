package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.model.ChildBean;
import com.yunyisheng.app.yunys.tasks.model.GroupBean;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：fuduo on 2018/1/17 17:31
 * 邮箱：duoendeavor@163.com
 * 用途：外层反馈项适配器
 */

public class MyAdapter extends BaseAdapter {

    Context context;
    List<GroupBean> strList = new ArrayList<>();//外层集合
    List<ChildBean> stringList;//里层集合
    private MySmallitemAdapter adapter;//里层适配器

    public MyAdapter(Context context, List<GroupBean> strList) {
        this.context = context;
        this.strList = strList;
    }

    @Override
    public int getCount() {
        return strList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.template_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //保证edittext内容不重复
        if (viewHolder.ed_fankui.getTag() instanceof TextWatcher) {
            viewHolder.ed_fankui.removeTextChangedListener((TextWatcher) viewHolder.ed_fankui.getTag());
        }
        if (viewHolder.ed_wenzi.getTag() instanceof TextWatcher) {
            viewHolder.ed_wenzi.removeTextChangedListener((TextWatcher) viewHolder.ed_wenzi.getTag());
        }
        //如果spinner数据为空给spinner填充数据
        if (viewHolder.sp_type.getCount() == 0) {
            SelectFankuiTypeSpinnerAdapter spadapter=new SelectFankuiTypeSpinnerAdapter(context);
            //绑定 Adapter到控件
            viewHolder.sp_type.setAdapter(spadapter);
        }
        viewHolder.te_fankuisize.setText("("+ CommonUtils.formatInteger(position+1)+")");
        viewHolder.ed_fankui.setText(strList.get(position).getfeedbackName());
        stringList = strList.get(position).getModel();//从外层集合获取里层集合
        //赋值
        adapter = new MySmallitemAdapter(context, stringList);
        viewHolder.myListView.setAdapter(adapter);
        viewHolder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加一个里层选项并刷新适配器
                List<ChildBean> list = strList.get(position).getModel();
                ChildBean childBean = new ChildBean();
                list.add(childBean);
                stringList=list;
                strList.get(position).setModel(stringList);
                notifyDataSetChanged();
            }
        });
        viewHolder.img_delete_fankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除一个反馈项 刷新列表
                strList.remove(position);
                notifyDataSetChanged();
            }
        });
        //根据选中的类型设置反馈项类型
        int type = strList.get(position).getfeedbackType();
        if (type==1){
            viewHolder.sp_type.setSelection(2);
        }else if (type==2){
            viewHolder.sp_type.setSelection(0);
        }else if (type==3){
            viewHolder.sp_type.setSelection(3);
        }else if (type==4){
            viewHolder.sp_type.setSelection(1);
        }else {
            viewHolder.sp_type.setSelection(0);
        }
        viewHolder.sp_type.setOnItemSelectedListener(new myItemSelectedListener(viewHolder, stringList, position));

        List<ChildBean> childBeanList = adapter.getStringList();
        strList.get(position).setModel(childBeanList);

        TextWatcher fankuiwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    strList.get(position).setfeedbackName("");
                } else {
                    strList.get(position).setfeedbackName(s.toString());
                }
            }
        };
        TextWatcher wenziwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (TextUtils.isEmpty(s)) {
//                    strList.get(position).setWeniz("");
//                } else {
//                    strList.get(position).setWeniz(s.toString());
//                }
            }
        };
        viewHolder.ed_fankui.addTextChangedListener(fankuiwatcher);
        viewHolder.ed_fankui.setTag(fankuiwatcher);
        viewHolder.ed_wenzi.addTextChangedListener(wenziwatcher);
        viewHolder.ed_wenzi.setTag(wenziwatcher);
        return convertView;
    }

    class ViewHolder {
        EditText ed_fankui, ed_wenzi;
        ImageView img_add,img_delete_fankui;
        Spinner sp_type;
        TextView te_fankuisize;
        LinearLayout line_item, line_beixuan;
        RelativeLayout line_select_type;
        MyListView myListView;

        public ViewHolder(View view) {
            sp_type = (Spinner) view.findViewById(R.id.sp_type);
            ed_fankui = (EditText) view.findViewById(R.id.ed_fankui);
            ed_wenzi = (EditText) view.findViewById(R.id.ed_wenzi);
            img_add = (ImageView) view.findViewById(R.id.img_add);
            te_fankuisize=(TextView) view.findViewById(R.id.te_fankuisize);
            img_delete_fankui=(ImageView)view.findViewById(R.id.img_delete_fankui);
            line_beixuan = (LinearLayout) view.findViewById(R.id.line_beixuan);
            line_select_type = (RelativeLayout) view.findViewById(R.id.line_select_type);
            line_item = (LinearLayout) view.findViewById(R.id.line_item);
            myListView = (MyListView) view.findViewById(R.id.lv_small_item);
        }

    }

    class myItemSelectedListener implements AdapterView.OnItemSelectedListener {
        ViewHolder viewHolder;
        int position;
        List<ChildBean> stringList;

        public myItemSelectedListener(ViewHolder viewHolder, List<ChildBean> stringList, int postition) {
            this.viewHolder = viewHolder;
            this.position = postition;
            this.stringList = stringList;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (pos==0){
                strList.get(position).setfeedbackType(2);
            }else if (pos==1){
                strList.get(position).setfeedbackType(4);
            }else if (pos==2){
                strList.get(position).setfeedbackType(1);
            }else if (pos==3){
                strList.get(position).setfeedbackType(3);
            }
            if (pos == 2 || pos == 1) {
                viewHolder.line_beixuan.setVisibility(View.GONE);
                stringList.clear();
                adapter.notifyDataSetChanged();
            } else {
                viewHolder.line_beixuan.setVisibility(View.VISIBLE);
                notifyDataSetChanged();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public List<GroupBean> getStrList() {
        return strList;
    }
}
