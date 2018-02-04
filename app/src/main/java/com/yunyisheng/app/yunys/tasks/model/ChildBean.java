package com.yunyisheng.app.yunys.tasks.model;

/**
 * 作者：fuduo on 2018/1/18 10:40
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ChildBean {

    private String dynamic_type_name;
    private int index;

    @Override
    public String toString() {
        return "{" +
                "dynamic_type_name='" + dynamic_type_name + '\'' +
                ", index=" + index +
                '}';
    }

    public String getDynamic_type_name() {
        return dynamic_type_name;
    }

    public void setDynamic_type_name(String dynamic_type_name) {
        this.dynamic_type_name = dynamic_type_name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
