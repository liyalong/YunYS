package com.yunyisheng.app.yunys.main.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.main.fragement.BasicDataFragement;
import com.yunyisheng.app.yunys.main.fragement.ParticipateinFragement;
import com.yunyisheng.app.yunys.main.fragement.ScheduleFragement;
import com.yunyisheng.app.yunys.main.model.GetOtherinfoBean;
import com.yunyisheng.app.yunys.main.model.QuanxianBean;
import com.yunyisheng.app.yunys.main.model.WorkerBean;
import com.yunyisheng.app.yunys.main.present.WorkerDataPresent;
import com.yunyisheng.app.yunys.tasks.activity.CreateDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateNoneDeviceTaskAcitvity;
import com.yunyisheng.app.yunys.tasks.activity.CreateProcessTaskAcitvity;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.ToastUtils;
import com.yunyisheng.app.yunys.utils.glide.GlideDownLoadImage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yunyisheng.app.yunys.utils.ScreenUtils.setIndicator;

/**
 * @author fuduo
 * @time 2018/1/16  21:26
 * @describe 员工详情
 */
public class WorkerDataActivity extends BaseActivity<WorkerDataPresent> {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.te_edit)
    TextView teEdit;
    @BindView(R.id.img_worker_head)
    ImageView imgWorkerHead;
    @BindView(R.id.te_name_zhize)
    TextView teNameZhize;
    @BindView(R.id.tablayout_information)
    TabLayout tablayoutInformation;
    @BindView(R.id.vp_information)
    ViewPager vpInformation;
    @BindView(R.id.btn_anpai_work)
    Button btnAnpaiWork;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private List<WorkerBean> selectlist = new ArrayList<>();//选中的人员
    private int tabindex;
    public int userid;
    private BasicDataFragement basicDataFragement;
    private boolean canArrangeWork;
    private String workername;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mTitleList.add("基本资料");
        mTitleList.add("日程安排");
        mTitleList.add("参与项目");
        vpInformation.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabindex = position;
                if (tabindex == 0) {
                    if (canArrangeWork) {
                        btnAnpaiWork.setVisibility(View.VISIBLE);
                    }
                } else {
                    btnAnpaiWork.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnAnpaiWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workername != null && !workername.equals("")) {
                    WorkerBean workerBean = new WorkerBean();
                    workerBean.setName(workername);
                    workerBean.setUserId(userid);
                    selectlist.add(workerBean);
                    createSelectTaskDialog(WorkerDataActivity.this);
                } else {
                    ToastUtils.showToast("获取员工信息失败");
                }

            }
        });

    }


    @Override
    public void initAfter() {
        Intent intent = getIntent();
        userid = intent.getIntExtra("userid", 0);
        basicDataFragement = new BasicDataFragement();
        Bundle bundle = new Bundle();
        bundle.putInt("userid", userid);
        basicDataFragement.setArguments(bundle);
        ScheduleFragement scheduleFragement = new ScheduleFragement();
        ParticipateinFragement participateinFragement = new ParticipateinFragement();
        fragmentList.add(basicDataFragement);
        fragmentList.add(scheduleFragement);
        fragmentList.add(participateinFragement);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, mTitleList);
        vpInformation.setAdapter(adapter);
        tablayoutInformation.setupWithViewPager(vpInformation);
        setIndicator(this, tablayoutInformation, 35, 35);
        getP().getQuanxian(userid);
    }

    /**
     * 选择任务对话框
     *
     * @param activity
     * @return
     */
    public void createSelectTaskDialog(final Activity activity) {
        final Dialog mSelectTask = new Dialog(activity, R.style.dialog_bottom_full);
        mSelectTask.setCanceledOnTouchOutside(true);
        mSelectTask.setCancelable(true);
        Window window = mSelectTask.getWindow();
        window.setGravity(Gravity.BOTTOM);
        View view1 = View.inflate(activity, R.layout.dialog_select_task, null);
        RelativeLayout rl_shebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_shebei_task);
        RelativeLayout rl_wrongshebei_task = (RelativeLayout) view1
                .findViewById(R.id.rl_wrongshebei_task);

        RelativeLayout rl_liucheng_task = (RelativeLayout) view1
                .findViewById(R.id.rl_liucheng_task);
        RelativeLayout rl_close = (RelativeLayout) view1
                .findViewById(R.id.rl_close);
        rl_shebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, CreateDeviceTaskAcitvity.class);
                intent.putExtra("selectlist", (Serializable) selectlist);
                startActivity(intent);
            }
        });
        rl_wrongshebei_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, CreateNoneDeviceTaskAcitvity.class);
                intent.putExtra("selectlist", (Serializable) selectlist);
                startActivity(intent);
            }
        });
        rl_liucheng_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CreateProcessTaskAcitvity.class);
                intent.putExtra("selectlist", (Serializable) selectlist);
                startActivity(intent);
            }
        });

        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectTask.dismiss();
            }
        });

        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        mSelectTask.show();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_worker_data;
    }

    @Override
    public WorkerDataPresent bindPresent() {
        return new WorkerDataPresent();
    }

    @Override
    public void setListener() {
        imgBack.setOnClickListener(this);
        teEdit.setOnClickListener(this);
    }

    public void getQuanResultInfo(QuanxianBean quanxianBean) {
        QuanxianBean.RespBodyBean respBody = quanxianBean.getRespBody();
        canArrangeWork = respBody.isCanArrangeWork();
        boolean canEditInfo = respBody.isCanEditInfo();
        if (canEditInfo) {
            teEdit.setVisibility(View.VISIBLE);
        }
        if (tabindex == 0) {
            if (canArrangeWork) {
                btnAnpaiWork.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setInfodetail(GetOtherinfoBean getOtherinfoBean) {
        if (getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPicture() != null && !getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPicture().equals("")
                && !getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPicture().equals("null")) {
            Bitmap bitmap = CommonUtils.stringtoBitmap(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPicture());
            GlideDownLoadImage.getInstance().loadBitmapCircleImageRole(context, imgWorkerHead, bitmap);
        } else {
            String sex = getOtherinfoBean.getRespBody().getEnterpriseUser().getUserSex();
            if (sex != null && !sex.equals("") && !sex.equals("null")) {
                if (sex.equals("男")) {
                    imgWorkerHead.setBackgroundResource(R.mipmap.maillist_man);
                } else {
                    imgWorkerHead.setBackgroundResource(R.mipmap.maillist_woman);
                }

            } else {
                imgWorkerHead.setBackgroundResource(R.mipmap.maillist_man);
            }
        }
        workername = getOtherinfoBean.getRespBody().getEnterpriseUser().getUserName();
        teNameZhize.setText(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserName() + " | " + getOtherinfoBean.getRespBody().getEnterpriseUser().getUserJobTitle());
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.te_edit:
                if (tabindex == 0) {
                    if (basicDataFragement != null) {
                        basicDataFragement.editInfo();
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 5:
                if (basicDataFragement != null) {
                    basicDataFragement.getinfo();
                }
                break;
        }
    }
}
