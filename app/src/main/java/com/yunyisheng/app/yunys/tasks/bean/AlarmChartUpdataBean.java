package com.yunyisheng.app.yunys.tasks.bean;

import java.util.List;

/**
 * Created by liyalong on 2018/5/23.
 */

public class AlarmChartUpdataBean {
    private String openTime;
    private String endTime;
    private String timeExpansion;
    private List<PropertyList> list;

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeExpansion() {
        return timeExpansion;
    }

    public void setTimeExpansion(String timeExpansion) {
        this.timeExpansion = timeExpansion;
    }

    public List<PropertyList> getList() {
        return list;
    }

    public void setList(List<PropertyList> list) {
        this.list = list;
    }

    public static class PropertyList {
        private Integer equipmentId;
        private String plcName;
        private String projectId;
        private String detail;

        public Integer getEquipmentId() {
            return equipmentId;
        }

        public void setEquipmentId(Integer equipmentId) {
            this.equipmentId = equipmentId;
        }

        public String getPlcName() {
            return plcName;
        }

        public void setPlcName(String plcName) {
            this.plcName = plcName;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    /**
     * 作者：fuduo on 2018/1/18 10:40
     * 邮箱：duoendeavor@163.com
     * 用途：
     */

    public static class GroupBean {

        private String feedbackName;

        private int feedbackType;

        @Override
        public String toString() {
            return "{" +
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
}
