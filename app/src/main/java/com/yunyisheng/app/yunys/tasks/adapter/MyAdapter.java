package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.model.ChildBean;
import com.yunyisheng.app.yunys.tasks.model.GroupBean;
import com.yunyisheng.app.yunys.utils.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：fuduo on 2018/1/17 17:31
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MyAdapter extends BaseAdapter {

    Context context;
    List<GroupBean> strList = new ArrayList<>();
    String[] str = new String[]{"单选", "照片", "文本", "多选"};
    private MySmallitemAdapter adapter;

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
        if (viewHolder.ed_fankui.getTag() instanceof TextWatcher) {
            viewHolder.ed_fankui.removeTextChangedListener((TextWatcher) viewHolder.ed_fankui.getTag());
        }
        if (viewHolder.ed_wenzi.getTag() instanceof TextWatcher) {
            viewHolder.ed_wenzi.removeTextChangedListener((TextWatcher) viewHolder.ed_wenzi.getTag());
        }
        if (viewHolder.sp_type.getCount() == 0) {
            ArrayAdapter<String> spadapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, str);
            spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //绑定 Adapter到控件
            viewHolder.sp_type.setAdapter(spadapter);
        }

        viewHolder.ed_wenzi.setText(strList.get(position).getWeniz());
        viewHolder.ed_fankui.setText(strList.get(position).getFankuiname());
        final List<ChildBean> stringList = strList.get(position).getChilddata();
        adapter = new MySmallitemAdapter(context, stringList);
        viewHolder.myListView.setAdapter(adapter);
        viewHolder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildBean childBean = new ChildBean();
                stringList.add(childBean);
                strList.get(position).setChilddata(stringList);
                adapter.notifyDataSetChanged();
            }
        });
        viewHolder.sp_type.setOnItemSelectedListener(new myItemSelectedListener(viewHolder, stringList, position));
        List<ChildBean> childBeanList = adapter.getStringList();
        strList.get(position).setChilddata(childBeanList);

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
                    strList.get(position).setFankuiname("");
                } else {
                    strList.get(position).setFankuiname(s.toString());
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
                if (TextUtils.isEmpty(s)) {
                    strList.get(position).setWeniz("");
                } else {
                    strList.get(position).setWeniz(s.toString());
                }
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
        ImageView img_add;
        Spinner sp_type;
        LinearLayout line_item, line_beixuan;
        RelativeLayout line_select_type;
        MyListView myListView;

        public ViewHolder(View view) {
            sp_type = (Spinner) view.findViewById(R.id.sp_type);
            ed_fankui = (EditText) view.findViewById(R.id.ed_fankui);
            ed_wenzi = (EditText) view.findViewById(R.id.ed_wenzi);
            img_add = (ImageView) view.findViewById(R.id.img_add);
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
            strList.get(position).setFankuitype(str[pos]);
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
