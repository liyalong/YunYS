package com.yunyisheng.app.yunys.tasks.bean;

/**
 * 作者：fuduo on 2018/1/18 10:40
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ChildBean {

    private String dynamicTypeName;
    private int index;

    @Override
    public String toString() {
        return "{" +
                "dynamicTypeName='" + dynamicTypeName + '\'' +
                ", index=" + index +
                '}';
    }

    public String getDynamicTypeName() {
        return dynamicTypeName;
    }

    public void setDynamicTypeName(String dynamicTypeName) {
        this.dynamicTypeName = dynamicTypeName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
