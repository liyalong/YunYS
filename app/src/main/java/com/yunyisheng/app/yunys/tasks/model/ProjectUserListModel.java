package com.yunyisheng.app.yunys.tasks.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.tasks.bean.ProjectUserBean;

import java.util.List;

/**
 * Created by liyalong on 2018/2/2.
 */

public class ProjectUserListModel extends BaseModel {
    private List<ProjectUserBean> respBody;

    public void setRespBody(List<ProjectUserBean> respBody) {
        this.respBody = respBody;
    }

    public List<ProjectUserBean> getRespBody() {

        return respBody;
    }
}
