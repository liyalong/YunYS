package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liyalong on 2018/1/17.
 */

public class ProjectListModel extends BaseModel {
    private static List<ProjectBean> respBody;
    private int total;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {

        return total;
    }

    public static void setRespBody(List<ProjectBean> respBody) {
        ProjectListModel.respBody = respBody;
    }

    public static List<ProjectBean> getRespBody() {

        return respBody;
    }
}
