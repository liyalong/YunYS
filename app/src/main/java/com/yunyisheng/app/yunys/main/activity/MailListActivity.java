package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/16  21:24
 * @describe 通讯录
 */
public class MailListActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_title)
    TextView teTitle;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.rl_organizationframe)
    RelativeLayout rlOrganizationframe;
    @BindView(R.id.rl_projectframe)
    RelativeLayout rlProjectframe;
    @BindView(R.id.rl_arrangework)
    RelativeLayout rlArrangework;
    @BindView(R.id.rl_invite)
    RelativeLayout rlInvite;
    @BindView(R.id.elv_organizationframe)
    ExpandableListView elvOrganizationframe;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    private String sousuo_neirong;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        teTitle.setText(R.string.tongxunlu);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sousuo_neirong = edSearch.getText().toString();
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(MailListActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (sousuo_neirong == null || sousuo_neirong.equals("")) {
                        ToastUtils.showToast("搜索内容不能为空");
                    } else {

                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_mail_list;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        rlOrganizationframe.setOnClickListener(this);
        rlProjectframe.setOnClickListener(this);
        rlArrangework.setOnClickListener(this);
        rlInvite.setOnClickListener(this);
        imgClear.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_organizationframe:
                break;
            case R.id.rl_projectframe:
                break;
            case R.id.rl_arrangework:
                startActivity(new Intent(MailListActivity.this, SelectPeopleActivity.class));
                break;
            case R.id.rl_invite:
                break;
            case R.id.img_clear:
                edSearch.setText("");
                break;
        }
    }

}
