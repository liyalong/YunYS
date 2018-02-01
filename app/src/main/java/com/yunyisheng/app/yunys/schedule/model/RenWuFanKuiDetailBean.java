package com.yunyisheng.app.yunys.schedule.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/1 16:55
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class RenWuFanKuiDetailBean extends BaseModel {


    /**
     * respBody : {"task":{"taskId":121,"releaseId":61,"releaseName":"任务负责人创建的任务","releaseRemark":"这个任务的内容很多","releaseBegint":null,"releaseEndt":null,"releaseUserId":312,"releaseUsername":"毛硕","releaseFormId":null,"releaseFormInstanceid":null,"taskStat":3,"taskUserId":139,"taskUserName":"小a3","taskSubmitTime":null,"releaseAllot":2,"feedbackBacknum":"eeea587287a9478d8ea8a5dcf4318fb9","releaseTaskType":1,"equipmentId":null,"projectId":null,"companyId":null,"terraceId":null},"feedbackItem":[{"feedbackItemId":172,"feedbackName":"性别","feedbackType":2,"createt":1515729299000,"updatet":null,"model":"[{\"dynamic_type_name\":\"男\",\"index\":0},{\"dynamic_type_name\":\"女\",\"index\":1}]","taskType":1,"taskId":121,"feedbackBacknum":"eeea587287a9478d8ea8a5dcf4318fb9","feedbackVal":null},{"feedbackItemId":173,"feedbackName":"职位","feedbackType":2,"createt":1515729302000,"updatet":null,"model":"[{\"dynamic_type_name\":\"开发工程师\",\"index\":0},{\"dynamic_type_name\":\"架构师\",\"index\":1}]","taskType":1,"taskId":121,"feedbackBacknum":"eeea587287a9478d8ea8a5dcf4318fb9","feedbackVal":null}],"taskback":[{"taskbackId":4,"releaseId":121,"releaseName":"任务负责人创建的任务","taskbackUserId":139,"taskbackUsername":"小a3","taskbackVal":"不想做这个任务，有点困难","taskbackCreatet":1515736914000}]}
     */

    private RespBodyBean respBody;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * task : {"taskId":121,"releaseId":61,"releaseName":"任务负责人创建的任务","releaseRemark":"这个任务的内容很多","releaseBegint":null,"releaseEndt":null,"releaseUserId":312,"releaseUsername":"毛硕","releaseFormId":null,"releaseFormInstanceid":null,"taskStat":3,"taskUserId":139,"taskUserName":"小a3","taskSubmitTime":null,"releaseAllot":2,"feedbackBacknum":"eeea587287a9478d8ea8a5dcf4318fb9","releaseTaskType":1,"equipmentId":null,"projectId":null,"companyId":null,"terraceId":null}
         * feedbackItem : [{"feedbackItemId":172,"feedbackName":"性别","feedbackType":2,"createt":1515729299000,"updatet":null,"model":"[{\"dynamic_type_name\":\"男\",\"index\":0},{\"dynamic_type_name\":\"女\",\"index\":1}]","taskType":1,"taskId":121,"feedbackBacknum":"eeea587287a9478d8ea8a5dcf4318fb9","feedbackVal":null},{"feedbackItemId":173,"feedbackName":"职位","feedbackType":2,"createt":1515729302000,"updatet":null,"model":"[{\"dynamic_type_name\":\"开发工程师\",\"index\":0},{\"dynamic_type_name\":\"架构师\",\"index\":1}]","taskType":1,"taskId":121,"feedbackBacknum":"eeea587287a9478d8ea8a5dcf4318fb9","feedbackVal":null}]
         * taskback : [{"taskbackId":4,"releaseId":121,"releaseName":"任务负责人创建的任务","taskbackUserId":139,"taskbackUsername":"小a3","taskbackVal":"不想做这个任务，有点困难","taskbackCreatet":1515736914000}]
         */

        private TaskBean task;
        private List<FeedbackItemBean> feedbackItem;

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public List<FeedbackItemBean> getFeedbackItem() {
            return feedbackItem;
        }

        public void setFeedbackItem(List<FeedbackItemBean> feedbackItem) {
            this.feedbackItem = feedbackItem;
        }

        public static class TaskBean {
            /**
             * taskId : 121
             * releaseId : 61
             * releaseName : 任务负责人创建的任务
             * releaseRemark : 这个任务的内容很多
             * releaseBegint : null
             * releaseEndt : null
             * releaseUserId : 312
             * releaseUsername : 毛硕
             * releaseFormId : null
             * releaseFormInstanceid : null
             * taskStat : 3
             * taskUserId : 139
             * taskUserName : 小a3
             * taskSubmitTime : null
             * releaseAllot : 2
             * feedbackBacknum : eeea587287a9478d8ea8a5dcf4318fb9
             * releaseTaskType : 1
             * equipmentId : null
             * projectId : null
             * companyId : null
             * terraceId : null
             */

            private int taskId;
            private int releaseId;
            private String releaseName;
            private String releaseRemark;
            private Object releaseBegint;
            private Object releaseEndt;
            private int releaseUserId;
            private String releaseUsername;
            private Object releaseFormId;
            private Object releaseFormInstanceid;
            private int taskStat;
            private int taskUserId;
            private String taskUserName;
            private Object taskSubmitTime;
            private int releaseAllot;
            private String feedbackBacknum;
            private int releaseTaskType;
            private Object equipmentId;
            private Object projectId;
            private Object companyId;
            private Object terraceId;

            public int getTaskId() {
                return taskId;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }

            public int getReleaseId() {
                return releaseId;
            }

            public void setReleaseId(int releaseId) {
                this.releaseId = releaseId;
            }

            public String getReleaseName() {
                return releaseName;
            }

            public void setReleaseName(String releaseName) {
                this.releaseName = releaseName;
            }

            public String getReleaseRemark() {
                return releaseRemark;
            }

            public void setReleaseRemark(String releaseRemark) {
                this.releaseRemark = releaseRemark;
            }

            public Object getReleaseBegint() {
                return releaseBegint;
            }

            public void setReleaseBegint(Object releaseBegint) {
                this.releaseBegint = releaseBegint;
            }

            public Object getReleaseEndt() {
                return releaseEndt;
            }

            public void setReleaseEndt(Object releaseEndt) {
                this.releaseEndt = releaseEndt;
            }

            public int getReleaseUserId() {
                return releaseUserId;
            }

            public void setReleaseUserId(int releaseUserId) {
                this.releaseUserId = releaseUserId;
            }

            public String getReleaseUsername() {
                return releaseUsername;
            }

            public void setReleaseUsername(String releaseUsername) {
                this.releaseUsername = releaseUsername;
            }

            public Object getReleaseFormId() {
                return releaseFormId;
            }

            public void setReleaseFormId(Object releaseFormId) {
                this.releaseFormId = releaseFormId;
            }

            public Object getReleaseFormInstanceid() {
                return releaseFormInstanceid;
            }

            public void setReleaseFormInstanceid(Object releaseFormInstanceid) {
                this.releaseFormInstanceid = releaseFormInstanceid;
            }

            public int getTaskStat() {
                return taskStat;
            }

            public void setTaskStat(int taskStat) {
                this.taskStat = taskStat;
            }

            public int getTaskUserId() {
                return taskUserId;
            }

            public void setTaskUserId(int taskUserId) {
                this.taskUserId = taskUserId;
            }

            public String getTaskUserName() {
                return taskUserName;
            }

            public void setTaskUserName(String taskUserName) {
                this.taskUserName = taskUserName;
            }

            public Object getTaskSubmitTime() {
                return taskSubmitTime;
            }

            public void setTaskSubmitTime(Object taskSubmitTime) {
                this.taskSubmitTime = taskSubmitTime;
            }

            public int getReleaseAllot() {
                return releaseAllot;
            }

            public void setReleaseAllot(int releaseAllot) {
                this.releaseAllot = releaseAllot;
            }

            public String getFeedbackBacknum() {
                return feedbackBacknum;
            }

            public void setFeedbackBacknum(String feedbackBacknum) {
                this.feedbackBacknum = feedbackBacknum;
            }

            public int getReleaseTaskType() {
                return releaseTaskType;
            }

            public void setReleaseTaskType(int releaseTaskType) {
                this.releaseTaskType = releaseTaskType;
            }

            public Object getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(Object equipmentId) {
                this.equipmentId = equipmentId;
            }

            public Object getProjectId() {
                return projectId;
            }

            public void setProjectId(Object projectId) {
                this.projectId = projectId;
            }

            public Object getCompanyId() {
                return companyId;
            }

            public void setCompanyId(Object companyId) {
                this.companyId = companyId;
            }

            public Object getTerraceId() {
                return terraceId;
            }

            public void setTerraceId(Object terraceId) {
                this.terraceId = terraceId;
            }
        }

        public static class FeedbackItemBean {
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
            private long createt;
            private Object updatet;
            private List<Valueitem> modellist;
            private int taskType;
            private int taskId;
            private String feedbackBacknum;
            private Object feedbackVal;

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

            public long getCreatet() {
                return createt;
            }

            public void setCreatet(long createt) {
                this.createt = createt;
            }

            public Object getUpdatet() {
                return updatet;
            }

            public void setUpdatet(Object updatet) {
                this.updatet = updatet;
            }

            public List<Valueitem> getModel() {
                return modellist;
            }

            public void setModel(List<Valueitem> model) {
                this.modellist = model;
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

            public Object getFeedbackVal() {
                return feedbackVal;
            }

            public void setFeedbackVal(Object feedbackVal) {
                this.feedbackVal = feedbackVal;
            }
        }

        public static class Valueitem{

            /**
             * dynamic_type_name : 医生
             * index : 0
             */

            private String dynamic_type_name;
            private int index;

            public String getDynamic_type_name() {
                return dynamic_type_name;
            }

            public void setDynamic_type_name(String dynamic_type_name) {
                this.dynamic_type_name = dynamic_type_name;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }
        }


    }
}
