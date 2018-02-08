package com.yunyisheng.app.yunys.main.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.base.PressionListener;
import com.yunyisheng.app.yunys.main.service.HomeService;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.schedule.model.PositionMessageEvent;
import com.yunyisheng.app.yunys.utils.FileUtil;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.SDCardHelper;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.Util;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fuduo
 * @time 2018/1/16  21:26
 * @describe 发布公告
 */
public class SendNoticeActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_send)
    TextView teSend;
    @BindView(R.id.ed_noticetitle)
    EditText edNoticetitle;
    @BindView(R.id.rl_fujian)
    RelativeLayout rlFujian;
    @BindView(R.id.te_frangesize)
    TextView teFrangesize;
    @BindView(R.id.rl_senfrange)
    RelativeLayout rlSenfrange;
    @BindView(R.id.te_notice_deatil)
    EditText teNoticeDeatil;
    private String pathuri;
    private String selectjson;
    private String title;
    private String content;
    private RequestBody requestBody;
    private List<File> fileList = new ArrayList<>();

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_send_notice;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teSend.setOnClickListener(this);
        rlFujian.setOnClickListener(this);
        rlSenfrange.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.te_send:
                title = edNoticetitle.getText().toString().trim();
                content = teNoticeDeatil.getText().toString().trim();
                if (title != null && !title.equals("") && !title.equals("null")) {
                    if (selectjson != null && !selectjson.equals("") && !selectjson.equals("null")) {
                        if (content != null && !content.equals("") && !content.equals("null")) {
                            sendNotice();
                        } else {
                            ToastUtils.showToast("请输入公告内容");
                        }
                    } else {
                        ToastUtils.showToast("请选择发布范围");
                    }

                } else {
                    ToastUtils.showToast("请输入公告标题");
                }
                break;
            case R.id.rl_fujian:
                requestPermission();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    startActivityForResult(Intent.createChooser(intent, "选择文件上传"), 1);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SendNoticeActivity.this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_senfrange:
                startActivityForResult(new Intent(SendNoticeActivity.this, SelectPeopleActivity.class), 8);
                break;
        }
    }

    private void sendNotice() {
        LoadingDialog.show(SendNoticeActivity.this);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_PATH)
                .build();
        HomeService service = retrofit.create(HomeService.class);
        String token = SharedPref.getInstance(SendNoticeActivity.this).getString("TOKEN", null);
        if (fileList != null && fileList.size() > 0) {

            //构建body
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart("title", title);
            builder.addFormDataPart("content", content);
            builder.addFormDataPart("receiverMap", selectjson);
            for (File file : fileList) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("file"), file);
                builder.addFormDataPart("file", file.getName(), requestBody);
            }
            requestBody = builder.build();
        } else {
            //构建body
            requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("title", title)
                    .addFormDataPart("content", content)
                    .addFormDataPart("receiverMap", selectjson)
                    .build();
        }

        //如果和rxjava1.x , call就换成 Observable
        Call<BaseModel> call = service.sendNotice(token, "announcement/publish", requestBody);
        // 执行
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                String msg = response.body().getRespMsg();
                int code = response.body().getRespCode();
                if (code == 0) {
                    ToastUtils.showToast("发布成功!");
                    EventBus.getDefault().post(new PositionMessageEvent("updatenotice"));
                } else {
                    ToastUtils.showToast("发布失败!");
                }
                LogUtils.i("fjdlkf", msg + code);
                LoadingDialog.dismiss(SendNoticeActivity.this);
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                ToastUtils.showToast("请检查网络设置");
                LogUtils.i("fjdlkf", t.toString());
                LoadingDialog.dismiss(SendNoticeActivity.this);
            }
        });
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        requestRunTimePression(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PressionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onFailure(List<String> failurePression) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("提示")
                        .setMessage("请您去设置中授予内部存储的权限")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                                ComponentName cName = new ComponentName("com.android.phone", "com.android.phone.Settings");
                                intent.setComponent(cName);
                                startActivity(intent);
                            }
                        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 8) {
            int size = data.getIntExtra("size", 0);
            selectjson = data.getStringExtra("selectjson");
            teFrangesize.setText(size + "人");
        }

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String path = FileUtil.getFileName(FileUtil.getFilePath(this, data.getData()));
                    String fileType = FileUtil.getFileType(path);
                    if (fileType.equals("image") || fileType.equals("text")) {
                        Uri uri = data.getData();
                        String realPathFromURI = Util.getFileAbsolutePath(this, uri);
                        long fileSize = 0;
                        try {
                            fileSize = SDCardHelper.getFileSize(new File(realPathFromURI)) / 1024;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LogUtils.i("size", (int) fileSize + "");
                        if (fileSize > 1024 * 2) {
                            ToastUtils.showToast("上传文件不能大于2M");
                        } else {
                            pathuri = realPathFromURI;
                            if (fileList.size() > 3) {
                                ToastUtils.showToast("最多上传三个附件");
                            } else {
                                if (fileList.size() > 0) {
                                    for (int i = 0; i < fileList.size(); i++) {
                                        String absolutePath = fileList.get(i).getAbsolutePath();
                                        if (absolutePath.equals(pathuri)) {
                                            ToastUtils.showToast("已在附件列表中");
                                        } else {
                                            File file = new File(pathuri);
                                            fileList.add(file);
                                            return;
                                        }
                                    }
                                } else {
                                    File file = new File(pathuri);
                                    fileList.add(file);
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

}
