package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * 作者：fuduo on 2018/2/7 18:28
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class QuanxianBean extends BaseModel {


    /**
     * respBody : {"canArrangeWork":true,"canEditInfo":true}
     */

    private RespBodyBean respBody;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * canArrangeWork : true
         * canEditInfo : true
         */

        private boolean canArrangeWork;
        private boolean canEditInfo;

        public boolean isCanArrangeWork() {
            return canArrangeWork;
        }

        public void setCanArrangeWork(boolean canArrangeWork) {
            this.canArrangeWork = canArrangeWork;
        }

        public boolean isCanEditInfo() {
            return canEditInfo;
        }

        public void setCanEditInfo(boolean canEditInfo) {
            this.canEditInfo = canEditInfo;
        }
    }
}
