package com.yunyisheng.app.yunys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.main.fragement.HomeFragement;
import com.yunyisheng.app.yunys.main.model.WarningMessageEvent;
import com.yunyisheng.app.yunys.main.service.MessageService;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.fragement.ProjectFragement;
import com.yunyisheng.app.yunys.schedule.fragement.ScheduleTaskFragement;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateProcessTaskAcitvity;
import com.yunyisheng.app.yunys.userset.fragement.MineFragement;
import com.yunyisheng.app.yunys.userset.service.UserSetService;
import com.yunyisheng.app.yunys.utils.ActivityManager;
import com.yunyisheng.app.yunys.utils.DialogManager;
import com.yunyisheng.app.yunys.utils.FileCache;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.XRadioGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.router.Router;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    @BindView(R.id.img_oval)
    ImageView imgOval;
    private boolean initiated = false;
    private long exitTime = 0;

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
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        checkToken();
        initTab();
        rbCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSelectTaskDialog(MainActivity.this);
                rbShouye.setChecked(false);
                rbTask.setChecked(false);
                rbXiangmu.setChecked(false);
                rbMine.setChecked(false);
            }
        });
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WarningMessageEvent messageEvent) {
        int size = messageEvent.getSize();
        if (size > 0) {
            imgOval.setVisibility(View.VISIBLE);
        } else {
            imgOval.setVisibility(View.GONE);
        }

    }

    @Override
    public void initAfter() {
        Intent intent=new Intent(MainActivity.this, MessageService.class);
        startService(intent);
    }

    public void changerTask() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (scheduleTaskFragement == null) {
            scheduleTaskFragement = new ScheduleTaskFragement();
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
            case R.id.rb_task:
                if (scheduleTaskFragement == null) {
                    scheduleTaskFragement = new ScheduleTaskFragement();
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
            initiated = true;
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

        mSelectTask.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                rbCenter.setChecked(false);
            }
        });

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
                Uri contentUri = FileProvider.getUriForFile(MainActivity.this, "com.yunyisheng.app.yunys.fileprovider", DialogManager.tempFile);
                startPhotoZoom(contentUri, 150);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
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

                try {
                    FileCache.saveMyBitmap(123 + ".png", photo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    setHeadImage(Environment
                            .getExternalStorageDirectory() + "/yunys/123.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @author fuduo
     * @time 2018/1/23  11:05
     * @describe 上传头像
     */
    private void setHeadImage(String path) {
        LoadingDialog.show(MainActivity.this);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_PATH)
                .build();
        UserSetService service = retrofit.create(UserSetService.class);
        String userid = SharedPref.getInstance(MainActivity.this).getInt("userid", 0) + "";
        String token = SharedPref.getInstance(MainActivity.this).getString("TOKEN", null);
        File file = new File(path);
        //构建body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userId", userid)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("file"), file))
                .build();

        Call<BaseModel> call = service.changeHead(token, "enterpriseUser/upload/pictures", requestBody);
        // 执行
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                String msg = response.body().getRespMsg();
                int code = response.body().getRespCode();
                if (code == 200) {
                    if (myFragment != null) {
                        myFragment.setNewHead();
                    }
                }
                ToastUtils.showToast(msg);
                LogUtils.i("fjdlkf", msg + code);
                LoadingDialog.dismiss(MainActivity.this);
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                LoadingDialog.dismiss(MainActivity.this);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
//				NotificationManager nm =(NotificationManager)BottomMenuActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
//				nm.cancelAll();//清空通知栏
//				Session.onKillProcess();
//				ExampleApplication.exit();
                ActivityManager.getScreenManager().popAllActivity();
//				System.exit(0);

            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
