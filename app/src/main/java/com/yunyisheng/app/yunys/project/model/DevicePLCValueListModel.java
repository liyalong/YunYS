package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.DevicePLCValueBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/23.
 */

public class DevicePLCValueListModel extends BaseModel {
    private List<DevicePLCValueBean> respBody;

    public void setRespBody(List<DevicePLCValueBean> respBody) {
        this.respBody = respBody;
    }

    public List<DevicePLCValueBean> getRespBody() {

        return respBody;
    }
}
