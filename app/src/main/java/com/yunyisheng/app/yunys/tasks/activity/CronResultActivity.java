package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.tasks.fragment.CronDayFragment;
import com.yunyisheng.app.yunys.tasks.fragment.CronHourFragment;
import com.yunyisheng.app.yunys.tasks.fragment.CronMinuteFragment;
import com.yunyisheng.app.yunys.tasks.fragment.CronMouthFragment;
import com.yunyisheng.app.yunys.tasks.fragment.CronWeekFragment;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * Created by liyalong on 2018/1/15.
 */

public class CronResultActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.cron_tablayout)
    TabLayout cronTablayout;
    @BindView(R.id.cron_viewpage)
    ViewPager cronViewpage;

    private List<Fragment> fragmentLists = new ArrayList<>();
    private List<String> mtitle = new ArrayList<>();
    CronMinuteFragment cronMinuteFragment = new CronMinuteFragment();
    CronMouthFragment cronMouthFragment = new CronMouthFragment();
    CronHourFragment cronHourFragment = new CronHourFragment();
    CronDayFragment cronDayFragment = new CronDayFragment();
    CronWeekFragment cronWeekFragment = new CronWeekFragment();

    private int tabposition;

    public int getTabposition() {
        return tabposition;
    }

    public void setTabposition(int tabposition) {
        this.tabposition = tabposition;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mtitle.add("分钟");
        mtitle.add("小时");
        mtitle.add("每天");
        mtitle.add("每周");
        mtitle.add("每月");
        fragmentLists.add(cronMinuteFragment);
        fragmentLists.add(cronHourFragment);
        fragmentLists.add(cronDayFragment);
        fragmentLists.add(cronWeekFragment);
        fragmentLists.add(cronMouthFragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentLists,mtitle);
        cronViewpage.setAdapter(adapter);
        cronTablayout.setupWithViewPager(cronViewpage);
        cronViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTabposition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.crontab_tablayout;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.img_back:
                setResult(2,intent);
                finish();
                break;
            case R.id.submit:
                getCronValue(intent);
                break;
        }

    }
    private void getCronValue(Intent intent){
        String cron = "";
        int index = getTabposition();
        String txtMinutes,txtHourly,txtDaily,week,ChoiceMonth,ChoiceDay;
        int hourPart,minutePart;
        String[] first_runtime;
        switch (index){
            case 0:
                txtMinutes = cronMinuteFragment.getMinute_value().toString();
                if (txtMinutes.equals("") || txtMinutes == null){
                    ToastUtils.showToast("每分钟不能为空！");
                    return;
                }
                first_runtime = cronMinuteFragment.getMinute_first_runtime().toString().split(":");
                hourPart = Integer.parseInt(first_runtime[0]);
                minutePart = Integer.parseInt(first_runtime[1]);

                cron = "0 "+minutePart+"/"+txtMinutes+" "+hourPart+"/1 * * ?";
                break;
            case 1:
                txtHourly = cronHourFragment.getHourValue().toString();
                if (txtHourly.equals("") || txtHourly.equals(null)){
                    ToastUtils.showToast("每小时不能为空！");
                    return;
                }
                first_runtime = cronHourFragment.getHourFirstRuntime().toString().split(":");
                hourPart = Integer.parseInt(first_runtime[0]);
                minutePart = Integer.parseInt(first_runtime[1]);
                cron = "0 "+minutePart+" "+hourPart+"/"+txtHourly+" * * ?";
                break;
            case 2:
                txtDaily = cronDayFragment.getDayValue().toString();
                if (txtDaily.equals("") || txtDaily.equals(null)){
                    ToastUtils.showToast("每天不能为空！");
                    return;
                }
                first_runtime = cronDayFragment.getDayFirstRuntime().toString().split(":");
                hourPart = Integer.parseInt(first_runtime[0]);
                minutePart = Integer.parseInt(first_runtime[1]);
                cron = "0 "+minutePart+" "+hourPart+" 1/"+txtDaily+" * ?";
                break;
            case 3:
                week = cronWeekFragment.getCheckedValue();
                if (week.equals("")){
                    ToastUtils.showToast("周不能为空！");
                    return;
                }
                week = week.substring(0,week.length() - 1);
                first_runtime = cronWeekFragment.getWeekFirstRuntime().toString().split(":");
                hourPart = Integer.parseInt(first_runtime[0]);
                minutePart = Integer.parseInt(first_runtime[1]);
                cron = "0 "+minutePart+" "+hourPart+" ? * "+week+" * ";
                break;
            case 4:
                ChoiceMonth = cronMouthFragment.getMonthFirstValue().toString();
                ChoiceDay = cronMouthFragment.getMonthSecendValue().toString();
                if (ChoiceMonth.length() == 0 || ChoiceDay.length() == 0){
                    ToastUtils.showToast("每月和每天不能为空！");
                    return;
                }
                first_runtime = cronMouthFragment.getMonthFirstRuntime().toString().split(":");
                hourPart = Integer.parseInt(first_runtime[0]);
                minutePart = Integer.parseInt(first_runtime[1]);
                cron = "0 "+minutePart+" "+hourPart+" "+ChoiceDay+" 1/"+ChoiceMonth+" ? * ";
                break;
        }
        intent.putExtra("cron",cron);
        setResult(1,intent);
        finish();
    }


}
