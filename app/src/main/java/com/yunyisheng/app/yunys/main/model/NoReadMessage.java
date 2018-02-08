package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/8 20:23
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NoReadMessage extends BaseModel {


    /**
     * respBody : {"warn":[{"messageId":28056,"messageType":"2","messageInfoId":"1619","messageStat":"1","messageRemark":"在项目 [一轻大厦环境监测] 设备 [类加载器]中触发报警，具体报警位置: 报警规则 [类名找不到] PLC [Y003_TIC2102B2]","messageCreateTime":"2018-02-08 06:09:10","messageUpdateTime":null,"messageReceiveUserId":469,"messageReleaseUserId":1,"applicationId":null,"companyId":"9d853bbaabe94a29a2d06aa49d68194f","messageReleaseUserName":null}],"task":[{"messageId":28105,"messageType":"1","messageInfoId":"786","messageStat":"1","messageRemark":"✧<font color=\"orange\">hhhh<\/font>✧<br/>任务已经下发给你<font color=\"orange\">(๑\u2022̀ㅂ\u2022́)و✧<\/font>","messageCreateTime":"2018-02-08 06:59:49","messageUpdateTime":"2018-02-08 09:03:51","messageReceiveUserId":469,"messageReleaseUserId":428,"applicationId":null,"companyId":"9d853bbaabe94a29a2d06aa49d68194f","messageReleaseUserName":null}],"anno":[{"messageId":28069,"messageType":"3","messageInfoId":"71","messageStat":"1","messageRemark":"您有新的公告：今天可以早点下班","messageCreateTime":"2018-02-08 06:14:18","messageUpdateTime":null,"messageReceiveUserId":469,"messageReleaseUserId":428,"applicationId":null,"companyId":"9d853bbaabe94a29a2d06aa49d68194f","messageReleaseUserName":null}],"warnnum":1,"project":[{"messageId":28069,"messageType":"3","messageInfoId":"71","messageStat":"1","messageRemark":"项目成立没人五十红包","messageCreateTime":"2018-02-08 06:14:18","messageUpdateTime":null,"messageReceiveUserId":469,"messageReleaseUserId":428,"applicationId":null,"companyId":"9d853bbaabe94a29a2d06aa49d68194f","messageReleaseUserName":null}],"projectnum":0,"annonum":1,"tasknum":1,"Mids":[28056,28069,28105]}
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
         * warn : [{"messageId":28056,"messageType":"2","messageInfoId":"1619","messageStat":"1","messageRemark":"在项目 [一轻大厦环境监测] 设备 [类加载器]中触发报警，具体报警位置: 报警规则 [类名找不到] PLC [Y003_TIC2102B2]","messageCreateTime":"2018-02-08 06:09:10","messageUpdateTime":null,"messageReceiveUserId":469,"messageReleaseUserId":1,"applicationId":null,"companyId":"9d853bbaabe94a29a2d06aa49d68194f","messageReleaseUserName":null}]
         * task : [{"messageId":28105,"messageType":"1","messageInfoId":"786","messageStat":"1","messageRemark":"✧<font color=\"orange\">hhhh<\/font>✧<br/>任务已经下发给你<font color=\"orange\">(๑\u2022̀ㅂ\u2022́)و✧<\/font>","messageCreateTime":"2018-02-08 06:59:49","messageUpdateTime":"2018-02-08 09:03:51","messageReceiveUserId":469,"messageReleaseUserId":428,"applicationId":null,"companyId":"9d853bbaabe94a29a2d06aa49d68194f","messageReleaseUserName":null}]
         * anno : [{"messageId":28069,"messageType":"3","messageInfoId":"71","messageStat":"1","messageRemark":"您有新的公告：今天可以早点下班","messageCreateTime":"2018-02-08 06:14:18","messageUpdateTime":null,"messageReceiveUserId":469,"messageReleaseUserId":428,"applicationId":null,"companyId":"9d853bbaabe94a29a2d06aa49d68194f","messageReleaseUserName":null}]
         * warnnum : 1
         * project : [{"messageId":28069,"messageType":"3","messageInfoId":"71","messageStat":"1","messageRemark":"项目成立没人五十红包","messageCreateTime":"2018-02-08 06:14:18","messageUpdateTime":null,"messageReceiveUserId":469,"messageReleaseUserId":428,"applicationId":null,"companyId":"9d853bbaabe94a29a2d06aa49d68194f","messageReleaseUserName":null}]
         * projectnum : 0
         * annonum : 1
         * tasknum : 1
         * Mids : [28056,28069,28105]
         */

        private int warnnum;
        private int projectnum;
        private int annonum;
        private int tasknum;
        private List<WarnBean> warn;
        private List<TaskBean> task;
        private List<AnnoBean> anno;
        private List<ProjectBean> project;
        private List<Integer> Mids;

        public int getWarnnum() {
            return warnnum;
        }

        public void setWarnnum(int warnnum) {
            this.warnnum = warnnum;
        }

        public int getProjectnum() {
            return projectnum;
        }

        public void setProjectnum(int projectnum) {
            this.projectnum = projectnum;
        }

        public int getAnnonum() {
            return annonum;
        }

        public void setAnnonum(int annonum) {
            this.annonum = annonum;
        }

        public int getTasknum() {
            return tasknum;
        }

        public void setTasknum(int tasknum) {
            this.tasknum = tasknum;
        }

        public List<WarnBean> getWarn() {
            return warn;
        }

        public void setWarn(List<WarnBean> warn) {
            this.warn = warn;
        }

        public List<TaskBean> getTask() {
            return task;
        }

        public void setTask(List<TaskBean> task) {
            this.task = task;
        }

        public List<AnnoBean> getAnno() {
            return anno;
        }

        public void setAnno(List<AnnoBean> anno) {
            this.anno = anno;
        }

        public List<ProjectBean> getProject() {
            return project;
        }

        public void setProject(List<ProjectBean> project) {
            this.project = project;
        }

        public List<Integer> getMids() {
            return Mids;
        }

        public void setMids(List<Integer> Mids) {
            this.Mids = Mids;
        }

        public static class WarnBean {
            /**
             * messageId : 28056
             * messageType : 2
             * messageInfoId : 1619
             * messageStat : 1
             * messageRemark : 在项目 [一轻大厦环境监测] 设备 [类加载器]中触发报警，具体报警位置: 报警规则 [类名找不到] PLC [Y003_TIC2102B2]
             * messageCreateTime : 2018-02-08 06:09:10
             * messageUpdateTime : null
             * messageReceiveUserId : 469
             * messageReleaseUserId : 1
             * applicationId : null
             * companyId : 9d853bbaabe94a29a2d06aa49d68194f
             * messageReleaseUserName : null
             */

            private int messageId;
            private String messageType;
            private String messageInfoId;
            private String messageStat;
            private String messageRemark;
            private String messageCreateTime;
            private Object messageUpdateTime;
            private int messageReceiveUserId;
            private int messageReleaseUserId;
            private Object applicationId;
            private String companyId;
            private Object messageReleaseUserName;

            public int getMessageId() {
                return messageId;
            }

            public void setMessageId(int messageId) {
                this.messageId = messageId;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getMessageInfoId() {
                return messageInfoId;
            }

            public void setMessageInfoId(String messageInfoId) {
                this.messageInfoId = messageInfoId;
            }

            public String getMessageStat() {
                return messageStat;
            }

            public void setMessageStat(String messageStat) {
                this.messageStat = messageStat;
            }

            public String getMessageRemark() {
                return messageRemark;
            }

            public void setMessageRemark(String messageRemark) {
                this.messageRemark = messageRemark;
            }

            public String getMessageCreateTime() {
                return messageCreateTime;
            }

            public void setMessageCreateTime(String messageCreateTime) {
                this.messageCreateTime = messageCreateTime;
            }

            public Object getMessageUpdateTime() {
                return messageUpdateTime;
            }

            public void setMessageUpdateTime(Object messageUpdateTime) {
                this.messageUpdateTime = messageUpdateTime;
            }

            public int getMessageReceiveUserId() {
                return messageReceiveUserId;
            }

            public void setMessageReceiveUserId(int messageReceiveUserId) {
                this.messageReceiveUserId = messageReceiveUserId;
            }

            public int getMessageReleaseUserId() {
                return messageReleaseUserId;
            }

            public void setMessageReleaseUserId(int messageReleaseUserId) {
                this.messageReleaseUserId = messageReleaseUserId;
            }

            public Object getApplicationId() {
                return applicationId;
            }

            public void setApplicationId(Object applicationId) {
                this.applicationId = applicationId;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public Object getMessageReleaseUserName() {
                return messageReleaseUserName;
            }

            public void setMessageReleaseUserName(Object messageReleaseUserName) {
                this.messageReleaseUserName = messageReleaseUserName;
            }
        }

        public static class TaskBean {
            /**
             * messageId : 28105
             * messageType : 1
             * messageInfoId : 786
             * messageStat : 1
             * messageRemark : ✧<font color="orange">hhhh</font>✧<br/>任务已经下发给你<font color="orange">(๑•̀ㅂ•́)و✧</font>
             * messageCreateTime : 2018-02-08 06:59:49
             * messageUpdateTime : 2018-02-08 09:03:51
             * messageReceiveUserId : 469
             * messageReleaseUserId : 428
             * applicationId : null
             * companyId : 9d853bbaabe94a29a2d06aa49d68194f
             * messageReleaseUserName : null
             */

            private int messageId;
            private String messageType;
            private String messageInfoId;
            private String messageStat;
            private String messageRemark;
            private String messageCreateTime;
            private String messageUpdateTime;
            private int messageReceiveUserId;
            private int messageReleaseUserId;
            private Object applicationId;
            private String companyId;
            private Object messageReleaseUserName;

            public int getMessageId() {
                return messageId;
            }

            public void setMessageId(int messageId) {
                this.messageId = messageId;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getMessageInfoId() {
                return messageInfoId;
            }

            public void setMessageInfoId(String messageInfoId) {
                this.messageInfoId = messageInfoId;
            }

            public String getMessageStat() {
                return messageStat;
            }

            public void setMessageStat(String messageStat) {
                this.messageStat = messageStat;
            }

            public String getMessageRemark() {
                return messageRemark;
            }

            public void setMessageRemark(String messageRemark) {
                this.messageRemark = messageRemark;
            }

            public String getMessageCreateTime() {
                return messageCreateTime;
            }

            public void setMessageCreateTime(String messageCreateTime) {
                this.messageCreateTime = messageCreateTime;
            }

            public String getMessageUpdateTime() {
                return messageUpdateTime;
            }

            public void setMessageUpdateTime(String messageUpdateTime) {
                this.messageUpdateTime = messageUpdateTime;
            }

            public int getMessageReceiveUserId() {
                return messageReceiveUserId;
            }

            public void setMessageReceiveUserId(int messageReceiveUserId) {
                this.messageReceiveUserId = messageReceiveUserId;
            }

            public int getMessageReleaseUserId() {
                return messageReleaseUserId;
            }

            public void setMessageReleaseUserId(int messageReleaseUserId) {
                this.messageReleaseUserId = messageReleaseUserId;
            }

            public Object getApplicationId() {
                return applicationId;
            }

            public void setApplicationId(Object applicationId) {
                this.applicationId = applicationId;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public Object getMessageReleaseUserName() {
                return messageReleaseUserName;
            }

            public void setMessageReleaseUserName(Object messageReleaseUserName) {
                this.messageReleaseUserName = messageReleaseUserName;
            }
        }

        public static class AnnoBean {
            /**
             * messageId : 28069
             * messageType : 3
             * messageInfoId : 71
             * messageStat : 1
             * messageRemark : 您有新的公告：今天可以早点下班
             * messageCreateTime : 2018-02-08 06:14:18
             * messageUpdateTime : null
             * messageReceiveUserId : 469
             * messageReleaseUserId : 428
             * applicationId : null
             * companyId : 9d853bbaabe94a29a2d06aa49d68194f
             * messageReleaseUserName : null
             */

            private int messageId;
            private String messageType;
            private String messageInfoId;
            private String messageStat;
            private String messageRemark;
            private String messageCreateTime;
            private Object messageUpdateTime;
            private int messageReceiveUserId;
            private int messageReleaseUserId;
            private Object applicationId;
            private String companyId;
            private Object messageReleaseUserName;

            public int getMessageId() {
                return messageId;
            }

            public void setMessageId(int messageId) {
                this.messageId = messageId;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getMessageInfoId() {
                return messageInfoId;
            }

            public void setMessageInfoId(String messageInfoId) {
                this.messageInfoId = messageInfoId;
            }

            public String getMessageStat() {
                return messageStat;
            }

            public void setMessageStat(String messageStat) {
                this.messageStat = messageStat;
            }

            public String getMessageRemark() {
                return messageRemark;
            }

            public void setMessageRemark(String messageRemark) {
                this.messageRemark = messageRemark;
            }

            public String getMessageCreateTime() {
                return messageCreateTime;
            }

            public void setMessageCreateTime(String messageCreateTime) {
                this.messageCreateTime = messageCreateTime;
            }

            public Object getMessageUpdateTime() {
                return messageUpdateTime;
            }

            public void setMessageUpdateTime(Object messageUpdateTime) {
                this.messageUpdateTime = messageUpdateTime;
            }

            public int getMessageReceiveUserId() {
                return messageReceiveUserId;
            }

            public void setMessageReceiveUserId(int messageReceiveUserId) {
                this.messageReceiveUserId = messageReceiveUserId;
            }

            public int getMessageReleaseUserId() {
                return messageReleaseUserId;
            }

            public void setMessageReleaseUserId(int messageReleaseUserId) {
                this.messageReleaseUserId = messageReleaseUserId;
            }

            public Object getApplicationId() {
                return applicationId;
            }

            public void setApplicationId(Object applicationId) {
                this.applicationId = applicationId;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public Object getMessageReleaseUserName() {
                return messageReleaseUserName;
            }

            public void setMessageReleaseUserName(Object messageReleaseUserName) {
                this.messageReleaseUserName = messageReleaseUserName;
            }
        }

        public static class ProjectBean {
            /**
             * messageId : 28069
             * messageType : 3
             * messageInfoId : 71
             * messageStat : 1
             * messageRemark : 项目成立没人五十红包
             * messageCreateTime : 2018-02-08 06:14:18
             * messageUpdateTime : null
             * messageReceiveUserId : 469
             * messageReleaseUserId : 428
             * applicationId : null
             * companyId : 9d853bbaabe94a29a2d06aa49d68194f
             * messageReleaseUserName : null
             */

            private int messageId;
            private String messageType;
            private String messageInfoId;
            private String messageStat;
            private String messageRemark;
            private String messageCreateTime;
            private Object messageUpdateTime;
            private int messageReceiveUserId;
            private int messageReleaseUserId;
            private Object applicationId;
            private String companyId;
            private Object messageReleaseUserName;

            public int getMessageId() {
                return messageId;
            }

            public void setMessageId(int messageId) {
                this.messageId = messageId;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getMessageInfoId() {
                return messageInfoId;
            }

            public void setMessageInfoId(String messageInfoId) {
                this.messageInfoId = messageInfoId;
            }

            public String getMessageStat() {
                return messageStat;
            }

            public void setMessageStat(String messageStat) {
                this.messageStat = messageStat;
            }

            public String getMessageRemark() {
                return messageRemark;
            }

            public void setMessageRemark(String messageRemark) {
                this.messageRemark = messageRemark;
            }

            public String getMessageCreateTime() {
                return messageCreateTime;
            }

            public void setMessageCreateTime(String messageCreateTime) {
                this.messageCreateTime = messageCreateTime;
            }

            public Object getMessageUpdateTime() {
                return messageUpdateTime;
            }

            public void setMessageUpdateTime(Object messageUpdateTime) {
                this.messageUpdateTime = messageUpdateTime;
            }

            public int getMessageReceiveUserId() {
                return messageReceiveUserId;
            }

            public void setMessageReceiveUserId(int messageReceiveUserId) {
                this.messageReceiveUserId = messageReceiveUserId;
            }

            public int getMessageReleaseUserId() {
                return messageReleaseUserId;
            }

            public void setMessageReleaseUserId(int messageReleaseUserId) {
                this.messageReleaseUserId = messageReleaseUserId;
            }

            public Object getApplicationId() {
                return applicationId;
            }

            public void setApplicationId(Object applicationId) {
                this.applicationId = applicationId;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public Object getMessageReleaseUserName() {
                return messageReleaseUserName;
            }

            public void setMessageReleaseUserName(Object messageReleaseUserName) {
                this.messageReleaseUserName = messageReleaseUserName;
            }
        }
    }
}
