package com.yunyisheng.app.yunys.login.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.main.activity.MainActivity;
import com.yunyisheng.app.yunys.login.model.LoginModel;
import com.yunyisheng.app.yunys.login.present.LoginPresent;
import com.yunyisheng.app.yunys.utils.AndroidIDUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2017/12/16.
 */

public class LoginActivity extends XActivity<LoginPresent> {
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.forgetPassword)
    TextView forgetPassword;


    @Override
    public void initData(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresent newP() {
        return new LoginPresent();
    }

    @OnClick({R.id.btn_login, R.id.register, R.id.forgetPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.register:
                toRegisiter();
                break;
            case R.id.forgetPassword:
                toRetrievePassword();
                break;
        }
    }

    private void toRetrievePassword() {
        Router.newIntent(context)
                .to(RetrievePassword.class)
                .launch();
        this.finish();
    }

    private void toRegisiter() {
        Router.newIntent(context)
                .to(RegisterActivity.class)
                .launch();
        this.finish();
    }

    private void login() {
        String userPhone = etAccount.getText().toString().trim();
        String userPassword = etPassword.getText().toString().trim();
        String uuid = AndroidIDUtil.getDeviceid();
        Log.i("LOGIN", userPhone + "----" + userPassword + "----" + uuid);
        if (TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(userPassword)) {
            Log.i("LOGIN", "phone or password is empty!");
            this.showToastMsg("手机号或者密码不能为空！");
            return;
        }
        getP().Login(userPhone, userPassword, uuid);
    }

    /**
     * 验证登录返回结果
     *
     * @param loginModel
     */
    public void checkLogin(LoginModel loginModel) {
        if (loginModel.getStatus() != 200) {
            showToastMsg(loginModel.getMessage());
            return;
        } else {
            saveUserToken(loginModel.getToken());
            toMain();
        }
    }

    public void showToastMsg(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void toMain() {
        Router.newIntent(context)
                .to(MainActivity.class)
                .launch();
        this.finish();
    }

    public void saveUserToken(String token) {
        SharedPref.getInstance(context).putString("TOKEN", token);
    }




}
