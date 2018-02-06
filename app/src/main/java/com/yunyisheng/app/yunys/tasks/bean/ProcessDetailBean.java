package com.yunyisheng.app.yunys.tasks.bean;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/6 18:01
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProcessDetailBean extends BaseModel {

    private RespBodyBean respBody;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {

        /**
         * historyCommnets : [{"action":"AddComment","fullMessage":"审核通过，继续努力","fullMessageBytes":"5a6h5qC46YCa6L+H77yM57un57ut5Yqq5Yqb","id":"185001","message":"审核通过，继续努力","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntity","processInstanceId":"177528","taskId":"177532","time":1517910532401,"type":"comment","userId":"null"}]
         * startUserId : {"enterpriseId":"88d3c7fccd154c66861621c45ed4d75e","enterpriseRolesId":1,"parent":"谢智","userId":307,"userIsShow":true,"userJobTitle":"研发工程师","userMailbox":"jifeihu@foxmail.com","userMailboxState":"0","userName":"冀飞虎","userNumber":523456,"userPassword":"O3I432vkbjY=","userPhone":"13294029164","userPhoneState":"1","userPicture":"PCM/null/120ca675a2e64424a56a3d22a5e9b782.jpeg","userSetTime":1517258135000,"userSex":"男","userState":"1","userType":"1"}
         * task : {"id":"185004","state":"102"}
         * yseNoEnd : {"processDefinitionId":"未定义:19:107507","processInstanceId":"177528","tenantId":""}
         */

        private StartUserIdBean startUserId;
        private TaskBean task;
        private YseNoEndBean yseNoEnd;
        private List<HistoryCommnetsBean> historyCommnets;

        public StartUserIdBean getStartUserId() {
            return startUserId;
        }

        public void setStartUserId(StartUserIdBean startUserId) {
            this.startUserId = startUserId;
        }

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public YseNoEndBean getYseNoEnd() {
            return yseNoEnd;
        }

        public void setYseNoEnd(YseNoEndBean yseNoEnd) {
            this.yseNoEnd = yseNoEnd;
        }

        public List<HistoryCommnetsBean> getHistoryCommnets() {
            return historyCommnets;
        }

        public void setHistoryCommnets(List<HistoryCommnetsBean> historyCommnets) {
            this.historyCommnets = historyCommnets;
        }

        public static class StartUserIdBean {
            /**
             * enterpriseId : 88d3c7fccd154c66861621c45ed4d75e
             * enterpriseRolesId : 1
             * parent : 谢智
             * userId : 307
             * userIsShow : true
             * userJobTitle : 研发工程师
             * userMailbox : jifeihu@foxmail.com
             * userMailboxState : 0
             * userName : 冀飞虎
             * userNumber : 523456
             * userPassword : O3I432vkbjY=
             * userPhone : 13294029164
             * userPhoneState : 1
             * userPicture : PCM/null/120ca675a2e64424a56a3d22a5e9b782.jpeg
             * userSetTime : 1517258135000
             * userSex : 男
             * userState : 1
             * userType : 1
             */

            private String enterpriseId;
            private int enterpriseRolesId;
            private String parent;
            private int userId;
            private boolean userIsShow;
            private String userJobTitle;
            private String userMailbox;
            private String userMailboxState;
            private String userName;
            private int userNumber;
            private String userPassword;
            private String userPhone;
            private String userPhoneState;
            private String userPicture;
            private long userSetTime;
            private String userSex;
            private String userState;
            private String userType;

            public String getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(String enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public int getEnterpriseRolesId() {
                return enterpriseRolesId;
            }

            public void setEnterpriseRolesId(int enterpriseRolesId) {
                this.enterpriseRolesId = enterpriseRolesId;
            }

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public boolean isUserIsShow() {
                return userIsShow;
            }

            public void setUserIsShow(boolean userIsShow) {
                this.userIsShow = userIsShow;
            }

            public String getUserJobTitle() {
                return userJobTitle;
            }

            public void setUserJobTitle(String userJobTitle) {
                this.userJobTitle = userJobTitle;
            }

            public String getUserMailbox() {
                return userMailbox;
            }

            public void setUserMailbox(String userMailbox) {
                this.userMailbox = userMailbox;
            }

            public String getUserMailboxState() {
                return userMailboxState;
            }

            public void setUserMailboxState(String userMailboxState) {
                this.userMailboxState = userMailboxState;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getUserNumber() {
                return userNumber;
            }

            public void setUserNumber(int userNumber) {
                this.userNumber = userNumber;
            }

            public String getUserPassword() {
                return userPassword;
            }

            public void setUserPassword(String userPassword) {
                this.userPassword = userPassword;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getUserPhoneState() {
                return userPhoneState;
            }

            public void setUserPhoneState(String userPhoneState) {
                this.userPhoneState = userPhoneState;
            }

            public String getUserPicture() {
                return userPicture;
            }

            public void setUserPicture(String userPicture) {
                this.userPicture = userPicture;
            }

            public long getUserSetTime() {
                return userSetTime;
            }

            public void setUserSetTime(long userSetTime) {
                this.userSetTime = userSetTime;
            }

            public String getUserSex() {
                return userSex;
            }

            public void setUserSex(String userSex) {
                this.userSex = userSex;
            }

            public String getUserState() {
                return userState;
            }

            public void setUserState(String userState) {
                this.userState = userState;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }
        }
    }

    public static class TaskBean {
        /**
         * id : 185004
         * state : 102
         */

        private String id;
        private String state;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public static class YseNoEndBean {

        /**
         * id : null
         * processInstanceId : 180029
         * processDefinitionId : 未定义:18:105007
         * startTime : null
         * endTime : null
         * processDefinitionName : null
         * name : null
         * tenantId :
         * suspenSionState : null
         */

        private Object id;
        private String processInstanceId;
        private String processDefinitionId;
        private String startTime;
        private String endTime;
        private String processDefinitionName;
        private String name;
        private String tenantId;
        private Object suspenSionState;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getProcessInstanceId() {
            return processInstanceId;
        }

        public void setProcessInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
        }

        public String getProcessDefinitionId() {
            return processDefinitionId;
        }

        public void setProcessDefinitionId(String processDefinitionId) {
            this.processDefinitionId = processDefinitionId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getProcessDefinitionName() {
            return processDefinitionName;
        }

        public void setProcessDefinitionName(String processDefinitionName) {
            this.processDefinitionName = processDefinitionName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public Object getSuspenSionState() {
            return suspenSionState;
        }

        public void setSuspenSionState(Object suspenSionState) {
            this.suspenSionState = suspenSionState;
        }
    }

    public static class HistoryCommnetsBean {
        /**
         * action : AddComment
         * fullMessage : 审核通过，继续努力
         * fullMessageBytes : 5a6h5qC46YCa6L+H77yM57un57ut5Yqq5Yqb
         * id : 185001
         * message : 审核通过，继续努力
         * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntity
         * processInstanceId : 177528
         * taskId : 177532
         * time : 1517910532401
         * type : comment
         * userId : null
         */

        private String action;
        private String fullMessage;
        private String fullMessageBytes;
        private String id;
        private String message;
        private String persistentState;
        private String processInstanceId;
        private String taskId;
        private long time;
        private String type;
        private String userId;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getFullMessage() {
            return fullMessage;
        }

        public void setFullMessage(String fullMessage) {
            this.fullMessage = fullMessage;
        }

        public String getFullMessageBytes() {
            return fullMessageBytes;
        }

        public void setFullMessageBytes(String fullMessageBytes) {
            this.fullMessageBytes = fullMessageBytes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPersistentState() {
            return persistentState;
        }

        public void setPersistentState(String persistentState) {
            this.persistentState = persistentState;
        }

        public String getProcessInstanceId() {
            return processInstanceId;
        }

        public void setProcessInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
