package com.yunyisheng.app.yunys.userset.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * 作者：fuduo on 2018/1/21 17:39
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class CompanyBean extends BaseModel {

    /**
     * respBody : {"enterpriseId":"94c614223b95437cacb34fd3af9b4023","enterprisePlatformId":"706311a4101d4d409c264b451c8d4672","enterpriseNumber":"123456","enterpriseName":"索尼","enterpriseLogo":null,"enterpriseAddressProvince":"日本","enterpriseAddressCity":"大阪","enterpriseAddressDistrict":"大阪","enterpriseAddressParticular":null,"enterprisePossessorId":340,"enterprisePossessorName":"琐妮","enterpriseContact":"琐妮","enterprisePhone":"15850791238","enterpriseMailbox":"suoni@163.com","enterpriseCertification":"1","enterpriseEmploy":"0","enterpriseCreateUser":340,"enterpriseUpdateUser":330,"enterpriseCreateTime":1515786527000,"enterpriseUpdateTime":1515839219000,"enterpriseOpenTime":1515839205000,"enterpriseEndTime":null,"gatewayNumber":0,"exportBehavior":"0","customReportFunction":"0","enterpriseDeleted":"1","isPlatform":"0","description":null}
     * firstPage : 0
     * lastPage : 0
     */

    private RespBodyBean respBody;
    private int firstPage;
    private int lastPage;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public static class RespBodyBean {
        /**
         * enterpriseId : 94c614223b95437cacb34fd3af9b4023
         * enterprisePlatformId : 706311a4101d4d409c264b451c8d4672
         * enterpriseNumber : 123456
         * enterpriseName : 索尼
         * enterpriseLogo : null
         * enterpriseAddressProvince : 日本
         * enterpriseAddressCity : 大阪
         * enterpriseAddressDistrict : 大阪
         * enterpriseAddressParticular : null
         * enterprisePossessorId : 340
         * enterprisePossessorName : 琐妮
         * enterpriseContact : 琐妮
         * enterprisePhone : 15850791238
         * enterpriseMailbox : suoni@163.com
         * enterpriseCertification : 1
         * enterpriseEmploy : 0
         * enterpriseCreateUser : 340
         * enterpriseUpdateUser : 330
         * enterpriseCreateTime : 1515786527000
         * enterpriseUpdateTime : 1515839219000
         * enterpriseOpenTime : 1515839205000
         * enterpriseEndTime : null
         * gatewayNumber : 0
         * exportBehavior : 0
         * customReportFunction : 0
         * enterpriseDeleted : 1
         * isPlatform : 0
         * description : null
         */

        private String enterpriseId;
        private String enterprisePlatformId;
        private String enterpriseNumber;
        private String enterpriseName;
        private String enterpriseLogo;
        private String enterpriseAddressProvince;
        private String enterpriseAddressCity;
        private String enterpriseAddressDistrict;
        private String enterpriseAddressParticular;
        private int enterprisePossessorId;
        private String enterprisePossessorName;
        private String enterpriseContact;
        private String enterprisePhone;
        private String enterpriseMailbox;
        private String enterpriseCertification;
        private String enterpriseEmploy;
        private int enterpriseCreateUser;
        private int enterpriseUpdateUser;
        private long enterpriseCreateTime;
        private long enterpriseUpdateTime;
        private long enterpriseOpenTime;
        private String enterpriseEndTime;
        private int gatewayNumber;
        private String exportBehavior;
        private String customReportFunction;
        private String enterpriseDeleted;
        private String isPlatform;
        private String description;

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getEnterprisePlatformId() {
            return enterprisePlatformId;
        }

        public void setEnterprisePlatformId(String enterprisePlatformId) {
            this.enterprisePlatformId = enterprisePlatformId;
        }

        public String getEnterpriseNumber() {
            return enterpriseNumber;
        }

        public void setEnterpriseNumber(String enterpriseNumber) {
            this.enterpriseNumber = enterpriseNumber;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getEnterpriseLogo() {
            return enterpriseLogo;
        }

        public void setEnterpriseLogo(String enterpriseLogo) {
            this.enterpriseLogo = enterpriseLogo;
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

        public String getEnterpriseAddressDistrict() {
            return enterpriseAddressDistrict;
        }

        public void setEnterpriseAddressDistrict(String enterpriseAddressDistrict) {
            this.enterpriseAddressDistrict = enterpriseAddressDistrict;
        }

        public String getEnterpriseAddressParticular() {
            return enterpriseAddressParticular;
        }

        public void setEnterpriseAddressParticular(String enterpriseAddressParticular) {
            this.enterpriseAddressParticular = enterpriseAddressParticular;
        }

        public int getEnterprisePossessorId() {
            return enterprisePossessorId;
        }

        public void setEnterprisePossessorId(int enterprisePossessorId) {
            this.enterprisePossessorId = enterprisePossessorId;
        }

        public String getEnterprisePossessorName() {
            return enterprisePossessorName;
        }

        public void setEnterprisePossessorName(String enterprisePossessorName) {
            this.enterprisePossessorName = enterprisePossessorName;
        }

        public String getEnterpriseContact() {
            return enterpriseContact;
        }

        public void setEnterpriseContact(String enterpriseContact) {
            this.enterpriseContact = enterpriseContact;
        }

        public String getEnterprisePhone() {
            return enterprisePhone;
        }

        public void setEnterprisePhone(String enterprisePhone) {
            this.enterprisePhone = enterprisePhone;
        }

        public String getEnterpriseMailbox() {
            return enterpriseMailbox;
        }

        public void setEnterpriseMailbox(String enterpriseMailbox) {
            this.enterpriseMailbox = enterpriseMailbox;
        }

        public String getEnterpriseCertification() {
            return enterpriseCertification;
        }

        public void setEnterpriseCertification(String enterpriseCertification) {
            this.enterpriseCertification = enterpriseCertification;
        }

        public String getEnterpriseEmploy() {
            return enterpriseEmploy;
        }

        public void setEnterpriseEmploy(String enterpriseEmploy) {
            this.enterpriseEmploy = enterpriseEmploy;
        }

        public int getEnterpriseCreateUser() {
            return enterpriseCreateUser;
        }

        public void setEnterpriseCreateUser(int enterpriseCreateUser) {
            this.enterpriseCreateUser = enterpriseCreateUser;
        }

        public int getEnterpriseUpdateUser() {
            return enterpriseUpdateUser;
        }

        public void setEnterpriseUpdateUser(int enterpriseUpdateUser) {
            this.enterpriseUpdateUser = enterpriseUpdateUser;
        }

        public long getEnterpriseCreateTime() {
            return enterpriseCreateTime;
        }

        public void setEnterpriseCreateTime(long enterpriseCreateTime) {
            this.enterpriseCreateTime = enterpriseCreateTime;
        }

        public long getEnterpriseUpdateTime() {
            return enterpriseUpdateTime;
        }

        public void setEnterpriseUpdateTime(long enterpriseUpdateTime) {
            this.enterpriseUpdateTime = enterpriseUpdateTime;
        }

        public long getEnterpriseOpenTime() {
            return enterpriseOpenTime;
        }

        public void setEnterpriseOpenTime(long enterpriseOpenTime) {
            this.enterpriseOpenTime = enterpriseOpenTime;
        }

        public String getEnterpriseEndTime() {
            return enterpriseEndTime;
        }

        public void setEnterpriseEndTime(String enterpriseEndTime) {
            this.enterpriseEndTime = enterpriseEndTime;
        }

        public int getGatewayNumber() {
            return gatewayNumber;
        }

        public void setGatewayNumber(int gatewayNumber) {
            this.gatewayNumber = gatewayNumber;
        }

        public String getExportBehavior() {
            return exportBehavior;
        }

        public void setExportBehavior(String exportBehavior) {
            this.exportBehavior = exportBehavior;
        }

        public String getCustomReportFunction() {
            return customReportFunction;
        }

        public void setCustomReportFunction(String customReportFunction) {
            this.customReportFunction = customReportFunction;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
