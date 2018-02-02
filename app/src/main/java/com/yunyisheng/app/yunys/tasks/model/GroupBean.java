package com.yunyisheng.app.yunys.tasks.model;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/18 10:40
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class GroupBean {

    private String feedbackName;

    private int feedbackType;

    @Override
    public String toString() {
        return "GroupBean{" +
                "feedbackName='" + feedbackName + '\'' +
                ", feedbackType=" + feedbackType +
                ", model=" + model +
                '}';
    }

    private List<ChildBean> model;

    public String getfeedbackName() {
        return feedbackName;
    }

    public void setfeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public int getfeedbackType() {
        return feedbackType;
    }

    public void setfeedbackType(int feedbackType) {
        this.feedbackType = feedbackType;
    }

    public List<ChildBean> getModel() {
        return model;
    }

    public void setModel(List<ChildBean> model) {
        this.model = model;
    }
}
