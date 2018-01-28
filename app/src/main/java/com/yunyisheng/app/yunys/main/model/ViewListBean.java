package com.yunyisheng.app.yunys.main.model;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/28 17:14
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ViewListBean {

    private List<WorkerBean> list;

    public ViewListBean(List<WorkerBean> list) {
        this.list=list;
    }

    public List<WorkerBean> getList(){
        return list;
    }
}
