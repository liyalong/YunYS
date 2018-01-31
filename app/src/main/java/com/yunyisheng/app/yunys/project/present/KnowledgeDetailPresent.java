package com.yunyisheng.app.yunys.project.present;

import android.util.Log;

import com.yunyisheng.app.yunys.ConstantManager;
import com.yunyisheng.app.yunys.net.Api;
import com.yunyisheng.app.yunys.project.activity.KnowledgeDetailActivity;
import com.yunyisheng.app.yunys.project.model.KnowledgDetailModel;
import com.yunyisheng.app.yunys.project.model.KnowledgeListModel;
import com.yunyisheng.app.yunys.upload.ProgressHelper;
import com.yunyisheng.app.yunys.upload.ProgressUIListener;
import com.yunyisheng.app.yunys.utils.CallOtherOpeanFile;
import com.yunyisheng.app.yunys.utils.FileCache;
import com.yunyisheng.app.yunys.utils.LoadingDialog;
import com.yunyisheng.app.yunys.utils.ToastUtils;

import java.io.File;
import java.io.IOException;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/**
 * Created by liyalong on 2018/1/29.
 */

public class KnowledgeDetailPresent extends XPresent<KnowledgeDetailActivity> {
    private File outFile;
    public void getKnowledgeDetail(String projectId,String knowledgeId){
        Api.projectService().getKnowledgeDetail(projectId,knowledgeId)
                .compose(XApi.<KnowledgDetailModel>getApiTransformer())
                .compose(XApi.<KnowledgDetailModel>getScheduler())
                .compose(getV().<KnowledgDetailModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<KnowledgDetailModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("网络请求错误！");
                    }

                    @Override
                    public void onNext(KnowledgDetailModel knowledgDetailModel) {
                        if (knowledgDetailModel.getRespCode() == 1){
                            ToastUtils.showToast(knowledgDetailModel.getRespMsg());
                            return;
                        }
                        getV().setAdapter(knowledgDetailModel);
                    }
                });
    }
    public void downloadFujin(String url, final String filename, String fujianid) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("fileId", fujianid+"")//设置参数名称和参数值
                .build();
        Request.Builder builder = new Request.Builder();
        builder.addHeader("token", ConstantManager.token);
        builder.url(url);
        builder.post(formBody);
        Call call = okHttpClient.newCall(builder.build());

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "=============onFailure===============");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "=============onResponse===============");
                Log.e("TAG", "request headers:" + response.request().headers());
                Log.e("TAG", "response headers:" + response.headers());
                ResponseBody responseBody = ProgressHelper.withProgress(response.body(), new ProgressUIListener() {

                    //if you don't need this method, don't override this methd. It isn't an abstract method, just an empty method.
                    @Override
                    public void onUIProgressStart(long totalBytes) {
                        super.onUIProgressStart(totalBytes);
                        Log.e("TAG", "onUIProgressStart:" + totalBytes);
                        LoadingDialog.show(getV());
                        ToastUtils.showToast("开始下载！");
                    }

                    @Override
                    public void onUIProgressChanged(long numBytes, long totalBytes, float percent, float speed) {
                        Log.e("TAG", "=============start===============");
                        Log.e("TAG", "numBytes:" + numBytes);
                        Log.e("TAG", "totalBytes:" + totalBytes);
                        Log.e("TAG", "percent:" + percent);
                        Log.e("TAG", "speed:" + speed);
                        Log.e("TAG", "============= end ===============");
//                        downloadProgeress.setProgress((int) (100 * percent));
//                        downloadInfo.setText("numBytes:" + numBytes + " bytes" + "\ntotalBytes:" + totalBytes + " bytes" + "\npercent:" + percent * 100 + " %" + "\nspeed:" + speed * 1000 / 1024 / 1024 + " MB/秒");
                    }

                    //if you don't need this method, don't override this methd. It isn't an abstract method, just an empty method.
                    @Override
                    public void onUIProgressFinish() {
                        super.onUIProgressFinish();
                        LoadingDialog.dismiss(getV());
                        Log.e("TAG", "onUIProgressFinish:");
                        ToastUtils.showToast("附件下载成功！");
                        CallOtherOpeanFile callOtherOpeanFile = new CallOtherOpeanFile();
                        callOtherOpeanFile.openFile(getV(), outFile);
                    }
                });

                BufferedSource source = responseBody.source();

                outFile = new File(FileCache.path + filename);
                outFile.delete();
                outFile.getParentFile().mkdirs();
                outFile.createNewFile();

                BufferedSink sink = Okio.buffer(Okio.sink(outFile));
                source.readAll(sink);
                sink.flush();
                source.close();
            }
        });
    }
}
