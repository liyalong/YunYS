package com.yunyisheng.app.yunys.tasks.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;

import java.util.List;

/**
 * Created by liyalong on 2018/2/28.
 */

public class ProcessDetailModel extends BaseModel {
    private DetailBean respBody;

    public void setRespBody(DetailBean respBody) {
        this.respBody = respBody;
    }

    public DetailBean getRespBody() {

        return respBody;
    }

    public class DetailBean{
        private StartUser startUserId;
        private Task task;
        private List<HistoryCommnets> historyCommnets;
        private YesNoEnd yesNoEnd;

        public void setSelectByIdAndUuid(SelectByIdAndUuid selectByIdAndUuid) {
            this.selectByIdAndUuid = selectByIdAndUuid;
        }

        public SelectByIdAndUuid getSelectByIdAndUuid() {

            return selectByIdAndUuid;
        }

        private SelectByIdAndUuid selectByIdAndUuid;

        public void setStartUserId(StartUser startUserId) {
            this.startUserId = startUserId;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public void setHistoryCommnets(List<HistoryCommnets> historyCommnets) {
            this.historyCommnets = historyCommnets;
        }

        public void setYesNoEnd(YesNoEnd yesNoEnd) {
            this.yesNoEnd = yesNoEnd;
        }

        public StartUser getStartUserId() {

            return startUserId;
        }

        public Task getTask() {
            return task;
        }

        public List<HistoryCommnets> getHistoryCommnets() {
            return historyCommnets;
        }

        public YesNoEnd getYesNoEnd() {
            return yesNoEnd;
        }

        public class StartUser{
            private Integer userId;
            private String userName;
            private String consoleUsername;
            private String userSex;
            private String userJobTitle;
            private String userPhone;

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getUserPhone() {

                return userPhone;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public void setConsoleUsername(String consoleUsername) {
                this.consoleUsername = consoleUsername;
            }

            public void setUserSex(String userSex) {
                this.userSex = userSex;
            }

            public void setUserJobTitle(String userJobTitle) {
                this.userJobTitle = userJobTitle;
            }


            public Integer getUserId() {

                return userId;
            }

            public String getUserName() {
                return userName;
            }

            public String getConsoleUsername() {
                return consoleUsername;
            }

            public String getUserSex() {
                return userSex;
            }

            public String getUserJobTitle() {
                return userJobTitle;
            }

        }
        public class Task{
            private Integer id;
            private String theme;
            private String initiator;
            private String creationTime;
            private String endTime3;
            private String type;
            private String state;
            private String yesOrNoApproval;
            private String over;
            private String selectUserById;
            private String assignee;

            public void setId(Integer id) {
                this.id = id;
            }

            public void setTheme(String theme) {
                this.theme = theme;
            }

            public void setInitiator(String initiator) {
                this.initiator = initiator;
            }

            public void setCreationTime(String creationTime) {
                this.creationTime = creationTime;
            }

            public void setEndTime3(String endTime3) {
                this.endTime3 = endTime3;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setState(String state) {
                this.state = state;
            }

            public void setYesOrNoApproval(String yesOrNoApproval) {
                this.yesOrNoApproval = yesOrNoApproval;
            }

            public void setOver(String over) {
                this.over = over;
            }

            public void setSelectUserById(String selectUserById) {
                this.selectUserById = selectUserById;
            }

            public void setAssignee(String assignee) {
                this.assignee = assignee;
            }

            public Integer getId() {

                return id;
            }

            public String getTheme() {
                return theme;
            }

            public String getInitiator() {
                return initiator;
            }

            public String getCreationTime() {
                return creationTime;
            }

            public String getEndTime3() {
                return endTime3;
            }

            public String getType() {
                return type;
            }

            public String getState() {
                return state;
            }

            public String getYesOrNoApproval() {
                return yesOrNoApproval;
            }

            public String getOver() {
                return over;
            }

            public String getSelectUserById() {
                return selectUserById;
            }

            public String getAssignee() {
                return assignee;
            }
        }
        public class HistoryCommnets{
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

            public void setAction(String action) {
                this.action = action;
            }

            public void setFullMessage(String fullMessage) {
                this.fullMessage = fullMessage;
            }

            public void setFullMessageBytes(String fullMessageBytes) {
                this.fullMessageBytes = fullMessageBytes;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public void setPersistentState(String persistentState) {
                this.persistentState = persistentState;
            }

            public void setProcessInstanceId(String processInstanceId) {
                this.processInstanceId = processInstanceId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getAction() {

                return action;
            }

            public String getFullMessage() {
                return fullMessage;
            }

            public String getFullMessageBytes() {
                return fullMessageBytes;
            }

            public String getId() {
                return id;
            }

            public String getMessage() {
                return message;
            }

            public String getPersistentState() {
                return persistentState;
            }

            public String getProcessInstanceId() {
                return processInstanceId;
            }

            public String getTaskId() {
                return taskId;
            }

            public long getTime() {
                return time;
            }

            public String getType() {
                return type;
            }

            public String getUserId() {
                return userId;
            }
        }
        public class YesNoEnd{
            private Integer id;
            private String processInstanceId;
            private String processDefinitionId;
            private String startTime;
            private String endTime;
            private String processDefinitionName;
            private String name;
            private String tenantId;
            private String suspenSionState;

            public void setId(Integer id) {
                this.id = id;
            }

            public void setProcessInstanceId(String processInstanceId) {
                this.processInstanceId = processInstanceId;
            }

            public void setProcessDefinitionId(String processDefinitionId) {
                this.processDefinitionId = processDefinitionId;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public void setProcessDefinitionName(String processDefinitionName) {
                this.processDefinitionName = processDefinitionName;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTenantId(String tenantId) {
                this.tenantId = tenantId;
            }

            public void setSuspenSionState(String suspenSionState) {
                this.suspenSionState = suspenSionState;
            }

            public Integer getId() {

                return id;
            }

            public String getProcessInstanceId() {
                return processInstanceId;
            }

            public String getProcessDefinitionId() {
                return processDefinitionId;
            }

            public String getStartTime() {
                return startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public String getProcessDefinitionName() {
                return processDefinitionName;
            }

            public String getName() {
                return name;
            }

            public String getTenantId() {
                return tenantId;
            }

            public String getSuspenSionState() {
                return suspenSionState;
            }
        }
        public class SelectByIdAndUuid{
            private ScheduleDetailBean.RespBodyBean.FormBean form;

            public void setForm(ScheduleDetailBean.RespBodyBean.FormBean form) {
                this.form = form;
            }

            public ScheduleDetailBean.RespBodyBean.FormBean getForm() {

                return form;
            }
        }

    }
}
