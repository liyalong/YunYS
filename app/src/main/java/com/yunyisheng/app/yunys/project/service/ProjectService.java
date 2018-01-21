package com.yunyisheng.app.yunys.project.service;

import com.yunyisheng.app.yunys.project.model.ProjectListModel;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liyalong on 2018/1/17.
 */

public interface ProjectService {
    /**
     * 获取公司项目列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("project/list/company")
    Flowable<ProjectListModel> getCompanyProjectList(@Field("pageNum") int pageNum,
                                                     @Field("pageSize") int pageSize);
}
