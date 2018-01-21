package com.yunyisheng.app.yunys.login.activity;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseStatusModel;
import com.yunyisheng.app.yunys.login.present.RetrievePasswordPresent;
import com.yunyisheng.app.yunys.utils.RegularUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/5.
 * 忘记密码activity
 */
public class RetrievePassword extends BaseActivity<RetrievePasswordPresent> {
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.yzm)
    EditText yzm;
    @BindView(R.id.get_yzm)
    Button get_yzm;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.passworded)
    EditText passworded;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    TextView login;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_retrieve_password;
    }

    @Override
    public RetrievePasswordPresent bindPresent() {
        return new RetrievePasswordPresent();
    }

    @Override
    public void setListener() {
        get_yzm.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.get_yzm:
                getYzm();
                break;
            case R.id.btn_update:
                toUpdate();
                break;
            case R.id.register:
                toRegister();
                break;
            case R.id.login:
                toLogin();
                break;
        }
    }

    private void toLogin() {
        Router.newIntent(context)
                .to(LoginActivity.class)
                .launch();
        this.finish();
    }

    private void toRegister() {
        Router.newIntent(context)
                .to(RegisterActivity.class)
                .launch();
        this.finish();

    }

    private void toUpdate() {
        String phone = etAccount.getText().toString().trim();
        String code = yzm.getText().toString().trim();
        String passwordValue = password.getText().toString().trim();
        String passwordedValue = passworded.getText().toString().trim();
        if(phone.isEmpty()){
            ToastUtils.showToast("手机号不能为空！");
            return;
        }
        if(!RegularUtil.isPhone(phone)){
            ToastUtils.showToast("手机号格式错误!");
            return;
        }
        if(code.isEmpty()){
            ToastUtils.showToast("短信验证码不能为空！");
            return;
        }
        if(passwordedValue.isEmpty() || passwordValue.isEmpty()){
            ToastUtils.showToast("新密码不能为空！");
            return;
        }

        if(!passwordedValue.equals(passwordValue)){
            ToastUtils.showToast("两次密码输入不一致！请重新输入！");
            return;
        }
        getP().changePassword(phone,code,passwordValue);

    }


    public void getYzm() {
        String phone = etAccount.getText().toString().trim();
        Log.i("yzm_phone",phone);
        if(phone.isEmpty()){
            ToastUtils.showToast("手机号不能为空！");
            return;
        }
        if(!RegularUtil.isPhone(phone)){
            ToastUtils.showToast("手机号格式错误！");
            return;
        }
        get_yzm.setEnabled(false);
        CountDownTimer timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                get_yzm.setText(millisUntilFinished / 1000+"");
            }

            @Override
            public void onFinish() {
                get_yzm.setEnabled(true);
                get_yzm.setText("获取验证码");
            }
        };
        timer.start();
        getP().getShortMessage(phone);
    }

    

    public void checkMsgResault(BaseStatusModel baseStatusModel) {
        if (baseStatusModel.getRespCode() != 0){
            ToastUtils.showToast(baseStatusModel.getRespMsg());
            return;
        }else{
            ToastUtils.showToast("短信验证码已发送成功！");
            return;
        }

    }

    public void checkResault(BaseStatusModel baseStatusModel, String phone, String password) {
        if(baseStatusModel.getRespCode() != 0){
            ToastUtils.showToast(baseStatusModel.getRespMsg());
            return;
        }else{
            ToastUtils.showToast("密码修改成功！");
            toLogin();
            return;
        }
    }
}
