package com.yunyisheng.app.yunys.main.model;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/24 18:11
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class WorkerListBean {

    private String groupname;
    private boolean ischeckgroup;
    private List<WorkerBean> workerBeanList;

    public boolean isIscheckgroup() {
        return ischeckgroup;
    }

    public void setIscheckgroup(boolean ischeckgroup) {
        this.ischeckgroup = ischeckgroup;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<WorkerBean> getWorkerBeanList() {
        return workerBeanList;
    }

    public void setWorkerBeanList(List<WorkerBean> workerBeanList) {
        this.workerBeanList = workerBeanList;
    }
}
