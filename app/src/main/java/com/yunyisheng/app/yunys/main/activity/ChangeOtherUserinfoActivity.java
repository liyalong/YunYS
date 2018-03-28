package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.model.GetOtherinfoBean;
import com.yunyisheng.app.yunys.main.present.ChangeOtherPresent;
import com.yunyisheng.app.yunys.utils.RegularUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChangeOtherUserinfoActivity extends BaseActivity<ChangeOtherPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_ok)
    TextView teOk;
    @BindView(R.id.te_username)
    EditText teUsername;
    @BindView(R.id.te_userphone)
    EditText teUserphone;
    @BindView(R.id.te_useremail)
    EditText teUseremail;
    @BindView(R.id.te_userbumen)
    TextView teUserbumen;
    @BindView(R.id.rl_bumen)
    RelativeLayout rlBumen;
    @BindView(R.id.te_userzhiwei)
    EditText teUserzhiwei;
    @BindView(R.id.te_userjiaose)
    TextView teUserjiaose;
    @BindView(R.id.rl_role)
    RelativeLayout rlRole;
    @BindView(R.id.cb_isshowphone)
    CheckBox cbIsshowphone;
    private int bumenid;
    private int roleid;
    private boolean isshowphone = true;
    private GetOtherinfoBean getOtherinfoBean;
    private boolean isnull;
    private int userId;
    private String userphone;
    private String num;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        getOtherinfoBean = (GetOtherinfoBean) intent.getSerializableExtra("otherinfo");
        if (getOtherinfoBean != null) {
            isnull = false;
        } else {
            isnull = true;
        }
        if (!isnull) {
            userId = getOtherinfoBean.getRespBody().getEnterpriseUser().getUserId();
            teUsername.setText(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserName());
            boolean isshow=getOtherinfoBean.getRespBody().getEnterpriseUser().isUserIsShow();
            userphone = getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPhone();
            num = userphone.substring(0, 3) + "****"
                    + userphone.substring(7, userphone.length());
            if (!isshow){
                teUserphone.setText(num);
            }else {
                teUserphone.setText(userphone);
            }
            teUserzhiwei.setText(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserJobTitle());
            teUseremail.setText(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserMailbox());
            cbIsshowphone.setChecked(!isshow);
//            teUsername.setText(getOtherinfoBean.getRespBody().getUserName());
//            teUsername.setText(getOtherinfoBean.getRespBody().getUserName());
        }
        cbIsshowphone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isshowphone = !isChecked;
                if (isChecked){
                }else {
                    teUserphone.setText(userphone);
                }
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_change_other_userinfo;
    }

    @Override
    public ChangeOtherPresent bindPresent() {
        return new ChangeOtherPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teOk.setOnClickListener(this);
        rlBumen.setOnClickListener(this);
        rlRole.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.te_ok:
                String useremail = teUseremail.getText().toString().trim();
                String username = teUsername.getText().toString().trim();
                String userphone = teUserphone.getText().toString().trim();
                String userzhiwei = teUserzhiwei.getText().toString().trim();
                if (isnull(username)) {
                    ToastUtils.showToast("请输入用户名");
                } else {
                    if (!RegularUtil.isPhone(userphone)) {
                        ToastUtils.showToast("请输入正确格式手机号");
                    } else {
                        if (!RegularUtil.isEmail(useremail)) {
                            ToastUtils.showToast("请输入正确格式邮箱");
                        } else {
                            if (isnull(userzhiwei)) {
                                ToastUtils.showToast("请输入员工职位");
                            } else {
//                                        if (bumenid == 0) {
//                                            ToastUtils.showToast("请输入选择员工部门");
//                                        } else {
//                                            if (roleid == 0) {
//                                                ToastUtils.showToast("请输入选择员工通讯录角色");
//                                            } else {
//                                                getP().inviteWorker(username, usersex, userphone, useremail,
//                                                        userworkernum, userzhiwei, bumenid, roleid);
//                                            }
//                                        }
                                getP().changeOtherInf(username, userphone, useremail, userId, userzhiwei, isshowphone);
                            }
                        }
                    }
                }
                break;
            case R.id.rl_bumen:
                startActivityForResult(new Intent(this, BumenActivity.class), 8);
                break;
            case R.id.rl_role:
                startActivityForResult(new Intent(this, RoleActivity.class), 6);
                break;
        }
    }

    public boolean isnull(String string) {
        if (string == null || string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public void setResultFinish(){
        Intent intent = getIntent();
        setResult(5,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 8:
                bumenid = data.getIntExtra("bumenid", 0);
                String bumenname = data.getStringExtra("bumenname");
                teUserbumen.setText(bumenname);
                break;
            case 6:
                roleid = data.getIntExtra("roleid", 0);
                String rolename = data.getStringExtra("rolename");
                teUserjiaose.setText(rolename);
                break;
        }
    }
}
