package com.yunyisheng.app.yunys.tasks.activity;

import android.content.Intent;
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
import com.yunyisheng.app.yunys.project.model.ProcessTaskFormDetailBean;
import com.yunyisheng.app.yunys.tasks.present.ProcessTaskPresent;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

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
        getP().getProcessTaskDetail(selectFormId);
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

    private void initUi() {
        for (int i = 0; i < dataBeanList.size(); i++) {
            ProcessTaskFormDetailBean.RespBodyBean.DataBean dataBean = dataBeanList.get(i);
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
                    List<ProcessTaskFormDetailBean.RespBodyBean.opationValue> options = dataBean.getOptions();
                    if (options.size() < 1) return;
                    for (int j = 0; j < options.size(); j++) {
                        String valuetext = options.get(j).getValue();
                        RadioButton radioButton = new RadioButton(this);
                        radioButton.setTextColor(getResources().getColor(R.color.color_666));
                        radioButton.setTextSize(14);
                        radioButton.setId(Integer.parseInt(id + "1" + j));
                        radioButton.setText(valuetext);
                        radioGroup.addView(radioButton);
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
//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    l.setId(id);
                    l.setOrientation(LinearLayout.VERTICAL);
                    List<ProcessTaskFormDetailBean.RespBodyBean.opationValue> options = dataBean.getOptions();
                    if (options.size() < 1) return;
                    for (int j = 0; j < options.size(); j++) {
                        String valuetext = options.get(j).getValue();
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
            }
        }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
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
                } else if (state.equals("103")) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            selectUserId1 = data.getIntExtra("selectUserId", 0);
            selectUserName = data.getStringExtra("selectUserName");
            getP().zhuanProcessTaskForm(selectFormId, selectUserId1);
        }
    }
}
