package com.yunyisheng.app.yunys.userset.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

public class ClearCatchActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_catchsize)
    TextView teCatchsize;
    @BindView(R.id.te_usecatchsize)
    TextView teUsecatchsize;
    @BindView(R.id.but_clearcatch)
    Button butClearcatch;
    String type;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        String catchsize = null;
        try {
            catchsize = CommonUtils.getTotalCacheSize(ClearCatchActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long allzonesizeK = CommonUtils.getTotalInternalMemorySize();
        long allzonesizeM = CommonUtils.getTotalInternalMemorySize() / 1024;
        try {
            teCatchsize.setText(catchsize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (catchsize != null && !catchsize.equals("")) {
            type = catchsize.substring(catchsize.length() - 2, catchsize.length());
        }
        if (type.equals("KB")) {
            String usesize = catchsize.substring(0, catchsize.length() - 2);
            teUsecatchsize.setText("占据手机" + String.format("%.2f", Double.parseDouble(Long.parseLong(usesize) / allzonesizeK + "")) + "%存储空间");
        }

        if (type.equals("MB")) {
            String usesize = catchsize.substring(0, catchsize.length() - 2);
            teUsecatchsize.setText("占据手机" + String.format("%.2f", Double.parseDouble(Long.parseLong(usesize) / allzonesizeM + "")) + "%存储空间");
        }

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_clear_catch;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        butClearcatch.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.but_clearcatch:
                break;
        }
    }

}
