package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liyalong on 2018/1/17.
 */

public class ProjectListModel extends BaseModel {
    private List<ProjectBean> respBody;
    private int total;
    private int lastPage;

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getLastPage() {

        return lastPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {

        return total;
    }

    public void setRespBody(List<ProjectBean> respBody) {
        this.respBody = respBody;
    }

    public List<ProjectBean> getRespBody() {

        return respBody;
    }
}
