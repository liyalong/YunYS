package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.tasks.bean.TaskBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/29.
 */

public class TaskListModel extends BaseModel {
    private List<TaskBean> respBody;

    public void setRespBody(List<TaskBean> respBody) {
        this.respBody = respBody;
    }

    public List<TaskBean> getRespBody() {

        return respBody;
    }

    @Override
    public String toString() {
        return "TaskListModel{" +
                "respBody=" + respBody +
                '}';
    }
}
