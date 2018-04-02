package com.yunyisheng.app.yunys.project.bean;

import java.util.List;

/**
 * Created by liyalong on 2018/4/2.
 */

public class FeedbackJSONBean {
    private String feedbackName;
    private Integer feedbackType;
    private List<BackModel> model;

    public Integer getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Integer feedbackType) {
        this.feedbackType = feedbackType;
    }

    public List<BackModel> getModel() {
        return model;
    }

    public void setModel(List<BackModel> model) {
        this.model = model;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public static class BackModel{
        private String dynamicTypeName;
        private Integer index;

        public String getDynamicTypeName() {
            return dynamicTypeName;
        }

        public void setDynamicTypeName(String dynamicTypeName) {
            this.dynamicTypeName = dynamicTypeName;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }
    }
}
