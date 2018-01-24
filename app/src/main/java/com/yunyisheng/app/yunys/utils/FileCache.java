package com.yunyisheng.app.yunys.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 作者:fuDuo
 * 时间：2017/8/17 15:37
 * 邮箱:18610922052@163.com
 * 类的意图:sd卡存储缓存文件
 */

public class FileCache {

    private File cacheDir;
    // 图片SDCard缓存路径
    public static String path = Environment.getExternalStorageDirectory()
            .toString() + "/yunys/";

    public FileCache(Context context) {
        // 如果有SD卡则在SD卡中建一个LazyList的目录存放缓存的图片
        // 没有SD卡就放在系统的缓存目录中
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            cacheDir = new File(
                    Environment.getExternalStorageDirectory(),
                    "yunyslog");
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url) {
        // 将url的hashCode作为缓存的文件名
        String filename = String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;

    }

    //获取缓存目录
    public File getCacheDir() {
        return cacheDir;
    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            deleteFile(f);
    }

    //使用递归进行文件的删除
    public static void deleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                deleteFile(childFiles[i]);
            }
            file.delete();
        }
    }

    /**
     * @Author :付铎
     * @DATE :2017/8/18 10:31
     * @Params 获取sd卡路径文件
     */
    public static File getFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            Log.i("xiaoqiang", "new  yige  file" + file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {

        }
    }

    public static void saveMyBitmap(String bitName, Bitmap mBitmap)
            throws Exception {

        if (SDCardHelper.isSDCardMounted()) {

            File f = getFilePath(path, bitName);

            FileOutputStream fOut = null;

            fOut = new FileOutputStream(f);

            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            Log.i("xiaoqiang", "image is save");

            fOut.flush();

            fOut.close();

        }
    }


}
