package com.yunyisheng.app.yunys.login.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * 作者：fuduo on 2018/2/5 12:39
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class WelcomePageBean extends BaseModel {


    /**
     * respBody : {"company":"平台base64编码","enterprise":"企业base64编码"}
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
         * company : 平台base64编码
         * enterprise : 企业base64编码
         */

        private String company;
        private String enterprise;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getEnterprise() {
            return enterprise;
        }

        public void setEnterprise(String enterprise) {
            this.enterprise = enterprise;
        }
    }
}
