package com.yunyisheng.app.yunys.tasks.bean;

/**
 * Created by liyalong on 2018/1/31.
 */

public class TaskBackBean {
    private int taskbackId;
    private int releaseId;
    private String releaseName;
    private String taskbackUserId;
    private String taskbackUsername;
    private String taskbackVal;
    private String taskbackCreatet;

    public void setTaskbackId(int taskbackId) {
        this.taskbackId = taskbackId;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public void setTaskbackUserId(String taskbackUserId) {
        this.taskbackUserId = taskbackUserId;
    }

    public void setTaskbackUsername(String taskbackUsername) {
        this.taskbackUsername = taskbackUsername;
    }

    public void setTaskbackVal(String taskbackVal) {
        this.taskbackVal = taskbackVal;
    }

    public void setTaskbackCreatet(String taskbackCreatet) {
        this.taskbackCreatet = taskbackCreatet;
    }

    public int getTaskbackId() {

        return taskbackId;
    }

    public int getReleaseId() {
        return releaseId;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public String getTaskbackUserId() {
        return taskbackUserId;
    }

    public String getTaskbackUsername() {
        return taskbackUsername;
    }

    public String getTaskbackVal() {
        return taskbackVal;
    }

    public String getTaskbackCreatet() {
        return taskbackCreatet;
    }

    @Override
    public String toString() {
        return "TaskBackBean{" +
                "taskbackId=" + taskbackId +
                ", releaseId=" + releaseId +
                ", releaseName='" + releaseName + '\'' +
                ", taskbackUserId='" + taskbackUserId + '\'' +
                ", taskbackUsername='" + taskbackUsername + '\'' +
                ", taskbackVal='" + taskbackVal + '\'' +
                ", taskbackCreatet='" + taskbackCreatet + '\'' +
                '}';
    }
}
