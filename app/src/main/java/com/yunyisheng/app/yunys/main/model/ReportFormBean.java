package com.yunyisheng.app.yunys.main.model;

import java.util.List;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * 作者：fuduo on 2018/1/26 18:56
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ReportFormBean implements IModel {

    private Integer respCode ;
    private String respMsg;
    private List<ListBean> list;

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

    public void ReportFormBean(){

    }


    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
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
        return false;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }

    public static class ListBean {
        /**
         * instanceId : 32
         * instanceName : 枯黄冰的报表
         * chartType : 1
         * reportUserId : 343
         * reportUserName : 阿斯顿
         * reportId : 21
         * refreshFrequency : null
         * reportCalculate : 0
         * recentTime : null
         * openTime : 2018-01-01 23:28:15
         * endTime : 2018-01-26 15:51:29
         * reportNumber : null
         * createTime : 2018-01-26 15:54:10
         * updateTime : 2018-01-26 16:11:00
         * isDisabled : 0
         * isDelete : 0
         */

        private int instanceId;
        private String instanceName;
        private String chartType;
        private int reportUserId;
        private String reportUserName;
        private int reportId;
        private String refreshFrequency;
        private String reportCalculate;
        private String recentTime;
        private String openTime;
        private String endTime;
        private String reportNumber;
        private String createTime;
        private String updateTime;
        private String isDisabled;
        private String isDelete;

        public void ListBean(){}

        public int getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(int instanceId) {
            this.instanceId = instanceId;
        }

        public String getInstanceName() {
            return instanceName;
        }

        public void setInstanceName(String instanceName) {
            this.instanceName = instanceName;
        }

        public String getChartType() {
            return chartType;
        }

        public void setChartType(String chartType) {
            this.chartType = chartType;
        }

        public int getReportUserId() {
            return reportUserId;
        }

        public void setReportUserId(int reportUserId) {
            this.reportUserId = reportUserId;
        }

        public String getReportUserName() {
            return reportUserName;
        }

        public void setReportUserName(String reportUserName) {
            this.reportUserName = reportUserName;
        }

        public int getReportId() {
            return reportId;
        }

        public void setReportId(int reportId) {
            this.reportId = reportId;
        }

        public String getRefreshFrequency() {
            return refreshFrequency;
        }

        public void setRefreshFrequency(String refreshFrequency) {
            this.refreshFrequency = refreshFrequency;
        }

        public String getReportCalculate() {
            return reportCalculate;
        }

        public void setReportCalculate(String reportCalculate) {
            this.reportCalculate = reportCalculate;
        }

        public String getRecentTime() {
            return recentTime;
        }

        public void setRecentTime(String recentTime) {
            this.recentTime = recentTime;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getReportNumber() {
            return reportNumber;
        }

        public void setReportNumber(String reportNumber) {
            this.reportNumber = reportNumber;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getIsDisabled() {
            return isDisabled;
        }

        public void setIsDisabled(String isDisabled) {
            this.isDisabled = isDisabled;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }
    }
}
