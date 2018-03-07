package com.yunyisheng.app.yunys.main.present;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.main.activity.MemorandumActivity;
import com.yunyisheng.app.yunys.main.model.MemorandumBean;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：fuduo on 2018/1/23 14:12
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MemorandumPresent extends XPresent<MemorandumActivity> {

    /**
     * @author fuduo
     * @time 2018/1/23  14:13
     * @describe 查询备忘录
     */
    public void getMemoList(int pagenum, int pagerows) {
        LoadingDialog.show(getV());
        Api.homeService().getMemorandumList(pagenum, pagerows)
                .compose(XApi.<MemorandumBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MemorandumBean>getScheduler()) //线程调度
                .compose(getV().<MemorandumBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MemorandumBean>() {
                    @Override
                    public void onNext(MemorandumBean memorandumBean) {
                        LoadingDialog.dismiss(getV());
                        if (memorandumBean.getRespCode() == 0) {
                            getV().getResult(memorandumBean);
                        } else {
                            ToastUtils.showToast(memorandumBean.getRespMsg());
                            getV().stopRefresh();
                        }
                        getV().setGoneQuesheng();
                    }

                    @Override
                    protected void onFail(NetError error) {
                        LoadingDialog.dismiss(getV());
                        ToastUtils.showToast("请求数据失败！");
                        getV().stopRefresh();
                        if (error.getType()==5){
                            getV().setImgQuesheng();
                        }
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/23  14:13
     * @describe 模糊查询备忘录
     */
    public void selectMemoList(int userid,int pagenum, int pagerows,String string) {
        Api.homeService().selectMemorandumList(userid,pagenum, pagerows,string)
                .compose(XApi.<MemorandumBean>getApiTransformer()) //统一异常处理
                .compose(XApi.<MemorandumBean>getScheduler()) //线程调度
                .compose(getV().<MemorandumBean>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<MemorandumBean>() {
                    @Override
                    public void onNext(MemorandumBean memorandumBean) {
                        if (memorandumBean.getRespCode() == 0) {
                            getV().getSelectResult(memorandumBean);
                        } else {
                            ToastUtils.showToast(memorandumBean.getRespMsg());
                            getV().stopRefresh();
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("请求数据失败！");
                        getV().stopRefresh();
                    }
                });
    }

    /**
     * @author fuduo
     * @time 2018/1/23  14:13
     * @describe 删除备忘录
     */
    public void deleteMemo(int ids) {
        Api.homeService().deleteMemo(ids)
                .compose(XApi.<BaseModel>getApiTransformer()) //统一异常处理
                .compose(XApi.<BaseModel>getScheduler()) //线程调度fu
                .compose(getV().<BaseModel>bindToLifecycle()) //内存泄漏处理
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.getRespCode()==0){
                            getV().getDelete(baseModel);
                            ToastUtils.showToast("删除成功");
                        }else {
                            ToastUtils.showToast(baseModel.getRespMsg());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("删除失败！");
                    }
                });
    }
}
