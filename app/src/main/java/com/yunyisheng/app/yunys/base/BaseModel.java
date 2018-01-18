package com.yunyisheng.app.yunys.base;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by liyalong on 2017/12/20.
 */
public class BaseModel implements IModel {
    protected boolean error;
    protected Integer respCode ;
    protected String respMsg ;

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public boolean isError(){
        if(this.respCode != 200){
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
        return this.getRespMsg();
    }
}
