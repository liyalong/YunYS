package com.yunyisheng.app.yunys.tasks.model;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/18 10:40
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class GroupBean {

    private String fankuiname;

    private String fankuitype;

    private String weniz;

    @Override
    public String toString() {
        return "GroupBean{" +
                "fankuiname='" + fankuiname + '\'' +
                ", fankuitype='" + fankuitype + '\'' +
                ", weniz='" + weniz + '\'' +
                ", childdata=" + childdata +
                '}';
    }

    public String getWeniz() {
        return weniz;
    }

    public void setWeniz(String weniz) {
        this.weniz = weniz;
    }

    private List<ChildBean> childdata;

    public String getFankuiname() {
        return fankuiname;
    }

    public void setFankuiname(String fankuiname) {
        this.fankuiname = fankuiname;
    }

    public String getFankuitype() {
        return fankuitype;
    }

    public void setFankuitype(String fankuitype) {
        this.fankuitype = fankuitype;
    }

    public List<ChildBean> getChilddata() {
        return childdata;
    }

    public void setChilddata(List<ChildBean> childdata) {
        this.childdata = childdata;
    }
}
