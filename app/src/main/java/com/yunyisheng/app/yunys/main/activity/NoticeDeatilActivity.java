package com.yunyisheng.app.yunys.main.activity;

import android.app.Dialog;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author fuduo
 * @time 2018/1/11  16:56
 * @describe 公告详情
 */
public class NoticeDeatilActivity extends BaseActivity {

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

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initAfter() {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_notice_deatil;
    }

    @Override
    public XPresent bindPresent() {
        return null;
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
     * @describe 删除图片对话框
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

            }
        });
        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mShareDialog.show();
    }

}
