package com.yunyisheng.app.yunys.login.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseStatusModel;
import com.yunyisheng.app.yunys.login.present.RegisterPresent;
import com.yunyisheng.app.yunys.utils.RegularUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/5.
 */

public class RegisterActivity extends BaseActivity<RegisterPresent> {
    @BindView(R.id.company_name)
    EditText companyName;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.phone_num)
    EditText phoneNum;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.yzm)
    EditText yzm;
    @BindView(R.id.get_yzm)
    Button get_yzm;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.xy)
    RadioButton xy;
    @BindView(R.id.toLogin)
    TextView toLogin;


    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.acitvity_register;
    }

    @Override
    public RegisterPresent bindPresent() {
        return new RegisterPresent();
    }

    @Override
    public void setListener() {
        get_yzm.setOnClickListener(this);
        toLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {

            case R.id.toLogin:
                toLoginView();
                break;
            case R.id.get_yzm:
                getYzm();
                break;
            case R.id.btn_register:
                register_company();
                break;
        }
    }

    private void register_company() {
        String company_name = companyName.getText().toString().trim();
        String user_name = userName.getText().toString().trim();
        String phone = phoneNum.getText().toString().trim();
        String passwordValue = password.getText().toString().trim();
        String code = yzm.getText().toString().trim();
        if (company_name.isEmpty()){
            ToastUtils.showToast("公司名称不能为空！");
            return;
        }
        if (user_name.isEmpty()){
            ToastUtils.showToast("姓名不能为空！");
            return;
        }
        if(phone.isEmpty()){
            ToastUtils.showToast("手机号不能为空!");
            return;
        }
        if(!RegularUtil.isPhone(phone)){
            ToastUtils.showToast("手机号格式错误！");
            return;
        }
        if(passwordValue.isEmpty()){
            ToastUtils.showToast("密码不能为空！");
            return;
        }
        if(!RegularUtil.isPsw(passwordValue)){
            ToastUtils.showToast("密码必须大于6位！");
            return;
        }
        if(code.isEmpty()){
            ToastUtils.showToast("验证码不能为空！");
            return;
        }
        getP().registerCompany(company_name,user_name,phone,passwordValue,code);

    }

    private void toLoginView() {
        Router.newIntent(context)
                .to(LoginActivity.class)
                .launch();
        this.finish();
    }
    public void getYzm() {
        String phone = phoneNum.getText().toString().trim();
//        Toast.makeText(this,phone,Toast.LENGTH_SHORT);
        if(phone.isEmpty()){
            ToastUtils.showToast("手机号不能为空！");
            return;
        }
        if(!RegularUtil.isPhone(phone)){
            ToastUtils.showToast("手机号者格式错误！");
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
        if(baseStatusModel.getRespCode() != 200){
            ToastUtils.showToast(baseStatusModel.getRespMsg());
            return;
        }else{
            ToastUtils.showToast("短信验证码已发送！");
            return;
        }
    }

    

    public void checkRegiterInfo(BaseStatusModel baseStatusModel) {
        if(baseStatusModel.getRespCode() != 200){
            ToastUtils.showToast(baseStatusModel.getRespMsg());
            return;
        }else{
            ToastUtils.showToast("注册成功！");
            toLoginView();
        }
    }

}
