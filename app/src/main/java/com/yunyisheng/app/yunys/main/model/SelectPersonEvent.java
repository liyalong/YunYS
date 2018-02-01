package com.yunyisheng.app.yunys.main.model;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/1 15:34
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SelectPersonEvent {

    private List<WorkerBean> selectlist;

    public SelectPersonEvent(List<WorkerBean> selectlist) {
        this.selectlist=selectlist;
    }

    public List<WorkerBean> getSelectlist(){
        return selectlist;
    }
}
