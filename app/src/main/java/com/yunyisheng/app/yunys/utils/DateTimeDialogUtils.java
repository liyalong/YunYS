package com.yunyisheng.app.yunys.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.TimePicker;

import com.yunyisheng.app.yunys.utils.customDatePicker.CustomDatePicker;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
                mCalendar.set(Calendar.MINUTE, i1);
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                textView.setText(format.format(mCalendar.getTime()));
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
    public static String DateCompare(String old,String now){
        java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Calendar c1=java.util.Calendar.getInstance();
        java.util.Calendar c2=java.util.Calendar.getInstance();
        try {
            c1.setTime(df.parse(old));
            c2.setTime(df.parse(now));
        } catch (Exception e) {
            System.out.println("格式不正确");
            e.printStackTrace();
        }
        int result=c1.compareTo(c2);
        if(result==0){
            return "相等";
        }else if(result<0){
            return "小于";
        }else
            return "大于";
    }


}
