package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/30 16:27
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MessageBean extends BaseModel {


    private List<RespBodyBean> list;

    public List<RespBodyBean> getRespBody() {
        return list;
    }

    public void setRespBody(List<RespBodyBean> respBody) {
        this.list = respBody;
    }

    public class RespBodyBean{
        /**
         * messageReleaseUserName : 毛硕
         * messageCreateTime : null
         * messageReleaseUserId : 312
         * messageRemark : 任务测试 -- 是否发送消息任务已经下发给你，下发时间：2018-01-24 11:44:18
         * messageReceiveUserId : 307
         * messageInfoId : 164
         * applicationId : null
         * companyId : null
         * messageUpdateTime : 2017-10-24 00:21:58
         * messageType : 1
         * messageId : 2
         * messageStat : 0
         */

        private String messageReleaseUserName;
        private String messageCreateTime;
        private int messageReleaseUserId;
        private String messageRemark;
        private int messageReceiveUserId;
        private String messageInfoId;
        private Object applicationId;
        private Object companyId;
        private String messageUpdateTime;
        private String messageType;
        private int messageId;
        private String messageStat;
        private String projectId;
        private String sameType;

        public String getSameType() {
            return sameType;
        }

        public void setSameType(String sameType) {
            this.sameType = sameType;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getMessageReleaseUserName() {
            return messageReleaseUserName;
        }

        public void setMessageReleaseUserName(String messageReleaseUserName) {
            this.messageReleaseUserName = messageReleaseUserName;
        }

        public String getMessageCreateTime() {
            return messageCreateTime;
        }

        public void setMessageCreateTime(String messageCreateTime) {
            this.messageCreateTime = messageCreateTime;
        }

        public int getMessageReleaseUserId() {
            return messageReleaseUserId;
        }

        public void setMessageReleaseUserId(int messageReleaseUserId) {
            this.messageReleaseUserId = messageReleaseUserId;
        }

        public String getMessageRemark() {
            return messageRemark;
        }

        public void setMessageRemark(String messageRemark) {
            this.messageRemark = messageRemark;
        }

        public int getMessageReceiveUserId() {
            return messageReceiveUserId;
        }

        public void setMessageReceiveUserId(int messageReceiveUserId) {
            this.messageReceiveUserId = messageReceiveUserId;
        }

        public String getMessageInfoId() {
            return messageInfoId;
        }

        public void setMessageInfoId(String messageInfoId) {
            this.messageInfoId = messageInfoId;
        }

        public Object getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(Object applicationId) {
            this.applicationId = applicationId;
        }

        public Object getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Object companyId) {
            this.companyId = companyId;
        }

        public String getMessageUpdateTime() {
            return messageUpdateTime;
        }

        public void setMessageUpdateTime(String messageUpdateTime) {
            this.messageUpdateTime = messageUpdateTime;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public int getMessageId() {
            return messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }

        public String getMessageStat() {
            return messageStat;
        }

        public void setMessageStat(String messageStat) {
            this.messageStat = messageStat;
        }
    }
}
