package com.yunyisheng.app.yunys.project.fragement;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.adapter.SpinnerAdapter;
import com.yunyisheng.app.yunys.main.model.SpinnerBean;
import com.yunyisheng.app.yunys.utils.ScreenUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/18.
 */

public class TaskPoolFragment extends BaseFragement {
    @BindView(R.id.tasks_type)
    Spinner tasksType;
    @BindView(R.id.task_list_view)
    PullToRefreshListView taskListView;

    private List<SpinnerBean> sList = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this, context);
        sList.clear();
        sList.add(new SpinnerBean("我认领的",0));
        sList.add(new SpinnerBean("我发布的",0));
        sList.add(new SpinnerBean("待认领的",0));
        tasksType.setDropDownWidth(ScreenUtils.getScreenHeight(TaskPoolFragment.super.context));
        SpinnerAdapter adapter = new SpinnerAdapter(TaskPoolFragment.super.context,sList);
        tasksType.setAdapter(adapter);
        tasksType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtils.showLongToast("你点击的是:"+sList.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_task_pool;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public static TaskPoolFragment newInstance() {
        return new TaskPoolFragment();
    }

}
