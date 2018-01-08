package com.yunyisheng.app.yunys.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import java.util.UUID;

import cn.droidlover.xdroidbase.cache.SharedPref;

import static com.yunyisheng.app.yunys.App.context;

public class AndroidIDUtil{
    private static String deviceid;

    public static String getDeviceid(){
        if(deviceid == null || "".equals(deviceid)){
            try {
                deviceid = getLocalMac(context).replace(":", "");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (deviceid == null || "".equals(deviceid)){
            try {
                deviceid = getAndroidId(context);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(deviceid == null || "".equals(deviceid)){
            UUID uuid = UUID.randomUUID();
            deviceid = uuid.toString().replace("-", "");
            SharedPref.getInstance(context).putString("DEVICEID",deviceid);
        }
        return deviceid;
    }


    // Mac地址
    private static String getLocalMac(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        if (info.getMacAddress().equals("020000000000")){
            return "";
        }
        return info.getMacAddress();
    }
    // Android Id
    private static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }
}