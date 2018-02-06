package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.PLCValueBean;

import java.util.List;

/**
 * Created by liyalong on 2018/2/6.
 */

public class PLCListModel extends BaseModel {
    private List<PLCValueBean> respBody;

    public void setRespBody(List<PLCValueBean> respBody) {
        this.respBody = respBody;
    }

    public List<PLCValueBean> getRespBody() {
        return respBody;

    }
}
