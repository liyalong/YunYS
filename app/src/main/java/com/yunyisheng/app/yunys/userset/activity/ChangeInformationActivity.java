package com.yunyisheng.app.yunys.userset.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.userset.present.ChangeInformationPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

public class ChangeInformationActivity extends BaseActivity<ChangeInformationPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.ed_put)
    EditText edPut;
    @BindView(R.id.btn_queren)
    Button btnQueren;
    private int type;
    private String putstr;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        if (type == 2) {
            teTitle.setText("更改姓名");
            img.setVisibility(View.GONE);
            edPut.setHint("请输入姓名");
            edPut.setPadding(10, 0, 0, 0);
        } else {
            teTitle.setText("更改邮箱");
        }
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_change_information;
    }

    @Override
    public ChangeInformationPresent bindPresent() {
        return new ChangeInformationPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        btnQueren.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_queren:
                putstr = edPut.getText().toString().trim();
                if (type == 2) {
                    Intent intent = new Intent();
                    intent.setAction("action");
                    intent.putExtra("data", "changename");
                    sendBroadcast(intent);//发送普通广播
                } else {
                    Pattern pattern = Pattern
                            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
                    Matcher mc = pattern.matcher(putstr);
                    if (mc.matches()) {
                        getP().updateEmail(putstr);
                    } else {
                        ToastUtils.showToast("邮箱格式错误");
                    }
                }
                break;

        }
    }

    /**
     * @author fuduo
     * @time 2018/1/22  14:22
     * @describe 修改邮箱返回的值
     */
    public void getResult(BaseModel baseModel) {
        if (baseModel.getRespCode() == 0) {
            SharedPref.getInstance(ChangeInformationActivity.this).putString("useremail", putstr);
            Intent intent = getIntent();
            setResult(3, intent);
            finish();
        }
    }

}
