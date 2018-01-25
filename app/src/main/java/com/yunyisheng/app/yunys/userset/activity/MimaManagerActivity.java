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
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.userset.present.UpdatePasswordPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;

/**
 * @author fuduo
 * @time 2018/1/18  18:01
 * @describe 密码管理activity
 */
public class MimaManagerActivity extends BaseActivity<UpdatePasswordPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.ed_yuanmima)
    EditText edYuanmima;
    @BindView(R.id.ed_newmima)
    EditText edNewmima;
    @BindView(R.id.ed_newmimaagain)
    EditText edNewmimaagain;
    @BindView(R.id.btn_queren)
    Button btnQueren;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText("密码");
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_mima_manager;
    }

    @Override
    public UpdatePasswordPresent bindPresent() {
        return new UpdatePasswordPresent();
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
                String newmima=edNewmima.getText().toString().trim();
                String newmimaagain=edNewmimaagain.getText().toString().trim();
                String oldmima=edYuanmima.getText().toString().trim();
                if (oldmima!=null&&!oldmima.equals("")){
                    if (newmima!=null&&!newmima.equals("")){
                        if (newmimaagain!=null&&!newmimaagain.equals("")){
                             if (newmima.equals(newmimaagain)){
                                 updatePasswoed(oldmima,newmima);
                             }else {
                                 ToastUtils.showLongToast("密码不一致");
                             }
                        }else {
                            ToastUtils.showLongToast("请再次填写新密码");
                        }
                    }else {
                        ToastUtils.showLongToast("请填写新密码");
                    }
                }else {
                    ToastUtils.showLongToast("请输入原密码");
                }
                break;
        }
    }

    private void updatePasswoed(String oldpassword,String newpassword){
        getP().updatePassword(oldpassword,newpassword);
    }

    /**
     * @author fuduo
     * @time 2018/1/21  10:48
     * @describe 检查是否修改成功
     */
    public void checkIssuccess(BaseModel baseModel){
        if (baseModel.getRespCode()==0){
            SharedPref.getInstance(context).remove("TOKEN");
            startActivity(new Intent(MimaManagerActivity.this, LoginActivity.class));
            finish();
        }
        ToastUtils.showLongToast(baseModel.getRespMsg());
    }

}
