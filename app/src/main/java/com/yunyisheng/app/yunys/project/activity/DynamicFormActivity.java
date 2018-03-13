package com.yunyisheng.app.yunys.project.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
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

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.bean.UploadDynamicFormImageBean;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.schedule.model.SeeScheduleDetailBean;
import com.yunyisheng.app.yunys.schedule.present.ScheduleDetailPresent;
import com.yunyisheng.app.yunys.schedule.service.ScheduleService;
import com.yunyisheng.app.yunys.utils.DialogManager;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.Util;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yunyisheng.app.yunys.utils.CommonUtils.stringtoBitmap;

public class DynamicFormActivity extends BaseActivity<ScheduleDetailPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.line_all)
    LinearLayout lineAll;
    private String scheduleid;
    private int type;
    private int userId;
    private String kongjianid = "fieldId";
    private String valuestr = "value";
    private List<ScheduleDetailBean.RespBodyBean.FormBean.DataBean> alldataBeanList = new ArrayList<>();
    private List<SeeScheduleDetailBean.RespBodyBean.ForminstanceBean.FormBean.DataBean> formalldataBeanList = new ArrayList<>();
    private MyHandler handler = new MyHandler(this);
    private int taskId;
    private String releaseFormId;
    private int seetype;
    private ImageView image;
    private int uploadimageid;
    private String uploadimageuuid;
    private ImageView imageView;
    private String imageurl;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", 0);
        type = intent.getIntExtra("type", 0);
        seetype = intent.getIntExtra("othertype", 0);
        scheduleid = intent.getStringExtra("scheduleid");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAfter() {
        if (userId == 0) {
            if (seetype == 2) {
                getP().getMineScheduleFormDetail(scheduleid, type);
            } else {
                getP().getMineScheduleDetail(scheduleid, type);
            }
        } else {
            if (seetype == 2) {
                getP().getOtherScheduleFormDetail(userId, scheduleid, type);
            } else {
                getP().getOtherScheduleDetail(userId, scheduleid, type);
            }
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_dynamic_form;
    }

    @Override
    public ScheduleDetailPresent bindPresent() {
        return new ScheduleDetailPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public void setFormDetail(ScheduleDetailBean scheduleDetailBean) {
        ScheduleDetailBean.RespBodyBean respBody = scheduleDetailBean.getRespBody();
        ScheduleDetailBean.RespBodyBean.TaskBean task = respBody.getTask();
        teTitle.setText(task.getReleaseName());
        taskId = task.getTaskId();
        releaseFormId = task.getReleaseFormId();
        List<ScheduleDetailBean.RespBodyBean.FormBean.DataBean> dataBeanList = respBody.getForm().getData();
        if (dataBeanList.size() > 0) {
            alldataBeanList.addAll(dataBeanList);
            initUi();
        }
    }

    public void setScheduleFormDetail(SeeScheduleDetailBean scheduleFormDetail) {
        SeeScheduleDetailBean.RespBodyBean respBody = scheduleFormDetail.getRespBody();
        SeeScheduleDetailBean.RespBodyBean.TaskBean task = respBody.getTask();
        teTitle.setText(task.getReleaseName());
        taskId = task.getTaskId();
        releaseFormId = task.getReleaseFormId();
        SeeScheduleDetailBean.RespBodyBean.ForminstanceBean forminstance = respBody.getForminstance();
        List<SeeScheduleDetailBean.RespBodyBean.ForminstanceBean.FormBean.DataBean> data = forminstance.getForm().getData();
        if (data.size() > 0) {
            List<SeeScheduleDetailBean.RespBodyBean.ForminstanceBean.DataListBean> dataList = forminstance.getDataList();
            formalldataBeanList.addAll(data);
            if (dataList.size() > 0) {
                initFormUi(dataList);
            }
        }
    }

    private void initFormUi(List<SeeScheduleDetailBean.RespBodyBean.ForminstanceBean.DataListBean> dataList) {
        for (int i = 0; i < formalldataBeanList.size(); i++) {
            SeeScheduleDetailBean.RespBodyBean.ForminstanceBean.FormBean.DataBean dataBean = formalldataBeanList.get(i);
            SeeScheduleDetailBean.RespBodyBean.ForminstanceBean.DataListBean dataListBean = dataList.get(i);
            String leipiplugins = dataBean.getLeipiplugins();
            int id = dataBean.getId();
            String value = dataListBean.getValue();

            TextView name = new TextView(this);
            name.setPadding(0, 10, 0, 0);
            name.setText(dataBean.getTitle());
            name.setTextColor(getResources().getColor(R.color.color_333));
            name.setTextSize(15);
            lineAll.addView(name);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    1);
            LinearLayout.LayoutParams bigimgview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lpview.setMargins(0, 10, 0, 0);
            if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {
                TextView namevalue = new TextView(this);
                namevalue.setPadding(0, 10, 0, 0);
                namevalue.setTextColor(getResources().getColor(R.color.color_333));
                namevalue.setTextSize(13);
                namevalue.setText(value);
                lineAll.addView(namevalue);

                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("radios")) {
                RadioGroup radioGroup = new RadioGroup(this);
                radioGroup.setLayoutParams(lp);
                radioGroup.setId(id);
                radioGroup.setPadding(0, 10, 0, 0);
                radioGroup.setOrientation(LinearLayout.VERTICAL);
                String valuestring = dataBean.getValue();
                try {
                    if (valuestring != null && !valuestring.equals("")) {
                        String[] values = null;
                        values = valuestring.split(",");
                        if (values.length < 1) return;
                        for (int j = 0; j < values.length; j++) {
                            String valuetext = values[j];
                            RadioButton radioButton = new RadioButton(this);
                            radioButton.setTextColor(getResources().getColor(R.color.color_666));
                            radioButton.setTextSize(14);
                            radioButton.setId(Integer.parseInt(id + "1" + j));
                            radioButton.setText(valuetext);
                            if (valuetext.equals(value)) {
                                radioButton.setChecked(true);
                            }
                            radioButton.setClickable(false);
                            radioButton.setFocusable(false);
                            radioGroup.setClickable(false);
                            radioGroup.setFocusable(false);
                            radioGroup.addView(radioButton);
                        }
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                lineAll.addView(radioGroup);

                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("checkboxs")) {
                LinearLayout l = new LinearLayout(this);
                l.setId(id);
                l.setOrientation(LinearLayout.VERTICAL);
                String valuestring = dataBean.getValue();
                try {
                    if (valuestring != null && !valuestring.equals("")) {
                        String[] selectvalue = null;
                        selectvalue = value.split(",");
                        String[] values = null;
                        values = valuestring.split(",");
                        if (values.length < 1) return;
                        for (int j = 0; j < values.length; j++) {
                            String valuetext = values[j];
                            CheckBox checkBox = new CheckBox(this);
                            checkBox.setTextColor(getResources().getColor(R.color.color_666));
                            checkBox.setTextSize(14);
                            checkBox.setId(Integer.parseInt(id + "2" + j));
                            checkBox.setText(valuetext);
                            for (int m = 0; m < selectvalue.length; m++) {
                                if (valuetext.equals(selectvalue[m])) {
                                    checkBox.setChecked(true);
                                }
                            }
                            checkBox.setClickable(false);
                            checkBox.setFocusable(false);
                            l.addView(checkBox);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lineAll.addView(l);

                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            }else if (leipiplugins.equals("formImage")) {
                imageView = new ImageView(this);
                imageView.setLayoutParams(bigimgview);
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                getP().getFormImage(value);
                lineAll.addView(imageView);
            }
        }
    }

    public void setFormImage(String respBody) {
        if (respBody!=null&&!respBody.equals("")){
            Bitmap bitmap = stringtoBitmap(respBody);
            imageView.setImageBitmap(bitmap);
        }
    }

    private void initUi() {
        for (int i = 0; i < alldataBeanList.size(); i++) {
            final ScheduleDetailBean.RespBodyBean.FormBean.DataBean dataBean = alldataBeanList.get(i);
            String leipiplugins = dataBean.getLeipiplugins();
            int id = dataBean.getId();
            TextView name = new TextView(this);
            name.setPadding(0, 10, 0, 0);
            name.setText(dataBean.getTitle());
            name.setTextColor(getResources().getColor(R.color.color_333));
            name.setTextSize(15);
            lineAll.addView(name);
            LinearLayout.LayoutParams imgview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    1);
            lpview.setMargins(0, 10, 0, 0);
            if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {

                EditText editText = new EditText(this);
                editText.setId(id);
                editText.setTextColor(getResources().getColor(R.color.color_666));
                editText.setTextSize(14);
                editText.setBackground(null);
                editText.setLayoutParams(lp);
                lineAll.addView(editText);
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("radios")) {
                RadioGroup radioGroup = new RadioGroup(this);
                radioGroup.setLayoutParams(lp);
                radioGroup.setId(id);
                radioGroup.setPadding(0, 10, 0, 0);
                radioGroup.setOrientation(LinearLayout.VERTICAL);
                String valuestring = dataBean.getValue();
                try {
                    if (valuestring != null && !valuestring.equals("")) {
                        String[] values = null;
                        values = valuestring.split(",");
                        if (values.length < 1) return;
                        for (int j = 0; j < values.length; j++) {
                            String valuetext = values[j];
                            RadioButton radioButton = new RadioButton(this);
                            radioButton.setTextColor(getResources().getColor(R.color.color_666));
                            radioButton.setTextSize(14);
                            radioButton.setId(Integer.parseInt(id + "1" + j));
                            radioButton.setText(valuetext);
                            radioGroup.addView(radioButton);
                        }
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                lineAll.addView(radioGroup);
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("checkboxs")) {
                LinearLayout l = new LinearLayout(this);
                l.setId(id);
                l.setOrientation(LinearLayout.VERTICAL);
                String valuestring = dataBean.getValue();
                try {
                    if (valuestring != null && !valuestring.equals("")) {
                        String[] values = null;
                        values = valuestring.split(",");
                        if (values.length < 1) return;
                        for (int j = 0; j < values.length; j++) {
                            String valuetext = values[j];
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
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lineAll.addView(l);
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            }else if (leipiplugins.equals("formImage")) {
                final ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(imgview);
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                imageView.setBackgroundResource(R.mipmap.put_img);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        image=imageView;
                        uploadimageid = dataBean.getId();
                        uploadimageuuid = dataBean.getUuid();
                        DialogManager.createPickImageDialog(DynamicFormActivity.this);
                    }
                });
                lineAll.addView(imageView);
            }
        }
        if (seetype == 1) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
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

        WeakReference<DynamicFormActivity> activityWeakReference;

        public MyHandler(DynamicFormActivity dynamicFormActivity) {
            activityWeakReference = new WeakReference<DynamicFormActivity>(dynamicFormActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                if (msg.what == 0) {
                    JSONObject object = new JSONObject();
                    org.json.JSONArray jsonArray = new org.json.JSONArray();
                    for (int i = 0; i < alldataBeanList.size(); i++) {
                        ScheduleDetailBean.RespBodyBean.FormBean.DataBean dataBean = alldataBeanList.get(i);
                        String leipiplugins = dataBean.getLeipiplugins();
                        int id = dataBean.getId();
                        if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {
                            JSONObject jsonObject = new JSONObject();
                            EditText editText = findViewById(id);
                            jsonObject.put(kongjianid, id + "");
                            jsonObject.put(valuestr, editText.getText().toString().trim());
                            jsonArray.put(jsonObject);
                        } else if (leipiplugins.equals("radios")) {
                            JSONObject jsonObject = new JSONObject();
                            RadioGroup radioGroup = findViewById(id);
                            jsonObject.put(kongjianid, id + "");
                            RadioButton r = findViewById(radioGroup.getCheckedRadioButtonId());
                            if (r != null) {
                                String val = r.getText().toString();
                                jsonObject.put(valuestr, val);
                                jsonArray.put(jsonObject);
                            } else {
                                ToastUtils.showToast("您还有未选择的选项");
                                return;
                            }
                        } else if (leipiplugins.equals("checkboxs")) {
                            JSONObject jsonObject = new JSONObject();
                            String val = dataBean.getValue();
                            jsonObject.put(kongjianid, id + "");
                            if (!val.equals("")) {
                                if (val.lastIndexOf(",") == val.length() - 1)
                                    val = val.substring(0, val.length() - 1);
                                jsonObject.put(valuestr, val);
                                jsonArray.put(jsonObject);
                            } else {
                                ToastUtils.showToast("您还有未选择的选项");
                                return;
                            }
                        }else if (leipiplugins.equals("formImage")) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put(kongjianid, id + "");
                            if (imageurl==null||imageurl.equals("")){
                                ToastUtils.showToast("请选择图片");
                                return;
                            }else {
                                jsonObject.put(valuestr, imageurl);
                                jsonArray.put(jsonObject);
                            }
                        }
                    }
                    object.put("uuid", releaseFormId);
                    object.put("dataList", jsonArray);
                    String str = object.toString();
                    LogUtils.i("gdsgfdsgfg", str);
                    getP().getScheduleDetail(taskId, str);
                }
            } catch (Exception e) {

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
                    contentUri = FileProvider.getUriForFile(DynamicFormActivity.this, "com.yunyisheng.app.yunys.fileprovider", DialogManager.tempFile);
                }else {
                    contentUri=Uri.fromFile(DialogManager.tempFile);
                }
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
        LoadingDialog.show(DynamicFormActivity.this);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_PATH)
                .build();
        ScheduleService service = retrofit.create(ScheduleService.class);
        String token = SharedPref.getInstance(DynamicFormActivity.this).getString("TOKEN", null);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("id", uploadimageid+"")
                .addFormDataPart("uuid", uploadimageuuid)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("file"), file))
                .build();

        //如果和rxjava1.x , call就换成 Observable
        Call<UploadDynamicFormImageBean> call = service.uploadImage(token, "formFile/upload", requestBody);
        // 执行
        call.enqueue(new Callback<UploadDynamicFormImageBean>() {
            @Override
            public void onResponse(Call<UploadDynamicFormImageBean> call, Response<UploadDynamicFormImageBean> response) {
                String msg = response.body().getRespMsg();
                int code = response.body().getRespCode();
                if (code == 0) {
                    ToastUtils.showToast("上传成功!");
                    imageurl = response.body().getRespBody();
                    image.setImageURI(uri);
                } else {
                    ToastUtils.showToast("上传失败!");
                }
                LogUtils.i("fjdlkf", msg + code);
                LoadingDialog.dismiss(DynamicFormActivity.this);
            }

            @Override
            public void onFailure(Call<UploadDynamicFormImageBean> call, Throwable t) {
                ToastUtils.showToast("请检查网络设置");
                LoadingDialog.dismiss(DynamicFormActivity.this);
            }
        });
    }
    
}
