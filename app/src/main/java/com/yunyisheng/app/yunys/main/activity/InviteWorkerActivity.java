package com.yunyisheng.app.yunys.main.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.SelectSexSpinnerAdapter;
import com.yunyisheng.app.yunys.main.model.SexBean;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

public class InviteWorkerActivity extends BaseActivity {

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
    private List<SexBean> list = new ArrayList<>();
    private boolean isshousuo = true;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        list.add(new SexBean(0, "男"));
        list.add(new SexBean(1, "女"));
        //设置spinner展开项的宽
        //spSex.setDropDownWidth(ScreenUtils.getScreenHeight(InviteWorkerActivity.this));
        SelectSexSpinnerAdapter adapter = new SelectSexSpinnerAdapter(InviteWorkerActivity.this, list);
        //绑定 Adapter到控件
        spSex.setAdapter(adapter);
        spSex.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (isshousuo) {
                            isshousuo = false;
                            imgStatus.setBackgroundResource(R.mipmap.zhankai);
                        } else {
                            isshousuo = true;
                            imgStatus.setBackgroundResource(R.mipmap.shouqi);
                        }
                        break;
                }
                return false;
            }
        });

        spSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                ToastUtils.showLongToast("你点击的是:" + list.get(pos).getSexnum() + list.get(pos).getSexname());
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
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_invite_worker;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }
}
