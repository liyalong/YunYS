package com.yunyisheng.app.yunys.tasks.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.tasks.bean.ProjectFormBean;

import java.util.List;

/**
 * Created by liyalong on 2018/2/4.
 */

public class ProjectFormListModel extends BaseModel {
    private List<ProjectFormBean> respBody;

    public void setRespBody(List<ProjectFormBean> respBody) {
        this.respBody = respBody;
    }

    public List<ProjectFormBean> getRespBody() {

        return respBody;
    }
}
