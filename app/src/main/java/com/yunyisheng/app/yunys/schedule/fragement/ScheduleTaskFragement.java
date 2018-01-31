package com.yunyisheng.app.yunys.schedule.fragement;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;
import com.yunyisheng.app.yunys.project.model.ProjectListModel;
import com.yunyisheng.app.yunys.schedule.activity.ScheduleSetActivity;
import com.yunyisheng.app.yunys.schedule.adapter.ProjectListAdapter;
import com.yunyisheng.app.yunys.schedule.model.PositionMessageEvent;
import com.yunyisheng.app.yunys.schedule.present.SchedulrTaskPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.yunyisheng.app.yunys.utils.ScreenUtils.setIndicator;

/**
 * 作者：fuduo on 2018/1/13 11:32
 * 邮箱：duoendeavor@163.com
 * 用途：日程fragement
 */
public class ScheduleTaskFragement extends BaseFragement<SchedulrTaskPresent> {

    Unbinder unbinder;
    @BindView(R.id.tablayout_task)
    TabLayout tablayoutTask;
    @BindView(R.id.img_set)
    ImageView imgSet;
    @BindView(R.id.vp_task)
    ViewPager vpTask;
    @BindView(R.id.te_shaixuan)
    TextView teShaixuan;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private List<ProjectBean> projectBeanList=new ArrayList<>();
    private PopupWindow screenPopupWindow;
    private ListView mScreenListView;

    @Override
    public void initView() {
        if (mTitleList.size() > 0) {
            mTitleList.clear();
        }
        mTitleList.add("我的日程");
        mTitleList.add("项目日程");
        if (fragmentList.size() > 0) {
            fragmentList.clear();
        }
        for (int i = 0; i < mTitleList.size(); i++) {
            fragmentList.add(OurProjeceScheduleFragement.getInstance(i));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList, mTitleList);
        vpTask.setAdapter(adapter);
        tablayoutTask.setupWithViewPager(vpTask);
        setIndicator(getActivity(), tablayoutTask, 10, 10);
        vpTask.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    imgSet.setVisibility(View.VISIBLE);
                    teShaixuan.setVisibility(View.GONE);
                } else {
                    imgSet.setVisibility(View.GONE);
                    teShaixuan.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initAfter() {
        getP().getMyProjectList();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_scheduletask;
    }

    @Override
    public SchedulrTaskPresent bindPresent() {
        return new SchedulrTaskPresent();
    }

    @Override
    public void setListener() {
        imgSet.setOnClickListener(this);
        teShaixuan.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_set:
                startActivity(new Intent(mContext, ScheduleSetActivity.class));
                break;
            case R.id.te_shaixuan:
                showPop();
                if (screenPopupWindow.isShowing()){
                    screenPopupWindow.dismiss();
                }else {
                    showPpw(screenPopupWindow,v);
                }
                break;
        }
    }

    private void showPop(){
        View screenPopView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_project_list, null);
        screenPopupWindow = new PopupWindow(getActivity());
        screenPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        screenPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        screenPopupWindow.setFocusable(true);
        screenPopupWindow.setTouchable(true);
        screenPopupWindow.setContentView(screenPopView);
        screenPopupWindow.setOutsideTouchable(false);
        screenPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        screenPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        mScreenListView = (ListView) screenPopView.findViewById(R.id.lv_projectlist);
        if (projectBeanList.size()>0){
            ProjectListAdapter adapter=new ProjectListAdapter(mContext,projectBeanList);
            mScreenListView.setAdapter(adapter);
        }else {
            ToastUtils.showToast("暂无参与项目");
        }

        mScreenListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectBean projectBean = projectBeanList.get(position);
                String projectId = projectBean.getProjectId();
                EventBus.getDefault().post(new PositionMessageEvent(projectId));
            }
        });

    }

    private void showPpw(PopupWindow popupWindow, View view) {
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.showAsDropDown(view);
        } else {
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] + view.getHeight());
        }
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

    public void setProjectListModel(ProjectListModel projectListModel) {
        List<ProjectBean> respBody = projectListModel.getRespBody();
        if (respBody.size()>0){
            projectBeanList.addAll(respBody);
            String projectId = respBody.get(0).getProjectId();
            EventBus.getDefault().post(new PositionMessageEvent(projectId));
        }
    }
}
