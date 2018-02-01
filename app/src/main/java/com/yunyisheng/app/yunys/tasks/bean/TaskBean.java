package com.yunyisheng.app.yunys.tasks.bean;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by liyalong on 2018/1/29.
 */

public class TaskBean implements Serializable{
    private String taskId;              //
    private String releaseId;           //任务id
    private String releaseName;         //任务名称
    private String releaseRemark;       //任务备注
    private String releaseBegint;       //任务开始时间
    private String releaseEndt;         //任务结束时间
    private String releaseUserId;       //任务创建人id
    private String releaseUsername;     //任务创建人名字
    private String releaseFormId;
    private String releaseFormInstanceId;
    private int taskStat;     //任务类型  1 设备任务，2 非设备任务
    private String taskUserId;
    private String taskUserName;
    private String taskSubmitTime;
    private String releaseAllot;
    private int releaseStatFinish;
    private int releaseStatClaim;
    private int releaseStatUnclaim;
    private int releaseStatBack;
    private String feedbackBacknum;
    private int releaseTaskType;
    private String equipmentId;
    private String projectId;
    private String companyId;
    private String terraceId;

    public void setReleaseStatFinish(int releaseStatFinish) {
        this.releaseStatFinish = releaseStatFinish;
    }

    public void setReleaseStatClaim(int releaseStatClaim) {
        this.releaseStatClaim = releaseStatClaim;
    }

    public void setReleaseStatUnclaim(int releaseStatUnclaim) {
        this.releaseStatUnclaim = releaseStatUnclaim;
    }

    public void setReleaseStatBack(int releaseStatBack) {
        this.releaseStatBack = releaseStatBack;
    }

    public int getReleaseStatFinish() {

        return releaseStatFinish;
    }

    public int getReleaseStatClaim() {
        return releaseStatClaim;
    }

    public int getReleaseStatUnclaim() {
        return releaseStatUnclaim;
    }

    public int getReleaseStatBack() {
        return releaseStatBack;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public void setReleaseRemark(String releaseRemark) {
        this.releaseRemark = releaseRemark;
    }

    public void setReleaseBegint(String releaseBegint) {
        this.releaseBegint = releaseBegint;
    }

    public void setReleaseEndt(String releaseEndt) {
        this.releaseEndt = releaseEndt;
    }

    public void setReleaseUserId(String releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    public void setReleaseUsername(String releaseUsername) {
        this.releaseUsername = releaseUsername;
    }

    public void setReleaseFormId(String releaseFormId) {
        this.releaseFormId = releaseFormId;
    }

    public void setReleaseFormInstanceId(String releaseFormInstanceId) {
        this.releaseFormInstanceId = releaseFormInstanceId;
    }

    public void setTaskStat(int taskStat) {
        this.taskStat = taskStat;
    }

    public void setTaskUserId(String taskUserId) {
        this.taskUserId = taskUserId;
    }

    public void setTaskUserName(String taskUserName) {
        this.taskUserName = taskUserName;
    }

    public void setTaskSubmitTime(String taskSubmitTime) {
        this.taskSubmitTime = taskSubmitTime;
    }

    public void setReleaseAllot(String releaseAllot) {
        this.releaseAllot = releaseAllot;
    }

    public void setFeedbackBacknum(String feedbackBacknum) {
        this.feedbackBacknum = feedbackBacknum;
    }

    public void setReleaseTaskType(int releaseTaskType) {
        this.releaseTaskType = releaseTaskType;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setTerraceId(String terraceId) {
        this.terraceId = terraceId;
    }

    public String getTaskId() {

        return taskId;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public String getReleaseRemark() {
        return releaseRemark;
    }

    public String getReleaseBegint() {
        return releaseBegint;
    }

    public String getReleaseEndt() {
        return releaseEndt;
    }

    public String getReleaseUserId() {
        return releaseUserId;
    }

    public String getReleaseUsername() {
        return releaseUsername;
    }

    public String getReleaseFormId() {
        return releaseFormId;
    }

    public String getReleaseFormInstanceId() {
        return releaseFormInstanceId;
    }

    public int getTaskStat() {
        return taskStat;
    }

    public String getTaskUserId() {
        return taskUserId;
    }

    public String getTaskUserName() {
        return taskUserName;
    }

    public String getTaskSubmitTime() {
        return taskSubmitTime;
    }

    public String getReleaseAllot() {
        return releaseAllot;
    }

    public String getFeedbackBacknum() {
        return feedbackBacknum;
    }

    public int getReleaseTaskType() {
        return releaseTaskType;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getTerraceId() {
        return terraceId;
    }

    @Override
    public String
    toString() {
        return "TaskBean{" +
                "taskId='" + taskId + '\'' +
                ", releaseId='" + releaseId + '\'' +
                ", releaseName='" + releaseName + '\'' +
                ", releaseRemark='" + releaseRemark + '\'' +
                ", releaseBegint='" + releaseBegint + '\'' +
                ", releaseEndt='" + releaseEndt + '\'' +
                ", releaseUserId='" + releaseUserId + '\'' +
                ", releaseUsername='" + releaseUsername + '\'' +
                ", releaseFormId='" + releaseFormId + '\'' +
                ", releaseFormInstanceId='" + releaseFormInstanceId + '\'' +
                ", taskStat=" + taskStat +
                ", taskUserId='" + taskUserId + '\'' +
                ", taskUserName='" + taskUserName + '\'' +
                ", taskSubmitTime='" + taskSubmitTime + '\'' +
                ", releaseAllot='" + releaseAllot + '\'' +
                ", feedbackBacknum='" + feedbackBacknum + '\'' +
                ", releaseTaskType=" + releaseTaskType +
                ", equipmentId='" + equipmentId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", terraceId='" + terraceId + '\'' +
                '}';
    }
}
