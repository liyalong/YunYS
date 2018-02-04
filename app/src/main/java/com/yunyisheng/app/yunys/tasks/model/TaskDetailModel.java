package com.yunyisheng.app.yunys.tasks.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.schedule.model.ScheduleDetailBean;
import com.yunyisheng.app.yunys.tasks.bean.FeedBackItemBean;
import com.yunyisheng.app.yunys.tasks.bean.TaskBackBean;
import com.yunyisheng.app.yunys.tasks.bean.TaskBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/31.
 */

public class TaskDetailModel extends BaseModel {
    private Resbody respBody;

    public void setRespBody(Resbody respBody) {
        this.respBody = respBody;
    }

    public Resbody getRespBody() {

        return respBody;
    }
    public class Resbody{
        private ScheduleDetailBean.RespBodyBean.TaskBean task;
        private List<ScheduleDetailBean.RespBodyBean.TaskBackBean> taskback;
        private ScheduleDetailBean.RespBodyBean.FormBean form;

        public void setTask(ScheduleDetailBean.RespBodyBean.TaskBean task) {
            this.task = task;
        }

        public void setTaskback(List<ScheduleDetailBean.RespBodyBean.TaskBackBean> taskback) {
            this.taskback = taskback;
        }

        public void setForm(ScheduleDetailBean.RespBodyBean.FormBean form) {
            this.form = form;
        }

        public ScheduleDetailBean.RespBodyBean.TaskBean getTask() {

            return task;
        }

        public List<ScheduleDetailBean.RespBodyBean.TaskBackBean> getTaskback() {
            return taskback;
        }

        public ScheduleDetailBean.RespBodyBean.FormBean getForm() {
            return form;
        }
    }
}
