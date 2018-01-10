package com.yunyisheng.app.yunys.login.model;

import android.content.Intent;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * Created by liyalong on 2017/12/20.
 */

public class LoginModel extends BaseModel {
    private String token;
    private Integer userId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {

        return userId;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getToken() {

        return token;
    }

}
