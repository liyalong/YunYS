package com.yunyisheng.app.yunys.userset.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/18  18:01
 * @describe 个人信息activity
 */
public class MyInformationActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    //    @BindView(R.id.te_ok)
//    TextView teOk;
    @BindView(R.id.rl_ed_username)
    RelativeLayout rlEdUsername;
    @BindView(R.id.rl_ed_userphone)
    RelativeLayout rlEdUserphone;
    @BindView(R.id.rl_ed_usereamil)
    RelativeLayout rlEdUsereamil;
    @BindView(R.id.te_userbumen)
    TextView teUserbumen;
    @BindView(R.id.te_userzhiwei)
    TextView teUserzhiwei;
    @BindView(R.id.te_userjiaose)
    TextView teUserjiaose;
    @BindView(R.id.te_username)
    TextView teUsername;
    @BindView(R.id.te_userphone)
    TextView teUserphone;
    @BindView(R.id.te_useremail)
    TextView teUseremail;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {
        String username = SharedPref.getInstance(mContext).getString("username", "");
        String userphone = SharedPref.getInstance(mContext).getString("userphone", "");
        String userjob = SharedPref.getInstance(mContext).getString("userjob", "");
        String useremail = SharedPref.getInstance(mContext).getString("useremail", "");
        String userbumen = SharedPref.getInstance(mContext).getString("userbumen", "");
        String userrole = SharedPref.getInstance(mContext).getString("userrole", "");
        teUserbumen.setText(userbumen);
        teUserjiaose.setText(userrole);
        teUsername.setText(username);
        teUserzhiwei.setText(userjob);
        teUserphone.setText(userphone);
        teUseremail.setText(useremail);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_information;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
//        teOk.setOnClickListener(this);
        rlEdUsername.setOnClickListener(this);
        rlEdUserphone.setOnClickListener(this);
        rlEdUsereamil.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_ed_username:
//                Intent intent = new Intent(this, ChangeInformationActivity.class);
//                intent.putExtra("type", 2);
//                startActivityForResult(intent, 2);
                break;
            case R.id.rl_ed_userphone:
                startActivityForResult(new Intent(this, AccountSetActivity.class), 1);
                break;
            case R.id.rl_ed_usereamil:
                Intent intent1 = new Intent(this, ChangeInformationActivity.class);
                intent1.putExtra("type", 3);
                startActivityForResult(intent1, 3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            String username = SharedPref.getInstance(mContext).getString("username", "");
            teUsername.setText(username);
        } else if (resultCode == 3) {
            String useremail = SharedPref.getInstance(mContext).getString("useremail", "");
            teUseremail.setText(useremail);
        } else if (resultCode == 1) {
            String userphone = SharedPref.getInstance(mContext).getString("userphone", "");
            teUserphone.setText(userphone);
        }
    }
}
