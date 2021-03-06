package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.activity.AddMemorandumActivity;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.schedule.model.PositionMessageEvent;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/23 14:18
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class UpdateMempPresent extends XPresent<AddMemorandumActivity> {

    /**
     *  @author fuduo
     *  @time 2018/1/23  14:18
     *  @describe 添加备忘录
     */
    public void addMemo(String memoVal){
        LoadingDialog.show(getV());
        Api.homeService().createMemo(memoVal)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    public void onNext(BaseModel baseModel) {
                        LoadingDialog.dismiss(getV());
                        if (baseModel.getRespCode()==0){
                            ToastUtils.showToast("添加成功");
                            EventBus.getDefault().post(new PositionMessageEvent("updatebeiwanglu"));
                            getV().finish();
                        }else {
                            ToastUtils.showToast(baseModel.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("添加失败！");
                    }
                });
    }

    /**
     *  @author fuduo
     *  @time 2018/1/23  14:18
     *  @describe 修改备忘录
     */
    public void updateMemo(String memoVal,int memoid){
        Api.homeService().updateMemo(memoVal,memoid)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode()==0){
                            ToastUtils.showToast("修改成功");
                            EventBus.getDefault().post(new PositionMessageEvent("updatebeiwanglu"));
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("修改失败！");
                    }
                });
    }
}
