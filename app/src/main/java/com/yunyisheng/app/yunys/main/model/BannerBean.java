package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/5 14:36
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class BannerBean extends BaseModel {


    private List<RespBodyBean> respBody;

    public List<RespBodyBean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<RespBodyBean> respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * lableUuid : 1234567890
         * personalLabelList : [{"isDelete":"0","labelCreateTime":1517727803000,"labelId":1,"labelUserId":1,"labelValue":"345643","lableUuid":"1234567890","projectId":"","statusCode":"0","statusName":"全部","typeCode":"0","typeName":"项目"},{"isDelete":"0","labelCreateTime":1517727839000,"labelId":2,"labelUserId":1,"labelValue":"234543","lableUuid":"1234567890","statusCode":"1","statusName":"进行中","typeCode":"0","typeName":"项目"},{"isDelete":"0","labelCreateTime":1517727874000,"labelId":3,"labelUserId":1,"labelValue":"23453","lableUuid":"1234567890","statusCode":"2","statusName":"已结束","typeCode":"0","typeName":"项目"}]
         * typeCode : 0
         * typeName : 项目
         */

        private String lableUuid;
        private String typeCode;
        private String typeName;
        private List<PersonalLabelListBean> personalLabelList;

        public String getLableUuid() {
            return lableUuid;
        }

        public void setLableUuid(String lableUuid) {
            this.lableUuid = lableUuid;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public List<PersonalLabelListBean> getPersonalLabelList() {
            return personalLabelList;
        }

        public void setPersonalLabelList(List<PersonalLabelListBean> personalLabelList) {
            this.personalLabelList = personalLabelList;
        }

        public static class PersonalLabelListBean {
            /**
             * isDelete : 0
             * labelCreateTime : 1517727803000
             * labelId : 1
             * labelUserId : 1
             * labelValue : 345643
             * lableUuid : 1234567890
             * projectId :
             * statusCode : 0
             * statusName : 全部
             * typeCode : 0
             * typeName : 项目
             */

            private String isDelete;
            private long labelCreateTime;
            private int labelId;
            private int labelUserId;
            private String labelValue;
            private String lableUuid;
            private String projectId;
            private String statusCode;
            private String statusName;
            private String typeCode;
            private String typeName;

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public long getLabelCreateTime() {
                return labelCreateTime;
            }

            public void setLabelCreateTime(long labelCreateTime) {
                this.labelCreateTime = labelCreateTime;
            }

            public int getLabelId() {
                return labelId;
            }

            public void setLabelId(int labelId) {
                this.labelId = labelId;
            }

            public int getLabelUserId() {
                return labelUserId;
            }

            public void setLabelUserId(int labelUserId) {
                this.labelUserId = labelUserId;
            }

            public String getLabelValue() {
                return labelValue;
            }

            public void setLabelValue(String labelValue) {
                this.labelValue = labelValue;
            }

            public String getLableUuid() {
                return lableUuid;
            }

            public void setLableUuid(String lableUuid) {
                this.lableUuid = lableUuid;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getStatusCode() {
                return statusCode;
            }

            public void setStatusCode(String statusCode) {
                this.statusCode = statusCode;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getTypeCode() {
                return typeCode;
            }

            public void setTypeCode(String typeCode) {
                this.typeCode = typeCode;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }
        }
    }
}
