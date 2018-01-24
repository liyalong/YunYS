package com.yunyisheng.app.yunys.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.yunyisheng.app.yunys.App;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件工具类
 */
public class FileUtil {

    private static final String TAG = "FileUtil";
    private static String pathDiv = "/";
    private static File cacheDirVideo = !isExternalStorageWritable()? App.getContext().getFilesDir(): App.getContext().getExternalCacheDir();
    private static File cacheDir = !isExternalStorageWritable()?App.getContext().getFilesDir(): App.getContext().getExternalFilesDir("QQImage.png");

    private FileUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取文件夹扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileType(String fileName) {
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (fileType.equalsIgnoreCase("AVI")
                || fileType.equalsIgnoreCase("WMV")
                || fileType.equalsIgnoreCase("rm")
                || fileType.equalsIgnoreCase("rmvb")
                || fileType.equalsIgnoreCase("mpeg1")
                || fileType.equalsIgnoreCase("mpeg2")
                || fileType.equalsIgnoreCase("mp4")
                || fileType.equalsIgnoreCase("3gp")
                || fileType.equalsIgnoreCase("gp")
                || fileType.equalsIgnoreCase("asf")
                || fileType.equalsIgnoreCase("swf")
                || fileType.equalsIgnoreCase("vob")
                || fileType.equalsIgnoreCase("dat")
                || fileType.equalsIgnoreCase("mov")
                || fileType.equalsIgnoreCase("m4v")
                || fileType.equalsIgnoreCase("flv")
                || fileType.equalsIgnoreCase("f4v")
                || fileType.equalsIgnoreCase("mkv")
                || fileType.equalsIgnoreCase("mts")
                || fileType.equalsIgnoreCase("ts")) {
            return "video";
        } else if (fileType.equalsIgnoreCase("mp3")
                || fileType.equalsIgnoreCase("wav")
                || fileType.equalsIgnoreCase("mp3")
                || fileType.equalsIgnoreCase("mp3pro")
                || fileType.equalsIgnoreCase("ASF")
                || fileType.equalsIgnoreCase("AAC")
                || fileType.equalsIgnoreCase("ogg")
                || fileType.equalsIgnoreCase("VQF")) {
            return "audio";
        } else if (fileType.equalsIgnoreCase("bmp")
                || fileType.equalsIgnoreCase("jpg")
                || fileType.equalsIgnoreCase("png")
                || fileType.equalsIgnoreCase("tiff")
                || fileType.equalsIgnoreCase("gif")
                || fileType.equalsIgnoreCase("tga")
                || fileType.equalsIgnoreCase("pcx")
                || fileType.equalsIgnoreCase("exif")
                || fileType.equalsIgnoreCase("fpx")
                || fileType.equalsIgnoreCase("svg")
                || fileType.equalsIgnoreCase("psd")
                || fileType.equalsIgnoreCase("cdr")
                || fileType.equalsIgnoreCase("pcd")
                || fileType.equalsIgnoreCase("dxf")
                || fileType.equalsIgnoreCase("ufo")
                || fileType.equalsIgnoreCase("eps")
                || fileType.equalsIgnoreCase("ai")
                || fileType.equalsIgnoreCase("raw")
                || fileType.equalsIgnoreCase("wmf")
                || fileType.equalsIgnoreCase("jpeg")) {
            return "image";
        } else {
            return "text";
        }
    }

    /**
     * 创建临时文件
     *
     * @param type 文件类型
     */
    public static File getTempFile(FileType type){
        try{
            File file = File.createTempFile(type.toString(), null, cacheDir);
            file.deleteOnExit();
            return file;
        }catch (IOException e){
            return null;
        }
    }

    public static long getFileSize(String path) throws Exception
    {
        File file=new File(path);
        long size = 0;
        if (file.exists()){
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        else{
            file.createNewFile();
            Log.e("获取文件大小","文件不存在!");
        }
        return size;
    }
    /**
     * 获取視頻缓存文件地址
     */
    public static String getCacheVideoPath(String fileName){
        return cacheDirVideo.getAbsolutePath()+pathDiv+fileName;
    }
    /**
     * 判断視頻缓存文件是否存在
     */
    public static boolean isCacheVideoExist(String fileName){
        File file = new File(getCacheVideoPath(fileName));
        return file.exists();
    }

    /**
     * 判断缓存文件是否存在
     */
    public static boolean isFileExist(String fileName, String type){
        if (isExternalStorageWritable()){
            File dir = App.getContext().getExternalFilesDir(type);
            if (dir != null){
                File f = new File(dir, fileName);
                return f.exists();
            }
        }
        return false;
    }

    /**
     * 判断 是否成功下载
     * @param fileName
     * @param size
     * @return
     */
    public static boolean isScussDown(String fileName, long size)   {
        File file = new File(getCacheVideoPath(fileName));
        long size1=0;
        if (file.exists()){
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                size1 = fis.available();
                if(size<size1||size==size1){
                    return true;
                }else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {

        }
        return false;
    }


    /**
     * 获取缓存文件地址
     */
    public static String getCacheFilePath(String fileName){
        return cacheDir.getAbsolutePath()+pathDiv+fileName;
    }


    /**
     * 判断缓存文件是否存在
     */
    public static boolean isCacheFileExist(String fileName){
        File file = new File(getCacheFilePath(fileName));
        return file.exists();
    }


    /**
     * 将图片存储为文件
     *
     * @param bitmap 图片
     */
    public static String createFile(Bitmap bitmap, String filename){
        File f = new File(cacheDir, filename);
        try{
            if (f.createNewFile()){
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                byte[] bitmapdata = bos.toByteArray();
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
            }
        }catch (IOException e){
            Log.e(TAG,"create bitmap file error" + e);
        }
        if (f.exists()){
            return f.getAbsolutePath();
        }
        return null;
    }

    /**
     * 将数据存储为文件
     *
     * @param data 数据
     */
    public static void createFile(byte[] data,String filename){
        File f = new File(cacheDir, filename);
        try{
            if (f.createNewFile()){
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(data);
                fos.flush();
                fos.close();
            }
        }catch (IOException e){
            Log.e(TAG,"create file error" + e);
        }
    }


    /**
     * 将数据存储为文件
     *
     * @param data 数据
     * @param fileName 文件名
     * @param type 文件类型
     */
    public static boolean createFile(byte[] data, String fileName, String type){
        if (isExternalStorageWritable()){
            File dir = App.getContext().getExternalFilesDir(type);
            if (dir != null){
                File f = new File(dir, fileName);
                try{
                    if (f.createNewFile()){
                        FileOutputStream fos = new FileOutputStream(f);
                        fos.write(data);
                        fos.flush();
                        fos.close();
                        return true;
                    }
                }catch (IOException e){
                    Log.e(TAG,"create file error" + e);
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * 从URI获取图片文件地址
     *
     * @param context 上下文
     * @param uri 文件uri
     */
    @SuppressLint("NewApi")
    public static String getImageFilePath(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        String path = null;
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKitKat){
            if (!isMediaDocument(uri)){
                try{
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };
                    path = getDataColumn(context, contentUri, selection, selectionArgs);
                }catch (IllegalArgumentException e){
                    path = null;
                }
            }
        }
        if (path == null){
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = ((Activity) context).managedQuery(uri, projection, null, null, null);
            if (cursor != null) {
                int column_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }

            path = null;
        }
        return path;
    }


    /**
     * 从URI获取文件地址
     *
     * @param context 上下文
     * @param uri 文件uri
     */
    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }






    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * 判断外部存储是否可用
     *
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        Log.e(TAG, "ExternalStorage not mounted");
        return false;
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * 获取文件名
     *
     * @param path
     *            全路径
     * @return
     */
    public static String getFileName(String path) {
        if (!TextUtils.isEmpty(path)) {
            return path.substring(path.lastIndexOf(File.separator) + 1);
        }
        return "";
    }


    public enum FileType{
        IMG,
        AUDIO,
        VIDEO,
        FILE,
    }


}
