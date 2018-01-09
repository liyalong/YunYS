package com.yunyisheng.app.yunys.utils;

import android.util.Log;

import com.yunyisheng.app.yunys.BuildConfig;


/**
 * Created by MarkShuai on 2016-10-28.
 * 发送LOG的一个工具类
 */

public class LogUtils {
    private static boolean isDeBug = BuildConfig.DEBUG;

    public static void v(String tag, String message) {
        if(isDeBug) {
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if(isDeBug) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if(isDeBug) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if(isDeBug) {
            Log.w(tag, message);

        }
    }

    public static void e(String tag, String message) {
        if(isDeBug) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message,Exception e) {
        if(isDeBug) {
            Log.e(tag, message);
        }
    }
}
