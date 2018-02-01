package com.yunyisheng.app.yunys.project.activity;

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

import com.alibaba.fastjson.JSONArray;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.schedule.present.ScheduleDetailPresent;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

public class DynamicFormActivity extends BaseActivity<ScheduleDetailPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.line_all)
    LinearLayout lineAll;
    private String scheduleid;
    private int type;
    private int userid;
    private String kongjianid="feedbackItemId";
    private String valuestr="feedbackVal";
    private List<ScheduleDetailBean.RespBodyBean.FormBean.DataBean> alldataBeanList = new ArrayList<>();
    private MyHandler handler=new MyHandler(this);
    private int taskId;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        userid = SharedPref.getInstance(this).getInt("userid", 0);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
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
        getP().getScheduleDetail(userid, scheduleid, type);
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
        List<ScheduleDetailBean.RespBodyBean> respBody = scheduleDetailBean.getRespBody();
        ScheduleDetailBean.RespBodyBean.TaskBean task = respBody.get(0).getTask();
        teTitle.setText(task.getReleaseName());
        taskId = task.getTaskId();
        List<ScheduleDetailBean.RespBodyBean.FormBean.DataBean> dataBeanList = respBody.get(0).getForm().getData();
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
            name.setPadding(0,10,0,0);
            name.setText(dataBean.getTitle());
            name.setTextColor(getResources().getColor(R.color.color_333));
            name.setTextSize(15);
            lineAll.addView(name);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    1);
            if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {
                EditText editText = new EditText(this);
                editText.setId(id);
                editText.setTextColor(getResources().getColor(R.color.color_666));
                editText.setTextSize(14);
                editText.setBackground(null);
                editText.setLayoutParams(lp);
                if (dataBean.getValue() != null && !dataBean.getValue().equals("")) {
                    editText.setText(value);
                }
                View view=new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                lineAll.addView(editText);
                lineAll.addView(view);
            } else if (leipiplugins.equals("radios")) {
                RadioGroup radioGroup = new RadioGroup(this);
                radioGroup.setLayoutParams(lp);
                radioGroup.setId(id);
                radioGroup.setPadding(0,10,0,0);
                radioGroup.setOrientation(LinearLayout.VERTICAL);
                View view=new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                List<ScheduleDetailBean.RespBodyBean.FormBean.VelueBean> options = dataBean.getOptions();
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
                lineAll.addView(view);
            } else if (leipiplugins.equals("checkboxs")) {
                LinearLayout l = new LinearLayout(this);
//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                l.setId(id);
                l.setOrientation(LinearLayout.VERTICAL);
                View view=new View(this);
                view.setLayoutParams(lpview);
                view.setBackgroundColor(getResources().getColor(R.color.color_e7));
                List<ScheduleDetailBean.RespBodyBean.FormBean.VelueBean> options = dataBean.getOptions();
                if (options.size() < 1) return;
                for (int j = 0; j < options.size(); j++) {
                    String valuetext = options.get(j).getValue();
                    CheckBox checkBox = new CheckBox(this);
                    checkBox.setTextColor(getResources().getColor(R.color.color_666));
                    checkBox.setTextSize(14);
                    checkBox.setId(Integer.parseInt(id + "2" + j));
                   // checkBox.setButtonDrawable(getResources().getDrawable(R.drawable.checkbox_selector));
                    checkBox.setText(valuetext);
                    l.addView(checkBox);
                }
                lineAll.addView(l);
                lineAll.addView(view);
            }
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Button button=new Button(this);
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

    class MyHandler extends Handler{

        WeakReference<DynamicFormActivity> activityWeakReference;

        public MyHandler(DynamicFormActivity dynamicFormActivity) {
            activityWeakReference=new WeakReference<DynamicFormActivity>(dynamicFormActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Map<String, String> params = new HashMap<>();

                List<Map> l = new ArrayList();
                for (int i=0;i<alldataBeanList.size();i++){
                    Map<String, String> map = new HashMap<>();
                    ScheduleDetailBean.RespBodyBean.FormBean.DataBean dataBean = alldataBeanList.get(i);
                    String leipiplugins = dataBean.getLeipiplugins();
                    int id = dataBean.getId();
                    if (leipiplugins.equals("text") || leipiplugins.equals("textarea")) {
                        EditText editText=findViewById(id);
                        map.put(kongjianid,id+"");
                        map.put(valuestr,editText.getText().toString().trim());
                        l.add(map);
                    } else if (leipiplugins.equals("radios")) {
                        RadioGroup radioGroup=findViewById(id);
                        map.put(kongjianid,id+"");
                        RadioButton r = findViewById(radioGroup.getCheckedRadioButtonId());
                        if (r!=null){
                            String val = r.getText().toString();
                            map.put(valuestr,val);
                            l.add(map);
                        }else {
                            ToastUtils.showToast("您还有未选择的选项");
                        }
                    }else if (leipiplugins.equals("checkboxs")) {
                        List<ScheduleDetailBean.RespBodyBean.FormBean.VelueBean> options = dataBean.getOptions();
                        map.put(kongjianid,id+"");
                        String val = "";
                        if(options.size() < 1) return;
                        for(int j = 0; j < options.size();j++){
                            CheckBox cb = findViewById(Integer.parseInt(id+"2"+j));
                            if(cb.isChecked()){
                                val += cb.getText().toString() +",";
                            }
                        }
                        if (!val.equals("")){
                            if(val.lastIndexOf(",") == val.length()-1)val = val.substring(0,val.length()-1);
                            map.put(valuestr,val);
                            l.add(map);
                        }else {
                            ToastUtils.showToast("您还有未选择的选项");
                        }
                    }
                }
                params.put("dynamic_template", JSONArray.toJSONString(l));
                String str=JSONArray.toJSONString(l);
                LogUtils.i("gdsgfdsgfg",str);
            }
        }
    }
}
