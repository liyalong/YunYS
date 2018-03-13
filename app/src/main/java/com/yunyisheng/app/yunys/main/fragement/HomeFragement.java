package com.yunyisheng.app.yunys.main.fragement;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.ConstantManager;
import com.yunyisheng.app.yunys.MainActivity;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.login.model.WelcomePageBean;
import com.yunyisheng.app.yunys.main.activity.MailListActivity;
import com.yunyisheng.app.yunys.main.activity.MemorandumActivity;
import com.yunyisheng.app.yunys.main.activity.MessageActivity;
import com.yunyisheng.app.yunys.main.activity.NoticeActivity;
import com.yunyisheng.app.yunys.main.activity.ReportformActivity;
import com.yunyisheng.app.yunys.main.adapter.HomeScheduleAdapter;
import com.yunyisheng.app.yunys.main.model.BannerBean;
import com.yunyisheng.app.yunys.main.model.NoReadMessage;
import com.yunyisheng.app.yunys.main.model.NoReadMessageEvent;
import com.yunyisheng.app.yunys.main.present.HomePresent;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.RecyclerBanner;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidbase.cache.SharedPref;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getDayEndTime;
import static com.yunyisheng.app.yunys.utils.CommonUtils.getDayStartTime;

/**
 * 作者：fuduo on 2018/1/10 09:24
 * 邮箱：duoendeavor@163.com
 * 用途：首页fragement
 */

public class HomeFragement extends BaseFragement<HomePresent> {
    @BindView(R.id.rcy_banner)
    RecyclerBanner rcyBanner;
    @BindView(R.id.img_baobiao)
    ImageView imgBaobiao;
    @BindView(R.id.img_quesheng)
    ImageView img_quesheng;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.te_columntitle)
    TextView teColumntitle;
    @BindView(R.id.te_columnsize)
    TextView teColumnsize;
    @BindView(R.id.te_seeall)
    TextView teSeeall;
    @BindView(R.id.rl_todaycolumn)
    RelativeLayout rlTodaycolumn;
    @BindView(R.id.pull_to_refresh_listview)
    PullToRefreshListView pullToRefreshListview;
    Unbinder unbinder;
    @BindView(R.id.line_notice)
    LinearLayout lineNotice;
    @BindView(R.id.line_tongxunlu)
    LinearLayout lineTongxunlu;
    @BindView(R.id.line_beiwanglu)
    LinearLayout lineBeiwanglu;
    private List<BannerBean.RespBodyBean> labelListBeans = new ArrayList<>();
    private List<MyScheduleBean.RespBodyBean.DataListBean> list = new ArrayList<>();
    int i;
    private HomeScheduleAdapter adapter;
    private int pageindex = 1;
    private String dayStartTime;
    private String dayEndTime;
    private boolean isfirst = true;
    private boolean isonce = true;
    private MediaPlayer mMediaPlayer;

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        rcyBanner.setContext((Activity) mContext);
        ScrowUtil.listViewConfig(pullToRefreshListview);
        dayStartTime = getDayStartTime();
        dayEndTime = getDayEndTime();
        adapter = new HomeScheduleAdapter(mContext, list);
        pullToRefreshListview.setAdapter(adapter);
        pullToRefreshListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex = 1;
                getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
                getP().getBannerList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageindex++;
                getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
            }
        });
    }

    @Override
    public void initAfter() {
        String token = SharedPref.getInstance(mContext).getString("TOKEN", "");
        ConstantManager.token = token;
        LogUtils.i("token", token);
        if (isonce) {
            isonce = false;
            getUserinfo();
            if (token != null && !token.equals("")) {
                getP().getWelcomePage();
            }
        }
        pageindex=1;
        getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
        getP().getBannerList();
        getP().getNoMessage();
    }

    public void getBannerList(BannerBean bannerBean) {
        labelListBeans.clear();
        List<BannerBean.RespBodyBean> respBody = bannerBean.getRespBody();
        if (respBody != null && respBody.size() > 0) {
            labelListBeans.addAll(respBody);
            rcyBanner.setDatas(labelListBeans);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public HomePresent bindPresent() {
        return new HomePresent();
    }

    @Override
    public void setListener() {
        imgBaobiao.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        img_quesheng.setOnClickListener(this);
        teSeeall.setOnClickListener(this);
        lineNotice.setOnClickListener(this);
        lineTongxunlu.setOnClickListener(this);
        lineBeiwanglu.setOnClickListener(this);
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NoReadMessageEvent messageEvent) {
        int size = messageEvent.getSize();
        if (size > 0) {
            imgMessage.setBackgroundResource(R.mipmap.red_msg);
            playAudio();
            doVibrator();
        } else {
            imgMessage.setBackgroundResource(R.mipmap.message);
        }

    }

    public void getUserinfo() {
        getP().getUserInfo();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_baobiao:
                startActivity(new Intent(mContext, ReportformActivity.class));
                break;
            case R.id.img_message:
                startActivity(new Intent(mContext, MessageActivity.class));
                break;
            case R.id.img_quesheng:
                getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
                break;
            case R.id.te_seeall:
                ((MainActivity) getActivity()).changerTask();
                break;
            case R.id.line_notice:
                startActivity(new Intent(mContext, NoticeActivity.class));
                break;
            case R.id.line_tongxunlu:
                startActivity(new Intent(mContext, MailListActivity.class));
                break;
            case R.id.line_beiwanglu:
                startActivity(new Intent(mContext, MemorandumActivity.class));
                break;
        }
    }

    public void getUserInfo(UserModel userModel) {
        if (userModel.getRespCode() == 1) {
            ToastUtils.showLongToast("获取用户信息失败");
        } else {
            SharedPref.getInstance(mContext).putInt("userid", userModel.getRespBody().getEnterpriseUser().getUserId());
            SharedPref.getInstance(mContext).putString("username", userModel.getRespBody().getEnterpriseUser().getUserName());
            SharedPref.getInstance(mContext).putString("usersex", userModel.getRespBody().getEnterpriseUser().getUserSex());
            SharedPref.getInstance(mContext).putString("userphone", userModel.getRespBody().getEnterpriseUser().getUserPhone());
            SharedPref.getInstance(mContext).putString("userjob", userModel.getRespBody().getEnterpriseUser().getUserJobTitle());
            SharedPref.getInstance(mContext).putString("userhead", userModel.getRespBody().getEnterpriseUser().getUserPicture());
            SharedPref.getInstance(mContext).putString("useremail", userModel.getRespBody().getEnterpriseUser().getUserMailbox());
            List<UserModel.RespBodyBean.SectionBean> section = userModel.getRespBody().getSection();
            String str = "";
            for (int i = 0; i < section.size(); i++) {
                String sectionName = section.get(i).getSectionName();
                if (i != section.size() - 1) {
                    str += sectionName + ",";
                } else {
                    str += sectionName;
                }
            }
            SharedPref.getInstance(mContext).putString("userbumen", str);
            SharedPref.getInstance(mContext).putString("userrole", userModel.getRespBody().getReloName());
        }
        LogUtils.i("userinfo", userModel.getRespBody().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void getResultList(MyScheduleBean myScheduleBean) {
        if (pageindex == 1) {
            list.clear();
            teColumnsize.setText("(" + myScheduleBean.getRespBody().getTotal() + ")");
        }
        if (myScheduleBean.getRespBody().getDataList() != null && myScheduleBean.getRespBody().getDataList().size() > 0) {
            list.addAll(myScheduleBean.getRespBody().getDataList());
            adapter.setData(list);
            pullToRefreshListview.setVisibility(View.VISIBLE);
            img_quesheng.setVisibility(View.GONE);
        } else {
            if (pageindex == 1) {
                pullToRefreshListview.setVisibility(View.GONE);
                img_quesheng.setVisibility(View.VISIBLE);
                img_quesheng.setBackgroundResource(R.mipmap.no_index_task);
            } else {
                ToastUtils.showToast("没有更多了");
            }
        }
        stopRefresh();
    }

    public void setimgBac(){
        pullToRefreshListview.setVisibility(View.GONE);
        img_quesheng.setVisibility(View.VISIBLE);
        img_quesheng.setBackgroundResource(R.mipmap.no_network);
    }

    public void stopRefresh() {
        pullToRefreshListview.onRefreshComplete();
    }

    public void preservationImg(WelcomePageBean welcomePageBean) {
        String companyimg = welcomePageBean.getRespBody().getCompany();
        String enterpriseimg = welcomePageBean.getRespBody().getEnterprise();
        if (enterpriseimg != null && !enterpriseimg.equals("")) {
            SharedPref.getInstance(mContext).putString("enterpriseimg", enterpriseimg);
        }
        if (companyimg != null && !companyimg.equals("")) {
            SharedPref.getInstance(mContext).putString("companyimg", companyimg);
        }
    }

    public void getNoreadmessage(NoReadMessage noReadMessage) {
        NoReadMessage.RespBodyBean respBody = noReadMessage.getRespBody();
        if (respBody.getMids()!=null&&respBody.getMids().size()>0) {
            int size = respBody.getMids().size();
            if (size > 0) {
                imgMessage.setBackgroundResource(R.mipmap.red_msg);
            }
        }
    }

    private  void  playAudio(){
        try {
            if (mContext != null){
                mMediaPlayer = MediaPlayer.create(mContext, R.raw.msg);
                mMediaPlayer.setLooping(false);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mMediaPlayer.release();
                        mMediaPlayer = null;
                    }
                });
                mMediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doVibrator(){
        Vibrator vibrator = (Vibrator) mContext.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1500);//振动两秒
    }

}
