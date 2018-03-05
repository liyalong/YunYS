package com.yunyisheng.app.yunys.schedule.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ldf.calendar.Utils;
import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.interf.OnSelectDateListener;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.Calendar;
import com.ldf.calendar.view.MonthPager;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.schedule.adapter.TaskAdapter;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.schedule.model.PositionMessageEvent;
import com.yunyisheng.app.yunys.schedule.present.MySchedulePresent;
import com.yunyisheng.app.yunys.schedule.view.CustomDayView;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateProcessTaskAcitvity;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.router.Router;

import static com.yunyisheng.app.yunys.utils.CommonUtils.ConverToDate;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getDayEndTime;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getDayStartTime;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getOtherEndtime;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getOtherStarttime;

/**
 * 作者：fuduo on 2018/1/13 15:56
 * 邮箱：duoendeavor@163.com
 * 用途：我的日程和项目日程fragement
 */
public class OurProjeceScheduleFragement extends BaseFragement<MySchedulePresent> {

    Unbinder unbinder;
    @BindView(R.id.calendar_view)
    MonthPager monthPager;
    @BindView(R.id.list)
    RecyclerView rvToDoList;
    @BindView(R.id.te_date)
    TextView teDate;
    @BindView(R.id.content)
    CoordinatorLayout content;
    @BindView(R.id.img_addtask)
    ImageView imgAddtask;
    @BindView(R.id.img_quesheng)
    ImageView imgQuesheng;
    @BindView(R.id.img_quesheng2)
    ImageView imgQuesheng2;

    private ArrayList<Calendar> currentCalendars = new ArrayList<>();
    private CalendarViewAdapter calendarAdapter;
    private OnSelectDateListener onSelectDateListener;
    private int mCurrentPage = MonthPager.CURRENT_DAY_INDEX;
    private CalendarDate currentDate;
    private int tabindex;
    private WindowsReceiver mWindowsReceiver = new WindowsReceiver();
    private List<MyScheduleBean.RespBodyBean.DataListBean> list = new ArrayList<>();
    private List<MyScheduleBean.RespBodyBean.DataListBean> projectschedulelist = new ArrayList<>();
    private TaskAdapter mineadapter;
    private TaskAdapter projectadapter;
    private int pageindex = 1;
    private String dayStartTime;
    private String dayEndTime;
    private String projectid;
    private boolean nomore;

    public static OurProjeceScheduleFragement getInstance(int i) {
        OurProjeceScheduleFragement ourProjeceScheduleFragement = new OurProjeceScheduleFragement();
        Bundle bundle = new Bundle();
        bundle.putInt("tabindex", i);
        ourProjeceScheduleFragement.setArguments(bundle);
        return ourProjeceScheduleFragement;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        tabindex = bundle.getInt("tabindex");
    }

    @Override
    public void initView() {
        monthPager.setViewHeight(Utils.dpi2px(mContext, 270));
        initCurrentDate();
        initCalendarView();
        initToolbarClickListener();
        Log.e("ldf", "OnCreated");
        IntentFilter intent2 = new IntentFilter("WindowFoucuschanged");
        mContext.registerReceiver(mWindowsReceiver, intent2);

        rvToDoList.setHasFixedSize(true);
        //这里用线性显示 类似于listview
        rvToDoList.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvToDoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int totalItemCount = recyclerView.getAdapter().getItemCount();
//                int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
//                int visibleItemCount = recyclerView.getChildCount();
//
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
//                        && lastVisibleItemPosition == totalItemCount - 1
//                        && visibleItemCount > 0) {
//                    if (fastClick()) {
//                        loadMore();
//                    }
//                }
//            }
//        });
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    private void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!nomore) {
                    //加载更多
                    if (tabindex == 0) {
                        pageindex++;
                        getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
                    } else {
                        pageindex++;
                        getP().getMyProjectSchedulrList(pageindex, projectid, dayStartTime, dayEndTime);
                    }
                }
            }
        }, 1000);
    }

    @Override
    public void initAfter() {
        refreshMonthPager();
        imgAddtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSelectTaskDialog(getActivity());
            }
        });
        dayStartTime = getDayStartTime();
        dayEndTime = getDayEndTime();
        if (tabindex == 0) {
            getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
        }
    }

    public void getResultList(MyScheduleBean myScheduleBean) {
        if (pageindex == 1) {
            list.clear();
        }
        if (myScheduleBean.getRespBody().getDataList() != null && myScheduleBean.getRespBody().getDataList().size() > 0) {
            list.addAll(myScheduleBean.getRespBody().getDataList());
            if (pageindex == 1) {
                mineadapter = new TaskAdapter(mContext, list);
                rvToDoList.setAdapter(mineadapter);
            } else {
                mineadapter.setData(list);
            }
            String[] str=null;
            String creationTime = myScheduleBean.getRespBody().getDataList().get(0).getCreationTime();
            str=creationTime.split(" ");
            LogUtils.i("sfdfdfdf",str[0]);
            mineadapter.setType(4);
        } else {
            if (pageindex == 1) {
                ToastUtils.showToast("当前日期暂无日程");
            } else {
                ToastUtils.showToast("没有更多了");
                nomore = true;
            }
        }
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PositionMessageEvent messageEvent) {
        Log.d("cylog", "receive it");
        if (tabindex==1) {
            projectid = messageEvent.getPosition();
            if (projectid != null && !projectid.equals("")) {
                projectschedulelist.clear();
                pageindex = 1;
                getP().getMyProjectSchedulrList(pageindex, projectid, dayStartTime, dayEndTime);
            }
        }
    }

    public void getProjectResultList(MyScheduleBean myScheduleBean) {
        if (pageindex == 1) {
            projectschedulelist.clear();
        }
        if (myScheduleBean.getRespBody().getDataList() != null && myScheduleBean.getRespBody().getDataList().size() > 0) {
            projectschedulelist.addAll(myScheduleBean.getRespBody().getDataList());
            if (pageindex == 1) {
                projectadapter = new TaskAdapter(mContext, projectschedulelist);
                rvToDoList.setAdapter(projectadapter);
            } else {
                projectadapter.setData(projectschedulelist);
            }
            projectadapter.setType(6);
        } else {
            if (pageindex == 1) {
            } else {
                ToastUtils.showToast("没有更多了");
                nomore = true;
            }
        }
    }

    private class WindowsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals("WindowFoucuschanged")) {
                int code = intent.getIntExtra("code", 0);
                if (code == 200) {
                    refreshMonthPager();
                    Log.d("odododood", "33333333");
                }
            }
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.fragement_richeng;
    }

    @Override
    public MySchedulePresent bindPresent() {
        return new MySchedulePresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    /**
     * 选择任务对话框
     *
     * @param activity
     * @return
     */
    public void createSelectTaskDialog(final Activity activity) {
        final Dialog mSelectTask = new Dialog(activity, R.style.dialog_bottom_full);
        mSelectTask.setCanceledOnTouchOutside(true);
        mSelectTask.setCancelable(true);
        Window window = mSelectTask.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View view1 = View.inflate(activity, R.layout.dialog_select_task, null);
        RelativeLayout rl_shebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_shebei_task);
        RelativeLayout rl_wrongshebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_wrongshebei_task);

        RelativeLayout rl_liucheng_task = (RelativeLayout) view1
                .findViewById(R.id.rl_liucheng_task);
        RelativeLayout rl_close = (RelativeLayout) view1
                .findViewById(R.id.rl_close);
        if (tabindex == 0) {
            rl_liucheng_task.setVisibility(View.VISIBLE);
        } else {
            rl_liucheng_task.setVisibility(View.GONE);
        }
        rl_shebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Router.newIntent(context)
                        .to(CreateDeviceTaskAcitvity.class)
                        .launch();
                mSelectTask.closeOptionsMenu();

            }
        });
        rl_wrongshebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Router.newIntent(context)
                        .to(CreateNoneDeviceTaskAcitvity.class)
                        .launch();
                mSelectTask.closeOptionsMenu();
            }
        });
        rl_liucheng_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.newIntent(context)
                        .to(CreateProcessTaskAcitvity.class)
                        .launch();
                mSelectTask.closeOptionsMenu();
            }
        });

        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectTask.dismiss();
            }
        });

        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mSelectTask.show();
    }

    /**
     * 初始化对应功能的listener
     *
     * @return void
     */
    private void initToolbarClickListener() {
//        backToday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickBackToDayBtn();
//            }
//        });
//        scrollSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (calendarAdapter.getCalendarType() == CalendarAttr.CalendarType.WEEK) {
//                    Utils.scrollTo(content, rvToDoList, monthPager.getViewHeight(), 200);
//                    calendarAdapter.switchToMonth();
//                } else {
//                    Utils.scrollTo(content, rvToDoList, monthPager.getCellHeight(), 200);
//                    calendarAdapter.switchToWeek(monthPager.getRowIndex());
//                }
//            }
//        });
//        themeSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                refreshSelectBackground();
//            }
//        });
//        nextMonthBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                monthPager.setCurrentItem(monthPager.getCurrentPosition() + 1);
//            }
//        });
//        lastMonthBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                monthPager.setCurrentItem(monthPager.getCurrentPosition() - 1);
//            }
//        });
    }

    /**
     * 初始化currentDate
     *
     * @return void
     */
    private void initCurrentDate() {
        currentDate = new CalendarDate();
        teDate.setText(currentDate.getYear() + "年" + currentDate.getMonth() + "月");
    }

    /**
     * 初始化CustomDayView，并作为CalendarViewAdapter的参数传入
     */
    private void initCalendarView() {
        initListener();
        CustomDayView customDayView = new CustomDayView(mContext, R.layout.custom_day);
        calendarAdapter = new CalendarViewAdapter(
                mContext,
                onSelectDateListener,
                CalendarAttr.CalendarType.MONTH,
                CalendarAttr.WeekArrayType.Sunday,
                customDayView);
        calendarAdapter.setOnCalendarTypeChangedListener(new CalendarViewAdapter.OnCalendarTypeChanged() {
            @Override
            public void onCalendarTypeChanged(CalendarAttr.CalendarType type) {
                rvToDoList.scrollToPosition(0);
            }
        });
        initMarkData();
        initMonthPager();
    }

    /**
     * 初始化标记数据，HashMap的形式，可自定义
     * 如果存在异步的话，在使用setMarkData之后调用 calendarAdapter.notifyDataChanged();
     */
    private void initMarkData() {
        HashMap<String, String> markData = new HashMap<>();
        markData.put("2018-3-4", "1");
        markData.put("2017-7-9", "0");
        markData.put("2017-6-9", "1");
        markData.put("2017-6-10", "0");
        calendarAdapter.setMarkData(markData);
    }

    private void initListener() {
        onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                try {
                    Date date1 = ConverToDate(date.toString());
                    dayStartTime = getOtherStarttime(date1);
                    dayEndTime = getOtherEndtime(date1);
                    if (tabindex == 0) {
                        nomore = false;
                        list.clear();
                        pageindex = 1;
                        getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
                    } else {
                        nomore = false;
                        list.clear();
                        pageindex = 1;
                        getP().getMyProjectSchedulrList(pageindex, projectid, dayStartTime, dayEndTime);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                refreshClickDate(date);
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
                monthPager.selectOtherMonth(offset);
            }
        };
    }

    private void refreshClickDate(CalendarDate date) {
        currentDate = date;
        teDate.setText(currentDate.getYear() + "年" + currentDate.getMonth() + "月");
    }

    /**
     * 初始化monthPager，MonthPager继承自ViewPager
     *
     * @return void
     */
    private void initMonthPager() {
        monthPager.setAdapter(calendarAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) != null) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    currentDate = date;
                    if (currentDate.getYear()!=date.getYear()||currentDate.getMonth()!=date.getMonth()){
                    }
                    teDate.setText(date.getYear() + "年" + date.getMonth() + "月");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void refreshMonthPager() {
        CalendarDate today = new CalendarDate();
        calendarAdapter.notifyDataChanged(today);
        teDate.setText(today.getYear() + "年" + today.getMonth() + "月");
    }

//    private void refreshSelectBackground() {
//        ThemeDayView themeDayView = new ThemeDayView(mContext, R.layout.custom_day_focus);
//        calendarAdapter.setCustomDayRenderer(themeDayView);
//        calendarAdapter.notifyDataSetChanged();
//        calendarAdapter.notifyDataChanged(new CalendarDate());
//    }p

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        getActivity().unregisterReceiver(mWindowsReceiver);
        EventBus.getDefault().unregister(this);
    }

}
