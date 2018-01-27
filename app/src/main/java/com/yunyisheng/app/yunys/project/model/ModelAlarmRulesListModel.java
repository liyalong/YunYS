package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.ModelAlarmRulesBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/26.
 */

public class ModelAlarmRulesListModel extends BaseModel {
    private List<ModelAlarmRulesBean> respBody;

    public void setRespBody(List<ModelAlarmRulesBean> respBody) {
        this.respBody = respBody;
    }

    public List<ModelAlarmRulesBean> getRespBody() {

        return respBody;
    }
}
