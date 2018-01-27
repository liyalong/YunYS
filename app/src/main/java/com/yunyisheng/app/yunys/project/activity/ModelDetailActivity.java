package com.yunyisheng.app.yunys.project.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.project.bean.ModelInfoBean;
import com.yunyisheng.app.yunys.project.model.ModelDetailModel;
import com.yunyisheng.app.yunys.project.present.ModelDetailPresent;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.glide.GlideDownLoadImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/25.
 */

public class ModelDetailActivity extends BaseActivity<ModelDetailPresent> {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.model_detail_title)
    TextView modelDetailTitle;
    @BindView(R.id.model_pic)
    ImageView modelPic;
    @BindView(R.id.model_detail_jbxx_drop)
    ImageView modelDetailJbxxDrop;
    @BindView(R.id.device_model_detail_status)
    TextView deviceModelDetailStatus;
    @BindView(R.id.to_model_device_list)
    RelativeLayout toModelDeviceList;
    @BindView(R.id.to_model_alarm_rules)
    RelativeLayout toModelAlarmRules;
    @BindView(R.id.model_detail_jbxx_box)
    RelativeLayout modelDetailJbxxBox;
    @BindView(R.id.to_model_knowledge)
    RelativeLayout toModelKnowledge;
    @BindView(R.id.model_pic_drop)
    ImageView modelPicDrop;
    @BindView(R.id.model_pic_box)
    RelativeLayout modelPicBox;


    private boolean jbxxBoxIsshow = true;
    private boolean MODELPICISSHOW = true;

    private String projectId;
    private String modelId;
    private String modelName;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.modelId = getIntent().getStringExtra("modelId");
        this.modelName = getIntent().getStringExtra("modelName");
        getP().getModelDetail(projectId, modelId);

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_model_detail;
    }

    @Override
    public ModelDetailPresent bindPresent() {
        return new ModelDetailPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        modelDetailJbxxDrop.setOnClickListener(this);
        modelPicDrop.setOnClickListener(this);
        toModelDeviceList.setOnClickListener(this);
        toModelAlarmRules.setOnClickListener(this);
        toModelKnowledge.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.model_detail_jbxx_drop:
                if (jbxxBoxIsshow == true) {
                    modelDetailJbxxBox.setVisibility(View.GONE);
                    modelDetailJbxxDrop.setImageResource(R.mipmap.icon_device_right);
                    jbxxBoxIsshow = false;
                } else {
                    modelDetailJbxxBox.setVisibility(View.VISIBLE);
                    modelDetailJbxxDrop.setImageResource(R.mipmap.icon_device_down);
                    jbxxBoxIsshow = true;
                }
                break;
            case R.id.model_pic_drop:
                if (MODELPICISSHOW){
                    modelPicBox.setVisibility(View.GONE);
                    modelPicDrop.setImageResource(R.mipmap.icon_device_right);
                    MODELPICISSHOW = false;
                }else {
                    modelPicBox.setVisibility(View.VISIBLE);
                    modelPicDrop.setImageResource(R.mipmap.icon_device_down);
                    MODELPICISSHOW = true;
                }
                break;
            case R.id.to_model_alarm_rules:
                Router.newIntent(context)
                        .to(ModelAlarmRulesActivity.class)
                        .putString("projectId",projectId)
                        .putString("modelId",modelId)
                        .putString("modelName",modelName)
                        .launch();
                break;
            case R.id.to_model_device_list:
                Router.newIntent(context)
                        .to(ModelDeviceListActivity.class)
                        .putString("projectId",projectId)
                        .putString("modelId",modelId)
                        .putString("modelName",modelName)
                        .launch();
                break;
            case R.id.to_model_knowledge:
                Router.newIntent(context)
                        .to(KnowledgeListActivity.class)
                        .putString("projectId",projectId)
                        .putString("modelId",modelId)
                        .putString("modelName",modelName)
                        .launch();
                break;
        }
    }

    public void setModelInfo(ModelDetailModel modelDetailModel) {
        ModelInfoBean modelInfoBean = modelDetailModel.getRespBody();
        String modelPicValue = modelInfoBean.getPcmBlueprint();
        if (modelPicValue != null && modelPicValue != "") {
            Bitmap bitmap = CommonUtils.stringtoBitmap(modelPicValue);
            GlideDownLoadImage.getInstance().loadBitmapImageRole(mContext, modelPic, bitmap);
        }
    }

}
