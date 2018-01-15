package com.yunyisheng.app.yunys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.main.fragement.HomeFragement;
import com.yunyisheng.app.yunys.project.fragement.ProjectFragement;
import com.yunyisheng.app.yunys.schedule.fragement.ScheduleTaskFragement;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateProcessTaskAcitvity;
import com.yunyisheng.app.yunys.userset.fragement.MineFragement;
import com.yunyisheng.app.yunys.utils.DialogManager;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.XRadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.router.Router;

@SuppressLint("SetTextI18n")
public class MainActivity extends BaseActivity implements XRadioGroup.OnCheckedChangeListener {
    HomeFragement homeFragement;
    ProjectFragement projectFragment;
    MineFragement myFragment;
    ScheduleTaskFragement scheduleTaskFragement;
    @BindView(R.id.rb_shouye)
    RadioButton rbShouye;
    @BindView(R.id.rb_xiangmu)
    RadioButton rbXiangmu;
    @BindView(R.id.rb_center)
    RadioButton rbCenter;
    @BindView(R.id.rb_task)
    RadioButton rbTask;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.radioGroup1)
    XRadioGroup radioGroup1;
    private boolean initiated = false;

    private void checkToken() {
        String token = SharedPref.getInstance(context).getString("TOKEN", "");
        if (token.length() == 0) {
            Log.i("TOKEN", "token is empty");
            Router.newIntent(context)
                    .to(LoginActivity.class)
                    .launch();
            this.finish();
        }
    }

    private void initTab() {
        homeFragement = new HomeFragement();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, homeFragement);
        transaction.commit();
    }


    @Override
    public void initView() {
        ButterKnife.bind(this);
        checkToken();
        initTab();
    }

    @Override
    public void initAfter() {

    }

    public void changerTask(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (scheduleTaskFragement==null){
            scheduleTaskFragement=new ScheduleTaskFragement();
        }
        transaction.replace(R.id.content_main, scheduleTaskFragement);
        transaction.commit();
        rbTask.setChecked(true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        radioGroup1.setOnCheckedChangeListener(this);
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onCheckedChanged(XRadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.rb_shouye:
                if (homeFragement == null) {
                    homeFragement = new HomeFragement();
                }
                transaction.replace(R.id.content_main, homeFragement);
                break;
            case R.id.rb_xiangmu:
                if (projectFragment == null) {
                    projectFragment = new ProjectFragement();
                }
                transaction.replace(R.id.content_main, projectFragment);
                break;
            case R.id.rb_center:
                createSelectTaskDialog(MainActivity.this);
                break;
            case R.id.rb_task:
                if (scheduleTaskFragement==null){
                    scheduleTaskFragement=new ScheduleTaskFragement();
                }
                transaction.replace(R.id.content_main, scheduleTaskFragement);
                break;
            case R.id.rb_mine:
                if (myFragment == null) {
                    myFragment = new MineFragement();
                }
                transaction.replace(R.id.content_main, myFragment);
                break;
        }
        transaction.commit();
    }

    /**
     * onWindowFocusChanged回调时，将当前月的种子日期修改为今天
     *
     * @return void
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !initiated) {
            Intent intent = new Intent();
            intent.setAction("WindowFoucuschanged");
            intent.putExtra("code", 200);
            mContext.sendBroadcast(intent);
            initiated=true;
        }

    }

    /**
     * 选择任务对话框
     *
     * @param activity
     * @return
     */
    public void createSelectTaskDialog(final Activity activity) {
        final Dialog mSelectTask = new Dialog(activity, R.style.dialog_bottom_full);
        mSelectTask.setCanceledOnTouchOutside(true);
        mSelectTask.setCancelable(true);
        Window window = mSelectTask.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View view1 = View.inflate(activity, R.layout.dialog_select_task, null);
        RelativeLayout rl_shebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_shebei_task);
        RelativeLayout rl_wrongshebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_wrongshebei_task);

        RelativeLayout rl_liucheng_task = (RelativeLayout) view1
                .findViewById(R.id.rl_liucheng_task);
        RelativeLayout rl_close = (RelativeLayout) view1
                .findViewById(R.id.rl_close);

        rl_shebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Router.newIntent(context)
                        .to(CreateDeviceTaskAcitvity.class)
                        .launch();
                mSelectTask.closeOptionsMenu();

            }
        });
        rl_wrongshebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Router.newIntent(context)
                        .to(CreateNoneDeviceTaskAcitvity.class)
                        .launch();
                mSelectTask.closeOptionsMenu();
            }
        });
        rl_liucheng_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.newIntent(context)
                        .to(CreateProcessTaskAcitvity.class)
                        .launch();
                mSelectTask.closeOptionsMenu();
            }
        });

        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectTask.dismiss();
            }
        });

        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mSelectTask.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        try {
            if (requestCode == 1 && resultCode == RESULT_OK) {
                startPhotoZoom(Uri.fromFile(DialogManager.tempFile), 150);
            } else if (requestCode == 2) {// 相册
                if (intent != null) {
                    Log.i("xiaoqiang", "smdongxi==" + intent.getData());
                    startPhotoZoom(intent.getData(), 150);
                }// 去裁剪
            } else if (requestCode == 3) {
                if (intent != null) {
                    setPicToView(intent);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        Log.i("xiaoqiang", "裁剪");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        LogUtils.i("xiaoqiang", "extras=" + extras);
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Log.i("xiaoqiang", "保存==" + photo);
            if (photo != null) {

//                try {
//                    FileCache.saveMyBitmap(123 + ".png", photo);
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
                try {
//                    setHeadImage(photo, CommonUtils.encodeBase64File(Environment
//                            .getExternalStorageDirectory() + "/yunxue/123.png"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
