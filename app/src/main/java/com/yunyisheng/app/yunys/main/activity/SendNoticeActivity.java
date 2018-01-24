package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.service.HomeService;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.FileUtil;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.SDCardHelper;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.Util;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

                break;
            case R.id.rl_fujian:
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
                break;
        }
    }

    private void sendNotice() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_PATH)
                .build();
        HomeService service = retrofit.create(HomeService.class);
        File file = new File(pathuri);
        //构建body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("title", "")
                .addFormDataPart("content", "")
                .addFormDataPart("receiverMap", "")
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("file"), file))
                .build();

        //如果和rxjava1.x , call就换成 Observable
        Observable<ResponseBody> call = service.sendNotice("fairyland-system/announcement/publish", requestBody);
        // 执行
        call.subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody value) {
                try {
                    String str = value.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
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
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

}
