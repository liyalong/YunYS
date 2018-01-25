package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.DeviceAlarmRulesBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/24.
 */

public class DeviceAlarmRulesModel extends BaseModel {
    private int total;
    private List<DeviceAlarmRulesBean> respBody;

    public int getTotal() {
        return total;
    }

    public List<DeviceAlarmRulesBean> getRespBody() {
        return respBody;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRespBody(List<DeviceAlarmRulesBean> respBody) {
        this.respBody = respBody;
    }

    @Override
    public String toString() {
        return "DeviceAlarmRulesModel{" +
                "total=" + total +
                ", respBody=" + respBody +
                '}';
    }
}
