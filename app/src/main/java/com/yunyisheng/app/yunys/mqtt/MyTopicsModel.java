package com.yunyisheng.app.yunys.mqtt;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.ArrayList;

/**
 * Created by liyalong on 2018/5/9.
 */

public class MyTopicsModel extends BaseModel {
    private ArrayList<String> respBody;

    public ArrayList<String> getRespBody() {
        return respBody;
    }

    public void setRespBody(ArrayList<String> respBody) {
        this.respBody = respBody;
    }
}
