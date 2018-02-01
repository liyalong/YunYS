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
        private TaskBean task;
        private List<TaskBackBean> taskback;
        private ScheduleDetailBean.RespBodyBean.FormBean form;

        public void setForm(ScheduleDetailBean.RespBodyBean.FormBean form) {
            this.form = form;
        }

        public ScheduleDetailBean.RespBodyBean.FormBean getForm() {

            return form;
        }



        public void setTask(TaskBean task) {
            this.task = task;
        }


        public void setFeedbackItem(FeedBackItemBean feedbackItem) {
            this.feedbackItem = feedbackItem;
        }

        public TaskBean getTask() {

            return task;
        }

        public List<TaskBackBean> getTaskback() {
            return taskback;
        }

        public void setTaskback(List<TaskBackBean> taskback) {

            this.taskback = taskback;
        }

        public FeedBackItemBean getFeedbackItem() {
            return feedbackItem;
        }

        private FeedBackItemBean feedbackItem;

        @Override
        public String toString() {
            return "Resbody{" +
                    "task=" + task +
                    ", taskback=" + taskback +
                    ", feedbackItem=" + feedbackItem +
                    '}';
        }
    }
}
