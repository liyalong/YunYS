package com.yunyisheng.app.yunys.login.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * Created by liyalong on 2017/12/20.
 * LoginModel
 */

public class LoginModel extends BaseModel {
    private String respBody;

    public void setRespBody(String respBody) {
        this.respBody = respBody;
    }

    public String getRespBody() {

        return respBody;
    }
}
