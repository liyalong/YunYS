package com.yunyisheng.app.yunys.schedule.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/28 18:40
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SeeScheduleDetailBean extends BaseModel {


    /**
     * respBody : {"task":{"taskId":1524,"releaseId":1320,"releaseName":"测试数据","releaseRemark":"不描述","releaseBegint":"2018-02-27 00:00:00","releaseEndt":"2018-03-01 00:00:00","releaseUserId":468,"releaseUsername":"飞虎","releaseFormId":"52a430a2db2140f495a2c5aa0b4d6dd2","releaseFormInstanceId":254,"taskStat":2,"taskUserId":468,"taskUserName":"飞虎","taskSubmitTime":"2018-02-28 10:09:43","releaseAllot":1,"feedbackBacknum":null,"releaseTaskType":2,"equipmentId":null,"projectId":"967a6821620d431f82af08d579f7a567","companyId":"5cefca7b4ac54aaf9bbef697dbdfd0d6","terraceId":null,"taskIsDelete":0,"taskLook":null,"projectName":"邯郸市有机垃圾处理","equipmentName":null,"taskCreatet":"2018-02-28 10:09:05","over":1},"forminstance":{"form":{"version":4,"baseId":151,"typeId":67,"applicationId":"706311a4101d4d409c264b451c8d4672","companyId":"5cefca7b4ac54aaf9bbef697dbdfd0d6","name":"工单日志","image":"page/library/2.png","createUser":467,"createTime":"2018-02-09 11:01:00","uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","fields":4,"description":"工单日志","explanation":"工单日志","isPrint":"1","template":"<table><tbody><tr class=\"firstRow\"><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">填写人：<input name=\"data_1\" title=\"填写人\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/><\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">时间：<input name=\"data_2\" title=\"时间\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/><\/td><\/tr><tr><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出沙量：<input name=\"data_3\" title=\"出沙量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" orgunit=\"t\" type=\"text\"/><\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出水量：<input name=\"data_4\" title=\"出水量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" type=\"text\"/><\/td><\/tr><\/tbody><\/table><p><br/><\/p>","parse":"<table><tbody><tr class=\"firstRow\"><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">填写人：{data_1}<\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">时间：{data_2}<\/td><\/tr><tr><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出沙量：{data_3}<\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出水量：{data_4}<\/td><\/tr><\/tbody><\/table><p><br/><\/p>","data":[{"id":747,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_1","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"填写人","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_1\" title=\"填写人\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":748,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_2","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"时间","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_2\" title=\"时间\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":749,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_3","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"出沙量","orgtitle":null,"orgcoltype":null,"orgunit":"t","orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_3\" title=\"出沙量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" orgunit=\"t\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"float"},{"id":750,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_4","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"出水量","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_4\" title=\"出水量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"float"}]},"dataList":[{"id":796,"instanceId":254,"fieldId":747,"value":"飞虎","fieldName":null,"createTime":null},{"id":797,"instanceId":254,"fieldId":748,"value":"12345","fieldName":null,"createTime":null},{"id":798,"instanceId":254,"fieldId":749,"value":"1234","fieldName":null,"createTime":null},{"id":799,"instanceId":254,"fieldId":750,"value":"2345","fieldName":null,"createTime":null}]},"taskback":[]}
     * firstPage : 0
     * lastPage : 0
     */

    private RespBodyBean respBody;
    private int firstPage;
    private int lastPage;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public static class RespBodyBean {
        /**
         * task : {"taskId":1524,"releaseId":1320,"releaseName":"测试数据","releaseRemark":"不描述","releaseBegint":"2018-02-27 00:00:00","releaseEndt":"2018-03-01 00:00:00","releaseUserId":468,"releaseUsername":"飞虎","releaseFormId":"52a430a2db2140f495a2c5aa0b4d6dd2","releaseFormInstanceId":254,"taskStat":2,"taskUserId":468,"taskUserName":"飞虎","taskSubmitTime":"2018-02-28 10:09:43","releaseAllot":1,"feedbackBacknum":null,"releaseTaskType":2,"equipmentId":null,"projectId":"967a6821620d431f82af08d579f7a567","companyId":"5cefca7b4ac54aaf9bbef697dbdfd0d6","terraceId":null,"taskIsDelete":0,"taskLook":null,"projectName":"邯郸市有机垃圾处理","equipmentName":null,"taskCreatet":"2018-02-28 10:09:05","over":1}
         * forminstance : {"form":{"version":4,"baseId":151,"typeId":67,"applicationId":"706311a4101d4d409c264b451c8d4672","companyId":"5cefca7b4ac54aaf9bbef697dbdfd0d6","name":"工单日志","image":"page/library/2.png","createUser":467,"createTime":"2018-02-09 11:01:00","uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","fields":4,"description":"工单日志","explanation":"工单日志","isPrint":"1","template":"<table><tbody><tr class=\"firstRow\"><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">填写人：<input name=\"data_1\" title=\"填写人\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/><\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">时间：<input name=\"data_2\" title=\"时间\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/><\/td><\/tr><tr><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出沙量：<input name=\"data_3\" title=\"出沙量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" orgunit=\"t\" type=\"text\"/><\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出水量：<input name=\"data_4\" title=\"出水量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" type=\"text\"/><\/td><\/tr><\/tbody><\/table><p><br/><\/p>","parse":"<table><tbody><tr class=\"firstRow\"><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">填写人：{data_1}<\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">时间：{data_2}<\/td><\/tr><tr><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出沙量：{data_3}<\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出水量：{data_4}<\/td><\/tr><\/tbody><\/table><p><br/><\/p>","data":[{"id":747,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_1","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"填写人","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_1\" title=\"填写人\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":748,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_2","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"时间","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_2\" title=\"时间\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":749,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_3","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"出沙量","orgtitle":null,"orgcoltype":null,"orgunit":"t","orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_3\" title=\"出沙量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" orgunit=\"t\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"float"},{"id":750,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_4","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"出水量","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_4\" title=\"出水量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"float"}]},"dataList":[{"id":796,"instanceId":254,"fieldId":747,"value":"飞虎","fieldName":null,"createTime":null},{"id":797,"instanceId":254,"fieldId":748,"value":"12345","fieldName":null,"createTime":null},{"id":798,"instanceId":254,"fieldId":749,"value":"1234","fieldName":null,"createTime":null},{"id":799,"instanceId":254,"fieldId":750,"value":"2345","fieldName":null,"createTime":null}]}
         * taskback : []
         */

        private TaskBean task;
        private ForminstanceBean forminstance;
        private List<?> taskback;

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public ForminstanceBean getForminstance() {
            return forminstance;
        }

        public void setForminstance(ForminstanceBean forminstance) {
            this.forminstance = forminstance;
        }

        public List<?> getTaskback() {
            return taskback;
        }

        public void setTaskback(List<?> taskback) {
            this.taskback = taskback;
        }

        public static class TaskBean {
            /**
             * taskId : 1524
             * releaseId : 1320
             * releaseName : 测试数据
             * releaseRemark : 不描述
             * releaseBegint : 2018-02-27 00:00:00
             * releaseEndt : 2018-03-01 00:00:00
             * releaseUserId : 468
             * releaseUsername : 飞虎
             * releaseFormId : 52a430a2db2140f495a2c5aa0b4d6dd2
             * releaseFormInstanceId : 254
             * taskStat : 2
             * taskUserId : 468
             * taskUserName : 飞虎
             * taskSubmitTime : 2018-02-28 10:09:43
             * releaseAllot : 1
             * feedbackBacknum : null
             * releaseTaskType : 2
             * equipmentId : null
             * projectId : 967a6821620d431f82af08d579f7a567
             * companyId : 5cefca7b4ac54aaf9bbef697dbdfd0d6
             * terraceId : null
             * taskIsDelete : 0
             * taskLook : null
             * projectName : 邯郸市有机垃圾处理
             * equipmentName : null
             * taskCreatet : 2018-02-28 10:09:05
             * over : 1
             */

            private int taskId;
            private int releaseId;
            private String releaseName;
            private String releaseRemark;
            private String releaseBegint;
            private String releaseEndt;
            private int releaseUserId;
            private String releaseUsername;
            private String releaseFormId;
            private int releaseFormInstanceId;
            private int taskStat;
            private int taskUserId;
            private String taskUserName;
            private String taskSubmitTime;
            private int releaseAllot;
            private Object feedbackBacknum;
            private int releaseTaskType;
            private Object equipmentId;
            private String projectId;
            private String companyId;
            private Object terraceId;
            private int taskIsDelete;
            private Object taskLook;
            private String projectName;
            private Object equipmentName;
            private String taskCreatet;
            private int over;

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

            public String getReleaseBegint() {
                return releaseBegint;
            }

            public void setReleaseBegint(String releaseBegint) {
                this.releaseBegint = releaseBegint;
            }

            public String getReleaseEndt() {
                return releaseEndt;
            }

            public void setReleaseEndt(String releaseEndt) {
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

            public String getReleaseFormId() {
                return releaseFormId;
            }

            public void setReleaseFormId(String releaseFormId) {
                this.releaseFormId = releaseFormId;
            }

            public int getReleaseFormInstanceId() {
                return releaseFormInstanceId;
            }

            public void setReleaseFormInstanceId(int releaseFormInstanceId) {
                this.releaseFormInstanceId = releaseFormInstanceId;
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

            public String getTaskSubmitTime() {
                return taskSubmitTime;
            }

            public void setTaskSubmitTime(String taskSubmitTime) {
                this.taskSubmitTime = taskSubmitTime;
            }

            public int getReleaseAllot() {
                return releaseAllot;
            }

            public void setReleaseAllot(int releaseAllot) {
                this.releaseAllot = releaseAllot;
            }

            public Object getFeedbackBacknum() {
                return feedbackBacknum;
            }

            public void setFeedbackBacknum(Object feedbackBacknum) {
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

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public Object getTerraceId() {
                return terraceId;
            }

            public void setTerraceId(Object terraceId) {
                this.terraceId = terraceId;
            }

            public int getTaskIsDelete() {
                return taskIsDelete;
            }

            public void setTaskIsDelete(int taskIsDelete) {
                this.taskIsDelete = taskIsDelete;
            }

            public Object getTaskLook() {
                return taskLook;
            }

            public void setTaskLook(Object taskLook) {
                this.taskLook = taskLook;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public Object getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(Object equipmentName) {
                this.equipmentName = equipmentName;
            }

            public String getTaskCreatet() {
                return taskCreatet;
            }

            public void setTaskCreatet(String taskCreatet) {
                this.taskCreatet = taskCreatet;
            }

            public int getOver() {
                return over;
            }

            public void setOver(int over) {
                this.over = over;
            }
        }

        public static class ForminstanceBean {
            /**
             * form : {"version":4,"baseId":151,"typeId":67,"applicationId":"706311a4101d4d409c264b451c8d4672","companyId":"5cefca7b4ac54aaf9bbef697dbdfd0d6","name":"工单日志","image":"page/library/2.png","createUser":467,"createTime":"2018-02-09 11:01:00","uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","fields":4,"description":"工单日志","explanation":"工单日志","isPrint":"1","template":"<table><tbody><tr class=\"firstRow\"><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">填写人：<input name=\"data_1\" title=\"填写人\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/><\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">时间：<input name=\"data_2\" title=\"时间\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/><\/td><\/tr><tr><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出沙量：<input name=\"data_3\" title=\"出沙量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" orgunit=\"t\" type=\"text\"/><\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出水量：<input name=\"data_4\" title=\"出水量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" type=\"text\"/><\/td><\/tr><\/tbody><\/table><p><br/><\/p>","parse":"<table><tbody><tr class=\"firstRow\"><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">填写人：{data_1}<\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">时间：{data_2}<\/td><\/tr><tr><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出沙量：{data_3}<\/td><td style=\"word-break: break-all;\" width=\"362\" valign=\"top\">出水量：{data_4}<\/td><\/tr><\/tbody><\/table><p><br/><\/p>","data":[{"id":747,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_1","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"填写人","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_1\" title=\"填写人\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":748,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_2","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"时间","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_2\" title=\"时间\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":749,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_3","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"出沙量","orgtitle":null,"orgcoltype":null,"orgunit":"t","orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_3\" title=\"出沙量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" orgunit=\"t\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"float"},{"id":750,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_4","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"出水量","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_4\" title=\"出水量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"float"}]}
             * dataList : [{"id":796,"instanceId":254,"fieldId":747,"value":"飞虎","fieldName":null,"createTime":null},{"id":797,"instanceId":254,"fieldId":748,"value":"12345","fieldName":null,"createTime":null},{"id":798,"instanceId":254,"fieldId":749,"value":"1234","fieldName":null,"createTime":null},{"id":799,"instanceId":254,"fieldId":750,"value":"2345","fieldName":null,"createTime":null}]
             */

            private FormBean form;
            private List<DataListBean> dataList;

            public FormBean getForm() {
                return form;
            }

            public void setForm(FormBean form) {
                this.form = form;
            }

            public List<DataListBean> getDataList() {
                return dataList;
            }

            public void setDataList(List<DataListBean> dataList) {
                this.dataList = dataList;
            }

            public static class FormBean {
                /**
                 * version : 4
                 * baseId : 151
                 * typeId : 67
                 * applicationId : 706311a4101d4d409c264b451c8d4672
                 * companyId : 5cefca7b4ac54aaf9bbef697dbdfd0d6
                 * name : 工单日志
                 * image : page/library/2.png
                 * createUser : 467
                 * createTime : 2018-02-09 11:01:00
                 * uuid : 52a430a2db2140f495a2c5aa0b4d6dd2
                 * fields : 4
                 * description : 工单日志
                 * explanation : 工单日志
                 * isPrint : 1
                 * template : <table><tbody><tr class="firstRow"><td style="word-break: break-all;" width="362" valign="top">填写人：<input name="data_1" title="填写人" value="" leipiplugins="text" orghide="0" style="text-align: left; width: 150px;" orgalign="left" orgwidth="150" orgtype="text" type="text"/></td><td style="word-break: break-all;" width="362" valign="top">时间：<input name="data_2" title="时间" value="" leipiplugins="text" orghide="0" style="text-align: left; width: 150px;" orgalign="left" orgwidth="150" orgtype="text" type="text"/></td></tr><tr><td style="word-break: break-all;" width="362" valign="top">出沙量：<input name="data_3" title="出沙量" value="" leipiplugins="text" orghide="0" style="text-align: left; width: 150px;" orgalign="left" orgwidth="150" orgtype="float" orgunit="t" type="text"/></td><td style="word-break: break-all;" width="362" valign="top">出水量：<input name="data_4" title="出水量" value="" leipiplugins="text" orghide="0" style="text-align: left; width: 150px;" orgalign="left" orgwidth="150" orgtype="float" type="text"/></td></tr></tbody></table><p><br/></p>
                 * parse : <table><tbody><tr class="firstRow"><td style="word-break: break-all;" width="362" valign="top">填写人：{data_1}</td><td style="word-break: break-all;" width="362" valign="top">时间：{data_2}</td></tr><tr><td style="word-break: break-all;" width="362" valign="top">出沙量：{data_3}</td><td style="word-break: break-all;" width="362" valign="top">出水量：{data_4}</td></tr></tbody></table><p><br/></p>
                 * data : [{"id":747,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_1","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"填写人","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_1\" title=\"填写人\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":748,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_2","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"时间","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_2\" title=\"时间\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"text\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":749,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_3","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"出沙量","orgtitle":null,"orgcoltype":null,"orgunit":"t","orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_3\" title=\"出沙量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" orgunit=\"t\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"float"},{"id":750,"uuid":"52a430a2db2140f495a2c5aa0b4d6dd2","name":"data_4","leipiplugins":"text","type":"text","value":"","readonly":null,"title":"出水量","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input name=\"data_4\" title=\"出水量\" value=\"\" leipiplugins=\"text\" orghide=\"0\" style=\"text-align: left; width: 150px;\" orgalign=\"left\" orgwidth=\"150\" orgtype=\"float\" type=\"text\"/>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"float"}]
                 */

                private int version;
                private int baseId;
                private int typeId;
                private String applicationId;
                private String companyId;
                private String name;
                private String image;
                private int createUser;
                private String createTime;
                private String uuid;
                private int fields;
                private String description;
                private String explanation;
                private String isPrint;
                private String template;
                private String parse;
                private List<DataBean> data;

                public int getVersion() {
                    return version;
                }

                public void setVersion(int version) {
                    this.version = version;
                }

                public int getBaseId() {
                    return baseId;
                }

                public void setBaseId(int baseId) {
                    this.baseId = baseId;
                }

                public int getTypeId() {
                    return typeId;
                }

                public void setTypeId(int typeId) {
                    this.typeId = typeId;
                }

                public String getApplicationId() {
                    return applicationId;
                }

                public void setApplicationId(String applicationId) {
                    this.applicationId = applicationId;
                }

                public String getCompanyId() {
                    return companyId;
                }

                public void setCompanyId(String companyId) {
                    this.companyId = companyId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public int getCreateUser() {
                    return createUser;
                }

                public void setCreateUser(int createUser) {
                    this.createUser = createUser;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getUuid() {
                    return uuid;
                }

                public void setUuid(String uuid) {
                    this.uuid = uuid;
                }

                public int getFields() {
                    return fields;
                }

                public void setFields(int fields) {
                    this.fields = fields;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getExplanation() {
                    return explanation;
                }

                public void setExplanation(String explanation) {
                    this.explanation = explanation;
                }

                public String getIsPrint() {
                    return isPrint;
                }

                public void setIsPrint(String isPrint) {
                    this.isPrint = isPrint;
                }

                public String getTemplate() {
                    return template;
                }

                public void setTemplate(String template) {
                    this.template = template;
                }

                public String getParse() {
                    return parse;
                }

                public void setParse(String parse) {
                    this.parse = parse;
                }

                public List<DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBean> data) {
                    this.data = data;
                }

                public static class DataBean {
                    /**
                     * id : 747
                     * uuid : 52a430a2db2140f495a2c5aa0b4d6dd2
                     * name : data_1
                     * leipiplugins : text
                     * type : text
                     * value :
                     * readonly : null
                     * title : 填写人
                     * orgtitle : null
                     * orgcoltype : null
                     * orgunit : null
                     * orgsum : null
                     * orgcolvalue : null
                     * orgwidth : 150
                     * style : text-align: left; width: 150px;
                     * content : <input name="data_1" title="填写人" value="" leipiplugins="text" orghide="0" style="text-align: left; width: 150px;" orgalign="left" orgwidth="150" orgtype="text" type="text"/>
                     * orgfontsize : null
                     * orgrich : null
                     * orgheight : null
                     * src : null
                     * orgsigntype : null
                     * parseName : null
                     * options : []
                     * orgalign : left
                     * optionStr : []
                     * orgtype : text
                     */

                    private int id;
                    private String uuid;
                    private String name;
                    private String leipiplugins;
                    private String type;
                    private String value;
                    private Object readonly;
                    private String title;
                    private Object orgtitle;
                    private Object orgcoltype;
                    private Object orgunit;
                    private Object orgsum;
                    private Object orgcolvalue;
                    private String orgwidth;
                    private String style;
                    private String content;
                    private Object orgfontsize;
                    private Object orgrich;
                    private Object orgheight;
                    private Object src;
                    private Object orgsigntype;
                    private Object parseName;
                    private String orgalign;
                    private String optionStr;
                    private String orgtype;
                    private List<?> options;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getUuid() {
                        return uuid;
                    }

                    public void setUuid(String uuid) {
                        this.uuid = uuid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getLeipiplugins() {
                        return leipiplugins;
                    }

                    public void setLeipiplugins(String leipiplugins) {
                        this.leipiplugins = leipiplugins;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public Object getReadonly() {
                        return readonly;
                    }

                    public void setReadonly(Object readonly) {
                        this.readonly = readonly;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public Object getOrgtitle() {
                        return orgtitle;
                    }

                    public void setOrgtitle(Object orgtitle) {
                        this.orgtitle = orgtitle;
                    }

                    public Object getOrgcoltype() {
                        return orgcoltype;
                    }

                    public void setOrgcoltype(Object orgcoltype) {
                        this.orgcoltype = orgcoltype;
                    }

                    public Object getOrgunit() {
                        return orgunit;
                    }

                    public void setOrgunit(Object orgunit) {
                        this.orgunit = orgunit;
                    }

                    public Object getOrgsum() {
                        return orgsum;
                    }

                    public void setOrgsum(Object orgsum) {
                        this.orgsum = orgsum;
                    }

                    public Object getOrgcolvalue() {
                        return orgcolvalue;
                    }

                    public void setOrgcolvalue(Object orgcolvalue) {
                        this.orgcolvalue = orgcolvalue;
                    }

                    public String getOrgwidth() {
                        return orgwidth;
                    }

                    public void setOrgwidth(String orgwidth) {
                        this.orgwidth = orgwidth;
                    }

                    public String getStyle() {
                        return style;
                    }

                    public void setStyle(String style) {
                        this.style = style;
                    }

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public Object getOrgfontsize() {
                        return orgfontsize;
                    }

                    public void setOrgfontsize(Object orgfontsize) {
                        this.orgfontsize = orgfontsize;
                    }

                    public Object getOrgrich() {
                        return orgrich;
                    }

                    public void setOrgrich(Object orgrich) {
                        this.orgrich = orgrich;
                    }

                    public Object getOrgheight() {
                        return orgheight;
                    }

                    public void setOrgheight(Object orgheight) {
                        this.orgheight = orgheight;
                    }

                    public Object getSrc() {
                        return src;
                    }

                    public void setSrc(Object src) {
                        this.src = src;
                    }

                    public Object getOrgsigntype() {
                        return orgsigntype;
                    }

                    public void setOrgsigntype(Object orgsigntype) {
                        this.orgsigntype = orgsigntype;
                    }

                    public Object getParseName() {
                        return parseName;
                    }

                    public void setParseName(Object parseName) {
                        this.parseName = parseName;
                    }

                    public String getOrgalign() {
                        return orgalign;
                    }

                    public void setOrgalign(String orgalign) {
                        this.orgalign = orgalign;
                    }

                    public String getOptionStr() {
                        return optionStr;
                    }

                    public void setOptionStr(String optionStr) {
                        this.optionStr = optionStr;
                    }

                    public String getOrgtype() {
                        return orgtype;
                    }

                    public void setOrgtype(String orgtype) {
                        this.orgtype = orgtype;
                    }

                    public List<?> getOptions() {
                        return options;
                    }

                    public void setOptions(List<?> options) {
                        this.options = options;
                    }
                }
            }

            public static class DataListBean {
                /**
                 * id : 796
                 * instanceId : 254
                 * fieldId : 747
                 * value : 飞虎
                 * fieldName : null
                 * createTime : null
                 */

                private int id;
                private int instanceId;
                private int fieldId;
                private String value;
                private Object fieldName;
                private Object createTime;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getInstanceId() {
                    return instanceId;
                }

                public void setInstanceId(int instanceId) {
                    this.instanceId = instanceId;
                }

                public int getFieldId() {
                    return fieldId;
                }

                public void setFieldId(int fieldId) {
                    this.fieldId = fieldId;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public Object getFieldName() {
                    return fieldName;
                }

                public void setFieldName(Object fieldName) {
                    this.fieldName = fieldName;
                }

                public Object getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(Object createTime) {
                    this.createTime = createTime;
                }
            }
        }
    }
}
