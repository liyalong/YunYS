package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.activity.AddMemorandumActivity;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.ToastUtils;

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
        Api.homeService().createMemo(memoVal)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    public void onNext(BaseModel baseModel) {
                        //getV().getAddResult(baseModel);
                        if (baseModel.getRespCode()==0){
                            ToastUtils.showToast("添加成功");
                        }
                        //ToastUtils.showToast(baseModel.getRespMsg());
                    }

                    @Override
                    protected void onFail(NetError error) {
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
                        //getV().getDeleteResult(baseModel);
                        if (baseModel.getRespCode()==0){
                            ToastUtils.showToast("修改成功");
                        }
                        //ToastUtils.showToast(baseModel.getRespMsg());
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("修改失败！");
                    }
                });
    }
}
