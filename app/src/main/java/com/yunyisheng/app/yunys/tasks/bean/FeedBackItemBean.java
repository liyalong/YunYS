package com.yunyisheng.app.yunys.tasks.bean;

/**
 * Created by liyalong on 2018/1/31.
 */

public class FeedBackItemBean {
    private int feedbackItemId;
    private String feedbackName;
    private int feedbackType;
    private String createt;
    private int taskType;
    private String taskId;
    private String feedbackBacknum;
    private String feedbackVal;

    public void setFeedbackItemId(int feedbackItemId) {
        this.feedbackItemId = feedbackItemId;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public void setFeedbackType(int feedbackType) {
        this.feedbackType = feedbackType;
    }

    public void setCreatet(String createt) {
        this.createt = createt;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setFeedbackBacknum(String feedbackBacknum) {
        this.feedbackBacknum = feedbackBacknum;
    }

    public void setFeedbackVal(String feedbackVal) {
        this.feedbackVal = feedbackVal;
    }

    public int getFeedbackItemId() {

        return feedbackItemId;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public int getFeedbackType() {
        return feedbackType;
    }

    public String getCreatet() {
        return createt;
    }

    public int getTaskType() {
        return taskType;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getFeedbackBacknum() {
        return feedbackBacknum;
    }

    public String getFeedbackVal() {
        return feedbackVal;
    }

    @Override
    public String toString() {
        return "FeedBackItemBean{" +
                "feedbackItemId=" + feedbackItemId +
                ", feedbackName='" + feedbackName + '\'' +
                ", feedbackType=" + feedbackType +
                ", createt='" + createt + '\'' +
                ", taskType=" + taskType +
                ", taskId='" + taskId + '\'' +
                ", feedbackBacknum='" + feedbackBacknum + '\'' +
                ", feedbackVal='" + feedbackVal + '\'' +
                '}';
    }
}
