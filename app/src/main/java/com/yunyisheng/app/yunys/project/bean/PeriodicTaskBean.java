package com.yunyisheng.app.yunys.project.bean;

/**
 * Created by liyalong on 2018/1/25.
 */

public class PeriodicTaskBean {
    private int cycletaskId;
    private String cycletaskName;
    private String cycletaskRemark;
    private int cycletaskStat;
    private String cycletaskBegint;
    private String cycletaskEndt;
    private String corn;
    private int cycletaskType;
    private int equipmentId;
    private String projectId;
    private String timeLength;
    private int overTimeStat;
    private String templateId;
    private String feedbackBacknum;
    private String createTime;
    private String updateTime;
    private String taskUseruuid;
    private String feedbackItemList;

    public void setCycletaskId(int cycletaskId) {
        this.cycletaskId = cycletaskId;
    }

    public void setCycletaskName(String cycletaskName) {
        this.cycletaskName = cycletaskName;
    }

    public void setCycletaskRemark(String cycletaskRemark) {
        this.cycletaskRemark = cycletaskRemark;
    }

    public void setCycletaskStat(int cycletaskStat) {
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

    public void setCycletaskType(int cycletaskType) {
        this.cycletaskType = cycletaskType;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }

    public void setOverTimeStat(int overTimeStat) {
        this.overTimeStat = overTimeStat;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setFeedbackBacknum(String feedbackBacknum) {
        this.feedbackBacknum = feedbackBacknum;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setTaskUseruuid(String taskUseruuid) {
        this.taskUseruuid = taskUseruuid;
    }

    public void setFeedbackItemList(String feedbackItemList) {
        this.feedbackItemList = feedbackItemList;
    }

    public int getCycletaskId() {

        return cycletaskId;
    }

    public String getCycletaskName() {
        return cycletaskName;
    }

    public String getCycletaskRemark() {
        return cycletaskRemark;
    }

    public int getCycletaskStat() {
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

    public int getCycletaskType() {
        return cycletaskType;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public int getOverTimeStat() {
        return overTimeStat;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getFeedbackBacknum() {
        return feedbackBacknum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getTaskUseruuid() {
        return taskUseruuid;
    }

    public String getFeedbackItemList() {
        return feedbackItemList;
    }

    @Override
    public String toString() {
        return "PeriodicTaskBean{" +
                "cycletaskId=" + cycletaskId +
                ", cycletaskName='" + cycletaskName + '\'' +
                ", cycletaskRemark='" + cycletaskRemark + '\'' +
                ", cycletaskStat=" + cycletaskStat +
                ", cycletaskBegint='" + cycletaskBegint + '\'' +
                ", cycletaskEndt='" + cycletaskEndt + '\'' +
                ", corn='" + corn + '\'' +
                ", cycletaskType=" + cycletaskType +
                ", equipmentId=" + equipmentId +
                ", projectId='" + projectId + '\'' +
                ", timeLength='" + timeLength + '\'' +
                ", overTimeStat=" + overTimeStat +
                ", templateId='" + templateId + '\'' +
                ", feedbackBacknum='" + feedbackBacknum + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", taskUseruuid='" + taskUseruuid + '\'' +
                ", feedbackItemList='" + feedbackItemList + '\'' +
                '}';
    }
}
