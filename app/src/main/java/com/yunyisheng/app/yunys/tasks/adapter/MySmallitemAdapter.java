package com.yunyisheng.app.yunys.tasks.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.tasks.model.ChildBean;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/17 18:40
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MySmallitemAdapter extends BaseAdapter {

    private Context context;
    private List<ChildBean> stringList;

    public MySmallitemAdapter(Context context, List<ChildBean> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
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
        ViewHplder viewHplder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.line_item_small, null);
            viewHplder = new ViewHplder(convertView);
            convertView.setTag(viewHplder);
        } else {
            viewHplder = (ViewHplder) convertView.getTag();
        }
        if (viewHplder.ed_beixuan.getTag() instanceof TextWatcher) {
            viewHplder.ed_beixuan.removeTextChangedListener((TextWatcher) viewHplder.ed_beixuan.getTag());
        }
        viewHplder.ed_beixuan.setText(stringList.get(position).getFankuiitem());
        viewHplder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringList.remove(position);
                notifyDataSetChanged();
            }
        });
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    stringList.get(position).setFankuiitem("");
                } else {
                    stringList.get(position).setFankuiitem(s.toString());
                }
            }
        };
        viewHplder.ed_beixuan.addTextChangedListener(watcher);
        viewHplder.ed_beixuan.setTag(watcher);
        return convertView;
    }

    public List<ChildBean> getStringList() {
        return stringList;
    }

    public void notfiy() {
        notifyDataSetChanged();
    }

    class ViewHplder {

        EditText ed_beixuan;
        ImageView img_remove;

        public ViewHplder(View view) {
            ed_beixuan = (EditText) view.findViewById(R.id.ed_beixuan);
            img_remove = (ImageView) view.findViewById(R.id.img_remove);
        }
    }

}
