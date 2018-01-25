package com.yunyisheng.app.yunys.main.fragement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunyisheng.app.yunys.ConstantManager;
import com.yunyisheng.app.yunys.MainActivity;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.main.activity.MailListActivity;
import com.yunyisheng.app.yunys.main.activity.MemorandumActivity;
import com.yunyisheng.app.yunys.main.activity.MessageActivity;
import com.yunyisheng.app.yunys.main.activity.NoticeActivity;
import com.yunyisheng.app.yunys.main.activity.ReportformActivity;
import com.yunyisheng.app.yunys.main.present.HomePresent;
import com.yunyisheng.app.yunys.utils.LogUtils;
import com.yunyisheng.app.yunys.utils.RecyclerBanner;
import com.yunyisheng.app.yunys.utils.ScrowUtil;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidbase.cache.SharedPref;

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
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
    int i;

    @Override
    public void initView() {
        rcyBanner.setContext((Activity) mContext);
        ScrowUtil.listViewConfig(pullToRefreshListview);
    }

    @Override
    public void initAfter() {
        i++;
        urls.clear();
        if (i % 2 == 0) {
            urls.add(new Entity("http://pic.58pic.com/58pic/12/46/13/03B58PICXxE.jpg"));
            urls.add(new Entity("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg"));
            urls.add(new Entity("http://pic34.nipic.com/20131025/2531170_132447503000_2.jpg"));
            urls.add(new Entity("http://img5.imgtn.bdimg.com/it/u=3462610901,3870573928&fm=206&gp=0.jpg"));
        } else {
            urls.add(new Entity("http://img0.imgtn.bdimg.com/it/u=726278301,2143262223&fm=11&gp=0.jpg"));
            urls.add(new Entity("http://pic51.nipic.com/file/20141023/2531170_115622554000_2.jpg"));
            urls.add(new Entity("http://img3.imgtn.bdimg.com/it/u=2968209827,470106340&fm=21&gp=0.jpg"));
        }
        long t = System.currentTimeMillis();
        rcyBanner.setDatas(urls);
        Log.w("---", System.currentTimeMillis() - t + "");
        getUserinfo();
        String token=SharedPref.getInstance(mContext).getString("TOKEN","");
        ConstantManager.token=token;
        LogUtils.i("token",token);
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
        teSeeall.setOnClickListener(this);
        lineNotice.setOnClickListener(this);
        lineTongxunlu.setOnClickListener(this);
        lineBeiwanglu.setOnClickListener(this);
    }

    public void getUserinfo(){
        getP().getUserInfo();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_baobiao:
                startActivity(new Intent(mContext, ReportformActivity.class));
                break;
            case R.id.img_message:
                startActivity(new Intent(mContext, MessageActivity.class));
                break;
            case R.id.te_seeall:
                ((MainActivity)getActivity()).changerTask();
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

    public void getUserInfo(UserModel userModel){
        if (userModel.getRespCode()==1){
            ToastUtils.showLongToast("获取用户信息失败");
        }else {
            ToastUtils.showLongToast("获取用户信息成功");
            SharedPref.getInstance(mContext).putInt("userid",userModel.getRespBody().getUserId());
            SharedPref.getInstance(mContext).putString("username",userModel.getRespBody().getUserName());
            SharedPref.getInstance(mContext).putString("usersex",userModel.getRespBody().getUserSex());
            SharedPref.getInstance(mContext).putString("userphone",userModel.getRespBody().getUserPhone());
            SharedPref.getInstance(mContext).putString("userjob",userModel.getRespBody().getUserJobTitle());
            SharedPref.getInstance(mContext).putString("userhead",userModel.getRespBody().getUserPicture());
            SharedPref.getInstance(mContext).putString("useremail",userModel.getRespBody().getUserMailbox());
            SharedPref.getInstance(mContext).putString("userbumen",userModel.getRespBody().getEnterpriseId());
            SharedPref.getInstance(mContext).putInt("userrole",userModel.getRespBody().getRolesId());
        }
        LogUtils.i("userinfo",userModel.getRespBody().toString());
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
    }

    private class Entity implements RecyclerBanner.BannerEntity {

        String url;

        public Entity(String url) {
            this.url = url;
        }

        @Override
        public String getUrl() {
            return url;
        }
    }
}
