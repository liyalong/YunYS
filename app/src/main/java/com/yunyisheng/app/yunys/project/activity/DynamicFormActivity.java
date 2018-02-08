package com.yunyisheng.app.yunys.project.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
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
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.schedule.present.ScheduleDetailPresent;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private MyHandler handler = new MyHandler(this);
    private int taskId;
    private String releaseFormId;
    private int seetype;

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
            getP().getMineScheduleDetail(scheduleid, type);
        } else {
            getP().getOtherScheduleDetail(userId, scheduleid, type);
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

    private void initUi() {
        for (int i = 0; i < alldataBeanList.size(); i++) {
            ScheduleDetailBean.RespBodyBean.FormBean.DataBean dataBean = alldataBeanList.get(i);
            String leipiplugins = dataBean.getLeipiplugins();
            int id = dataBean.getId();
            String value = dataBean.getValue();
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
            if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {

                if (seetype == 2) {
                    TextView namevalue = new TextView(this);
                    namevalue.setPadding(0, 10, 0, 0);
                    namevalue.setTextColor(getResources().getColor(R.color.color_333));
                    namevalue.setTextSize(13);
                    namevalue.setText(value);
                    lineAll.addView(namevalue);
                } else {
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
            } else if (leipiplugins.equals("radios")) {
                if (seetype == 2) {
                    TextView namevalue = new TextView(this);
                    namevalue.setPadding(0, 10, 0, 0);
                    namevalue.setTextColor(getResources().getColor(R.color.color_333));
                    namevalue.setTextSize(13);
                    namevalue.setText(value);
                    lineAll.addView(namevalue);
                } else {
                    RadioGroup radioGroup = new RadioGroup(this);
                    radioGroup.setLayoutParams(lp);
                    radioGroup.setId(id);
                    radioGroup.setPadding(0, 10, 0, 0);
                    radioGroup.setOrientation(LinearLayout.VERTICAL);
                    String valuestring=dataBean.getValue();
                    try {
                        if (valuestring!=null&&!valuestring.equals("")){
                            String[] values=null;
                            values=valuestring.split(",");
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
                }
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
            } else if (leipiplugins.equals("checkboxs")) {
                if (seetype == 2) {
                    TextView namevalue = new TextView(this);
                    namevalue.setPadding(0, 10, 0, 0);
                    namevalue.setTextColor(getResources().getColor(R.color.color_333));
                    namevalue.setTextSize(13);
                    namevalue.setText(value);
                    lineAll.addView(namevalue);
                } else {
                    LinearLayout l = new LinearLayout(this);
                    l.setId(id);
                    l.setOrientation(LinearLayout.VERTICAL);
                    String valuestring=dataBean.getValue();
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
                }
                View view = new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(view);
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
                            List<ScheduleDetailBean.RespBodyBean.FormBean.VelueBean> options = dataBean.getOptions();
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
}
