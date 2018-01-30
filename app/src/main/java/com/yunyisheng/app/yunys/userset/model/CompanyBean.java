package com.yunyisheng.app.yunys.userset.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * 作者：fuduo on 2018/1/21 17:39
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class CompanyBean extends BaseModel {


    /**
     * respBody : {"enterpriseOpenTime":null,"gatewayNumber":2,"enterpriseUpdateTime":null,"enterpriseContact":"张鹏辉","enterpriseUpdateUser":null,"enterpriseMailbox":"zhangpenghui@163.com","description":null,"enterprisePossessorId":302,"enterpriseCertification":"0","enterpriseAddressParticular":"一轻大厦3F","enterpriseEmploy":"1","enterpriseName":"友友天宇","enterpriseNumber":"123456","customReportFunction":"0","enterpriseId":"0d67725d97b644489419e7f700fcfcb6","enterpriseCreateUser":302,"enterpriseAddressProvince":"北京","enterpriseAddressCity":"北京","enterprisePhone":"15850791810","exportBehavior":"0","enterpriseEndTime":null,"enterpriseCreateTime":"2018-01-09 20:30:01","enterpriseLogo":null,"enterpriseAddressDistrict":"朝阳区","enterpriseDeleted":"1","isPlatform":"0","enterprisePossessorName":"张鹏辉","enterprisePlatformId":"6c512e43d3e144ca86b6c1a34d8801a8"}
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
         * enterpriseOpenTime : null
         * gatewayNumber : 2
         * enterpriseUpdateTime : null
         * enterpriseContact : 张鹏辉
         * enterpriseUpdateUser : null
         * enterpriseMailbox : zhangpenghui@163.com
         * description : null
         * enterprisePossessorId : 302
         * enterpriseCertification : 0
         * enterpriseAddressParticular : 一轻大厦3F
         * enterpriseEmploy : 1
         * enterpriseName : 友友天宇
         * enterpriseNumber : 123456
         * customReportFunction : 0
         * enterpriseId : 0d67725d97b644489419e7f700fcfcb6
         * enterpriseCreateUser : 302
         * enterpriseAddressProvince : 北京
         * enterpriseAddressCity : 北京
         * enterprisePhone : 15850791810
         * exportBehavior : 0
         * enterpriseEndTime : null
         * enterpriseCreateTime : 2018-01-09 20:30:01
         * enterpriseLogo : null
         * enterpriseAddressDistrict : 朝阳区
         * enterpriseDeleted : 1
         * isPlatform : 0
         * enterprisePossessorName : 张鹏辉
         * enterprisePlatformId : 6c512e43d3e144ca86b6c1a34d8801a8
         */

        private String enterpriseOpenTime;
        private int gatewayNumber;
        private String enterpriseUpdateTime;
        private String enterpriseContact;
        private Object enterpriseUpdateUser;
        private String enterpriseMailbox;
        private String description;
        private int enterprisePossessorId;
        private String enterpriseCertification;
        private String enterpriseAddressParticular;
        private String enterpriseEmploy;
        private String enterpriseName;
        private String enterpriseNumber;
        private String customReportFunction;
        private String enterpriseId;
        private int enterpriseCreateUser;
        private String enterpriseAddressProvince;
        private String enterpriseAddressCity;
        private String enterprisePhone;
        private String exportBehavior;
        private String enterpriseEndTime;
        private String enterpriseCreateTime;
        private String enterpriseLogo;
        private String enterpriseAddressDistrict;
        private String enterpriseDeleted;
        private String isPlatform;
        private String enterprisePossessorName;
        private String enterprisePlatformId;

        public String getEnterpriseOpenTime() {
            return enterpriseOpenTime;
        }

        public void setEnterpriseOpenTime(String enterpriseOpenTime) {
            this.enterpriseOpenTime = enterpriseOpenTime;
        }

        public int getGatewayNumber() {
            return gatewayNumber;
        }

        public void setGatewayNumber(int gatewayNumber) {
            this.gatewayNumber = gatewayNumber;
        }

        public String getEnterpriseUpdateTime() {
            return enterpriseUpdateTime;
        }

        public void setEnterpriseUpdateTime(String enterpriseUpdateTime) {
            this.enterpriseUpdateTime = enterpriseUpdateTime;
        }

        public String getEnterpriseContact() {
            return enterpriseContact;
        }

        public void setEnterpriseContact(String enterpriseContact) {
            this.enterpriseContact = enterpriseContact;
        }

        public Object getEnterpriseUpdateUser() {
            return enterpriseUpdateUser;
        }

        public void setEnterpriseUpdateUser(Object enterpriseUpdateUser) {
            this.enterpriseUpdateUser = enterpriseUpdateUser;
        }

        public String getEnterpriseMailbox() {
            return enterpriseMailbox;
        }

        public void setEnterpriseMailbox(String enterpriseMailbox) {
            this.enterpriseMailbox = enterpriseMailbox;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getEnterprisePossessorId() {
            return enterprisePossessorId;
        }

        public void setEnterprisePossessorId(int enterprisePossessorId) {
            this.enterprisePossessorId = enterprisePossessorId;
        }

        public String getEnterpriseCertification() {
            return enterpriseCertification;
        }

        public void setEnterpriseCertification(String enterpriseCertification) {
            this.enterpriseCertification = enterpriseCertification;
        }

        public String getEnterpriseAddressParticular() {
            return enterpriseAddressParticular;
        }

        public void setEnterpriseAddressParticular(String enterpriseAddressParticular) {
            this.enterpriseAddressParticular = enterpriseAddressParticular;
        }

        public String getEnterpriseEmploy() {
            return enterpriseEmploy;
        }

        public void setEnterpriseEmploy(String enterpriseEmploy) {
            this.enterpriseEmploy = enterpriseEmploy;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getEnterpriseNumber() {
            return enterpriseNumber;
        }

        public void setEnterpriseNumber(String enterpriseNumber) {
            this.enterpriseNumber = enterpriseNumber;
        }

        public String getCustomReportFunction() {
            return customReportFunction;
        }

        public void setCustomReportFunction(String customReportFunction) {
            this.customReportFunction = customReportFunction;
        }

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public int getEnterpriseCreateUser() {
            return enterpriseCreateUser;
        }

        public void setEnterpriseCreateUser(int enterpriseCreateUser) {
            this.enterpriseCreateUser = enterpriseCreateUser;
        }

        public String getEnterpriseAddressProvince() {
            return enterpriseAddressProvince;
        }

        public void setEnterpriseAddressProvince(String enterpriseAddressProvince) {
            this.enterpriseAddressProvince = enterpriseAddressProvince;
        }

        public String getEnterpriseAddressCity() {
            return enterpriseAddressCity;
        }

        public void setEnterpriseAddressCity(String enterpriseAddressCity) {
            this.enterpriseAddressCity = enterpriseAddressCity;
        }

        public String getEnterprisePhone() {
            return enterprisePhone;
        }

        public void setEnterprisePhone(String enterprisePhone) {
            this.enterprisePhone = enterprisePhone;
        }

        public String getExportBehavior() {
            return exportBehavior;
        }

        public void setExportBehavior(String exportBehavior) {
            this.exportBehavior = exportBehavior;
        }

        public String getEnterpriseEndTime() {
            return enterpriseEndTime;
        }

        public void setEnterpriseEndTime(String enterpriseEndTime) {
            this.enterpriseEndTime = enterpriseEndTime;
        }

        public String getEnterpriseCreateTime() {
            return enterpriseCreateTime;
        }

        public void setEnterpriseCreateTime(String enterpriseCreateTime) {
            this.enterpriseCreateTime = enterpriseCreateTime;
        }

        public String getEnterpriseLogo() {
            return enterpriseLogo;
        }

        public void setEnterpriseLogo(String enterpriseLogo) {
            this.enterpriseLogo = enterpriseLogo;
        }

        public String getEnterpriseAddressDistrict() {
            return enterpriseAddressDistrict;
        }

        public void setEnterpriseAddressDistrict(String enterpriseAddressDistrict) {
            this.enterpriseAddressDistrict = enterpriseAddressDistrict;
        }

        public String getEnterpriseDeleted() {
            return enterpriseDeleted;
        }

        public void setEnterpriseDeleted(String enterpriseDeleted) {
            this.enterpriseDeleted = enterpriseDeleted;
        }

        public String getIsPlatform() {
            return isPlatform;
        }

        public void setIsPlatform(String isPlatform) {
            this.isPlatform = isPlatform;
        }

        public String getEnterprisePossessorName() {
            return enterprisePossessorName;
        }

        public void setEnterprisePossessorName(String enterprisePossessorName) {
            this.enterprisePossessorName = enterprisePossessorName;
        }

        public String getEnterprisePlatformId() {
            return enterprisePlatformId;
        }

        public void setEnterprisePlatformId(String enterprisePlatformId) {
            this.enterprisePlatformId = enterprisePlatformId;
        }
    }
}
