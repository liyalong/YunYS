package com.yunyisheng.app.yunys.userset.fragement;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunyisheng.app.yunys.MainActivity;
import com.yunyisheng.app.yunys.R;
import com.yunyisheng.app.yunys.base.BaseFragement;
import com.yunyisheng.app.yunys.base.PressionListener;
import com.yunyisheng.app.yunys.login.activity.LoginActivity;
import com.yunyisheng.app.yunys.login.model.UserModel;
import com.yunyisheng.app.yunys.userset.activity.AboutOurActivity;
import com.yunyisheng.app.yunys.userset.activity.AccountSetActivity;
import com.yunyisheng.app.yunys.userset.activity.ClearCatchActivity;
import com.yunyisheng.app.yunys.userset.activity.EnterpriseinformationActivity;
import com.yunyisheng.app.yunys.userset.activity.FanKuiActivity;
import com.yunyisheng.app.yunys.userset.activity.MimaManagerActivity;
import com.yunyisheng.app.yunys.userset.activity.MyInformationActivity;
import com.yunyisheng.app.yunys.userset.present.MinePresent;
import com.yunyisheng.app.yunys.utils.CommonUtils;
import com.yunyisheng.app.yunys.utils.DialogManager;
import com.yunyisheng.app.yunys.utils.getapp.AppApplicationMgr;
import com.yunyisheng.app.yunys.utils.glide.GlideDownLoadImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidbase.router.Router;

/**
 * 作者：fuduo on 2018/1/10 09:22
 * 邮箱：duoendeavor@163.com
 * 用途：我的fragement
 */
public class MineFragement extends BaseFragement<MinePresent> {

    Unbinder unbinder;
    @BindView(R.id.img_worker_head)
    ImageView imgWorkerHead;
    @BindView(R.id.te_name_zhize)
    TextView teNameZhize;
    @BindView(R.id.te_phonenum)
    TextView tePhonenum;
    @BindView(R.id.my_info)
    RelativeLayout myInfo;
    @BindView(R.id.soft_version)
    RelativeLayout softVersion;
    @BindView(R.id.user_setting)
    RelativeLayout userSetting;
    @BindView(R.id.mimamanager)
    RelativeLayout mimamanager;
    @BindView(R.id.about_us)
    RelativeLayout aboutUs;
    @BindView(R.id.company_info)
    RelativeLayout companyInfo;
    @BindView(R.id.clean_cache)
    RelativeLayout cleanCache;
    @BindView(R.id.rl_fankui)
    RelativeLayout rlFankui;
    @BindView(R.id.logout)
    TextView logout;
    @BindView(R.id.img_carm)
    ImageView imgCarm;
    @BindView(R.id.te_versioncode)
    TextView teVersioncode;

    @Override
    public void initView() {
        teVersioncode.setText(AppApplicationMgr.getVersionCode(mContext));
        MyReceiver receiver = new MyReceiver();//广播接受者实例
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action");
        mContext.registerReceiver(receiver, intentFilter);
    }

    @Override
    public void initAfter() {
        String username = SharedPref.getInstance(mContext).getString("username", "");
        String userjob = SharedPref.getInstance(mContext).getString("userjob", "");
        String userhead = SharedPref.getInstance(mContext).getString("userhead", "");
        String userphone = SharedPref.getInstance(mContext).getString("userphone", "");
        String usersex = SharedPref.getInstance(mContext).getString("usersex", "");
        teNameZhize.setText(username + " | " + userjob);
        if (userhead != null && !userhead.equals("") && !userhead.equals("null")) {
            Bitmap bitmap = CommonUtils.stringtoBitmap(userhead);
            GlideDownLoadImage.getInstance().loadBitmapCircleImageRole(mContext, imgWorkerHead, bitmap);
        } else {
            if (usersex != null && !usersex.equals("") && !usersex.equals("null")) {
                if (usersex.equals("男")) {
                    imgWorkerHead.setBackgroundResource(R.mipmap.man);
                } else {
                    imgWorkerHead.setBackgroundResource(R.mipmap.woman);
                }
            } else {
                imgWorkerHead.setBackgroundResource(R.mipmap.man);
            }
        }
        tePhonenum.setText(userphone);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public MinePresent bindPresent() {
        return new MinePresent();
    }

    @Override
    public void setListener() {
        imgCarm.setOnClickListener(this);
        myInfo.setOnClickListener(this);
        softVersion.setOnClickListener(this);
        userSetting.setOnClickListener(this);
        mimamanager.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        companyInfo.setOnClickListener(this);
        cleanCache.setOnClickListener(this);
        logout.setOnClickListener(this);
        rlFankui.setOnClickListener(this);
    }


    public void getUserInfo(UserModel userModel) {
        if (userModel.getRespCode() == 1) {
        } else {
            String userhead = userModel.getRespBody().getEnterpriseUser().getUserPicture();
//            SharedPref.getInstance(mContext).putInt("userid",userModel.getRespBody().getUserId());
//            SharedPref.getInstance(mContext).putString("username",userModel.getRespBody().getUserName());
//            SharedPref.getInstance(mContext).putString("usersex",userModel.getRespBody().getUserSex());
//            SharedPref.getInstance(mContext).putString("userphone",userModel.getRespBody().getUserPhone());
//            SharedPref.getInstance(mContext).putString("userjob",userModel.getRespBody().getUserJobTitle());
            SharedPref.getInstance(mContext).putString("userhead", userhead);
//            SharedPref.getInstance(mContext).putString("useremail",userModel.getRespBody().getUserMailbox());
//            SharedPref.getInstance(mContext).putString("userbumen",userModel.getRespBody().getEnterpriseId());
//            SharedPref.getInstance(mContext).putInt("userrole",userModel.getRespBody().getRolesId());
            if (userhead != null && !userhead.equals("")) {
                Bitmap bitmap = CommonUtils.stringtoBitmap(userhead);
                GlideDownLoadImage.getInstance().loadBitmapCircleImageRole(mContext, imgWorkerHead, bitmap);
            }
        }
        //LogUtils.i("userinfo",userModel.getRespBody().toString());
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        ((MainActivity)getActivity()).requestRunTimePression(getActivity(), new String[]{Manifest.permission.CAMERA}, new PressionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onFailure(List<String> failurePression) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("提示")
                        .setMessage("请您去设置中授予拍照的权限")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                                ComponentName cName = new ComponentName("com.android.phone", "com.android.phone.Settings");
                                intent.setComponent(cName);
                                startActivity(intent);
                            }
                        });
            }
        });
    }

    class MyReceiver extends BroadcastReceiver {
        public MyReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("data");
            if ("changename".equals(data)) {
                String username = SharedPref.getInstance(mContext).getString("username", "");
                String userjob = SharedPref.getInstance(mContext).getString("userjob", "");
                teNameZhize.setText(username + " | " + userjob);
            }
        }

    }

    public void setNewHead() {
        getP().getUserInfo();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.img_carm:
                requestPermission();
                DialogManager.createPickImageDialog(getActivity());
                break;
            case R.id.my_info:
                startActivity(new Intent(mContext, MyInformationActivity.class));
                break;
            case R.id.soft_version:
                break;
            case R.id.user_setting:
                startActivity(new Intent(mContext, AccountSetActivity.class));
                break;
            case R.id.mimamanager:
                startActivity(new Intent(mContext, MimaManagerActivity.class));
                break;
            case R.id.about_us:
                startActivity(new Intent(mContext, AboutOurActivity.class));
                break;
            case R.id.company_info:
                startActivity(new Intent(mContext, EnterpriseinformationActivity.class));
                break;
            case R.id.clean_cache:
                startActivity(new Intent(mContext, ClearCatchActivity.class));
                break;
            case R.id.rl_fankui:
                startActivity(new Intent(mContext, FanKuiActivity.class));
                break;
            case R.id.logout:
                SharedPref.getInstance(context).clear();
                Router.newIntent(context)
                        .to(LoginActivity.class)
                        .launch();
                context.finish();
                break;
        }
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

}
