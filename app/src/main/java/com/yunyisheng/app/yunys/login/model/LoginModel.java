package com.yunyisheng.app.yunys.login.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * Created by liyalong on 2017/12/20.
 */

public class LoginModel extends BaseModel {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }


    public String getToken() {

        return token;
    }

}
