package com.yunyisheng.app.yunys.schedule.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
import com.yunyisheng.app.yunys.project.model.TaskMessageEvent;
import com.yunyisheng.app.yunys.schedule.adapter.TaskAdapter;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.schedule.model.ScheduleNoSizeBean;
import com.yunyisheng.app.yunys.schedule.present.MySchedulePresent;
import com.yunyisheng.app.yunys.schedule.view.CustomDayView;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateProcessTaskAcitvity;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.router.Router;

import static com.yunyisheng.app.yunys.utils.CommonUtils.ConverToDate;
import static com.yunyisheng.app.yunys.utils.CommonUtils.ConverToMonthDate;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getDayEndTime;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getDayStartTime;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getOtherEndtime;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getOtherStarttime;

/**
 * 作者：fuduo on 2018/1/13 15:56
 * 邮箱：duoendeavor@163.com
 * 用途：我的日程fragement
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
    private WindowsReceiver mWindowsReceiver = new WindowsReceiver();
    private List<MyScheduleBean.RespBodyBean.DataListBean> list = new ArrayList<>();
    private TaskAdapter mineadapter;
    private int pageindex = 1;
    private String dayStartTime;
    private String dayEndTime;
    private boolean nomore;
    private boolean isfirst = true;
    private boolean isfistopen=true;
    private String firstMonthDay;
    private String lastMonthDay;
    private HashMap<String, String> markData;

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
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
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        Date time = calendar.getTime();
        firstMonthDay = CommonUtils.getFirstMonthDay(time);
        lastMonthDay = CommonUtils.getTodayLastMonth();
        LogUtils.i("MonthDay", firstMonthDay + "====" + lastMonthDay);
        getP().getNoScheduleList(firstMonthDay, lastMonthDay, 1);
        getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TaskMessageEvent taskMessageEvent) {
        Log.d("cylog", "receive it");
        String position = taskMessageEvent.getPosition();
        if (position.equals("updateOK")){
            getP().getNoScheduleList(firstMonthDay, lastMonthDay, 1);
        }
    }

    public void getResultList(MyScheduleBean myScheduleBean) {
        if (pageindex == 1) {
            list.clear();
        }
        if (myScheduleBean.getRespBody().getDataList() != null && myScheduleBean.getRespBody().getDataList().size() > 0) {
            rvToDoList.setVisibility(View.VISIBLE);
            imgQuesheng.setVisibility(View.GONE);
            imgQuesheng2.setVisibility(View.GONE);
            list.addAll(myScheduleBean.getRespBody().getDataList());
            if (pageindex == 1) {
                mineadapter = new TaskAdapter(mContext, list);
                rvToDoList.setAdapter(mineadapter);
            } else {
                mineadapter.setData(list);
            }
            String[] str = null;
            String creationTime = myScheduleBean.getRespBody().getDataList().get(0).getCreationTime();
            str = creationTime.split(" ");
            LogUtils.i("sfdfdfdf", str[0]);
            mineadapter.setType(4);
        } else {
            if (pageindex == 1) {
                rvToDoList.setVisibility(View.GONE);
                if (isfirst) {
                    imgQuesheng2.setVisibility(View.GONE);
                    imgQuesheng.setVisibility(View.VISIBLE);
                    imgQuesheng.setBackgroundResource(R.mipmap.no_index_task);
                } else {
                    imgQuesheng.setVisibility(View.GONE);
                    imgQuesheng2.setVisibility(View.VISIBLE);
                    imgQuesheng2.setBackgroundResource(R.mipmap.no_task);
                }
                ToastUtils.showToast("当前日期暂无日程");
            } else {
                ToastUtils.showToast("没有更多了");
                nomore = true;
            }
        }
        isfirst = false;
    }

    public void setimgBac() {
        rvToDoList.setVisibility(View.GONE);
        imgQuesheng2.setVisibility(View.GONE);
        imgQuesheng.setVisibility(View.VISIBLE);
        imgQuesheng.setBackgroundResource(R.mipmap.no_network);
    }

    public void getNoScheduleResultList(ScheduleNoSizeBean scheduleNoSizeBean) {
        List<ScheduleNoSizeBean.RespBodyBean> respBody = scheduleNoSizeBean.getRespBody();
        markData = new HashMap<>();
        markData.clear();
        DateFormat df = new SimpleDateFormat("y-M-d");
        if (respBody.size() > 0) {
            try {
                for (int i = 0; i < respBody.size(); i++) {
                    ScheduleNoSizeBean.RespBodyBean respBodyBean = respBody.get(i);
                    int num = respBodyBean.getNum();
                    String date = respBodyBean.getDate();
                    Date date1 = CommonUtils.ConverToDate(date);
                    String format = df.format(date1);
                    markData.put(format, num + "");
                }
            } catch (Exception e) {

            }
        }
        calendarAdapter.setMarkData(markData);
        if (isfistopen) {
            isfistopen=false;
            calendarAdapter.notifyDataChanged();
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
        imgQuesheng.setOnClickListener(this);
        imgQuesheng2.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_quesheng:
                String dayStart = getDayStartTime();
                if (dayStart.equals(dayStartTime)) {
                    isfirst = true;
                }
                getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
                break;
            case R.id.img_quesheng2:
                getP().getMySchedulrList(pageindex, this.dayStartTime, dayEndTime);
                break;
        }
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
        rl_liucheng_task.setVisibility(View.VISIBLE);
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
        initMonthPager();
    }

    private void initListener() {
        onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                try {
                    Date date1 = ConverToDate(date.toString());
                    dayStartTime = getOtherStarttime(date1);
                    dayEndTime = getOtherEndtime(date1);
                    String dayStart = getDayStartTime();
                    if (dayStart.equals(dayStartTime)) {
                        isfirst = true;
                    }
                    nomore = false;
                    list.clear();
                    pageindex = 1;
                    getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);

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
                try {
                    if (currentCalendars.get(position % currentCalendars.size()) != null) {
                        CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                        teDate.setText(date.getYear() + "年" + date.getMonth() + "月");
                        Date date1 = ConverToMonthDate(date.toString());
                        firstMonthDay = CommonUtils.getFirstMonthDay(date1);
                        lastMonthDay = CommonUtils.getLastMonthDay(date1);
                        LogUtils.i("MonthDay", firstMonthDay + "====" + lastMonthDay);
                        if (currentDate.getYear() != date.getYear() || currentDate.getMonth() != date.getMonth()) {
                            getP().getNoScheduleList(firstMonthDay, lastMonthDay, 1);
                        }
                        currentDate = date;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        getActivity().unregisterReceiver(mWindowsReceiver);
    }

}
