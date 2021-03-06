package com.yunyisheng.app.yunys.main.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.base.PressionListener;
import com.yunyisheng.app.yunys.main.adapter.NoticeFujianListAdapter;
import com.yunyisheng.app.yunys.main.model.AnnexBean;
import com.yunyisheng.app.yunys.main.model.NoticeDetailBean;
import com.yunyisheng.app.yunys.main.present.NoticeDetaiPresent;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.CallOtherOpeanFile;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.FileCache;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.lv_fujianlist)
    ListView lvFujianlist;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;
    @BindView(R.id.img_quesheng)
    ImageView imgQuesheng;
    @BindView(R.id.te_noticerecevice)
    TextView teNoticerecevice;

    private int noticeid;
    private int type;
    List<AnnexBean> annexList = new ArrayList<>();
    private Dialog mShareDialog;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        noticeid = intent.getIntExtra("noticeid", 0);

        lvFujianlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Api.BASE_PATH + "announcement/annex/download";
                AnnexBean annexBean = annexList.get(position);
                if (CommonUtils.initDownPath(FileCache.path + annexBean.getAnnexName())) {
                    File outFile = new File(FileCache.path + annexBean.getAnnexName());
                    CallOtherOpeanFile callOtherOpeanFile = new CallOtherOpeanFile();
                    callOtherOpeanFile.openFile(NoticeDeatilActivity.this, outFile);
                } else {
                    getP().downloadFujin(url, annexBean.getAnnexName(), annexBean.getAnnouncementAnnexId());
                }

            }
        });
    }

    @Override
    public void initAfter() {
        requestPermission();
        if (type == 0) {
            getP().getMineSendNotice(noticeid);
            teMore.setVisibility(View.VISIBLE);
        } else {
            getP().getSendMineNotice(noticeid);
            teMore.setVisibility(View.GONE);
        }

    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        requestRunTimePression(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PressionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onFailure(List<String> failurePression) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("提示")
                        .setMessage("请您去设置中授予内部存储的权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                builder.setCancelable(false);
                builder.show();
            }
        });
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_notice_deatil;
    }

    @Override
    public NoticeDetaiPresent bindPresent() {
        return new NoticeDetaiPresent();
    }


    public void getResultDetail(NoticeDetailBean noticeDetailBean) {
        imgQuesheng.setVisibility(View.GONE);
        rlAll.setVisibility(View.VISIBLE);
        teNoticetitle.setText(noticeDetailBean.getRespBody().getTitle());
        teNoticedeatils.setText(noticeDetailBean.getRespBody().getContent());
        if (noticeDetailBean.getRespBody().getCreateUserName() != null &&
                !noticeDetailBean.getRespBody().getCreateUserName().equals("") &&
                !noticeDetailBean.getRespBody().getCreateUserName().equals("null")) {
            teNoticesender.setText("发布人：" + noticeDetailBean.getRespBody().getCreateUserName());
        }
        List<NoticeDetailBean.RespBodyBean.ReceiverListBean> receiverListBeanList = noticeDetailBean.getRespBody().getReceiverList();
        if (receiverListBeanList != null && receiverListBeanList.size() > 0){
            String receviers = "接收人：";
            for (int i=0;i<receiverListBeanList.size();i++){
                receviers += receiverListBeanList.get(i).getReceiverName()+"  ";
            }
            teNoticerecevice.setText(receviers);
            teNoticerecevice.setVisibility(View.VISIBLE);
        }else {
            teNoticerecevice.setVisibility(View.GONE);
        }
        teNoticetime.setText(noticeDetailBean.getRespBody().getCreateTime());
        List<AnnexBean> allannexList = noticeDetailBean.getRespBody().getAnnexList();
        if (allannexList == null || allannexList.size() == 0) {
            teFujinaType.setVisibility(View.GONE);
        } else {
            annexList.clear();
            annexList.addAll(allannexList);
            NoticeFujianListAdapter adapter = new NoticeFujianListAdapter(NoticeDeatilActivity.this, annexList, 1);
            lvFujianlist.setAdapter(adapter);
        }
    }

    public void setImgQuesheng(){
        rlAll.setVisibility(View.GONE);
        imgQuesheng.setVisibility(View.VISIBLE);
    }

    public void getResult() {
        if (mShareDialog != null && mShareDialog.isShowing()) {
            mShareDialog.dismiss();
            if (type == 0) {
                Intent intent = new Intent();
                intent.setAction("action");
                intent.putExtra("data", "deletemysend");
                sendBroadcast(intent);//发送普通广播
            } else {
                Intent intent = new Intent();
                intent.setAction("action");
                intent.putExtra("data", "deletesendme");
                sendBroadcast(intent);//发送普通广播
            }
            finish();
        }
    }

    @Override
    public void setListener() {
        imgQuesheng.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        teMore.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_quesheng:
                if (type == 0) {
                    getP().getMineSendNotice(noticeid);
                    teMore.setVisibility(View.VISIBLE);
                } else {
                    getP().getSendMineNotice(noticeid);
                    teMore.setVisibility(View.GONE);
                }
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
        mShareDialog = new Dialog(this, R.style.dialog_bottom_full);
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
