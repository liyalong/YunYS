package com.yunyisheng.app.yunys.schedule.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/3/6 18:04
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ScheduleNoSizeBean extends BaseModel {

    private List<RespBodyBean> respBody;

    public List<RespBodyBean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<RespBodyBean> respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * date : 2018-03-07
         * num : 3
         */

        private String date;
        private int num;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
