package com.yunyisheng.app.yunys.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.widget.TextView;
import android.widget.TimePicker;

import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.droidlover.xdroidmvp.log.XLog;

/**
 * Created by liyalong on 2018/1/16.
 */

public class DateTimeDialogUtils {
    private Calendar mCalendar;

    /**
     * 显示时间选择器
     * @param context
     * @param textView 回填viewID
     */
    public void showTimePickerDialog(final Context context, final TextView textView){
        mCalendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                mCalendar.set(Calendar.HOUR, i);
                mCalendar.add(Calendar.HOUR,+12);
                mCalendar.set(Calendar.MINUTE, i1);
                SimpleDateFormat formats = new SimpleDateFormat("HH:mm");
                textView.setText(formats.format(mCalendar.getTime()));
            }
        }, mCalendar.get(Calendar.HOUR), mCalendar.get(Calendar.MINUTE), true);
        dialog.show();
    }
    /**获取当前时间
     pattern 类型 yyyy-MM-dd HH:mm:ss
     @return
     */
    public static String getCurrentDate(String pattern) {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String timestamp = formatter.format(curDate);

        return timestamp;
    }
    /**
     获取当前日期的day天后的日期
     @param pattern 格式类型
     @param day 天数
     @return
     */
    public static String getNewData(String pattern, int day) {
        Format format = new SimpleDateFormat(pattern);
        long time = 0;
        Date today = new Date();
        time = (today.getTime() / 1000) + 60 * 60 * 24 * day;
        Date newDate = new Date();
        newDate.setTime(time * 1000);
        String date = format.format(newDate);
        return date;
    }
    /** 描述：比较两个日期的大小
     old 表示之前的时间
     now 现在的时间
     @return
     */
    public static boolean DateCompare(String old,String now) throws ParseException {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(old);
            dt2 = sdf.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }


}
