package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.DevicePartsBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/25.
 */

public class DevicePartsListModel extends BaseModel {
    private List<DevicePartsBean> respBody;

    public void setRespBody(List<DevicePartsBean> respBody) {
        this.respBody = respBody;
    }

    public List<DevicePartsBean> getRespBody() {
        return respBody;

    }
}
