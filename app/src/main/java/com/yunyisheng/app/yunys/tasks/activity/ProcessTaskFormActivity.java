package com.yunyisheng.app.yunys.tasks.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
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

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.bean.UploadDynamicFormImageBean;
import com.yunyisheng.app.yunys.project.model.ProcessTaskFormDetailBean;
import com.yunyisheng.app.yunys.schedule.service.ScheduleService;
import com.yunyisheng.app.yunys.tasks.bean.ProcessDetailBean;
import com.yunyisheng.app.yunys.tasks.present.ProcessTaskPresent;
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

/**
 * 作者：fuduo on 2018/2/5 17:11
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProcessTaskFormActivity extends BaseActivity<ProcessTaskPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.line_all)
    LinearLayout lineAll;

    private List<ProcessTaskFormDetailBean.RespBodyBean.DataBean> dataBeanList = new ArrayList<>();
    private int selectUserId;
    private String selectFormId;
    private String endTime;

    private String kongjianid = "fieldId";
    private String valuestr = "value";
    private String name;
    private String uuid;
    private int seetype;
    private MyHandler myHandler = new MyHandler(this);
    private int selectUserId1;
    private String selectUserName;
    private int userid;
    private String taskid;
    private String state;
    private List<ProcessDetailBean.SelectByIdAndUuid.DataListBean> dataList;
    private ProcessDetailBean.SelectByIdAndUuid.DataListBean dataListBean;
    private String value;
    private ProcessDetailBean processDetailBean;
    private List<ProcessDetailBean.SelectByIdAndUuid.FormBean.DataBean> data;
    private ImageView image;
    private int uploadimageid;
    private String uploadimageuuid;
    private ImageView imageView;
    private String imageurl;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAfter() {
        userid = SharedPref.getInstance(ProcessTaskFormActivity.this).getInt("userid", 0);
        Intent intent = getIntent();
        seetype = intent.getIntExtra("type", 0);
        selectUserId = intent.getIntExtra("selectUserId", 0);
        selectFormId = intent.getStringExtra("selectFormId");
        endTime = intent.getStringExtra("endTime");
        taskid = intent.getStringExtra("taskid");
        state = intent.getStringExtra("state");
        processDetailBean = (ProcessDetailBean) intent.getSerializableExtra("processDetail");
        if (seetype == 1) {
            getP().getProcessTaskDetail(selectFormId);
        } else {
            initSeeFromUi();
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_processtask;
    }

    @Override
    public ProcessTaskPresent bindPresent() {
        return new ProcessTaskPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    public void getProcessTaskForm(ProcessTaskFormDetailBean processTaskFormDetailBean) {
        name = processTaskFormDetailBean.getRespBody().getName();
        teTitle.setText(name);
        uuid = processTaskFormDetailBean.getRespBody().getUuid();
        List<ProcessTaskFormDetailBean.RespBodyBean.DataBean> data = processTaskFormDetailBean.getRespBody().getData();
        if (data != null && data.size() > 0) {
            dataBeanList.addAll(data);
            initUi();
        }
    }

    private void initSeeFromUi() {
        String theme = processDetailBean.getRespBody().getTask().getTheme();
        teTitle.setText(theme);
        dataList = processDetailBean.getRespBody().getSelectByIdAndUuid().getDataList();
        data = processDetailBean.getRespBody().getSelectByIdAndUuid().getForm().getData();
        for (int i = 0; i < data.size(); i++) {
            final ProcessDetailBean.SelectByIdAndUuid.FormBean.DataBean dataBean = data.get(i);
            dataListBean = dataList.get(i);
            value = dataListBean.getValue();
            String leipiplugins = dataBean.getLeipiplugins();
            int id = dataBean.getId();
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
            lpview.setMargins(0, 10, 0, 0);
            LinearLayout.LayoutParams bigimgview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
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
                            if (value.equals(valuetext)) {
                                radioButton.setChecked(true);
                            }
                            radioButton.setClickable(false);
                            radioButton.setFocusable(false);
                            radioGroup.addView(radioButton);
                            radioGroup.setClickable(false);
                            radioGroup.setFocusable(false);
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
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
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
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 0);
        if (state != null && !state.equals("")) {
            if (state.equals("101")) {
                Button but_ok = new Button(this);
                but_ok.setLayoutParams(layoutParams);
                but_ok.setText("同意");
                but_ok.setBackgroundResource(R.drawable.btn_anpai_work);
                but_ok.setTextColor(getResources().getColor(R.color.white));
                but_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createYijianDialog(1);
                    }
                });
                lineAll.addView(but_ok);

                Button but_no = new Button(this);
                but_no.setLayoutParams(layoutParams);
                but_no.setText("拒绝");
                but_no.setBackgroundResource(R.drawable.btn_anpai_work);
                but_no.setTextColor(getResources().getColor(R.color.white));
                but_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createYijianDialog(2);
                    }
                });
                lineAll.addView(but_no);

                Button zhuanbutton = new Button(this);
                zhuanbutton.setLayoutParams(layoutParams);
                zhuanbutton.setText("转办");
                zhuanbutton.setBackgroundResource(R.drawable.btn_anpai_work);
                zhuanbutton.setTextColor(getResources().getColor(R.color.white));
                zhuanbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(ProcessTaskFormActivity.this, RadioSelectUserActivity.class), 5);
                    }
                });
                lineAll.addView(zhuanbutton);
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
        for (int i = 0; i < dataBeanList.size(); i++) {
            final ProcessTaskFormDetailBean.RespBodyBean.DataBean dataBean = dataBeanList.get(i);
            String leipiplugins = dataBean.getLeipiplugins();
            int id = dataBean.getId();
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
            LinearLayout.LayoutParams imgview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
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
                            checkBox.setText(valuetext);
                            l.addView(checkBox);
                        }
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
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
                        DialogManager.createPickImageDialog(ProcessTaskFormActivity.this);
                    }
                });
                lineAll.addView(imageView);
            }
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 0);
        if (state != null && !state.equals("")) {
            if (state.equals("103")) {
                Button button = new Button(this);
                button.setLayoutParams(layoutParams);
                button.setText("提交");
                button.setBackgroundResource(R.drawable.btn_anpai_work);
                button.setTextColor(getResources().getColor(R.color.white));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myHandler.sendEmptyMessage(0);
                    }
                });
                lineAll.addView(button);
            }
        }

    }

    class MyHandler extends Handler {

        WeakReference<ProcessTaskFormActivity> activityWeakReference;

        public MyHandler(ProcessTaskFormActivity processTaskFormActivity) {
            activityWeakReference = new WeakReference<ProcessTaskFormActivity>(processTaskFormActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                if (msg.what == 0) {
                    JSONObject object = new JSONObject();
                    org.json.JSONArray jsonArray = new org.json.JSONArray();
                    for (int i = 0; i < dataBeanList.size(); i++) {
                        ProcessTaskFormDetailBean.RespBodyBean.DataBean dataBean = dataBeanList.get(i);
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
                            List<ProcessTaskFormDetailBean.RespBodyBean.opationValue> options = dataBean.getOptions();
                            jsonObject.put(kongjianid, id + "");
                            String val = "";
                            if (options.size() < 1) return;
                            for (int j = 0; j < options.size(); j++) {
                                CheckBox cb = findViewById(Integer.parseInt(id + "2" + j));
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
                    object.put("uuid", uuid);
                    object.put("dataList", jsonArray);
                    String str = object.toString();
                    LogUtils.i("gdsgfdsgfg", str);
                    getP().putProcessTaskForm(str, selectUserId, selectFormId, endTime);
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * @author fuduo
     * @time 2018/2/6  21:26
     * @describe 反馈意见框
     */
    private void createYijianDialog(final int type) {
        final Dialog mShareDialog = new Dialog(this, R.style.dialog_bottom_full);
        mShareDialog.setCanceledOnTouchOutside(true);
        mShareDialog.setCancelable(true);
        Window window = mShareDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        View view1 = View.inflate(this, R.layout.dialog_put_shenhestring, null);
        RelativeLayout rl_ok = (RelativeLayout) view1
                .findViewById(R.id.rl_ok);
        RelativeLayout btn_cancel = (RelativeLayout) view1
                .findViewById(R.id.rl_cancle);

        final EditText ed_putstr = (EditText) view1
                .findViewById(R.id.ed_putstr);

        rl_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String trim = ed_putstr.getText().toString().trim();
                if (type == 1) {
                    getP().agreeProcessTaskForm(taskid, trim);
                } else {
                    getP().refuseProcessTaskForm(taskid, trim);
                }
                mShareDialog.dismiss();

            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareDialog.dismiss();
            }
        });

        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mShareDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            selectUserId1 = data.getIntExtra("selectUserId", 0);
            selectUserName = data.getStringExtra("selectUserName");
            if (taskid != null && !taskid.equals("")) {
                getP().zhuanProcessTaskForm(taskid, selectUserId1);
            }
        }

        try {
            if (requestCode == 1 && resultCode == RESULT_OK) {
                Uri contentUri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    contentUri = FileProvider.getUriForFile(ProcessTaskFormActivity.this, "com.yunyisheng.app.yunys.fileprovider", DialogManager.tempFile);
                }else {
                    contentUri=Uri.fromFile(DialogManager.tempFile);
                }
                putPic(DialogManager.tempFile,contentUri);
            } else if (requestCode == 2) {// 相册
                if (data != null) {
                    Log.i("xiaoqiang", "smdongxi==" + data.getData());
                    Uri uri = data.getData();
                    String realPathFromURI = Util.getFileAbsolutePath(this, uri);
                    File file = new File(realPathFromURI);
                    putPic(file,uri);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }


    /**
     * @author fuduo
     * @time 2018/2/1  18:22
     * @describe 上传图片
     */
    private void putPic(File file, final Uri uri) {
        LoadingDialog.show(ProcessTaskFormActivity.this);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_PATH)
                .build();
        ScheduleService service = retrofit.create(ScheduleService.class);
        String token = SharedPref.getInstance(ProcessTaskFormActivity.this).getString("TOKEN", null);

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
                LoadingDialog.dismiss(ProcessTaskFormActivity.this);
            }

            @Override
            public void onFailure(Call<UploadDynamicFormImageBean> call, Throwable t) {
                ToastUtils.showToast("请检查网络设置");
                LoadingDialog.dismiss(ProcessTaskFormActivity.this);
            }
        });
    }

}
