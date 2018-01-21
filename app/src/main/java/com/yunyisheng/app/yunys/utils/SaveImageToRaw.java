package com.yunyisheng.app.yunys.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者：fuduo on 2018/1/19 09:49
 * 邮箱：duoendeavor@163.com
 * 用途：保存文件到缓存目录内/从缓存目录中取文件
 */

public class SaveImageToRaw {

    private Context context;
    private Bitmap bitmap;
    public SaveImageToRaw(Context context){
        this.context = context;
    }
    public void savaImage(String filename,Bitmap bitmap){
        try {
            FileOutputStream fos = context.openFileOutput(filename,context.MODE_PRIVATE);
            //bitmap转文件对象
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Bitmap getImage(String filename){
        try {
            FileInputStream fis = context.openFileInput(filename);
            bitmap = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
