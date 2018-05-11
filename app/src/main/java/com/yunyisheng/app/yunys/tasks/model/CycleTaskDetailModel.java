package com.yunyisheng.app.yunys.tasks.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.schedule.model.RenWuFanKuiDetailBean;
import com.yunyisheng.app.yunys.tasks.bean.UpdateCycleTaskBean;

import java.util.List;

/**
 * Created by liyalong on 2018/2/7.
 */

public class CycleTaskDetailModel extends BaseModel {
    private taskDetail respBody;

    public void setRespBody(taskDetail respBody) {
        this.respBody = respBody;
    }

    public taskDetail getRespBody() {

        return respBody;
    }

    public class taskDetail{
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
        private String timeLengthMin;
        private String timeLengthSec;
        private String templateId;
        private String userIdStr;
        private String equipmentName;
        private String projectName;
        private String feedbackBacknum;
        private List<FeedbackItemBean> feedbackItemList;
        private List<SelectUser> cycleTaskUserList;

        public String getTimeLengthMin() {
            return timeLengthMin;
        }

        public void setTimeLengthMin(String timeLengthMin) {
            this.timeLengthMin = timeLengthMin;
        }

        public String getTimeLengthSec() {
            return timeLengthSec;
        }

        public void setTimeLengthSec(String timeLengthSec) {
            this.timeLengthSec = timeLengthSec;
        }

        public void setFeedbackItemList(List<FeedbackItemBean> feedbackItemList) {
            this.feedbackItemList = feedbackItemList;
        }

        public List<FeedbackItemBean> getFeedbackItemList() {

            return feedbackItemList;
        }

        public String getFeedbackBacknum() {

            return feedbackBacknum;
        }

        public void setFeedbackBacknum(String feedbackBacknum) {

            this.feedbackBacknum = feedbackBacknum;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
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

        public void setUserIdStr(String userIdStr) {
            this.userIdStr = userIdStr;
        }

        public void setEquipmentName(String equipmentName) {
            this.equipmentName = equipmentName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }


        public void setCycleTaskUserList(List<SelectUser> cycleTaskUserList) {
            this.cycleTaskUserList = cycleTaskUserList;
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

        public String getUserIdStr() {
            return userIdStr;
        }

        public String getEquipmentName() {
            return equipmentName;
        }

        public String getProjectName() {
            return projectName;
        }


        public List<SelectUser> getCycleTaskUserList() {
            return cycleTaskUserList;
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
        public class FeedbackItemBean {
            /**
             * feedbackItemId : 172
             * feedbackName : 性别
             * feedbackType : 2
             * createt : 1515729299000
             * updatet : null
             * model : [{"dynamic_type_name":"男","index":0},{"dynamic_type_name":"女","index":1}]
             * taskType : 1
             * taskId : 121
             * feedbackBacknum : eeea587287a9478d8ea8a5dcf4318fb9
             * feedbackVal : null
             */

            private int feedbackItemId;
            private String feedbackName;
            private int feedbackType;
            private String createt;
            private Object updatet;
            private List<RenWuFanKuiDetailBean.RespBodyBean.Valueitem> modelArray;
            private int taskType;
            private int taskId;
            private String feedbackBacknum;
            private String feedbackVal;
            private String model;

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public List<RenWuFanKuiDetailBean.RespBodyBean.Valueitem> getModelArray() {
                return modelArray;
            }

            public void setModelArray(List<RenWuFanKuiDetailBean.RespBodyBean.Valueitem> modelArray) {
                this.modelArray = modelArray;
            }

            public int getFeedbackItemId() {
                return feedbackItemId;
            }

            public void setFeedbackItemId(int feedbackItemId) {
                this.feedbackItemId = feedbackItemId;
            }

            public String getFeedbackName() {
                return feedbackName;
            }

            public void setFeedbackName(String feedbackName) {
                this.feedbackName = feedbackName;
            }

            public int getFeedbackType() {
                return feedbackType;
            }

            public void setFeedbackType(int feedbackType) {
                this.feedbackType = feedbackType;
            }

            public String getCreatet() {
                return createt;
            }

            public void setCreatet(String createt) {
                this.createt = createt;
            }

            public Object getUpdatet() {
                return updatet;
            }

            public void setUpdatet(Object updatet) {
                this.updatet = updatet;
            }


            public int getTaskType() {
                return taskType;
            }

            public void setTaskType(int taskType) {
                this.taskType = taskType;
            }

            public int getTaskId() {
                return taskId;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }

            public String getFeedbackBacknum() {
                return feedbackBacknum;
            }

            public void setFeedbackBacknum(String feedbackBacknum) {
                this.feedbackBacknum = feedbackBacknum;
            }

            public String getFeedbackVal() {
                return feedbackVal;
            }

            public void setFeedbackVal(String feedbackVal) {
                this.feedbackVal = feedbackVal;
            }
        }
    }

}
