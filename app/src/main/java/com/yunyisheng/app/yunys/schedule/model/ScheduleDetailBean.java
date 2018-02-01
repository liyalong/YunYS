package com.yunyisheng.app.yunys.schedule.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/31 18:42
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ScheduleDetailBean extends BaseModel {

    private List<RespBodybean> respBody;

    public List<RespBodybean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<RespBodybean> respBody) {
        this.respBody = respBody;
    }

    public static class RespBodybean{

    }

}
