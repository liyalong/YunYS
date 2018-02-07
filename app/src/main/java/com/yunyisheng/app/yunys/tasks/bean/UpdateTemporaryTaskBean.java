package com.yunyisheng.app.yunys.tasks.bean;

/**
 * Created by liyalong on 2018/2/4.
 */

public class UpdateTemporaryTaskBean {
    private String releaseId;   //任务id
    private int releaseTaskType; //任务类型
    private String releaseName; //任务名称
    private String releaseRemark; //任务备注
    private String releaseBegint; //开始时间
    private String releaseEndt;     //结束时间
    private String listStr;         //指派用户
    private String releaseBaseformId; //模板id（非设备任务专用）
    private String equipmentId;     //设备id
    private String feedbackJSON;     //任务反馈项
    private String projectId;
    private String feedbackBacknum; //反馈项id
    private String userlist;

    public void setUserlist(String userlist) {
        this.userlist = userlist;
    }

    public String getUserlist() {

        return userlist;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {

        return projectId;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public int getReleaseTaskType() {
        return releaseTaskType;
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

    public String getListStr() {
        return listStr;
    }

    public String getReleaseBaseformId() {
        return releaseBaseformId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getFeedbackJSON() {
        return feedbackJSON;
    }

    public void setReleaseId(String releaseId) {

        this.releaseId = releaseId;
    }

    public void setReleaseTaskType(int releaseTaskType) {
        this.releaseTaskType = releaseTaskType;
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

    public void setListStr(String listStr) {
        this.listStr = listStr;
    }

    public void setReleaseBaseformId(String releaseBaseformId) {
        this.releaseBaseformId = releaseBaseformId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setFeedbackJSON(String feedbackJSON) {
        this.feedbackJSON = feedbackJSON;
    }

    public void setFeedbackBacknum(String feedbackBacknum) {
        this.feedbackBacknum = feedbackBacknum;
    }

    public String getFeedbackBacknum() {

        return feedbackBacknum;
    }
}
