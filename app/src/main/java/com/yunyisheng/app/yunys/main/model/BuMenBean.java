package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/28 14:02
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class BuMenBean extends BaseModel {

    private List<RespBodyBean> respBody;

    public List<RespBodyBean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<RespBodyBean> respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * sectionId : 2
         * sectionName : 友友
         * sectionParentid : 0
         * sectionStatus : 1
         * sectionSort : 1
         * sectionCreatedTime : 1512191993000
         * sectionUpdatedTime : 1512191993000
         * isParent : true
         * enterpriseId : youyou
         */

        private int sectionId;
        private String sectionName;
        private int sectionParentid;
        private int sectionStatus;
        private int sectionSort;
        private long sectionCreatedTime;
        private long sectionUpdatedTime;
        private boolean isParent;
        private String enterpriseId;

        public int getSectionId() {
            return sectionId;
        }

        public void setSectionId(int sectionId) {
            this.sectionId = sectionId;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public int getSectionParentid() {
            return sectionParentid;
        }

        public void setSectionParentid(int sectionParentid) {
            this.sectionParentid = sectionParentid;
        }

        public int getSectionStatus() {
            return sectionStatus;
        }

        public void setSectionStatus(int sectionStatus) {
            this.sectionStatus = sectionStatus;
        }

        public int getSectionSort() {
            return sectionSort;
        }

        public void setSectionSort(int sectionSort) {
            this.sectionSort = sectionSort;
        }

        public long getSectionCreatedTime() {
            return sectionCreatedTime;
        }

        public void setSectionCreatedTime(long sectionCreatedTime) {
            this.sectionCreatedTime = sectionCreatedTime;
        }

        public long getSectionUpdatedTime() {
            return sectionUpdatedTime;
        }

        public void setSectionUpdatedTime(long sectionUpdatedTime) {
            this.sectionUpdatedTime = sectionUpdatedTime;
        }

        public boolean isIsParent() {
            return isParent;
        }

        public void setIsParent(boolean isParent) {
            this.isParent = isParent;
        }

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }
    }
}
