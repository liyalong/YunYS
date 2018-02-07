package com.yunyisheng.app.yunys.tasks.bean;

import android.content.Intent;

import java.util.List;

/**
 * Created by liyalong on 2018/2/4.
 */

public class UpdateCycleTaskBean {
    private String projectId;
    private Integer cycletaskId;
    private String cycletaskName;
    private String cycletaskRemark;
    private String cycletaskStat;
    private String cycletaskBegint;
    private String cycletaskEndt;
    private String corn;
    private String cycletaskType;
    private String equipmentId;
    private String timeLength;
    private String templateId;
    private String userIds;
    private String feedbackJSON;
    private String userIdStr;
    private String equipmentName;
    private String projectName;
    private String feedbackBacknum;
    private String feedbackItemList;
    private List<SelectUser> cycleTaskUserList;

    public void setCycleTaskUserList(List<SelectUser> cycleTaskUserList) {
        this.cycleTaskUserList = cycleTaskUserList;
    }

    public List<SelectUser> getCycleTaskUserList() {

        return cycleTaskUserList;
    }

    public void setFeedbackItemList(String feedbackItemList) {
        this.feedbackItemList = feedbackItemList;
    }

    public String getFeedbackItemList() {

        return feedbackItemList;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setFeedbackBacknum(String feedbackBacknum) {
        this.feedbackBacknum = feedbackBacknum;
    }

    public String getProjectName() {

        return projectName;
    }

    public String getFeedbackBacknum() {
        return feedbackBacknum;
    }

    public String getEquipmentName() {

        return equipmentName;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getUserIdStr() {

        return userIdStr;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {

        return projectId;
    }

    public Integer getCycletaskId() {
        return cycletaskId;
    }

    public String getCycletaskName() {
        return cycletaskName;
    }

    public String getCycletaskRemark() {
        return cycletaskRemark;
    }

    public String getCycletaskStat() {
        return cycletaskStat;
    }

    public String getCycletaskBegint() {
        return cycletaskBegint;
    }

    public String getCycletaskEndt() {
        return cycletaskEndt;
    }

    public String getCorn() {
        return corn;
    }

    public String getCycletaskType() {
        return cycletaskType;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getUserIds() {
        return userIds;
    }

    public String getFeedbackJSON() {
        return feedbackJSON;
    }

    public void setCycletaskId(Integer cycletaskId) {
        this.cycletaskId = cycletaskId;
    }

    public void setCycletaskName(String cycletaskName) {
        this.cycletaskName = cycletaskName;
    }

    public void setCycletaskRemark(String cycletaskRemark) {
        this.cycletaskRemark = cycletaskRemark;
    }

    public void setCycletaskStat(String cycletaskStat) {
        this.cycletaskStat = cycletaskStat;
    }

    public void setCycletaskBegint(String cycletaskBegint) {
        this.cycletaskBegint = cycletaskBegint;
    }

    public void setCycletaskEndt(String cycletaskEndt) {
        this.cycletaskEndt = cycletaskEndt;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }

    public void setCycletaskType(String cycletaskType) {
        this.cycletaskType = cycletaskType;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public void setFeedbackJSON(String feedbackJSON) {
        this.feedbackJSON = feedbackJSON;
    }

    public class SelectUser{
        private String userName;
        private Integer userId;

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getUserId() {

            return userId;
        }

        public String getUserName() {

            return userName;
        }
    }
}
