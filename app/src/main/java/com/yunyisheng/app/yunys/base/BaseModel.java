package com.yunyisheng.app.yunys.base;

import android.support.annotation.IntDef;

import java.util.ArrayList;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by liyalong on 2017/12/20.
 */
public class BaseModel implements IModel {
    protected boolean error;
    protected Integer status;
    protected String message;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {

        return status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError(){
        if(this.status != 200){
            this.setError(true);
        }else{
            this.setError(false);
        }
        return error;
    }

    public void setError(boolean error){
        this.error = error;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {

        return false;
    }

    @Override
    public boolean isBizError() {
        return error;
    }

    @Override
    public String getErrorMsg() {
        return this.getMessage();
    }
}
