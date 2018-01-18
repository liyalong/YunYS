package com.yunyisheng.app.yunys.main.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 作者：fuduo on 2018/1/18 17:38
 * 邮箱：duoendeavor@163.com
 * 用途：公告fragement
 */

public class NoticeFragement extends BaseFragement {
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.lv_notice)
    ListView lvNotice;
    Unbinder unbinder;
    private int tabindex;

    public static NoticeFragement getInstance(int index){
        NoticeFragement fragement=new NoticeFragement();
        Bundle bundle=new Bundle();
        bundle.putInt("tabindex",index);
        fragement.setArguments(bundle);
        return fragement;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        tabindex = bundle.getInt("tabindex",0);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_notice;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSearch.setText("");
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
