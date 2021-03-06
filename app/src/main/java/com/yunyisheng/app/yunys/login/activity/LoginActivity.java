package com.yunyisheng.app.yunys.login.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.MainActivity;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.BaseStatusModel;
import com.yunyisheng.app.yunys.login.model.LoginModel;
import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.login.present.LoginPresent;
import com.yunyisheng.app.yunys.main.service.MessageService;
import com.yunyisheng.app.yunys.mqtt.MQTTService;
import com.yunyisheng.app.yunys.utils.AndroidIDUtil;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.RegularUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.eclipse.paho.android.service.MqttService;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2017/12/16.
 * 登录activity
 */

public class LoginActivity extends BaseActivity<LoginPresent> {
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
    @BindView(R.id.yzm)
    EditText yzm;
    @BindView(R.id.get_yzm)
    Button getYzm;
    @BindView(R.id.yzm_layout)
    LinearLayout yzmLayout;
    private long exitTime = 0;

    @Override
    public void initView() {
//        setSwipeEnabled(false);
        ButterKnife.bind(this);
    }

    /**
     * 初始化
     */
    @Override
    public void initAfter() {
        if (CommonUtils.isServiceRunning(this, "com.yunyisheng.app.yunys.main.service.MessageService")) {
            stopService(new Intent(mContext, MessageService.class));
        }
        if (CommonUtils.isServiceRunning(this, "org.eclipse.paho.android.service.MqttService")) {
            stopService(new Intent(mContext, MqttService.class));
        }

        String errorlog = getIntent().getStringExtra("errorlog");
        if (errorlog!=null&&!errorlog.equals("")){
            showErrorlog(errorlog);
        }
    }
    
    private void showErrorlog(String string){
        final Dialog mErrorDialog = new Dialog(this, R.style.dialog_bottom_full);
        mErrorDialog.setCanceledOnTouchOutside(true);
        mErrorDialog.setCancelable(true);
        Window window = mErrorDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        View view1 = View.inflate(this, R.layout.dialog_warning_login, null);
        TextView text = (TextView) view1
                .findViewById(R.id.text);
        text.setText(string);
        RelativeLayout rl_ok = (RelativeLayout) view1
                .findViewById(R.id.rl_ok);

        rl_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mErrorDialog.dismiss();
            }
        });
        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mErrorDialog.show();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresent bindPresent() {
        return new LoginPresent();
    }

    @Override
    public void setListener() {
        btnLogin.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
        getYzm.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.register:
                toRegisiter();
                break;
            case R.id.forgetPassword:
                toRetrievePassword();
                break;
            case R.id.get_yzm:
                getYzm();
                break;
        }
    }


    private void toRetrievePassword() {
        Router.newIntent(context)
                .to(RetrievePassword.class)
                .launch();
    }

    private void toRegisiter() {
        Router.newIntent(context)
                .to(RegisterActivity.class)
                .launch();
    }

    private void login() {
        String userPhone = etAccount.getText().toString().trim();
        String userPassword = etPassword.getText().toString().trim();
        String uuid = AndroidIDUtil.getDeviceid();
        String yzmValue = yzm.getText().toString().trim();
        int yzmLayoutStatus = yzmLayout.getVisibility();

        Log.i("LOGIN", userPhone + "----" + userPassword + "----" + uuid);
        if (TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(userPassword)) {
            Log.i("LOGIN", "phone or password is empty!");
            ToastUtils.showToast("手机号或者密码不能为空！");
            return;
        }
        if (yzmLayoutStatus == 0) {
            if (TextUtils.isEmpty(yzmValue)) {
                ToastUtils.showToast("短信验证码不能为空！");
                return;
            }
        }
        if (yzmValue != null && !yzmValue.equals("")) {
            getP().Login(userPhone, userPassword, uuid, yzmValue);
        } else {
            getP().Login(userPhone, userPassword, uuid, null);
        }

    }

    /**
     * 验证登录返回结果
     *
     * @param loginModel
     */
    public void checkLogin(LoginModel loginModel) {
        if (loginModel.getRespCode() == 1) {
            ToastUtils.showToast(loginModel.getRespMsg());
            return;
        } else if (loginModel.getRespCode() == 2) {
            yzmLayout.setVisibility(View.VISIBLE);
            ToastUtils.showToast(loginModel.getRespMsg());
            return;
        } else if (loginModel.getRespCode() == 0) {
            saveUserToken(loginModel.getRespBody());
            toMain();
        }
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

    public void getYzm() {
        String phone = etAccount.getText().toString().trim();
        Log.i("yzm_phone", phone);
        if (phone.isEmpty()) {
            ToastUtils.showToast("手机号不能为空！");
            return;
        }
        if (!RegularUtil.isPhone(phone)) {
            ToastUtils.showToast("手机号格式错误！");
            return;
        }
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
        getP().getShortMessage(phone);
    }


    public void checkMsgResault(BaseStatusModel baseStatusModel) {
        if (baseStatusModel.getRespCode() != 0) {
            ToastUtils.showToast(baseStatusModel.getRespMsg());
            return;
        } else {
            ToastUtils.showToast("短信验证码已发送成功！");
            return;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
//				NotificationManager nm =(NotificationManager)BottomMenuActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
//				nm.cancelAll();//清空通知栏
//				Session.onKillProcess();
//				ExampleApplication.exit();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
//				System.exit(0);

            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
