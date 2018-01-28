package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.SelectSexSpinnerAdapter;
import com.yunyisheng.app.yunys.main.model.SexBean;
import com.yunyisheng.app.yunys.main.present.InviteWorkerPresent;
import com.yunyisheng.app.yunys.utils.RegularUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteWorkerActivity extends BaseActivity<InviteWorkerPresent> {

    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.sp_sex)
    Spinner spSex;
    @BindView(R.id.ed_phonenum)
    EditText edPhonenum;
    @BindView(R.id.ed_email)
    EditText edEmail;
    @BindView(R.id.ed_workernum)
    EditText edWorkernum;
    @BindView(R.id.ed_zhiwei)
    EditText edZhiwei;
    @BindView(R.id.rl_select_bumen)
    RelativeLayout rlSelectBumen;
    @BindView(R.id.rl_role)
    RelativeLayout rlRole;
    @BindView(R.id.btn_queren)
    Button btnQueren;
    @BindView(R.id.img_status)
    ImageView imgStatus;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_bumen)
    TextView teBumen;
    @BindView(R.id.te_role)
    TextView teRole;
    private List<SexBean> list = new ArrayList<>();
    private boolean isshousuo = true;
    private String usersex;
    private int bumenid;
    private int roleid;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        list.add(new SexBean(0, "男"));
        list.add(new SexBean(1, "女"));
        imgStatus.setVisibility(View.GONE);
        //设置spinner展开项的宽
        //spSex.setDropDownWidth(ScreenUtils.getScreenHeight(InviteWorkerActivity.this));
        SelectSexSpinnerAdapter adapter = new SelectSexSpinnerAdapter(InviteWorkerActivity.this, list);
        //绑定 Adapter到控件
        spSex.setAdapter(adapter);
//        spSex.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_UP:
//                        if (isshousuo) {
//                            isshousuo = false;
//                            imgStatus.setBackgroundResource(R.mipmap.zhankai);
//                        } else {
//                            isshousuo = true;
//                            imgStatus.setBackgroundResource(R.mipmap.shouqi);
//                        }
//                        break;
//                }
//                return false;
//            }
//        });

        spSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                usersex = list.get(pos).getSexname();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlSelectBumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InviteWorkerActivity.this, BumenActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        rlRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InviteWorkerActivity.this, RoleActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        btnQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = edEmail.getText().toString().trim();
                String userphone = edPhonenum.getText().toString().trim();
                String username = edUsername.getText().toString().trim();
                String userworkernum = edWorkernum.getText().toString().trim();
                String userzhiwei = edZhiwei.getText().toString().trim();
                if (isnull(username)) {
                    ToastUtils.showToast("请输入用户名");
                } else {
                    if (isnull(usersex)) {
                        ToastUtils.showToast("请选择性别");
                    } else {
                        if (!RegularUtil.isPhone(userphone)) {
                            ToastUtils.showToast("请输入正确格式手机号");
                        } else {
                            if (!RegularUtil.isEmail(useremail)) {
                                ToastUtils.showToast("请输入正确格式邮箱");
                            } else {
                                if (isnull(userworkernum)) {
                                    ToastUtils.showToast("请输入员工编号");
                                } else {
                                    if (isnull(userzhiwei)) {
                                        ToastUtils.showToast("请输入员工职位");
                                    } else {
                                        if (bumenid == 0) {
                                            ToastUtils.showToast("请输入选择员工部门");
                                        } else {
                                            if (roleid == 0) {
                                                ToastUtils.showToast("请输入选择员工通讯录角色");
                                            } else {
                                                getP().inviteWorker(username, usersex, userphone, useremail,
                                                        userworkernum, userzhiwei, bumenid, roleid);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public boolean isnull(String string) {
        if (string == null || string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_invite_worker;
    }

    @Override
    public InviteWorkerPresent bindPresent() {
        return new InviteWorkerPresent();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 8:
                bumenid = data.getIntExtra("bumenid", 0);
                String bumenname = data.getStringExtra("bumenname");
                teBumen.setText(bumenname);
                break;
            case 6:
                roleid = data.getIntExtra("roleid", 0);
                String rolename = data.getStringExtra("rolename");
                teRole.setText(rolename);
                break;
        }
    }
}
