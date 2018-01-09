package com.yunyisheng.app.yunys.login.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseStatusModel;
import com.yunyisheng.app.yunys.login.present.RegisterPresent;
import com.yunyisheng.app.yunys.utils.RegularUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/5.
 */

public class RegisterActivity extends XActivity<RegisterPresent>{
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
    public void initData(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.acitvity_register;
    }

    @Override
    public RegisterPresent newP() {
        return new RegisterPresent();
    }



    @OnClick({R.id.yzm, R.id.toLogin,R.id.get_yzm,R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yzm:
                break;
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
            showToastMsg("公司名称不能为空！");
            return;
        }
        if (user_name.isEmpty()){
            showToastMsg("姓名不能为空！");
            return;
        }
        if(phone.isEmpty()){
            showToastMsg("手机号不能为空!");
            return;
        }
        if(!RegularUtil.isPhone(phone)){
            showToastMsg("手机号格式错误！");
            return;
        }
        if(passwordValue.isEmpty()){
            showToastMsg("密码不能为空！");
            return;
        }
        if(!RegularUtil.isPsw(passwordValue)){
            showToastMsg("密码必须大于6位！");
            return;
        }
        if(code.isEmpty()){
            showToastMsg("验证码不能为空！");
            return;
        }
        //getP().registerCompany(company_name,user_name,phone,passwordValue,code);

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
            showToastMsg("手机号不能为空！");
            return;
        }
        if(!RegularUtil.isPhone(phone)){
            showToastMsg("手机号者格式错误！");
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
        if(baseStatusModel.getStatus() != 200){
            showToastMsg(baseStatusModel.getMessage());
            return;
        }else{
            showToastMsg("短信验证码已发送！");
            return;
        }
    }

    public void showToastMsg(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void checkRegiterInfo(BaseStatusModel baseStatusModel) {
        if(baseStatusModel.getStatus() != 200){
            showToastMsg(baseStatusModel.getMessage());
            return;
        }else{
            showToastMsg("注册成功！");
            toLoginView();
        }
    }

}
