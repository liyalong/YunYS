package com.yunyisheng.app.yunys.schedule.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/29 17:06
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class MyScheduleBean extends BaseModel {


    /**
     * respBody : {"star":0,"dataList":[{"state":"1","taskId":"165","creationTime":"2018-01-24 00:00:00","type":"2","endTime":"2018-02-23 00:00:00","initiator":"毛硕","theme":"任务用于测试message"},{"state":"1","taskId":"186","creationTime":"2018-01-25 17:14:31","type":"2","endTime":"2018-02-10 00:00:00","initiator":"冀飞虎","theme":"我给毛硕发任务"},{"state":"0","taskId":"187","creationTime":"2018-01-25 17:22:07","type":"2","endTime":"2018-02-10 00:00:00","initiator":"冀飞虎","theme":"我给毛硕发任务2"},{"state":"0","taskId":"188","creationTime":"2018-01-25 17:22:07","type":"2","endTime":"2018-02-10 00:00:00","initiator":"冀飞虎","theme":"我给毛硕发任务2"},{"state":"0","taskId":"190","creationTime":"2018-01-25 18:19:11","type":"2","endTime":"2018-02-10 00:00:00","initiator":"冀飞虎","theme":"飞虎发给毛硕的第三个任务"},{"state":"1","taskId":"209","creationTime":"2018-01-25 20:52:56","type":"2","endTime":"2018-02-01 00:00:00","initiator":"冀飞虎","theme":"测试拖拽式表单任务"},{"state":"1","taskId":"210","creationTime":"2018-01-25 20:52:56","type":"2","endTime":"2018-02-01 00:00:00","initiator":"冀飞虎","theme":"测试拖拽式表单任务"}],"pageSize":10,"totalPage":2,"pageNum":1}
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
         * star : 0
         * dataList : [{"state":"1","taskId":"165","creationTime":"2018-01-24 00:00:00","type":"2","endTime":"2018-02-23 00:00:00","initiator":"毛硕","theme":"任务用于测试message"},{"state":"1","taskId":"186","creationTime":"2018-01-25 17:14:31","type":"2","endTime":"2018-02-10 00:00:00","initiator":"冀飞虎","theme":"我给毛硕发任务"},{"state":"0","taskId":"187","creationTime":"2018-01-25 17:22:07","type":"2","endTime":"2018-02-10 00:00:00","initiator":"冀飞虎","theme":"我给毛硕发任务2"},{"state":"0","taskId":"188","creationTime":"2018-01-25 17:22:07","type":"2","endTime":"2018-02-10 00:00:00","initiator":"冀飞虎","theme":"我给毛硕发任务2"},{"state":"0","taskId":"190","creationTime":"2018-01-25 18:19:11","type":"2","endTime":"2018-02-10 00:00:00","initiator":"冀飞虎","theme":"飞虎发给毛硕的第三个任务"},{"state":"1","taskId":"209","creationTime":"2018-01-25 20:52:56","type":"2","endTime":"2018-02-01 00:00:00","initiator":"冀飞虎","theme":"测试拖拽式表单任务"},{"state":"1","taskId":"210","creationTime":"2018-01-25 20:52:56","type":"2","endTime":"2018-02-01 00:00:00","initiator":"冀飞虎","theme":"测试拖拽式表单任务"}]
         * pageSize : 10
         * totalPage : 2
         * pageNum : 1
         */

        private List<DataListBean> dataList;

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * state : 1
             * taskId : 165
             * creationTime : 2018-01-24 00:00:00
             * type : 2
             * endTime : 2018-02-23 00:00:00
             * initiator : 毛硕
             * theme : 任务用于测试message
             */

            private String state;
            private String taskId;
            private String creationTime;
            private String type;
            private String endTime;
            private String initiator;
            private String theme;

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public String getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(String creationTime) {
                this.creationTime = creationTime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getInitiator() {
                return initiator;
            }

            public void setInitiator(String initiator) {
                this.initiator = initiator;
            }

            public String getTheme() {
                return theme;
            }

            public void setTheme(String theme) {
                this.theme = theme;
            }
        }
    }
}
