package com.yunyisheng.app.yunys.login.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.login.activity.RegisterActivity;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2018/1/5.
 */

public class RegisterPresent extends XPresent<RegisterActivity> {

    /**
     * @author fuduo
     * @time 2018/2/4  16:20
     * @describe 企业注册
     */
    public void registerCompany(String enterpriseName, String enterpriseAddressProvince, String enterpriseAddressCity,
                                String enterpriseAddressDistrict,
                                String enterpriseAddressParticular, String enterpriseContact, String enterprisePhone,
                                String enterpriseMailbox, String description) {
        Api.companyService().registerCompany(enterpriseName, enterpriseAddressProvince, enterpriseAddressCity, enterpriseAddressDistrict,
                enterpriseAddressParticular,
                enterpriseContact, enterprisePhone, enterpriseMailbox, description)
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        getV().checkRegiterInfo(baseModel);
                    }
                });


    }
}
