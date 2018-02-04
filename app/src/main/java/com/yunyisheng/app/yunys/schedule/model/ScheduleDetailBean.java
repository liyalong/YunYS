package com.yunyisheng.app.yunys.schedule.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/31 18:42
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ScheduleDetailBean extends BaseModel {


    private List<RespBodyBean> respBody;

    public List<RespBodyBean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<RespBodyBean> respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * task : {"taskId":211,"releaseId":133,"releaseName":"测试拖拽式表单任务","releaseRemark":"拖拽式表单","releaseBegint":"2018-01-25 12:52:56","releaseEndt":"2018-01-31 16:00:00","releaseUserId":307,"releaseUsername":"冀飞虎","releaseFormId":"d12c618486bc46eea0ac0eb3f544090f","releaseFormInstanceId":null,"taskStat":1,"taskUserId":308,"taskUserName":"付铎","taskSubmitTime":null,"releaseAllot":3,"feedbackBacknum":null,"releaseTaskType":2,"equipmentId":null,"projectId":"c49558c1a2b541dd9915c52ef64a0f25","companyId":"88d3c7fccd154c66861621c45ed4d75e","terraceId":null,"task_is_delete":null}
         * form : {"version":3,"baseId":90,"typeId":41,"applicationId":"706311a4101d4d409c264b451c8d4672","companyId":"88d3c7fccd154c66861621c45ed4d75e","name":"二分法","image":"page/library/8.png","createUser":304,"createTime":"2018-01-25 04:20:23","uuid":"d12c618486bc46eea0ac0eb3f544090f","fields":10,"description":"而非","explanation":"","isPrint":"1","template":"<p style=\"text-align: center;\"><br/><\/p><p style=\"text-align: center;\"><span style=\"font-size: 24px;\">请假单<\/span><\/p><table><tbody><tr class=\"firstRow\"><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><tr><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><tr><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><tr><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><tr><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><\/tbody><\/table><p style=\"text-align: center;\"><span style=\"font-size: 24px;\"><\/span><br/><\/p><table class=\"table table-bordered\"><tbody><tr class=\"firstRow\"><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\">文本框<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"227\"><input style=\"text-align: left; width: 150px;\" title=\"文本框\" value=\"雷劈网\" name=\"data_1\" orgheight=\"\" orgwidth=\"150\" orgalign=\"left\" orgfontsize=\"\" orghide=\"0\" leipiplugins=\"text\" orgtype=\"text\"/><\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"85\">下拉菜单<\/td><td valign=\"top\" style=\"border-color: rgb(221, 221, 221);\" width=\"312\">{|-<span leipiplugins=\"select\"><select name=\"data_2\" title=\"下拉菜单\" leipiplugins=\"select\" size=\"1\" orgwidth=\"150\" style=\"width: 150px;\"><option value=\"下拉\">下拉<\/option><option value=\"菜单\">菜单<\/option><\/select>&nbsp;&nbsp;<\/span>-|}<\/td><\/tr><tr><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\">单选<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"41\">{|-<span leipiplugins=\"radios\" name=\"data_3\" title=\"单选\"><input type=\"radio\" name=\"data_3\" value=\"单选1\"  checked=\"checked\"/>单选1&nbsp;<input type=\"radio\" name=\"data_3\" value=\"单选2\"  />单选2&nbsp;<\/span>-|}<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"85\">复选<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"312\">{|-<span leipiplugins=\"checkboxs\"  title=\"复选\"><input type=\"checkbox\" name=\"data_4\" value=\"复选1\"  checked=\"checked\"/>复选1&nbsp;<input type=\"checkbox\" name=\"data_5\" value=\"复选2\"  checked=\"checked\"/>复选2&nbsp;<input type=\"checkbox\" name=\"data_6\" value=\"复选3\"  />复选3&nbsp;<\/span>-|}<\/td><\/tr><tr><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\">宏控件<\/td><td valign=\"top\" style=\"border-color: rgb(221, 221, 221);\" width=\"41\"><input name=\"data_7\" type=\"text\" value=\"{macros}\" title=\"宏控件\" leipiplugins=\"macros\" orgtype=\"sys_date_cn\" orghide=\"0\" orgfontsize=\"12\" orgwidth=\"150\" style=\"font-size: 12px; width: 150px;\"/><\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"85\">二维码<\/td><td valign=\"top\" style=\"border-color: rgb(221, 221, 221);\" width=\"312\"><img name=\"data_8\" title=\"雷劈网\" value=\"http://www.leipi.org\" orgtype=\"url\" leipiplugins=\"qrcode\" src=\"/Public/js/ueditor/formdesign/images/qrcode.gif\" orgwidth=\"40\" orgheight=\"40\" style=\"width: 40px; height: 40px;\"/><\/td><\/tr><\/tbody><\/table><p><input name=\"data_9\" leipiplugins=\"listctrl\" type=\"text\" value=\"{列表控件}\" readonly=\"readonly\" title=\"采购商品列表\" orgtitle=\"商品名称`数量`单价`小计`描述`\" orgcoltype=\"text`int`int`int`text`\" orgunit=\"```元``\" orgsum=\"0`0`0`1`0`\" orgcolvalue=\"`````\" orgwidth=\"100%\" style=\"width: 100%;\"/><\/p><p><img name=\"data_10\" title=\"进度条\" leipiplugins=\"progressbar\" orgvalue=\"20\" orgsigntype=\"progress-info\" src=\"/Public/js/ueditor/formdesign/images/progressbar.gif\"/><\/p><p><br/><\/p>","parse":"<p style=\"text-align: center;\"><br/><\/p><p style=\"text-align: center;\"><span style=\"font-size: 24px;\">请假单<\/span><\/p><table><tbody><tr class=\"firstRow\"><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><tr><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><tr><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><tr><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><tr><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><td width=\"106\" valign=\"top\"><br/><\/td><\/tr><\/tbody><\/table><p style=\"text-align: center;\"><span style=\"font-size: 24px;\"><\/span><br/><\/p><table class=\"table table-bordered\"><tbody><tr class=\"firstRow\"><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\">文本框<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"227\">{data_1}<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"85\">下拉菜单<\/td><td valign=\"top\" style=\"border-color: rgb(221, 221, 221);\" width=\"312\">{data_2}<\/td><\/tr><tr><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\">单选<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"41\">{data_3}<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"85\">复选<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"312\">{checkboxs_0}<\/td><\/tr><tr><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\">宏控件<\/td><td valign=\"top\" style=\"border-color: rgb(221, 221, 221);\" width=\"41\">{data_7}<\/td><td valign=\"top\" style=\"word-break: break-all; border-color: rgb(221, 221, 221);\" width=\"85\">二维码<\/td><td valign=\"top\" style=\"border-color: rgb(221, 221, 221);\" width=\"312\">{data_8}<\/td><\/tr><\/tbody><\/table><p>{data_9}<\/p><p>{data_10}<\/p><p><br/><\/p>","data":[{"id":334,"uuid":"d12c618486bc46eea0ac0eb3f544090f","name":"data_1","leipiplugins":"text","type":null,"value":"雷劈网","readonly":null,"title":"文本框","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input style=\"text-align: left; width: 150px;\" title=\"文本框\" value=\"雷劈网\" name=\"data_1\" orgheight=\"\" orgwidth=\"150\" orgalign=\"left\" orgfontsize=\"\" orghide=\"0\" leipiplugins=\"text\" orgtype=\"text\"/>","orgfontsize":"","orgrich":null,"orgheight":"","src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":336,"uuid":"d12c618486bc46eea0ac0eb3f544090f","name":"data_3","leipiplugins":"radios","type":null,"value":"单选1,单选2","readonly":null,"title":"单选","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":"<span leipiplugins=\"radios\" name=\"data_3\" title=\"单选\"><input type=\"radio\" name=\"data_3\" value=\"单选1\"  checked=\"checked\"/>单选1&nbsp;<input type=\"radio\" name=\"data_3\" value=\"单选2\"  />单选2&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[{"value":"单选1","type":"radio","checked":"checked","name":"data_3"},{"value":"单选2","type":"radio","checked":null,"name":"data_3"}],"orgalign":null,"optionStr":"[{\"checked\":\"checked\",\"name\":\"data_3\",\"type\":\"radio\",\"value\":\"单选1\"},{\"name\":\"data_3\",\"type\":\"radio\",\"value\":\"单选2\"}]","orgtype":null},{"id":337,"uuid":"d12c618486bc46eea0ac0eb3f544090f","name":"data_4,data_5,data_6","leipiplugins":"checkboxs","type":null,"value":"复选1,复选2,复选3","readonly":null,"title":"复选","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":"<span leipiplugins=\"checkboxs\"  title=\"复选\"><input type=\"checkbox\" name=\"data_4\" value=\"复选1\"  checked=\"checked\"/>复选1&nbsp;<input type=\"checkbox\" name=\"data_5\" value=\"复选2\"  checked=\"checked\"/>复选2&nbsp;<input type=\"checkbox\" name=\"data_6\" value=\"复选3\"  />复选3&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[{"value":"复选1","type":"checkbox","checked":"checked","name":"data_4"},{"value":"复选2","type":"checkbox","checked":"checked","name":"data_5"},{"value":"复选3","type":"checkbox","checked":null,"name":"data_6"}],"orgalign":null,"optionStr":"[{\"checked\":\"checked\",\"name\":\"data_4\",\"type\":\"checkbox\",\"value\":\"复选1\"},{\"checked\":\"checked\",\"name\":\"data_5\",\"type\":\"checkbox\",\"value\":\"复选2\"},{\"name\":\"data_6\",\"type\":\"checkbox\",\"value\":\"复选3\"}]","orgtype":null},{"id":339,"uuid":"d12c618486bc46eea0ac0eb3f544090f","name":"data_8","leipiplugins":"qrcode","type":null,"value":"http://www.leipi.org","readonly":null,"title":"雷劈网","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"40","style":"width: 40px; height: 40px;","content":"<img name=\"data_8\" title=\"雷劈网\" value=\"http://www.leipi.org\" orgtype=\"url\" leipiplugins=\"qrcode\" src=\"/Public/js/ueditor/formdesign/images/qrcode.gif\" orgwidth=\"40\" orgheight=\"40\" style=\"width: 40px; height: 40px;\"/>","orgfontsize":null,"orgrich":null,"orgheight":"40","src":"/Public/js/ueditor/formdesign/images/qrcode.gif","orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":"url"}]}
         */

        private TaskBean task;
        private FormBean form;
        private List<TaskBackBean> taskback;

        public void setTaskback(List<TaskBackBean> taskback) {
            this.taskback = taskback;
        }

        public List<TaskBackBean> getTaskback() {

            return taskback;
        }



        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public FormBean getForm() {
            return form;
        }

        public void setForm(FormBean form) {
            this.form = form;
        }

        public static class TaskBean {
            /**
             * taskId : 211
             * releaseId : 133
             * releaseName : 测试拖拽式表单任务
             * releaseRemark : 拖拽式表单
             * releaseBegint : 2018-01-25 12:52:56
             * releaseEndt : 2018-01-31 16:00:00
             * releaseUserId : 307
             * releaseUsername : 冀飞虎
             * releaseFormId : d12c618486bc46eea0ac0eb3f544090f
             * releaseFormInstanceId : null
             * taskStat : 1
             * taskUserId : 308
             * taskUserName : 付铎
             * taskSubmitTime : null
             * releaseAllot : 3
             * feedbackBacknum : null
             * releaseTaskType : 2
             * equipmentId : null
             * projectId : c49558c1a2b541dd9915c52ef64a0f25
             * companyId : 88d3c7fccd154c66861621c45ed4d75e
             * terraceId : null
             * task_is_delete : null
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
            private Object releaseFormInstanceId;
            private int taskStat;
            private int taskUserId;
            private String taskUserName;
            private Object taskSubmitTime;
            private int releaseAllot;
            private Object feedbackBacknum;
            private int releaseTaskType;
            private Object equipmentId;
            private String projectId;
            private String companyId;
            private Object terraceId;
            private Object task_is_delete;
            private String taskCreatet;
            private String projectName;
            private String equipmentName;

            public String getTaskCreatet() {
                return taskCreatet;
            }

            public String getProjectName() {
                return projectName;
            }

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setTaskCreatet(String taskCreatet) {

                this.taskCreatet = taskCreatet;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

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

            public Object getReleaseFormInstanceId() {
                return releaseFormInstanceId;
            }

            public void setReleaseFormInstanceId(Object releaseFormInstanceId) {
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

            public Object getTask_is_delete() {
                return task_is_delete;
            }

            public void setTask_is_delete(Object task_is_delete) {
                this.task_is_delete = task_is_delete;
            }
        }

        public static class FormBean {
            /**
             * version : 3
             * baseId : 90
             * typeId : 41
             * applicationId : 706311a4101d4d409c264b451c8d4672
             * companyId : 88d3c7fccd154c66861621c45ed4d75e
             * name : 二分法
             * image : page/library/8.png
             * createUser : 304
             * createTime : 2018-01-25 04:20:23
             * uuid : d12c618486bc46eea0ac0eb3f544090f
             * fields : 10
             * description : 而非
             * explanation :
             * isPrint : 1
             * template : <p style="text-align: center;"><br/></p><p style="text-align: center;"><span style="font-size: 24px;">请假单</span></p><table><tbody><tr class="firstRow"><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr><tr><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr><tr><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr><tr><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr><tr><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr></tbody></table><p style="text-align: center;"><span style="font-size: 24px;"></span><br/></p><table class="table table-bordered"><tbody><tr class="firstRow"><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);">文本框</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="227"><input style="text-align: left; width: 150px;" title="文本框" value="雷劈网" name="data_1" orgheight="" orgwidth="150" orgalign="left" orgfontsize="" orghide="0" leipiplugins="text" orgtype="text"/></td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="85">下拉菜单</td><td valign="top" style="border-color: rgb(221, 221, 221);" width="312">{|-<span leipiplugins="select"><select name="data_2" title="下拉菜单" leipiplugins="select" size="1" orgwidth="150" style="width: 150px;"><option value="下拉">下拉</option><option value="菜单">菜单</option></select>&nbsp;&nbsp;</span>-|}</td></tr><tr><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);">单选</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="41">{|-<span leipiplugins="radios" name="data_3" title="单选"><input type="radio" name="data_3" value="单选1"  checked="checked"/>单选1&nbsp;<input type="radio" name="data_3" value="单选2"  />单选2&nbsp;</span>-|}</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="85">复选</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="312">{|-<span leipiplugins="checkboxs"  title="复选"><input type="checkbox" name="data_4" value="复选1"  checked="checked"/>复选1&nbsp;<input type="checkbox" name="data_5" value="复选2"  checked="checked"/>复选2&nbsp;<input type="checkbox" name="data_6" value="复选3"  />复选3&nbsp;</span>-|}</td></tr><tr><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);">宏控件</td><td valign="top" style="border-color: rgb(221, 221, 221);" width="41"><input name="data_7" type="text" value="{macros}" title="宏控件" leipiplugins="macros" orgtype="sys_date_cn" orghide="0" orgfontsize="12" orgwidth="150" style="font-size: 12px; width: 150px;"/></td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="85">二维码</td><td valign="top" style="border-color: rgb(221, 221, 221);" width="312"><img name="data_8" title="雷劈网" value="http://www.leipi.org" orgtype="url" leipiplugins="qrcode" src="/Public/js/ueditor/formdesign/images/qrcode.gif" orgwidth="40" orgheight="40" style="width: 40px; height: 40px;"/></td></tr></tbody></table><p><input name="data_9" leipiplugins="listctrl" type="text" value="{列表控件}" readonly="readonly" title="采购商品列表" orgtitle="商品名称`数量`单价`小计`描述`" orgcoltype="text`int`int`int`text`" orgunit="```元``" orgsum="0`0`0`1`0`" orgcolvalue="`````" orgwidth="100%" style="width: 100%;"/></p><p><img name="data_10" title="进度条" leipiplugins="progressbar" orgvalue="20" orgsigntype="progress-info" src="/Public/js/ueditor/formdesign/images/progressbar.gif"/></p><p><br/></p>
             * parse : <p style="text-align: center;"><br/></p><p style="text-align: center;"><span style="font-size: 24px;">请假单</span></p><table><tbody><tr class="firstRow"><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr><tr><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr><tr><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr><tr><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr><tr><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td><td width="106" valign="top"><br/></td></tr></tbody></table><p style="text-align: center;"><span style="font-size: 24px;"></span><br/></p><table class="table table-bordered"><tbody><tr class="firstRow"><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);">文本框</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="227">{data_1}</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="85">下拉菜单</td><td valign="top" style="border-color: rgb(221, 221, 221);" width="312">{data_2}</td></tr><tr><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);">单选</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="41">{data_3}</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="85">复选</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="312">{checkboxs_0}</td></tr><tr><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);">宏控件</td><td valign="top" style="border-color: rgb(221, 221, 221);" width="41">{data_7}</td><td valign="top" style="word-break: break-all; border-color: rgb(221, 221, 221);" width="85">二维码</td><td valign="top" style="border-color: rgb(221, 221, 221);" width="312">{data_8}</td></tr></tbody></table><p>{data_9}</p><p>{data_10}</p><p><br/></p>
             * data : [{"id":334,"uuid":"d12c618486bc46eea0ac0eb3f544090f","name":"data_1","leipiplugins":"text","type":null,"value":"雷劈网","readonly":null,"title":"文本框","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input style=\"text-align: left; width: 150px;\" title=\"文本框\" value=\"雷劈网\" name=\"data_1\" orgheight=\"\" orgwidth=\"150\" orgalign=\"left\" orgfontsize=\"\" orghide=\"0\" leipiplugins=\"text\" orgtype=\"text\"/>","orgfontsize":"","orgrich":null,"orgheight":"","src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":336,"uuid":"d12c618486bc46eea0ac0eb3f544090f","name":"data_3","leipiplugins":"radios","type":null,"value":"单选1,单选2","readonly":null,"title":"单选","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":"<span leipiplugins=\"radios\" name=\"data_3\" title=\"单选\"><input type=\"radio\" name=\"data_3\" value=\"单选1\"  checked=\"checked\"/>单选1&nbsp;<input type=\"radio\" name=\"data_3\" value=\"单选2\"  />单选2&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[{"value":"单选1","type":"radio","checked":"checked","name":"data_3"},{"value":"单选2","type":"radio","checked":null,"name":"data_3"}],"orgalign":null,"optionStr":"[{\"checked\":\"checked\",\"name\":\"data_3\",\"type\":\"radio\",\"value\":\"单选1\"},{\"name\":\"data_3\",\"type\":\"radio\",\"value\":\"单选2\"}]","orgtype":null},{"id":337,"uuid":"d12c618486bc46eea0ac0eb3f544090f","name":"data_4,data_5,data_6","leipiplugins":"checkboxs","type":null,"value":"复选1,复选2,复选3","readonly":null,"title":"复选","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":"<span leipiplugins=\"checkboxs\"  title=\"复选\"><input type=\"checkbox\" name=\"data_4\" value=\"复选1\"  checked=\"checked\"/>复选1&nbsp;<input type=\"checkbox\" name=\"data_5\" value=\"复选2\"  checked=\"checked\"/>复选2&nbsp;<input type=\"checkbox\" name=\"data_6\" value=\"复选3\"  />复选3&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[{"value":"复选1","type":"checkbox","checked":"checked","name":"data_4"},{"value":"复选2","type":"checkbox","checked":"checked","name":"data_5"},{"value":"复选3","type":"checkbox","checked":null,"name":"data_6"}],"orgalign":null,"optionStr":"[{\"checked\":\"checked\",\"name\":\"data_4\",\"type\":\"checkbox\",\"value\":\"复选1\"},{\"checked\":\"checked\",\"name\":\"data_5\",\"type\":\"checkbox\",\"value\":\"复选2\"},{\"name\":\"data_6\",\"type\":\"checkbox\",\"value\":\"复选3\"}]","orgtype":null},{"id":339,"uuid":"d12c618486bc46eea0ac0eb3f544090f","name":"data_8","leipiplugins":"qrcode","type":null,"value":"http://www.leipi.org","readonly":null,"title":"雷劈网","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"40","style":"width: 40px; height: 40px;","content":"<img name=\"data_8\" title=\"雷劈网\" value=\"http://www.leipi.org\" orgtype=\"url\" leipiplugins=\"qrcode\" src=\"/Public/js/ueditor/formdesign/images/qrcode.gif\" orgwidth=\"40\" orgheight=\"40\" style=\"width: 40px; height: 40px;\"/>","orgfontsize":null,"orgrich":null,"orgheight":"40","src":"/Public/js/ueditor/formdesign/images/qrcode.gif","orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":"url"}]
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
                 * id : 334
                 * uuid : d12c618486bc46eea0ac0eb3f544090f
                 * name : data_1
                 * leipiplugins : text
                 * type : null
                 * value : 雷劈网
                 * readonly : null
                 * title : 文本框
                 * orgtitle : null
                 * orgcoltype : null
                 * orgunit : null
                 * orgsum : null
                 * orgcolvalue : null
                 * orgwidth : 150
                 * style : text-align: left; width: 150px;
                 * content : <input style="text-align: left; width: 150px;" title="文本框" value="雷劈网" name="data_1" orgheight="" orgwidth="150" orgalign="left" orgfontsize="" orghide="0" leipiplugins="text" orgtype="text"/>
                 * orgfontsize :
                 * orgrich : null
                 * orgheight :
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
                private Object type;
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
                private String orgfontsize;
                private Object orgrich;
                private String orgheight;
                private Object src;
                private Object orgsigntype;
                private Object parseName;
                private String orgalign;
                private String optionStr;
                private String orgtype;
                private List<VelueBean> options;

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

                public String getOrgfontsize() {
                    return orgfontsize;
                }

                public void setOrgfontsize(String orgfontsize) {
                    this.orgfontsize = orgfontsize;
                }

                public Object getOrgrich() {
                    return orgrich;
                }

                public void setOrgrich(Object orgrich) {
                    this.orgrich = orgrich;
                }

                public String getOrgheight() {
                    return orgheight;
                }

                public void setOrgheight(String orgheight) {
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

                public List<VelueBean> getOptions() {
                    return options;
                }

                public void setOptions(List<VelueBean> options) {
                    this.options = options;
                }
            }

            public static class VelueBean{

                /**
                 * value : 单选1
                 * type : radio
                 * checked : checked
                 * name : data_3
                 */

                private String value;
                private String type;
                private String checked;
                private String name;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getChecked() {
                    return checked;
                }

                public void setChecked(String checked) {
                    this.checked = checked;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public class TaskBackBean {
            private int taskbackId;
            private int releaseId;
            private String releaseName;
            private int taskbackUserId;
            private String taskbackUsername;
            private String taskbackVal;
            private String taskbackCreatet;

            public void setTaskbackId(int taskbackId) {
                this.taskbackId = taskbackId;
            }

            public void setReleaseId(int releaseId) {
                this.releaseId = releaseId;
            }

            public void setReleaseName(String releaseName) {
                this.releaseName = releaseName;
            }

            public void setTaskbackUserId(int taskbackUserId) {
                this.taskbackUserId = taskbackUserId;
            }

            public void setTaskbackUsername(String taskbackUsername) {
                this.taskbackUsername = taskbackUsername;
            }

            public void setTaskbackVal(String taskbackVal) {
                this.taskbackVal = taskbackVal;
            }

            public void setTaskbackCreatet(String taskbackCreatet) {
                this.taskbackCreatet = taskbackCreatet;
            }

            public int getTaskbackId() {

                return taskbackId;
            }

            public int getReleaseId() {
                return releaseId;
            }

            public String getReleaseName() {
                return releaseName;
            }

            public int getTaskbackUserId() {
                return taskbackUserId;
            }

            public String getTaskbackUsername() {
                return taskbackUsername;
            }

            public String getTaskbackVal() {
                return taskbackVal;
            }

            public String getTaskbackCreatet() {
                return taskbackCreatet;
            }
        }
    }
}
