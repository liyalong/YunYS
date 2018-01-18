package com.yunyisheng.app.yunys.tasks.model;

/**
 * 作者：fuduo on 2018/1/18 10:40
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ChildBean {

    private String fankuiitem;

    @Override
    public String toString() {
        return "ChildBean{" +
                "fankuiitem='" + fankuiitem + '\'' +
                '}';
    }

    public String getFankuiitem() {
        return fankuiitem;
    }

    public void setFankuiitem(String fankuiitem) {
        this.fankuiitem = fankuiitem;
    }
}
