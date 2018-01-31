package com.yunyisheng.app.yunys.project.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.activity.NoticeDeatilActivity;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.adapter.KnowledgeFileListAdapter;
import com.yunyisheng.app.yunys.project.bean.FileItem;
import com.yunyisheng.app.yunys.project.bean.KnowledgeBean;
import com.yunyisheng.app.yunys.project.model.KnowledgDetailModel;
import com.yunyisheng.app.yunys.project.model.KnowledgeListModel;
import com.yunyisheng.app.yunys.project.present.KnowledgeDetailPresent;
import com.yunyisheng.app.yunys.utils.CallOtherOpeanFile;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.FileCache;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by liyalong on 2018/1/18.
 * 设备的相关知识详情
 */

public class KnowledgeDetailActivity extends BaseActivity<KnowledgeDetailPresent> implements KnowledgeFileListAdapter.Callback {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.knowledge_title)
    TextView knowledgeTitle;
    @BindView(R.id.knowledge_detail)
    WebView knowledgeDetail;
    @BindView(R.id.knowledge_file_box)
    LinearLayout knowledgeFileBox;
   @BindView(R.id.knowledge_file_list)
    ListView knowledgeFileList;

    private String projectId;
    private String knowledgeId;
    private KnowledgeBean knowledgeBean;
    private KnowledgeFileListAdapter filesAdapter;
    private String downloadUrl;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        this.projectId = getIntent().getStringExtra("projectId");
        this.knowledgeId = getIntent().getStringExtra("knowledgeId");
        getP().getKnowledgeDetail(projectId,knowledgeId);
        this.downloadUrl = Api.BASE_PATH + "project/knowledge/file/download/"+projectId;

    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_knowledge_detail;
    }

    @Override
    public KnowledgeDetailPresent bindPresent() {
        return new KnowledgeDetailPresent();
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

    public void setAdapter(KnowledgDetailModel knowledgDetailModel) {
        this.knowledgeBean = knowledgDetailModel.getRespBody();
        knowledgeTitle.setText(knowledgeBean.getKnowledgeName().toString());
        knowledgeDetail.loadDataWithBaseURL(null,knowledgeBean.getKnowledgeBase(),"text/html","utf-8",null);
        if (knowledgeBean.getFiles().size() > 0){
            knowledgeFileBox.setVisibility(View.VISIBLE);
            filesAdapter = new KnowledgeFileListAdapter(context,knowledgeBean.getFiles(),projectId,this);
            knowledgeFileList.setAdapter(filesAdapter);
        }
    }

    @Override
    public void click(View view) {
        int position = (int) view.getTag();
        FileItem fileItem = knowledgeBean.getFiles().get(position);
        if (CommonUtils.initDownPath(FileCache.path + fileItem.getFname())) {
            File outFile = new File(FileCache.path + fileItem.getFname());
            CallOtherOpeanFile callOtherOpeanFile = new CallOtherOpeanFile();
            callOtherOpeanFile.openFile(KnowledgeDetailActivity.this, outFile);
        } else {
            getP().downloadFujin(downloadUrl,fileItem.getFname(),fileItem.getFileId());
        }
    }
}
