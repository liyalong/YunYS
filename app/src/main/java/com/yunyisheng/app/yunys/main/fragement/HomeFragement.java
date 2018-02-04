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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
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
import com.yunyisheng.app.yunys.main.adapter.HomeScheduleAdapter;
import com.yunyisheng.app.yunys.main.present.HomePresent;
import com.yunyisheng.app.yunys.schedule.model.MyScheduleBean;
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
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
    private List<MyScheduleBean.RespBodyBean.DataListBean> list = new ArrayList<>();
    int i;
    private HomeScheduleAdapter adapter;
    private int pageindex = 1;
    private long dayStartTime;
    private long dayEndTime;
    private boolean isfirst = true;
    private boolean isonce = true;

    @Override
    public void initView() {
        rcyBanner.setContext((Activity) mContext);
        ScrowUtil.listViewUpConfig(pullToRefreshListview);
        dayStartTime = getDayStartTime();
        dayEndTime = getDayEndTime();
        adapter = new HomeScheduleAdapter(mContext, list);
        pullToRefreshListview.setAdapter(adapter);
        pullToRefreshListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

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
        if (isonce) {
            isonce = false;
            getUserinfo();
        }
        String token = SharedPref.getInstance(mContext).getString("TOKEN", "");
        ConstantManager.token = token;
        LogUtils.i("token", token);
        getP().getMySchedulrList(pageindex, dayStartTime, dayEndTime);
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

    public void getUserinfo() {
        getP().getUserInfo();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_baobiao:
                selectAddress();
//                startActivity(new Intent(mContext, ReportformActivity.class));
                break;
            case R.id.img_message:
                startActivity(new Intent(mContext, MessageActivity.class));
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

    private void selectAddress(){
        CityPickerView mCityPickerView = new CityPickerView(getContext());
        // 设置点击外部是否消失
        mCityPickerView.setCancelable(true);
        // 设置滚轮字体大小
//        mCityPickerView.setTextSize(18f);
        // 设置标题
//        mCityPickerView.setTitle("我是标题");
        // 设置取消文字
//        mCityPickerView.setCancelText("我是取消文字");
        // 设置取消文字颜色
//        mCityPickerView.setCancelTextColor(Color.GRAY);
        // 设置取消文字大小
//        mCityPickerView.setCancelTextSize(14f);
        // 设置确定文字
//        mCityPickerView.setSubmitText("我是确定文字");
        // 设置确定文字颜色
//        mCityPickerView.setSubmitTextColor(Color.BLACK);
        // 设置确定文字大小
//        mCityPickerView.setSubmitTextSize(14f);
        // 设置头部背景
//        mCityPickerView.setHeadBackgroundColor(Color.RED);
        mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener(){
            @Override
            public void onCitySelect(String prov, String city, String area) {
                // 省、市、区 分开获取
                Log.e(TAG, "省: " + prov + " 市: " + city + " 区: " + area);
            }

            @Override
            public void onCitySelect(String str) {
                // 一起获取
                Toast.makeText(getContext(), "选择了：" + str, Toast.LENGTH_SHORT).show();
            }
        });
        mCityPickerView.show();
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
    }

    public void getResultList(MyScheduleBean myScheduleBean) {
        if (pageindex == 1) {
            list.clear();
            teColumnsize.setText("(" + myScheduleBean.getRespBody().getTotal() + ")");
        }
        if (myScheduleBean.getRespBody().getDataList() != null && myScheduleBean.getRespBody().getDataList().size() > 0) {
            list.addAll(myScheduleBean.getRespBody().getDataList());
            adapter.setData(list);
        } else {
            if (pageindex == 1) {
                ToastUtils.showToast("当前日期暂无日程");
            } else {
                ToastUtils.showToast("没有更多了");
            }
        }
        stopRefresh();
    }

    public void stopRefresh() {
        pullToRefreshListview.onRefreshComplete();
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
