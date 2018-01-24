package com.yunyisheng.app.yunys.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yunyisheng.app.yunys.App;
import com.yunyisheng.app.yunys.R;

import java.io.ByteArrayOutputStream;


/**
 * 作者：fuduo
 * 时间 2017-8-9 11:13
 * 邮箱：duoendeavor@163.com
 * <p>
 * 类的意图：Glide加载图片工具类
 */

public class GlideDownLoadImage {

    private static final String TAG = "ImageLoader";

    private static GlideDownLoadImage instance = new GlideDownLoadImage();

    public static GlideDownLoadImage getInstance() {
        return instance;
    }

    /**
     * @param
     * @name 加载本地图片的重载方法
     * @auhtor fuduo
     * @Data 2018-1-9
     */
    public void loadImage(Context mContext, int resId, ImageView view) {
        Glide.with(mContext)
                .load(resId)
                .placeholder(R.mipmap.moren_new)
                .error(R.mipmap.moren_new)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param
     * @name 加载本地图片的重载方法
     * @auhtor fuduo
     * @Data 2018-1-9
     */
    public void loadImage(Fragment fragment, int resId, ImageView view) {
        Glide.with(fragment)
                .load(resId)
                .placeholder(R.mipmap.moren_new)
                .error(R.mipmap.moren_new)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }


    /**
     * @param
     * @name 加载本地图片的重载方法
     * @auhtor fuduo
     * @Data 2018-1-9
     */
    public void loadImage(Activity activity, int resId, ImageView view) {
        Glide.with(activity)
                .load(resId)
                .placeholder(R.mipmap.moren_new)
                .error(R.mipmap.moren_new)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param
     * @name 加载本地图片的重载方法 此方法慎用
     * @auhtor fuduo
     * @Data 2018-1-9
     */
    public void loadImage(int resId, ImageView view) {
        Glide.with(App.getContext())
                .load(resId)
                .placeholder(R.mipmap.moren_new)
                .error(R.mipmap.moren_new)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * 加载网络图片
     *
     * @param url
     * @param view
     */
    public void loadImage(Context mContext, String url, ImageView view) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.moren_new)
                .error(R.mipmap.moren_new)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param activity
     * @param url
     * @param view
     * @name 加载网络图片重载方法
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadImage(Activity activity, String url, ImageView view) {
        Glide.with(activity)
                .load(url)
                .placeholder(R.mipmap.moren_new)
                .error(R.mipmap.moren_new)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param fragment
     * @param url
     * @param view
     * @name 加载网络图片重载方法
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadImage(Fragment fragment, String url, ImageView view) {
        Glide.with(fragment)
                .load(url)
                .placeholder(R.mipmap.moren_new)
                .error(R.mipmap.moren_new)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }


    /**
     * @param mContext
     * @param resId
     * @param view
     * @name 加载本地圆图
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImage(Context mContext, int resId, ImageView view) {
        Glide.with(mContext)
                .load(resId)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param activity
     * @param resId
     * @param view
     * @name 加载本地圆图
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImage(Activity activity, int resId, ImageView view) {
        Glide.with(activity)
                .load(resId)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideCircleTransform(activity))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }


    /**
     * @param resId
     * @param view
     * @name 加载本地圆图
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImage(int resId, ImageView view) {
        Glide.with(App.getContext())
                .load(resId)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideCircleTransform(App.getContext()))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param url
     * @param view
     * @name 加载网络圆图
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImage(String url, ImageView view) {
        Glide.with(App.getContext())
                .load(url)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideCircleTransform(App.getContext()))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param activity
     * @param url
     * @param view
     * @name 加载网络圆图
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImage(Activity activity, String url, ImageView view) {
        Glide.with(activity)
                .load(url)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideCircleTransform(activity))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param mComtext
     * @param url
     * @param view
     * @name 加载网络圆图
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImage(Context mComtext, String url, ImageView view) {
        Glide.with(mComtext)
                .load(url)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideCircleTransform(mComtext))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param fragment
     * @param url
     * @param view
     * @name 加载网络圆图
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImage(Fragment fragment, String url, ImageView view) {
        Glide.with(fragment)
                .load(url)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideCircleTransform(fragment.getContext()))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param fragment
     * @param resId
     * @param view
     * @param dp
     * @name 加载网络带角度的图片
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImageRole(Fragment fragment, int resId, ImageView view, int dp) {
        Glide.with(fragment)
                .load(resId)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideRoundTransform(fragment.getContext(), dp))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param activity
     * @param resId
     * @param view
     * @param dp
     * @name 加载网络带角度的图片
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImageRole(Activity activity, int resId, ImageView view, int dp) {
        Glide.with(activity)
                .load(resId)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideRoundTransform(activity, dp))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param mContext
     * @param resId
     * @param view
     * @param dp
     * @name 加载网络带角度的图片
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImageRole(Context mContext, int resId, ImageView view, int dp) {
        Glide.with(mContext)
                .load(resId)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideRoundTransform(mContext, dp))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param resId
     * @param view
     * @param dp
     * @name 加载网络带角度的图片
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadCircleImageRole( int resId, ImageView view, int dp) {
        Glide.with(App.getContext())
                .load(resId)
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideRoundTransform(App.getContext(), dp))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @name 加载bitmap带角度的图片
     * @auhtor fuduo
     * @Data 2017-9-5 11:18
     */
    public void loadBitmapCircleImageRole(Context mContext, ImageView view, Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes=baos.toByteArray();

        Glide.with(mContext)
                .load(bytes)
                .centerCrop()
                .placeholder(R.mipmap.moren_head)
                .error(R.mipmap.moren_head)
                .bitmapTransform(new GlideCircleTransform(mContext))
                .into(view);
    }


}
