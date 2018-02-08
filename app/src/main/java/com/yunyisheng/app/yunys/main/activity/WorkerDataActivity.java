package com.yunyisheng.app.yunys.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseActivity;
import com.yunyisheng.app.yunys.main.adapter.ViewPagerAdapter;
import com.yunyisheng.app.yunys.main.fragement.BasicDataFragement;
import com.yunyisheng.app.yunys.main.fragement.ParticipateinFragement;
import com.yunyisheng.app.yunys.main.fragement.ScheduleFragement;
import com.yunyisheng.app.yunys.main.model.GetOtherinfoBean;
import com.yunyisheng.app.yunys.main.model.QuanxianBean;
import com.yunyisheng.app.yunys.main.present.WorkerDataPresent;

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
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private int tabindex;
    public  int userid;
    private BasicDataFragement basicDataFragement;
    public boolean canArrangeWork;
    public String workername;

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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
        if (canEditInfo){
            teEdit.setVisibility(View.VISIBLE);
        }

    }

    public void setInfodetail(GetOtherinfoBean getOtherinfoBean){
        if (getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPicture() != null && !getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPicture().equals("")
                && !getOtherinfoBean.getRespBody().getEnterpriseUser().getUserPicture().equals("null")) {
//            Bitmap bitmap = CommonUtils.stringtoBitmap(getOtherinfoBean.getRespBody().getUserPicture());
//            GlideDownLoadImage.getInstance().loadBitmapCircleImageRole(context, imgWorkerHead, bitmap);
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
        teNameZhize.setText(getOtherinfoBean.getRespBody().getEnterpriseUser().getUserName()+" | "+getOtherinfoBean.getRespBody().getEnterpriseUser().getUserJobTitle());
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
