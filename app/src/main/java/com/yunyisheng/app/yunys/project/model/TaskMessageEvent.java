package com.yunyisheng.app.yunys.project.model;

/**
 * 作者：fuduo on 2018/1/30 18:15
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class TaskMessageEvent {

    private String position;

    public TaskMessageEvent(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
