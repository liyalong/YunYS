package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.bean.PLCValueBean;
import com.yunyisheng.app.yunys.project.model.PLCListModel;
import com.yunyisheng.app.yunys.project.present.PLCDetailPresent;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * plc详情页面
 */
public class PLCDetailActivity extends BaseActivity<PLCDetailPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.device_detail_title)
    TextView deviceDetailTitle;
    @BindView(R.id.plc_detail)
    WebView plcDetail;
    private List<PLCValueBean> dataList;
    Timer timer;
    private String plcName;
    private String plcUnits;
    private String plcDesc;
    @Override
    public void initView() {
        ButterKnife.bind(this);
        plcName = getIntent().getStringExtra("plcName");
        plcUnits = getIntent().getStringExtra("plcUnits");
        plcDesc = getIntent().getStringExtra("plcDesc");
        deviceDetailTitle.setText(plcDesc+" 最新10分钟数据");
        plcDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings plcDetailSettings = plcDetail.getSettings();
        plcDetailSettings.setJavaScriptEnabled(true);

        plcDetail.loadUrl("file:///android_asset/plcDetail.html");


        plcDetail.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getP().getPlcDetail(plcName);
                    }
                },0,10000);
            }
        });

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_plcdetail;
    }

    @Override
    public PLCDetailPresent bindPresent() {
        return new PLCDetailPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }

    public void setPLCData(PLCListModel plcListModel) {
        if (plcListModel.getRespBody().size() > 0){
            dataList = plcListModel.getRespBody();
            plcDetail.post(new Runnable() {
                @Override
                public void run() {
                    plcDetail.loadUrl("javascript:createCharts('"+ JSON.toJSONString(dataList)+"','"+plcUnits+"')");
                }
            });

        }else {
            ToastUtils.showToast("暂无数据！");
        }
    }
}
