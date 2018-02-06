package com.yunyisheng.app.yunys.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;

import com.yunyisheng.app.yunys.utils.encrypt.MD5;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者:fuDuo
 * 时间：2017/8/19 16:15
 * 邮箱:18610922052@163.com
 * 类的意图:工具
 */

public class CommonUtils {

    public static ConnectivityManager manager;
    static String ss[] = new String[] { "个", "十", "百", "千", "万", "十", "百", "千", "亿" };
    static char[] numArray = {'零','一','二','三','四','五','六','七','八','九'};

//    public static HttpHandler<File> handler = null;
//    public static FinalHttp fh = new FinalHttp();

    /**
     * 没有网络
     */
    public static final int NETWORKTYPE_INVALID = 0;
    /**
     * wap网络
     */
    public static final int NETWORKTYPE_SJ = 1;
    /**
     * wifi网络
     */
    public static final int NETWORKTYPE_WIFI = 4;
    private static int mNetWorkType;


    /**
     * 获取网络状态，wifi,wap,2g,3g.
     *
     * @param context 上下文
     * @return
     */
    public static int getNetWorkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                mNetWorkType = NETWORKTYPE_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                mNetWorkType = NETWORKTYPE_SJ;
            }
        } else {
            mNetWorkType = NETWORKTYPE_INVALID;
        }
        return mNetWorkType;
    }

    /**
     * 将整数转换成汉字数字
     * @param num 需要转换的数字
     * @return 转换后的汉字
     */
    public static String formatInteger(int num) {
        String s = String.valueOf(num);
        System.out.println(s);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            String index = String.valueOf(s.charAt(i));
            sb = sb.append(numArray[Integer.parseInt(index)]);
        }
//        String sss = String.valueOf(sb);
//        int i = 0;
//        for (int j = sss.length(); j > 0; j--) {
//            sb = sb.insert(j, ss[i++]);
//        }
        return sb.toString();
    }

    /**
     * @Author :付铎
     * @DATE :2017/11/2 19:26
     * @Params 是否输入的为emoji表情
     */
    /**
     * 判断是否是Emoji * @param codePoint 比较的单个字符 * @return
     */
    public static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * @Author :付铎
     * @DATE :2017/11/2 19:33
     * @Params Edittext表情过滤器
     */
    public static InputFilter emojiFilter = new InputFilter() {
        Pattern emoji = Pattern.compile(
                "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                   int dstart,
                                   int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                return "";
            }
            return null;
        }
    };

    /**
     * 检查当前网络是否可用
     *
     * @param
     * @return
     */
    public static boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @Author :付铎
     * @DATE :2017/10/30 11:50
     * @Params 设置是否隐藏状态栏
     */
    public static void setIsfull(Activity activity, boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(lp);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attr);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 显示隐藏状态栏，全屏不变，只在有全屏时有效
     *
     * @param enable
     */
    public static void setStatusBarVisibility(Activity activity, boolean enable) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        if (enable) {
            lp.flags |= WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
        } else {
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
        activity.getWindow().setAttributes(lp);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    /**
     * @Author :付铎
     * @DATE :2017/10/24 14:09
     * @Params 判断SD卡路径是否存在
     */
    public static boolean initDownPath(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断某个Activity 界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     * @return
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;

    }

    /**
     * @Author :付铎
     * @DATE :2017/9/18 14:00
     * @Params 字符串转换为时间戳
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * @Author :付铎
     * @DATE :2017/9/21 10:32
     * @Params //把日期转为字符串
     */
    public static String ConverToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return df.format(date);
    }

    /**
     * @Author :付铎
     * @DATE :2017/9/21 10:32
     * @Params //把日期转为字符串
     */
    public static String ConverToStringminute(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(date);
    }

    /**
     * @Author :付铎
     * @DATE :2017/9/21 10:32
     * @Params //把字符串转为日期
     */
    public static Date ConverToDate(String strDate) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(strDate);
    }

    /**
     * @author fuduo
     * @time 2018/1/29  19:04
     * @describe 获取一天的开始时间
     */
    public static long getDayStartTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date=calendar.getTime();
        String s = ConverToStringminute(date);
        long stringToDate = getStringToDate(s, "yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间："+s);
        return stringToDate;
    }

    /**
     * @author fuduo
     * @time 2018/1/29  19:04
     * @describe 获取一天的结束时间
     */
    public static long getDayEndTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date=calendar.getTime();
        String s = ConverToStringminute(date);
        long stringToDate = getStringToDate(s, "yyyy-MM-dd HH:mm:ss");
        System.out.println("结束时间："+s);
        return stringToDate;
    }

    /**
     *  @author fuduo
     *  @time 2018/1/29  20:07
     *  @describe 获取某天的开始时间
     */
    public static long getOtherStarttime(Date date){
        date.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date time = calendar.getTime();
        String s = ConverToStringminute(time);
        long stringToDate = getStringToDate(s, "yyyy-MM-dd HH:mm:ss");
        System.out.println("结束时间："+s);
        return stringToDate;
    }

    /**
     *  @author fuduo
     *  @time 2018/1/29  20:07
     *  @describe 获取某天的结束时间
     */
    public static long getOtherEndtime(Date date){
        date.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        String s = ConverToStringminute(time);
        long stringToDate = getStringToDate(s, "yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间："+s);
        return stringToDate;
    }


    /**
     * @Author :付铎
     * @DATE :2017/9/18 14:00
     * @Params 时间戳转换为字符串
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * @Author :付铎
     * @DATE :2017/9/18 17:13
     * @Params URLEncoded
     */
    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            LogUtils.i("strfdfdf", "toURLEncoded error:" + paramString);
            return "";
        }

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
            LogUtils.i("toURLEncoded error:" + paramString, localException.toString());
        }

        return "";
    }

    /**
     * @Author :付铎
     * @DATE :2017/9/20 14:18
     * @Params sd卡地址转base64
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        Log.e("zsy", "base64=" + Base64.encodeToString(buffer, Base64.DEFAULT));
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }

    /**
     * @Author :付铎
     * @DATE :2017/9/20 14:18
     * @Params bitmap转base64
     */

    public static String Bitmap2StrByBase64(Bitmap bit) {
        String des = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bit.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            byte[] buffer = out.toByteArray();
            byte[] encode = Base64.encode(buffer, Base64.DEFAULT);
            des = new String(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return des;
    }

    /**
     * @author fuduo
     * @time 2018/2/5  22:08
     * @describe 保存字符串为文件
     */
    public static void saveFile(String str,String name) {
        String filePath = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) { // SD卡根目录的hello.text
            filePath = FileCache.path + name+".txt";
        } else {  // 系统下载缓存根目录的hello.text
            filePath =  FileCache.path + name+ ".txt";
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /**
         * 根据byte数组生成文件
         *
         * @param bytes
         *            生成文件用到的byte数组
         */
    private void createFileWithByte(byte[] bytes) {
        /**
         * 创建File对象，其中包含文件所在的目录以及文件的命名
         */
        File file = new File(Environment.getExternalStorageDirectory(),
                "123.jpg");
        // 创建FileOutputStream对象
        FileOutputStream outputStream = null;
        // 创建BufferedOutputStream对象
        BufferedOutputStream bufferedOutputStream = null;
        try {
            // 如果文件存在则删除
            if (file.exists()) {
                file.delete();
            }
            // 在文件系统中根据路径创建一个新的空文件
            file.createNewFile();
            // 获取FileOutputStream对象
            outputStream = new FileOutputStream(file);
            // 获取BufferedOutputStream对象
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            // 往文件所在的缓冲输出流中写byte数据
            bufferedOutputStream.write(bytes);
            // 刷出缓冲输出流，该步很关键，要是不执行flush()方法，那么文件的内容是空的。
            bufferedOutputStream.flush();
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
        } finally {
            // 关闭创建的流对象
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * @Author :付铎
     * @DATE :2017/9/20 14:17
     * @Params 将字符串转换成Bitmap类型
     */
    public static Bitmap stringtoBitmap(String string) {

        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * @param context
     * @return
     * @throws Exception 获取当前缓存
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * @param context 删除缓存
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            int size = 0;
            if (children != null) {
                size = children.length;
                for (int i = 0; i < size; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }

        }
        if (dir == null) {
            return true;
        } else {

            return dir.delete();
        }
    }

    // 获取文件
    // Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/
    // 目录，一般放一些长时间保存的数据
    // Context.getExternalCacheDir() -->
    // SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            int size2 = 0;
            if (fileList != null) {
                size2 = fileList.length;
                for (int i = 0; i < size2; i++) {
                    // 如果下面还有文件
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     * 计算缓存的大小
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            // return size + "Byte";
            return "0KB";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 获取文件选择器选中的文件路径
     * @param context
     * @param uri
     * @return
     */
    public static String getPath(Context context, Uri uri) {

        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection,null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }

        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    /**
     * 获取指定文件大小
     *
     * @param
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }


    /**
     * 微信支付生成随机字符串noncestr
     *
     * @return
     */
    public static String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    public static String genOutTradNo() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    public static long getTotalInternalMemorySize(){
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks*blockSize;
    }

    /**
     * 得到内置存储空间的总容量
     * @param context
     * @return
     */
    public static String getInternalToatalSpace(Context context){
        String path = Environment.getDataDirectory().getPath();
        Log.d("rongliang","root path is "+path);
        StatFs statFs = new StatFs(path);
        long blockSize = statFs.getBlockSize();
        long totalBlocks = statFs.getBlockCount();
        long availableBlocks = statFs.getAvailableBlocks();
        long useBlocks  = totalBlocks - availableBlocks;

        long rom_length = totalBlocks*blockSize;

        return Formatter.formatFileSize(context,rom_length);
    }

    /**
     * 微信支付生成时间戳timestamp
     *
     * @return
     */
    public static long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }


    /**
     * dialog
     */
    public static boolean progressShow;
    public static ProgressDialog pd = null;

    public static void showProgressDialog(Context context, String message) {
        if (pd == null) {
            progressShow = true;
            pd = new ProgressDialog(context);
            pd.setCanceledOnTouchOutside(false);
            pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    progressShow = false;
                }
            });
            pd.setMessage(message);
            pd.show();
        }

    }

    public static void dissProgressDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    private static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

//    public static void downLoadVedio(final CaCheIngBean bean, final Context context) {
//        if (DBOhelpe.selectOnedownCaChe(context, "0").size() == 0) {
//            if (DBOhelpe.selectOneCaChe(context, bean.getId()).size() == 0) {
//                String url = bean.getWangluoadress();
//                int i = url.indexOf("videos/");
//                Time time = new Time("GMT+8");
//                time.setToNow();
//                int year = time.year;
//                int month = time.month;
//                int day = time.monthDay;
//                int minute = time.minute;
//                int hour = time.hour;
//                int sec = time.second;
//                String bendi = "" + year + month + day + minute + hour + sec
//                        + ".mp4";
//                String wangluo = ConstantValue.DownSHIPIN
//                        + URLEncoder.encode(url.substring(i + 7, url.length()));
//                bean.setWangluoadress(wangluo);
//                bean.setBendiadress(bendi);
//                bean.setIsFinish("0");
//                DBOhelpe.addCaChe(context, bean);
//                // 调用download方法开始下载
////                handler = fh.download(wangluo, new AjaxParams(),
////                        AppUtils.getMyCacheDir("") + bendi, true,
////                        new AjaxCallBack() {
////
////                            @Override
////                            public void onLoading(long count, long current) {
////                                Log.e("zsy", "下载进度：" + current + "/" + count);
////                            }
////
////                            @Override
////                            public void onSuccess(Object t) {
////                                if (t instanceof File) {
////                                    File f = (File) t;
////                                    DBOhelpe.upDateCaChe(context, "1", bean.getId());
////                                }
////                            }
////                        });
//
//                OkHttpUtils//
//                        .get()//
//                        .url(url)//
//                        .build()//
//                        .execute(new FileCallBack(ExampleApplication.getInstance().lrcPath, System.currentTimeMillis() + ".lrc")//文件路径，文件名
//                        {
//
//                            @Override
//                            public void onBefore(Request request, int id) {
//
//                            }
//
//                            @Override
//                            public void inProgress(float progress, long total, int id) {
//                                Log.e("inProgress", "inProgress :" + (int) (100 * progress));
//                                Log.e("zsy", "下载进度：" + progress + "/" + total);
//                            }
//
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//                                Log.e("onError", "onError :" + e.getMessage());
//                            }
//
//                            @Override
//                            public void onResponse(File file, int id) {
//                                File f = file;
//                                DBOhelpe.upDateCaChe(context, "1", bean.getId());
//                                Log.e("onResponse", "onResponse :" + file.getAbsolutePath());
//                            }
//                        });
//            } else {
//                Util.showToast(context, "无需再次下载");
//            }
//        } else {
//            Util.showToast(context, "请等待下载任务完成");
//        }
//    }

//    /**
//     * 判断3g网络是否可以下载图片
//     *
//     * @param context
//     * @return
//     */
//    public static boolean isNetWorkConnected(Context context) {
//        boolean iswifi = false;
//        ;
//        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
//        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
//        if (gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING) {
//            if (ConstantValue.isWwifi) {
//                iswifi = true;
//            } else {
//                iswifi = false;
//            }
//        }
//        //判断为wifi状态下才加载广告，如果是GPRS手机网络则不加载！
//        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
//            iswifi = true;
//        }
//        return iswifi;
//    }


    public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

}
