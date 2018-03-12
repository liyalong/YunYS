package com.yunyisheng.app.yunys.project.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.base.PressionListener;
import com.yunyisheng.app.yunys.main.service.HomeService;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.present.RenwuFankuiDetailPresent;
import com.yunyisheng.app.yunys.schedule.model.RenWuFanKuiDetailBean;
import com.yunyisheng.app.yunys.utils.DialogManager;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.Util;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yunyisheng.app.yunys.utils.CommonUtils.stringtoBitmap;

/**
 * @author fuduo
 * @time 2018/2/1  1818:33* @describe
 */
public class RenwuFankuiFormActivity extends BaseActivity<RenwuFankuiDetailPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.line_all)
    LinearLayout lineAll;
    private List<RenWuFanKuiDetailBean.RespBodyBean.FeedbackItemBean> feedbackItemlist = new ArrayList<>();
    private int taskid;
    private String kongjianid = "feedbackItemId";
    private String valuestr = "feedbackVal";
    private MyHandler handler = new MyHandler(this);
    private String imgstr;
    private String projectId;
    private int seetype;
    private ImageView image;

    @Override
    public void initView() {
        requestPermission();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        taskid = intent.getIntExtra("taskid", 0);
        projectId = intent.getStringExtra("projectId");
        seetype = intent.getIntExtra("type", 0);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAfter() {
        getP().getScheduleDetail(projectId, taskid);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_renwu_fankui_form;
    }

    @Override
    public RenwuFankuiDetailPresent bindPresent() {
        return new RenwuFankuiDetailPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

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
                builder.show();
            }
        });
    }

    public void setRenwuFormDetail(RenWuFanKuiDetailBean renWuFanKuiDetailBean) {
        RenWuFanKuiDetailBean.RespBodyBean respBody = renWuFanKuiDetailBean.getRespBody();
        RenWuFanKuiDetailBean.RespBodyBean.TaskBean task = respBody.getTask();
//        int taskId = task.getTaskId();
        String releaseName = task.getReleaseName();
        teTitle.setText(releaseName);
        List<RenWuFanKuiDetailBean.RespBodyBean.FeedbackItemBean> feedbackItem = respBody.getFeedbackItem();
        if (feedbackItem != null && feedbackItem.size() > 0) {
            feedbackItemlist.addAll(feedbackItem);
            initUi();
        }
    }

    private void initUi() {
        for (int i = 0; i < feedbackItemlist.size(); i++) {
            RenWuFanKuiDetailBean.RespBodyBean.FeedbackItemBean feedbackItemBean = feedbackItemlist.get(i);
            int id = feedbackItemBean.getFeedbackItemId();
            int type = feedbackItemBean.getFeedbackType();
            String feedbackVal = feedbackItemBean.getFeedbackVal();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    1);
            lpview.setMargins(0, 10, 0, 0);
            LinearLayout.LayoutParams imgview = new LinearLayout.LayoutParams(200,
                    200);
            LinearLayout.LayoutParams bigimgview = new LinearLayout.LayoutParams(500,
                    600);
            if (type == 1) {
                TextView name = new TextView(this);
                name.setPadding(0, 10, 0, 0);
                name.setText(feedbackItemBean.getFeedbackName());
                name.setTextColor(getResources().getColor(R.color.color_333));
                name.setTextSize(15);
                lineAll.addView(name);

                if (seetype==2){
                    TextView namevalue = new TextView(this);
                    namevalue.setPadding(0, 10, 0, 0);
                    namevalue.setTextColor(getResources().getColor(R.color.color_333));
                    namevalue.setTextSize(13);
                    namevalue.setText(feedbackVal);
                    lineAll.addView(namevalue);
                }else {
                    EditText editText = new EditText(this);
                    editText.setId(id);
                    editText.setTextColor(getResources().getColor(R.color.color_666));
                    editText.setTextSize(14);
                    editText.setBackground(null);
                    editText.setLayoutParams(lp);
                    lineAll.addView(editText);
                }
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (type == 2) {
                TextView name = new TextView(this);
                name.setPadding(0, 10, 0, 0);
                name.setText(feedbackItemBean.getFeedbackName());
                name.setTextColor(getResources().getColor(R.color.color_333));
                name.setTextSize(15);
                lineAll.addView(name);

                if (seetype==2){
                    TextView namevalue = new TextView(this);
                    namevalue.setPadding(0, 10, 0, 0);
                    namevalue.setTextColor(getResources().getColor(R.color.color_333));
                    namevalue.setTextSize(13);
                    namevalue.setText(feedbackVal);
                    lineAll.addView(namevalue);
                }else {
                    RadioGroup radioGroup = new RadioGroup(this);
                    radioGroup.setLayoutParams(lp);
                    radioGroup.setId(id);
                    radioGroup.setPadding(0, 10, 0, 0);
                    radioGroup.setOrientation(LinearLayout.VERTICAL);
                    List<RenWuFanKuiDetailBean.RespBodyBean.Valueitem> model = feedbackItemBean.getModel();
                    if (model.size() < 1) return;
                    for (int j = 0; j < model.size(); j++) {
                        String valuetext = model.get(j).getDynamic_type_name();
                        RadioButton radioButton = new RadioButton(this);
                        radioButton.setTextColor(getResources().getColor(R.color.color_666));
                        radioButton.setTextSize(14);
                        radioButton.setId(Integer.parseInt(id + "1" + j));
                        radioButton.setText(valuetext);
                        if (seetype == 2) {
                            radioButton.setFocusable(false);
                            radioButton.setClickable(false);
                        }
                        radioGroup.addView(radioButton);
                    }
                    lineAll.addView(radioGroup);
                }
            } else if (type == 3) {
                TextView name = new TextView(this);
                name.setPadding(0, 10, 0, 0);
                name.setText(feedbackItemBean.getFeedbackName());
                name.setTextColor(getResources().getColor(R.color.color_333));
                name.setTextSize(15);
                lineAll.addView(name);
                if (seetype==2){
                    TextView namevalue = new TextView(this);
                    namevalue.setPadding(0, 10, 0, 0);
                    namevalue.setTextColor(getResources().getColor(R.color.color_333));
                    namevalue.setTextSize(13);
                    namevalue.setText(feedbackVal);
                    lineAll.addView(namevalue);
                }else {
                    LinearLayout l = new LinearLayout(this);
//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    l.setId(id);
                    l.setOrientation(LinearLayout.VERTICAL);
                    List<RenWuFanKuiDetailBean.RespBodyBean.Valueitem> model = feedbackItemBean.getModel();
                    if (model.size() < 1) return;
                    for (int j = 0; j < model.size(); j++) {
                        String valuetext = model.get(j).getDynamic_type_name();
                        CheckBox checkBox = new CheckBox(this);
                        checkBox.setTextColor(getResources().getColor(R.color.color_666));
                        checkBox.setTextSize(14);
                        checkBox.setId(Integer.parseInt(id + "2" + j));
                        // checkBox.setButtonDrawable(getResources().getDrawable(R.drawable.checkbox_selector));
                        checkBox.setText(valuetext);
                        if (seetype == 2) {
                            checkBox.setClickable(false);
                        }
                        l.addView(checkBox);
                    }
                    lineAll.addView(l);
                }
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);

            } else if (type == 4) {
                try {
                    if (seetype==2){
                        ImageView imageView = new ImageView(this);
                        imageView.setLayoutParams(bigimgview);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        if (feedbackVal!=null&&!feedbackVal.equals("")){
                            Bitmap bitmap = stringtoBitmap(feedbackVal);
                            imageView.setImageBitmap(bitmap);
                        }
                        lineAll.addView(imageView);
                    }else {
                        final JSONObject jsonObject = new JSONObject();
                        int imgid = feedbackItemBean.getFeedbackItemId();
                        jsonObject.put(kongjianid, imgid + "");
                        jsonObject.put(valuestr, null);
                        TextView name = new TextView(this);
                        name.setPadding(0, 10, 0, 0);
                        name.setText(feedbackItemBean.getFeedbackName());
                        name.setTextColor(getResources().getColor(R.color.color_333));
                        name.setTextSize(15);
                        lineAll.addView(name);
                        final ImageView imageView = new ImageView(this);
                        imageView.setLayoutParams(imgview);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setBackgroundResource(R.mipmap.put_img);

                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    imgstr = jsonObject.toString();
                                    image=imageView;
                                    DialogManager.createPickImageDialog(RenwuFankuiFormActivity.this);
                                }
                            });
                        lineAll.addView(imageView);
                    }
                } catch (Exception e) {

                }
            }
        }

        if (seetype == 1) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 20, 0, 0);
            Button button = new Button(this);
            button.setLayoutParams(layoutParams);
            button.setText("提交");
            button.setBackgroundResource(R.drawable.btn_anpai_work);
            button.setTextColor(getResources().getColor(R.color.white));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler.sendEmptyMessage(0);
                }
            });
            lineAll.addView(button);
        }


    }

    class MyHandler extends Handler {

        WeakReference<RenwuFankuiFormActivity> activityWeakReference;

        public MyHandler(RenwuFankuiFormActivity renwuFankuiFormActivity) {
            activityWeakReference = new WeakReference<RenwuFankuiFormActivity>(renwuFankuiFormActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                List<Map> l = new ArrayList();
                for (int i = 0; i < feedbackItemlist.size(); i++) {
                    Map<String, String> map = new HashMap<>();
                    RenWuFanKuiDetailBean.RespBodyBean.FeedbackItemBean feedbackItemBean = feedbackItemlist.get(i);
                    int type = feedbackItemBean.getFeedbackType();
                    int id = feedbackItemBean.getFeedbackItemId();
                    if (type == 1) {
                        EditText editText = findViewById(id);
                        map.put(kongjianid, id + "");
                        map.put(valuestr, editText.getText().toString().trim());
                        l.add(map);
                    } else if (type == 2) {
                        RadioGroup radioGroup = findViewById(id);
                        map.put(kongjianid, id + "");
                        RadioButton r = findViewById(radioGroup.getCheckedRadioButtonId());
                        if (r != null) {
                            String val = r.getText().toString();
                            map.put(valuestr, val);
                            l.add(map);
                        } else {
                            ToastUtils.showToast("您还有未选择的选项");
                            return;
                        }
                    } else if (type == 3) {
                        List<RenWuFanKuiDetailBean.RespBodyBean.Valueitem> model = feedbackItemBean.getModel();
                        map.put(kongjianid, id + "");
                        String val = "";
                        if (model.size() < 1) return;
                        for (int j = 0; j < model.size(); j++) {
                            CheckBox cb = findViewById(Integer.parseInt(id + "2" + j));
                            if (cb.isChecked()) {
                                val += cb.getText().toString() + ",";
                            }
                        }
                        if (!val.equals("")) {
                            if (val.lastIndexOf(",") == val.length() - 1)
                                val = val.substring(0, val.length() - 1);
                            map.put(valuestr, val);
                            l.add(map);
                        } else {
                            ToastUtils.showToast("您还有未选择的选项");
                            return;
                        }
                    }
                }
                String str = JSONArray.toJSONString(l);
                LogUtils.i("gdsgfdsgfg", str);
                getP().getScheduleDetail(taskid, str);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        try {
            if (requestCode == 1 && resultCode == RESULT_OK) {
                Uri contentUri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    contentUri = FileProvider.getUriForFile(RenwuFankuiFormActivity.this, "com.yunyisheng.app.yunys.fileprovider", DialogManager.tempFile);
                }else {
                    contentUri=Uri.fromFile(DialogManager.tempFile);
                }
//                String realPathFromURI = Util.getFileAbsolutePath(this, contentUri);
//                File file = new File(realPathFromURI);
                putPic(DialogManager.tempFile,contentUri);
            } else if (requestCode == 2) {// 相册
                if (intent != null) {
                    Log.i("xiaoqiang", "smdongxi==" + intent.getData());
                    Uri uri = intent.getData();
                    String realPathFromURI = Util.getFileAbsolutePath(this, uri);
                    File file = new File(realPathFromURI);
                    putPic(file,uri);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * @author fuduo
     * @time 2018/2/1  18:22
     * @describe 上传图片
     */
    private void putPic(File file, final Uri uri) {
        LoadingDialog.show(RenwuFankuiFormActivity.this);
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).
                writeTimeout(60, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_PATH)
                .build();
        HomeService service = retrofit.create(HomeService.class);
        String token = SharedPref.getInstance(RenwuFankuiFormActivity.this).getString("TOKEN", null);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("taskId", taskid + "")
                .addFormDataPart("feedbackStr", imgstr)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("file"), file))
                .build();

        //如果和rxjava1.x , call就换成 Observable
        Call<BaseModel> call = service.sendNotice(token, "task/executepic", requestBody);
        // 执行
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                String msg = response.body().getRespMsg();
                int code = response.body().getRespCode();
                if (code == 0) {
                    ToastUtils.showToast("上传成功!");
                    image.setImageURI(uri);
                } else {
                    ToastUtils.showToast("上传失败!");
                }
                LogUtils.i("fjdlkf", msg + code);
                LoadingDialog.dismiss(RenwuFankuiFormActivity.this);
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                ToastUtils.showToast("请检查网络设置");
                LoadingDialog.dismiss(RenwuFankuiFormActivity.this);
            }
        });
    }

}
