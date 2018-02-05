package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.DeviceWarningBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/23.
 */

public class DeviceWarningListModel extends BaseModel {
    private List<DeviceWarningBean> respBody;

    public void setRespBody(List<DeviceWarningBean> respBody) {
        this.respBody = respBody;
    }

    public List<DeviceWarningBean> getRespBody() {

        return respBody;
    }
}
