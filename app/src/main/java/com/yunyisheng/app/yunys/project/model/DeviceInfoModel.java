package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;

/**
 * Created by liyalong on 2018/1/23.
 */

public class DeviceInfoModel extends BaseModel {
   private DeviceBean respBody;

    public void setRespBody(DeviceBean respBody) {
        this.respBody = respBody;
    }

    public DeviceBean getRespBody() {

        return respBody;
    }

    @Override
    public String toString() {
        return "DeviceInfoModel{" +
                "respBody=" + respBody +
                '}';
    }
}
