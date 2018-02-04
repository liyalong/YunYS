package com.yunyisheng.app.yunys.userset.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.userset.present.AccountSetlPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidbase.router.Router;

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
    @BindView(R.id.ed_new_yanzhengnum)
    EditText edNewYanzhengnum;
    @BindView(R.id.get_new_yzm)
    Button getNewYzm;
    @BindView(R.id.line_new)
    LinearLayout lineNew;
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
        getNewYzm.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                String string = btnQueren.getText().toString();
                if (string.equals("确认")) {
                    rlPutyzm.setVisibility(View.VISIBLE);
                    lineNew.setVisibility(View.GONE);
                    btnQueren.setText("下一步");
                } else {
                    finish();
                }
                break;
            case R.id.get_yzm:
                getCode();
                break;
            case R.id.btn_queren:
                String str = btnQueren.getText().toString();
                if (str.equals("确认")) {
                    String newyanzhengma = edNewYanzhengnum.getText().toString().trim();
                    getP().changePhone(newyanzhengma, phonenum);
                } else {
                    yanzhengma = edYanzhengnum.getText().toString().trim();
                    getP().checkCode(yanzhengma);
                }
                break;
            case R.id.get_new_yzm:
                phonenum = edPhonenum.getText().toString().trim();
                getNewPhoneCode(phonenum);
                break;
        }
    }

    private void getNewPhoneCode(String phonenum){
        getNewYzm.setEnabled(false);
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getNewYzm.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                getNewYzm.setEnabled(true);
                getNewYzm.setText("获取验证码");
            }
        };
        timer.start();
        getP().sendCode(phonenum);
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

    public void checkCode() {
        rlPutyzm.setVisibility(View.GONE);
        lineNew.setVisibility(View.VISIBLE);
        btnQueren.setText("确认");
    }

    public void changePhone() {
        SharedPref.getInstance(context).clear();
        Router.newIntent(context)
                .to(LoginActivity.class)
                .launch();
        finish();
    }

}
