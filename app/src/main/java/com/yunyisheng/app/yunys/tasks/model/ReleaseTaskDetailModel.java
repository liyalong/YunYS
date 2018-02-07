package com.yunyisheng.app.yunys.tasks.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * Created by liyalong on 2018/2/7.
 */

public class ReleaseTaskDetailModel extends BaseModel {
    private ReleaseTask respBody;

    public void setRespBody(ReleaseTask respBody) {
        this.respBody = respBody;
    }

    public ReleaseTask getRespBody() {

        return respBody;
    }

    public class ReleaseTask{
        private String releaseId;
        private String releaseName;
        private String releaseRemark;
        private String releaseBegint;
        private String releaseEndt;
        private String releaseStatFinish;
        private String releaseStatClaim;
        private String releaseStatUnclaim;
        private String releaseStatBack;
        private String releaseCreatet;
        private String releaseUpdatet;
        private String releaseBaseformId;
        private String releaseAllot;
        private String releaseUserId;
        private String releaseUsername;
        private String feedbackBacknum;
        private String releaseTaskType;
        private String taskId;
        private String equipmentId;
        private String projectId;
        private String companyId;
        private String equipmentName;
        private String projectName;
        private String terraceId;

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

        public void setReleaseStatFinish(String releaseStatFinish) {
            this.releaseStatFinish = releaseStatFinish;
        }

        public void setReleaseStatClaim(String releaseStatClaim) {
            this.releaseStatClaim = releaseStatClaim;
        }

        public void setReleaseStatUnclaim(String releaseStatUnclaim) {
            this.releaseStatUnclaim = releaseStatUnclaim;
        }

        public void setReleaseStatBack(String releaseStatBack) {
            this.releaseStatBack = releaseStatBack;
        }

        public void setReleaseCreatet(String releaseCreatet) {
            this.releaseCreatet = releaseCreatet;
        }

        public void setReleaseUpdatet(String releaseUpdatet) {
            this.releaseUpdatet = releaseUpdatet;
        }

        public void setReleaseBaseformId(String releaseBaseformId) {
            this.releaseBaseformId = releaseBaseformId;
        }

        public void setReleaseAllot(String releaseAllot) {
            this.releaseAllot = releaseAllot;
        }

        public void setReleaseUserId(String releaseUserId) {
            this.releaseUserId = releaseUserId;
        }

        public void setReleaseUsername(String releaseUsername) {
            this.releaseUsername = releaseUsername;
        }

        public void setFeedbackBacknum(String feedbackBacknum) {
            this.feedbackBacknum = feedbackBacknum;
        }

        public void setReleaseTaskType(String releaseTaskType) {
            this.releaseTaskType = releaseTaskType;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
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

        public void setEquipmentName(String equipmentName) {
            this.equipmentName = equipmentName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public void setTerraceId(String terraceId) {
            this.terraceId = terraceId;
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

        public String getReleaseStatFinish() {
            return releaseStatFinish;
        }

        public String getReleaseStatClaim() {
            return releaseStatClaim;
        }

        public String getReleaseStatUnclaim() {
            return releaseStatUnclaim;
        }

        public String getReleaseStatBack() {
            return releaseStatBack;
        }

        public String getReleaseCreatet() {
            return releaseCreatet;
        }

        public String getReleaseUpdatet() {
            return releaseUpdatet;
        }

        public String getReleaseBaseformId() {
            return releaseBaseformId;
        }

        public String getReleaseAllot() {
            return releaseAllot;
        }

        public String getReleaseUserId() {
            return releaseUserId;
        }

        public String getReleaseUsername() {
            return releaseUsername;
        }

        public String getFeedbackBacknum() {
            return feedbackBacknum;
        }

        public String getReleaseTaskType() {
            return releaseTaskType;
        }

        public String getTaskId() {
            return taskId;
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

        public String getEquipmentName() {
            return equipmentName;
        }

        public String getProjectName() {
            return projectName;
        }

        public String getTerraceId() {
            return terraceId;
        }
    }
}
