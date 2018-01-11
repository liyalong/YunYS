package com.yunyisheng.app.yunys.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;

import com.yunyisheng.app.yunys.R;

import java.io.File;

/**
 * Created by zhangjinqi on 2017/8/9.
 */

public class DialogManager {

    public static File tempFile = FileCache.getFilePath(FileCache.path, "123.png");
    /**
     * 正在加载对话框
     *
     * @param context
     * @return
     */
    public static Dialog createProgressDialog(Context context) {
//        Dialog mProgressDialog = new Dialog(context, R.style.CustomProgressDialog);
//        mProgressDialog.setContentView(R.layout.progess);
//        mProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return null;
    }

    /**
     * 选择图片对话框
     *
     * @param activity
     * @return
     */
    public static void createPickImageDialog(final Activity activity) {
        final Dialog mShareDialog = new Dialog(activity, R.style.dialog_bottom_full);
        mShareDialog.setCanceledOnTouchOutside(true);
        mShareDialog.setCancelable(true);
        Window window = mShareDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
//        View view1 = View.inflate(activity, R.layout.dialog_pick_image, null);
//        RelativeLayout btn_pick_photo = (RelativeLayout) view1
//                .findViewById(R.id.btn_pick_photo);
//        RelativeLayout btn_cancel = (RelativeLayout) view1
//                .findViewById(R.id.btn_cancel);
//
//        RelativeLayout btn_take_photo = (RelativeLayout) view1
//                .findViewById(R.id.btn_take_photo);
//
//        btn_pick_photo.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                Intent intent = new Intent(Intent.ACTION_PICK, null);
//
//                intent.setDataAndType(
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        "image/*");
//                activity.startActivityForResult(intent, 2);
//                mShareDialog.dismiss();
//
//            }
//        });
//        btn_take_photo.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                // 指定调用相机拍照后照片的储存路径
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(tempFile));
//
//                activity.startActivityForResult(intent, 1);
//                mShareDialog.dismiss();
//            }
//        });
//
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mShareDialog.dismiss();
//            }
//        });
//
//        window.setContentView(view1);
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mShareDialog.show();
    }

}
