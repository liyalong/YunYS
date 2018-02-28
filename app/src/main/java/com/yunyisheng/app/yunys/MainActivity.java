package com.yunyisheng.app.yunys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
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

import com.yalantis.ucrop.UCrop;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.main.fragement.HomeFragement;
import com.yunyisheng.app.yunys.main.model.WarningMessageEvent;
import com.yunyisheng.app.yunys.main.roadcastReceiver.NotificationReceiver;
import com.yunyisheng.app.yunys.main.service.MessageService;
import com.yunyisheng.app.yunys.mqtt.MQTTMessage;
import com.yunyisheng.app.yunys.mqtt.MQTTService;
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
import java.util.Timer;
import java.util.TimerTask;

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

import static com.yunyisheng.app.yunys.utils.DialogManager.tempFile;

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
    private NotificationManager notificationManager;
    private PendingIntent pIntent;

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
        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        checkToken();
        initTab();
        rbCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSelectTaskDialog(MainActivity.this);
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

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMqttEvent(MQTTMessage mqttMessage) {
        LogUtils.i("mqttmessage", mqttMessage.getMessage());
        setNotification(mqttMessage.getMessage());
    }

    private void setNotification(String string) {
        //此类通知在Android 5.0以上版本才会有横幅有效！
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {//小于5.0
                Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
                broadcastIntent.putExtra("str",string);
                PendingIntent pendingIntent = PendingIntent. getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager notificationManager = (NotificationManager) MainActivity.this.getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.tubiao);
                builder.setContentTitle(string);
                builder.setContentText("我是默认显示的消息，我进来了");
                builder.setWhen(System.currentTimeMillis());
                builder.setTicker("接受到一条信息");
                builder.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
                builder.setAutoCancel(true);
                builder.setContentIntent(pendingIntent);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    Notification notification = builder.build();
                    notificationManager.notify(1, notification);
                }
        } else {
            //为了版本兼容  选择V7包下的NotificationCompat进行构造
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("横幅通知");
            builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
            builder.setDefaults(NotificationCompat.DEFAULT_ALL);

            builder.setSmallIcon(R.mipmap.tubiao);
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.tubiao));
            Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
            broadcastIntent.putExtra("str",string);
            pIntent = PendingIntent.getActivity(context, 1, broadcastIntent, 0);
            builder.setContentIntent(pIntent);
            builder.setFullScreenIntent(pIntent, true);
            builder.setAutoCancel(true);
            Notification notification = builder.build();
            notificationManager.notify(1, notification);//注意这里 1 为当前通知栏的 Id 号，和 Fragment 设置 Id 是一样的

            // 设置 heads-up 消失任务
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("start the cancel task....");
                    notificationManager.cancel(1); // 根据之前设置的通知栏 Id 号，让相关通知栏消失掉
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 2000);
        }

    }

    @Override
    public void initAfter() {
        Intent intent = new Intent(MainActivity.this, MessageService.class);
        startService(intent);
        startService(new Intent(this, MQTTService.class));
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
                Uri contentUri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    contentUri = FileProvider.getUriForFile(MainActivity.this, "com.yunyisheng.app.yunys.fileprovider", DialogManager.tempFile);
                    startPhotoZoom(contentUri, 150);
                } else {
                    contentUri = Uri.fromFile(DialogManager.tempFile);
                    cropRawPhoto(contentUri);
                }

            } else if (requestCode == 2) {// 相册
                if (intent != null) {
                    Log.i("xiaoqiang", "smdongxi==" + intent.getData());
                    cropRawPhoto(intent.getData());
                }// 去裁剪
            } else if (requestCode == UCrop.REQUEST_CROP) {
                try {
                    setHeadImage(Environment
                            .getExternalStorageDirectory() + "/yunys/123.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
     * 使用UCrop进行图片剪裁
     *
     * @param uri
     */
    public void cropRawPhoto(Uri uri) {

        UCrop.Options options = new UCrop.Options();
        // 修改标题栏颜色
        options.setToolbarColor(getResources().getColor(R.color.logoutColor));
        // 隐藏底部工具
        options.setHideBottomControls(true);
        // 图片格式
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        // 设置图片压缩质量
        options.setCompressionQuality(100);
        // 是否让用户调整范围(默认false)，如果开启，可能会造成剪切的图片的长宽比不是设定的
        // 如果不开启，用户不能拖动选框，只能缩放图片
        options.setFreeStyleCropEnabled(true);
        // 设置图片压缩质量
        options.setCompressionQuality(100);
        // 圆
        options.setCircleDimmedLayer(true);
        // 不显示网格线
        options.setShowCropGrid(false);

        // 设置源uri及目标uri
        UCrop.of(uri, Uri.fromFile(tempFile))
                // 长宽比
                .withAspectRatio(1, 1)
                // 图片大小
                .withMaxResultSize(150, 150)
                // 配置参数
                .withOptions(options)
                .start(this);
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
                if (code == 0) {
                    Intent intent = new Intent();
                    intent.setAction("action");
                    intent.putExtra("data", "changehead");
                    sendBroadcast(intent);//发送普通广播
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
