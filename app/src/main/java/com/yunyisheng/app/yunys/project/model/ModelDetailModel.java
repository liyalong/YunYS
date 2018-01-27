package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.ModelInfoBean;

/**
 * Created by liyalong on 2018/1/25.
 */

public class ModelDetailModel extends BaseModel {
    private ModelInfoBean respBody;

    public void setRespBody(ModelInfoBean respBody) {
        this.respBody = respBody;
    }

    public ModelInfoBean getRespBody() {

        return respBody;
    }
}
