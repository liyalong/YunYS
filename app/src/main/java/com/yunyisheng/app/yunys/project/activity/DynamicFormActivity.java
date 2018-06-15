package com.yunyisheng.app.yunys.project.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.PressionListener;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.bean.UploadDynamicFormImageBean;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.schedule.model.SeeScheduleDetailBean;
import com.yunyisheng.app.yunys.schedule.present.ScheduleDetailPresent;
import com.yunyisheng.app.yunys.schedule.service.ScheduleService;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.DialogManager;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ScaleImageView;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.Util;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
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
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

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
    private Handler mHandler=new Handler();
    private int taskId;
    private String releaseFormId;
    private int seetype;
    private ImageView image;
    private int uploadimageid;
    private String uploadimageuuid;
    private ImageView imageView;
    private List<String> imageurllist = new ArrayList<>();
    private int imageurlsize;

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
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                2);
        LinearLayout.LayoutParams bigimgview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams editTextLP =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1);
        LinearLayout.LayoutParams unitLP =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        lpview.setMargins(0, 10, 0, 0);
        for (int i = 0; i < formalldataBeanList.size(); i++) {
            SeeScheduleDetailBean.RespBodyBean.ForminstanceBean.FormBean.DataBean dataBean = formalldataBeanList.get(i);
            SeeScheduleDetailBean.RespBodyBean.ForminstanceBean.DataListBean dataListBean = dataList.get(i);
            String leipiplugins = dataBean.getLeipiplugins();
            int id = dataBean.getId();
            String value = dataListBean.getValue();

            TextView name = new TextView(this);
            name.setPadding(0, 10, 0, 10);
            name.setText(dataBean.getTitle());
            name.setTextColor(getResources().getColor(R.color.color_333));
            name.setTextSize(15);
            lineAll.addView(name);
            if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(lp);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setBackgroundResource(R.drawable.form_bac);
                TextView namevalue = new TextView(this);
                namevalue.setPadding(5, 10, 0, 10);
                namevalue.setTextColor(getResources().getColor(R.color.color_333));
                namevalue.setTextSize(14);
                namevalue.setText(value);
                namevalue.setLayoutParams(editTextLP);
                TextView unitTextView = new TextView(this);
                if (dataBean.getOrgunit() != null && !dataBean.getOrgunit().equals("")){
                    unitTextView.setText(dataBean.getOrgunit());
                    unitTextView.setTextColor(getResources().getColor(R.color.color_333));
                    unitTextView.setBackgroundColor(getResources().getColor(R.color.white));
                    unitTextView.setLayoutParams(unitLP);
                    unitTextView.setGravity(Gravity.CENTER);
                    unitTextView.setTextSize(14);

                }
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.addView(namevalue);
                linearLayout.addView(unitTextView);
                lineAll.addView(linearLayout);

                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("radios")) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(lp);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setBackgroundResource(R.drawable.form_bac);
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
                            radioButton.setTextColor(getResources().getColor(R.color.color_333));
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
                linearLayout.addView(radioGroup);
                lineAll.addView(linearLayout);

                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("checkboxs")) {
                LinearLayout l = new LinearLayout(this);
                l.setId(id);
                l.setOrientation(LinearLayout.VERTICAL);
                l.setLayoutParams(lp);
                l.setBackgroundResource(R.drawable.form_bac);
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
                            checkBox.setTextColor(getResources().getColor(R.color.color_333));
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
            } else if (leipiplugins.equals("formImage")) {
                imageView = new ImageView(this);
                imageView.setLayoutParams(bigimgview);
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                getP().getFormImage(value);
                lineAll.addView(imageView);
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            }
        }
    }
    /**
     * 图片放大框
     *
     * @return
     */
    public void createBigImageDialog(Bitmap bitmap) {
        final Dialog mSelectTask = new Dialog(this, R.style.dialog_bottom_full);
        mSelectTask.setCanceledOnTouchOutside(true);
        mSelectTask.setCancelable(true);
        Window window = mSelectTask.getWindow();
//        window.setGravity(Gravity.BOTTOM);
        View view1 = View.inflate(this, R.layout.dialog_show_image, null);
        ScaleImageView matriximage = (ScaleImageView) view1
                .findViewById(R.id.matriximage);
        matriximage.setImageBitmap(bitmap);
        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mSelectTask.show();
    }
    public void setFormImage(String respBody) {
        if (respBody != null && !respBody.equals("")) {
            final Bitmap bitmap = stringtoBitmap(respBody);
            imageView.setImageBitmap(bitmap);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createBigImageDialog(bitmap);
                }
            });
            CommonUtils.releaseImageViewResouce(imageView);
        }
    }

    private void initUi() {
        LinearLayout.LayoutParams imgview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams editTextLP =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1);
        LinearLayout.LayoutParams unitLP =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                2);
        lpview.setMargins(0, 20, 0, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 20);
        for (int i = 0; i < alldataBeanList.size(); i++) {
            final ScheduleDetailBean.RespBodyBean.FormBean.DataBean dataBean = alldataBeanList.get(i);
            LogUtils.i("fieldinfo----》",dataBean.toString());
            String leipiplugins = dataBean.getLeipiplugins();
            int id = dataBean.getId();
            String defaultValue = dataBean.getValue();
            TextView name = new TextView(this);
            name.setPadding(0, 10, 0, 10);
            name.setText(dataBean.getTitle());
            name.setTextColor(getResources().getColor(R.color.color_333));
            name.setTextSize(15);
            lineAll.addView(name);
            if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(lp);
                linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setBackgroundResource(R.drawable.form_bac);
                EditText editText = new EditText(this);
                editText.setId(id);
                if (dataBean.getOrgtype() != null && dataBean.getOrgtype().equals("float")){
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                editText.setTextColor(getResources().getColor(R.color.color_333));
                editText.setTextSize(14);
                if (defaultValue != null && !defaultValue.equals("")){
                    editText.setText(defaultValue);
                }
                editText.setHint("请输入" + dataBean.getTitle());
                editText.setHintTextColor((getResources().getColor(R.color.color_999)));
                editText.setBackground(null);
                editText.setLayoutParams(editTextLP);
                TextView unitTextView = new TextView(this);
                if (dataBean.getOrgunit() != null && !dataBean.getOrgunit().equals("")){
                    unitTextView.setText(dataBean.getOrgunit());
                    unitTextView.setTextColor(getResources().getColor(R.color.color_333));
                    unitTextView.setBackgroundColor(getResources().getColor(R.color.white));
                    unitTextView.setLayoutParams(unitLP);
                    unitTextView.setGravity(Gravity.CENTER);
                    unitTextView.setTextSize(14);

                }
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.addView(editText);
                linearLayout.addView(unitTextView);

                lineAll.addView(linearLayout);
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("radios")) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(lp);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setBackgroundResource(R.drawable.form_bac);
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
                        if (values.length < 1){
                            ToastUtils.showToast("数据格式错误！");
                            return;
                        }
                        for (int j = 0; j < values.length; j++) {
                            String valuetext = values[j];
                            RadioButton radioButton = new RadioButton(this);
                            radioButton.setTextColor(getResources().getColor(R.color.color_333));
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
                linearLayout.addView(radioGroup);
                lineAll.addView(linearLayout);
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("checkboxs")) {
                LinearLayout l = new LinearLayout(this);
                l.setId(id);
                l.setOrientation(LinearLayout.VERTICAL);
                l.setBackgroundResource(R.drawable.form_bac);
                l.setLayoutParams(lp);
                String valuestring = dataBean.getValue();
                try {
                    if (valuestring != null && !valuestring.equals("")) {
                        String[] values = null;
                        values = valuestring.split(",");
                        if (values.length < 1) return;
                        for (int j = 0; j < values.length; j++) {
                            String valuetext = values[j];
                            CheckBox checkBox = new CheckBox(this);
                            checkBox.setTextColor(getResources().getColor(R.color.color_333));
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
            } else if (leipiplugins.equals("formImage")) {
                final ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(imgview);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setBackgroundResource(R.mipmap.put_img);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        image = imageView;
                        uploadimageid = dataBean.getId();
                        uploadimageuuid = dataBean.getUuid();
                        requestPermission();
                        DialogManager.createPickImageDialog(DynamicFormActivity.this);
                    }
                });
                lineAll.addView(imageView);
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            }
        }
        if (seetype == 1) {
            Button button = new Button(this);
            button.setLayoutParams(layoutParams);
            button.setText("提交");
            button.setBackgroundResource(R.drawable.btn_anpai_work);
            button.setTextColor(getResources().getColor(R.color.white));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //handler.sendEmptyMessage(0);
                    mHandler.post(runnable);
                }
            });
            lineAll.addView(button);
        }
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            try {
                    JSONObject object = new JSONObject();
                    org.json.JSONArray jsonArray = new org.json.JSONArray();
                    for (int i = 0; i < alldataBeanList.size(); i++) {
                        ScheduleDetailBean.RespBodyBean.FormBean.DataBean dataBean = alldataBeanList.get(i);
                        String leipiplugins = dataBean.getLeipiplugins();
                        int id = dataBean.getId();
                        if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {
                            JSONObject jsonObject = new JSONObject();
                            EditText editText = findViewById(id);
                            if(editText.getText().toString().trim().length() == 0){
                                ToastUtils.showToast("您还有未填写的填写项");
                                return;
                            }
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
                            String valuestring = dataBean.getValue();
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put(kongjianid, id + "");
                            String val = "";
                            String[] values = null;
                            values = valuestring.split(",");
                            if (values.length < 1){
                                ToastUtils.showToast("数据格式不正确！");
                                return;
                            }
                            for (int m = 0; m < values.length; m++) {
                                CheckBox cb = findViewById(Integer.parseInt(id + "2" + m));
                                if (cb.isChecked()) {
                                    val += cb.getText().toString() + ",";
                                }
                            }
                            if (!val.equals("")) {
                                if (val.lastIndexOf(",") == val.length() - 1)
                                    val = val.substring(0, val.length() - 1);
                                jsonObject.put(valuestr, val);
                                jsonArray.put(jsonObject);
                            } else {
                                ToastUtils.showToast("您还有未选择的选项");
                                return;
                            }
                        } else if (leipiplugins.equals("formImage")) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put(kongjianid, id + "");
                            if (imageurllist.size() > 0) {
                                String imageurl = imageurllist.get(imageurlsize);
                                jsonObject.put(valuestr, imageurl);
                                jsonArray.put(jsonObject);
                                imageurlsize++;
                            }
                        }
                    }
                    object.put("uuid", releaseFormId);
                    object.put("dataList", jsonArray);
                    String str = object.toString();
                    LogUtils.i("gdsgfdsgfg", str);
                    getP().getScheduleDetail(taskId, str);
            } catch (Exception e) {

            }
        }
    };

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
                            List<ScheduleDetailBean.RespBodyBean.FormBean.VelueBean> options = dataBean.getOptions();
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put(kongjianid, id + "");
                            String val = "";
                            if (options.size() < 1) return;
                            for (int m = 0; m < options.size(); m++) {
                                CheckBox cb = findViewById(Integer.parseInt(id + "2" + m));
                                if (cb.isChecked()) {
                                    val += cb.getText().toString() + ",";
                                }
                            }
                            if (!val.equals("")) {
                                if (val.lastIndexOf(",") == val.length() - 1)
                                    val = val.substring(0, val.length() - 1);
                                jsonObject.put(valuestr, val);
                                jsonArray.put(jsonObject);
                            } else {
                                ToastUtils.showToast("您还有未选择的选项");
                                return;
                            }
                        } else if (leipiplugins.equals("formImage")) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put(kongjianid, id + "");
                            if (imageurllist.size() > 0) {
                                String imageurl = imageurllist.get(imageurlsize);
                                jsonObject.put(valuestr, imageurl);
                                jsonArray.put(jsonObject);
                                imageurlsize++;
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
                } else {
                    contentUri = Uri.fromFile(DialogManager.tempFile);
                }
                setCompressImg(DialogManager.tempFile);
            } else if (requestCode == 2) {// 相册
                if (intent != null) {
                    Log.i("xiaoqiang", "smdongxi==" + intent.getData());
                    Uri uri = intent.getData();
                    String realPathFromURI = Util.getFileAbsolutePath(this, uri);
                    File file = new File(realPathFromURI);
                    setCompressImg(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void setCompressImg(File file) {
        Luban.with(this).
                load(file).
                ignoreBy(1000).
                setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        putPic(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast("上传错误，请重试！");
                    }
                }).launch();
    }

    /**
     * @author fuduo
     * @time 2018/2/1  18:22
     * @describe 上传图片
     */
    private void putPic(final File file) {
        LoadingDialog.show(DynamicFormActivity.this);
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).
                writeTimeout(60, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_PATH)
                .build();
        ScheduleService service = retrofit.create(ScheduleService.class);
        String token = SharedPref.getInstance(DynamicFormActivity.this).getString("TOKEN", null);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("id", uploadimageid + "")
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
                    String imageurl = response.body().getRespBody();
                    imageurllist.add(imageurl);
                    image.setBackground(null);
                    Uri uri1 = Uri.fromFile(file);
                    image.setImageURI(uri1);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        requestRunTimePression(DynamicFormActivity.this, new String[]{Manifest.permission.CAMERA}, new PressionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onFailure(List<String> failurePression) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("提示")
                        .setMessage("请您去设置中授予拍照的权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                builder.setCancelable(false);
                builder.show();
            }
        });
    }

}
