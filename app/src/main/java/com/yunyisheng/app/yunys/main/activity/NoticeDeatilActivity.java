package com.yunyisheng.app.yunys.main.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.model.NoticeDetailBean;
import com.yunyisheng.app.yunys.main.present.NoticeDetaiPresent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fuduo
 * @time 2018/1/11  16:56
 * @describe 公告详情
 */
public class NoticeDeatilActivity extends BaseActivity<NoticeDetaiPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_more)
    ImageView teMore;
    @BindView(R.id.te_noticetitle)
    TextView teNoticetitle;
    @BindView(R.id.te_noticetime)
    TextView teNoticetime;
    @BindView(R.id.te_noticesender)
    TextView teNoticesender;
    @BindView(R.id.te_noticedeatils)
    TextView teNoticedeatils;
    @BindView(R.id.te_fujina_type)
    TextView teFujinaType;
    @BindView(R.id.gv_fujianlist)
    GridView gvFujianlist;
    private int noticeid;
    private int type;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        noticeid = intent.getIntExtra("noticeid", 0);
    }

    @Override
    public void initAfter() {
        if (type==0){
            getP().getMineSendNotice(noticeid);
            teMore.setVisibility(View.VISIBLE);
        }else {
            getP().getSendMineNotice(noticeid);
            teMore.setVisibility(View.GONE);
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_notice_deatil;
    }

    @Override
    public NoticeDetaiPresent bindPresent() {
        return new NoticeDetaiPresent();
    }


    public void getResultDetail(NoticeDetailBean noticeDetailBean){
         if (noticeDetailBean.getRespCode()==0){
             teNoticetitle.setText(noticeDetailBean.getRespBody().getTitle());
             teNoticedeatils.setText(noticeDetailBean.getRespBody().getContent());
             teNoticesender.setText("发布人："+noticeDetailBean.getRespBody().getCreateUserName());
             teNoticetime.setText(noticeDetailBean.getRespBody().getCreateTime());
         }
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teMore.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.te_more:
                createDeleteNotice();
                break;
        }
    }

    /**
     * @author fuduo
     * @time 2018/1/11  17:14
     * @describe 删除公告对话框
     */
    private void createDeleteNotice() {
        final Dialog mShareDialog = new Dialog(this, R.style.dialog_bottom_full);
        mShareDialog.setCanceledOnTouchOutside(true);
        mShareDialog.setCancelable(true);
        Window window = mShareDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View view1 = View.inflate(this, R.layout.dialog_delete_notice, null);
        Button butdelete = (Button) view1.findViewById(R.id.but_delete);
        butdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getP().deleteNotice(noticeid);
            }
        });
        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mShareDialog.show();
    }

}
