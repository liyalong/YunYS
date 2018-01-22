package com.yunyisheng.app.yunys.userset.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.userset.present.AccountSetlPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

/**
 * @author fuduo
 * @time 2018/1/18  18:09
 * @describe 个人账号设置
 */
public class AccountSetActivity extends BaseActivity<AccountSetlPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.ed_phonenum)
    EditText edPhonenum;
    @BindView(R.id.ed_yanzhengnum)
    EditText edYanzhengnum;
    @BindView(R.id.get_yzm)
    Button getYzm;
    @BindView(R.id.btn_queren)
    Button btnQueren;
    @BindView(R.id.rl_putphone)
    RelativeLayout rlPutphone;
    @BindView(R.id.rl_putyzm)
    RelativeLayout rlPutyzm;
    private String yanzhengma;
    private String phonenum;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("账号设置");
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_account_set;
    }

    @Override
    public AccountSetlPresent bindPresent() {
        return new AccountSetlPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        getYzm.setOnClickListener(this);
        btnQueren.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.get_yzm:
                getCode();
                break;
            case R.id.btn_queren:
                String str = btnQueren.getText().toString();
                if (str.equals("确认")) {
                    phonenum = edPhonenum.getText().toString().trim();

                    SharedPref.getInstance(AccountSetActivity.this).putString("userphone", phonenum);
                    Intent intent = new Intent();
                    intent.setAction("action");
                    intent.putExtra("data", "changephonenum");
                    sendBroadcast(intent);//发送普通广播

                    Intent intent1 = getIntent();
                    setResult(1, intent1);
                    finish();
                } else {
                    yanzhengma = edYanzhengnum.getText().toString().trim();
                    rlPutyzm.setVisibility(View.GONE);
                    rlPutphone.setVisibility(View.VISIBLE);
                    btnQueren.setText("确认");
                }
                break;
        }
    }

    private void getCode() {
        getYzm.setEnabled(false);
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getYzm.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                getYzm.setEnabled(true);
                getYzm.setText("获取验证码");
            }
        };
        timer.start();
        getP().sendCode(SharedPref.getInstance(AccountSetActivity.this).getString("userphone", ""));
    }

}
