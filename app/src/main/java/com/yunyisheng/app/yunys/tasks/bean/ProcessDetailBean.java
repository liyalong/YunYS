package com.yunyisheng.app.yunys.tasks.bean;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：fuduo on 2018/2/6 18:01
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProcessDetailBean extends BaseModel implements Serializable {

    private RespBodyBean respBody;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean implements Serializable {

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
        private SelectByIdAndUuid selectByIdAndUuid;

        public SelectByIdAndUuid getSelectByIdAndUuid() {
            return selectByIdAndUuid;
        }

        public void setSelectByIdAndUuid(SelectByIdAndUuid selectByIdAndUuid) {
            this.selectByIdAndUuid = selectByIdAndUuid;
        }

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

        public static class StartUserIdBean implements Serializable {
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

    public static class TaskBean implements Serializable {
        /**
         * id : 185004
         * state : 102
         */

        private String id;
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

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {

            return id;
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

    public static class YseNoEndBean implements Serializable {

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

    public static class HistoryCommnetsBean implements Serializable {
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

    public static class SelectByIdAndUuid implements Serializable{

        /**
         * form : {"version":1,"baseId":168,"typeId":67,"applicationId":"706311a4101d4d409c264b451c8d4672","companyId":"5cefca7b4ac54aaf9bbef697dbdfd0d6","name":"用车表单","image":"page/library/2.png","createUser":467,"createTime":"2018-02-09 15:27:49","uuid":"c36ee8f1321d405eb7f533f6a584e582","fields":null,"description":"用车表单","explanation":"用车表单","isPrint":"0","template":"<fieldset style=\"padding-top: 20px\">\n              <!--<div id=\"legend\" class=\"component\" rel=\"popover\" title=\"编辑属性\" trigger=\"manual\"-->\n                <!--data-content=\"-->\n                <!--<form class='form'>-->\n                  <!--<div class='controls'>-->\n                    <!--<label class='control-label'>表单名称<\/label> <input type='text' id='orgvalue' placeholder='请输入表单名称'>-->\n                    <!--<hr/>-->\n                    <!--<button class='btn btn-info' type='button'>确定<\/button><button class='btn btn-danger' type='button'>取消<\/button>-->\n                  <!--<\/div>-->\n                <!--<\/form>\"-->\n                <!--&gt;-->\n                <!--<input type=\"hidden\" name=\"form_name\" value=\"\" class=\"leipiplugins\" leipiplugins=\"form_name\"/>-->\n                <!--<legend class=\"leipiplugins-orgvalue\">点击填写表单名<\/legend>-->\n              <!--<\/div>-->\n            <div class=\"control-group component\" rel=\"popover\" trigger=\"manual\" data-content=\"\n  <form class='form'>\n    <div class='controls'>\n      <label class='control-label'>控件名称<\/label> <input type='text' id='orgname' placeholder='必填项'>\n      <label class='control-label'>默认值<\/label> <input type='text' id='orgvalue' placeholder='默认值'>\n      <hr/>\n      <button class='btn btn-info' type='button'>确定<\/button><button class='btn btn-danger' type='button'>取消<\/button>\n    <\/div>\n  <\/form>\" data-original-title=\"文本框控件\" style=\"border-top: 1px solid white; border-bottom: medium none;\">\n  <!-- Text -->\n  <label class=\"control-label leipiplugins-orgname\">用车人<\/label>\n  <div class=\"controls\">\n    <input name=\"leipiNewField\" placeholder=\"默认值\" title=\"用车人\" value=\"\" class=\"leipiplugins\" leipiplugins=\"text\" type=\"text\">\n  <\/div>\n\n<\/div>\n\n    <div class=\"control-group component\" rel=\"popover\" trigger=\"manual\" data-content=\"\n  <form class='form'>\n    <div class='controls'>\n      <label class='control-label'>控件名称<\/label> <input type='text' id='orgname' placeholder='必填项'>\n      <label class='control-label'>默认值<\/label> <input type='text' id='orgvalue' placeholder='默认值'>\n      <hr/>\n      <button class='btn btn-info' type='button'>确定<\/button><button class='btn btn-danger' type='button'>取消<\/button>\n    <\/div>\n  <\/form>\" data-original-title=\"多行文本控件\" style=\"border-top: 1px solid white; border-bottom: medium none;\">\n  <!-- Textarea -->\n  <label class=\"control-label leipiplugins-orgname\">用车说明<\/label>\n  <div class=\"controls\">\n    <div class=\"textarea\">\n          <textarea title=\"用车说明\" name=\"leipiNewField\" class=\"leipiplugins\" leipiplugins=\"textarea\" orgname=\"用车说明\"> <\/textarea>\n    <\/div>\n  <\/div>\n<\/div>\n\n    <div class=\"control-group component\" rel=\"popover\" trigger=\"manual\" data-content=\"\n  <form class='form'>\n    <div class='controls'>\n      <label class='control-label'>控件名称<\/label> <input type='text' id='orgname' placeholder='必填项'>\n      <label class='control-label'>默认值<\/label> <input type='text' id='orgvalue' placeholder='默认值'>\n      <hr/>\n      <button class='btn btn-info' type='button'>确定<\/button><button class='btn btn-danger' type='button'>取消<\/button>\n    <\/div>\n  <\/form>\" data-original-title=\"文本框控件\">\n  <!-- Text -->\n  <label class=\"control-label leipiplugins-orgname\">用车起点<\/label>\n  <div class=\"controls\">\n    <input name=\"leipiNewField\" placeholder=\"默认值\" title=\"用车起点\" value=\"\" class=\"leipiplugins\" leipiplugins=\"text\" type=\"text\">\n  <\/div>\n\n<\/div>\n\n    <\/fieldset>","parse":"<form class=\"form-horizontal\">\n<fieldset style=\"padding-top: 20px\">\n  <!--<div id=\"legend\" class=\"component\" rel=\"popover\" title=\"编辑属性\" trigger=\"manual\"-->\n    <!--data-content=\"-->\n    <!--<form class='form'>-->\n      <!--<div class='controls'>-->\n        <!--<label class='control-label'>表单名称<\/label> <input type='text' id='orgvalue' placeholder='请输入表单名称'>-->\n        <!--<hr/>-->\n        <!--<button class='btn btn-info' type='button'>确定<\/button><button class='btn btn-danger' type='button'>取消<\/button>-->\n      <!--<\/div>-->\n    <!--<\/form>\"-->\n    <!--&gt;-->\n    <!--<input type=\"hidden\" name=\"form_name\" value=\"\" class=\"leipiplugins\" leipiplugins=\"form_name\"/>-->\n    <!--<legend class=\"leipiplugins-orgvalue\">点击填写表单名<\/legend>-->\n  <!--<\/div>-->\n<div class=\"control-group\">\n  <!-- Text -->\n  <label class=\"control-label leipiplugins-orgname\">用车人<\/label>\n  <div class=\"controls\">\n    <input name=\"leipiNewField\" placeholder=\"默认值\" title=\"用车人\" value=\"\" class=\"leipiplugins\" leipiplugins=\"text\" type=\"text\">\n  <\/div>\n\n<\/div>\n\n    <div class=\"control-group\">\n  <!-- Textarea -->\n  <label class=\"control-label leipiplugins-orgname\">用车说明<\/label>\n  <div class=\"controls\">\n    <div class=\"textarea\">\n          <textarea title=\"用车说明\" name=\"leipiNewField\" class=\"leipiplugins\" leipiplugins=\"textarea\" orgname=\"用车说明\"> <\/textarea>\n    <\/div>\n  <\/div>\n<\/div>\n\n    <div class=\"control-group\">\n  <!-- Text -->\n  <label class=\"control-label leipiplugins-orgname\">用车起点<\/label>\n  <div class=\"controls\">\n    <input name=\"leipiNewField\" placeholder=\"默认值\" title=\"用车起点\" value=\"\" class=\"leipiplugins\" leipiplugins=\"text\" type=\"text\">\n  <\/div>\n\n<\/div>\n\n    <\/fieldset>\n          <\/form>","data":[{"id":753,"uuid":"c36ee8f1321d405eb7f533f6a584e582","name":"用车人","leipiplugins":"text","type":null,"value":"","readonly":null,"title":"用车人","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":null,"orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":null},{"id":754,"uuid":"c36ee8f1321d405eb7f533f6a584e582","name":"用车说明","leipiplugins":"textarea","type":null,"value":" ","readonly":null,"title":"用车说明","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":null,"orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":null},{"id":755,"uuid":"c36ee8f1321d405eb7f533f6a584e582","name":"用车起点","leipiplugins":"text","type":null,"value":"","readonly":null,"title":"用车起点","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":null,"orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":null}]}
         * dataList : [{"id":781,"instanceId":250,"fieldId":753,"value":"付铎","fieldName":null,"createTime":null},{"id":782,"instanceId":250,"fieldId":754,"value":"急用","fieldName":null,"createTime":null},{"id":783,"instanceId":250,"fieldId":755,"value":"北京","fieldName":null,"createTime":null}]
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
             * version : 1
             * baseId : 168
             * typeId : 67
             * applicationId : 706311a4101d4d409c264b451c8d4672
             * companyId : 5cefca7b4ac54aaf9bbef697dbdfd0d6
             * name : 用车表单
             * image : page/library/2.png
             * createUser : 467
             * createTime : 2018-02-09 15:27:49
             * uuid : c36ee8f1321d405eb7f533f6a584e582
             * fields : null
             * description : 用车表单
             * explanation : 用车表单
             * isPrint : 0
             * template : <fieldset style="padding-top: 20px">
             <!--<div id="legend" class="component" rel="popover" title="编辑属性" trigger="manual"-->
             <!--data-content="-->
             <!--<form class='form'>-->
             <!--<div class='controls'>-->
             <!--<label class='control-label'>表单名称</label> <input type='text' id='orgvalue' placeholder='请输入表单名称'>-->
             <!--<hr/>-->
             <!--<button class='btn btn-info' type='button'>确定</button><button class='btn btn-danger' type='button'>取消</button>-->
             <!--</div>-->
             <!--</form>"-->
             <!--&gt;-->
             <!--<input type="hidden" name="form_name" value="" class="leipiplugins" leipiplugins="form_name"/>-->
             <!--<legend class="leipiplugins-orgvalue">点击填写表单名</legend>-->
             <!--</div>-->
             <div class="control-group component" rel="popover" trigger="manual" data-content="
             <form class='form'>
             <div class='controls'>
             <label class='control-label'>控件名称</label> <input type='text' id='orgname' placeholder='必填项'>
             <label class='control-label'>默认值</label> <input type='text' id='orgvalue' placeholder='默认值'>
             <hr/>
             <button class='btn btn-info' type='button'>确定</button><button class='btn btn-danger' type='button'>取消</button>
             </div>
             </form>" data-original-title="文本框控件" style="border-top: 1px solid white; border-bottom: medium none;">
             <!-- Text -->
             <label class="control-label leipiplugins-orgname">用车人</label>
             <div class="controls">
             <input name="leipiNewField" placeholder="默认值" title="用车人" value="" class="leipiplugins" leipiplugins="text" type="text">
             </div>

             </div>

             <div class="control-group component" rel="popover" trigger="manual" data-content="
             <form class='form'>
             <div class='controls'>
             <label class='control-label'>控件名称</label> <input type='text' id='orgname' placeholder='必填项'>
             <label class='control-label'>默认值</label> <input type='text' id='orgvalue' placeholder='默认值'>
             <hr/>
             <button class='btn btn-info' type='button'>确定</button><button class='btn btn-danger' type='button'>取消</button>
             </div>
             </form>" data-original-title="多行文本控件" style="border-top: 1px solid white; border-bottom: medium none;">
             <!-- Textarea -->
             <label class="control-label leipiplugins-orgname">用车说明</label>
             <div class="controls">
             <div class="textarea">
             <textarea title="用车说明" name="leipiNewField" class="leipiplugins" leipiplugins="textarea" orgname="用车说明"> </textarea>
             </div>
             </div>
             </div>

             <div class="control-group component" rel="popover" trigger="manual" data-content="
             <form class='form'>
             <div class='controls'>
             <label class='control-label'>控件名称</label> <input type='text' id='orgname' placeholder='必填项'>
             <label class='control-label'>默认值</label> <input type='text' id='orgvalue' placeholder='默认值'>
             <hr/>
             <button class='btn btn-info' type='button'>确定</button><button class='btn btn-danger' type='button'>取消</button>
             </div>
             </form>" data-original-title="文本框控件">
             <!-- Text -->
             <label class="control-label leipiplugins-orgname">用车起点</label>
             <div class="controls">
             <input name="leipiNewField" placeholder="默认值" title="用车起点" value="" class="leipiplugins" leipiplugins="text" type="text">
             </div>

             </div>

             </fieldset>
             * parse : <form class="form-horizontal">
             <fieldset style="padding-top: 20px">
             <!--<div id="legend" class="component" rel="popover" title="编辑属性" trigger="manual"-->
             <!--data-content="-->
             <!--<form class='form'>-->
             <!--<div class='controls'>-->
             <!--<label class='control-label'>表单名称</label> <input type='text' id='orgvalue' placeholder='请输入表单名称'>-->
             <!--<hr/>-->
             <!--<button class='btn btn-info' type='button'>确定</button><button class='btn btn-danger' type='button'>取消</button>-->
             <!--</div>-->
             <!--</form>"-->
             <!--&gt;-->
             <!--<input type="hidden" name="form_name" value="" class="leipiplugins" leipiplugins="form_name"/>-->
             <!--<legend class="leipiplugins-orgvalue">点击填写表单名</legend>-->
             <!--</div>-->
             <div class="control-group">
             <!-- Text -->
             <label class="control-label leipiplugins-orgname">用车人</label>
             <div class="controls">
             <input name="leipiNewField" placeholder="默认值" title="用车人" value="" class="leipiplugins" leipiplugins="text" type="text">
             </div>

             </div>

             <div class="control-group">
             <!-- Textarea -->
             <label class="control-label leipiplugins-orgname">用车说明</label>
             <div class="controls">
             <div class="textarea">
             <textarea title="用车说明" name="leipiNewField" class="leipiplugins" leipiplugins="textarea" orgname="用车说明"> </textarea>
             </div>
             </div>
             </div>

             <div class="control-group">
             <!-- Text -->
             <label class="control-label leipiplugins-orgname">用车起点</label>
             <div class="controls">
             <input name="leipiNewField" placeholder="默认值" title="用车起点" value="" class="leipiplugins" leipiplugins="text" type="text">
             </div>

             </div>

             </fieldset>
             </form>
             * data : [{"id":753,"uuid":"c36ee8f1321d405eb7f533f6a584e582","name":"用车人","leipiplugins":"text","type":null,"value":"","readonly":null,"title":"用车人","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":null,"orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":null},{"id":754,"uuid":"c36ee8f1321d405eb7f533f6a584e582","name":"用车说明","leipiplugins":"textarea","type":null,"value":" ","readonly":null,"title":"用车说明","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":null,"orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":null},{"id":755,"uuid":"c36ee8f1321d405eb7f533f6a584e582","name":"用车起点","leipiplugins":"text","type":null,"value":"","readonly":null,"title":"用车起点","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":null,"orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":null}]
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
            private Object fields;
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

            public Object getFields() {
                return fields;
            }

            public void setFields(Object fields) {
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

            public static class DataBean implements Serializable {
                /**
                 * id : 753
                 * uuid : c36ee8f1321d405eb7f533f6a584e582
                 * name : 用车人
                 * leipiplugins : text
                 * type : null
                 * value :
                 * readonly : null
                 * title : 用车人
                 * orgtitle : null
                 * orgcoltype : null
                 * orgunit : null
                 * orgsum : null
                 * orgcolvalue : null
                 * orgwidth : null
                 * style : null
                 * content : null
                 * orgfontsize : null
                 * orgrich : null
                 * orgheight : null
                 * src : null
                 * orgsigntype : null
                 * parseName : null
                 * options : []
                 * orgalign : null
                 * optionStr : []
                 * orgtype : null
                 */

                private int id;
                private String uuid;
                private String name;
                private String leipiplugins;
                private Object type;
                private String value;
                private Object readonly;
                private String title;
                private Object orgtitle;
                private Object orgcoltype;
                private Object orgunit;
                private Object orgsum;
                private Object orgcolvalue;
                private Object orgwidth;
                private Object style;
                private Object content;
                private Object orgfontsize;
                private Object orgrich;
                private Object orgheight;
                private Object src;
                private Object orgsigntype;
                private Object parseName;
                private Object orgalign;
                private String optionStr;
                private Object orgtype;
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

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
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

                public Object getOrgwidth() {
                    return orgwidth;
                }

                public void setOrgwidth(Object orgwidth) {
                    this.orgwidth = orgwidth;
                }

                public Object getStyle() {
                    return style;
                }

                public void setStyle(Object style) {
                    this.style = style;
                }

                public Object getContent() {
                    return content;
                }

                public void setContent(Object content) {
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

                public Object getOrgalign() {
                    return orgalign;
                }

                public void setOrgalign(Object orgalign) {
                    this.orgalign = orgalign;
                }

                public String getOptionStr() {
                    return optionStr;
                }

                public void setOptionStr(String optionStr) {
                    this.optionStr = optionStr;
                }

                public Object getOrgtype() {
                    return orgtype;
                }

                public void setOrgtype(Object orgtype) {
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

        public static class DataListBean implements Serializable {
            /**
             * id : 781
             * instanceId : 250
             * fieldId : 753
             * value : 付铎
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
