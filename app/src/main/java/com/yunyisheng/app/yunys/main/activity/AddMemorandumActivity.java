package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.present.UpdateMempPresent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fuduo
 * @time 2018/1/16  21:24
 * @describe 添加备忘录
 */
public class AddMemorandumActivity extends BaseActivity<UpdateMempPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_addok)
    TextView teAddok;
    @BindView(R.id.ed_memorandum)
    EditText edMemorandum;
    private int memid;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        edMemorandum.addTextChangedListener(mTextWatcher);
    }

    @Override
    public void initAfter() {
        Intent intent = getIntent();
        memid = intent.getIntExtra("memid", 0);
        String str_memoblue = intent.getStringExtra("memovlue");
        if (str_memoblue!=null&&!str_memoblue.equals("")&&!str_memoblue.equals("null")){
            edMemorandum.setText(str_memoblue);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_add_memorandum;
    }

    @Override
    public UpdateMempPresent bindPresent() {
        return new UpdateMempPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teAddok.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.te_addok:
                String te_memo = edMemorandum.getText().toString().trim();
                if (memid != 0) {
                    getP().updateMemo(te_memo, memid);
                } else {
                    getP().addMemo(te_memo);
                }
                break;
        }
    }

    //监听是否输入
    TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            if (s.length() > 0) {
                teAddok.setVisibility(View.VISIBLE);
            } else {
                teAddok.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

//    public void getAddResult(BaseModel baseModel) {
//        if (baseModel.getRespCode() == 0) {
//            finish();
//        }
//    }
//
//    public void getDeleteResult(BaseModel baseModel) {
//        if (baseModel.getRespCode() == 0) {
//            finish();
//        }
//    }

}
