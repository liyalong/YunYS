package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.PeriodicTaskBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/25.
 */

public class PeriodicTaskListModel extends BaseModel {
    private List<PeriodicTaskBean> respBody;

    public List<PeriodicTaskBean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<PeriodicTaskBean> respBody) {

        this.respBody = respBody;
    }
}
