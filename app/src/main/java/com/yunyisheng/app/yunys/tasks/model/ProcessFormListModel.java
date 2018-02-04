package com.yunyisheng.app.yunys.tasks.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.tasks.bean.ProcessFormBean;

import java.util.List;

/**
 * Created by liyalong on 2018/2/4.
 */

public class ProcessFormListModel extends BaseModel {
    private List<ProcessFormBean> respBody;

    public void setRespBody(List<ProcessFormBean> respBody) {
        this.respBody = respBody;
    }

    public List<ProcessFormBean> getRespBody() {

        return respBody;
    }
}
