package com.yunyisheng.app.yunys.main.model;

/**
 * 作者：fuduo on 2018/2/4 19:27
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NoReadMessageEvent {

    private int size;

    public NoReadMessageEvent(int size) {
        this.size=size;
    }

    public int getSize(){
        return size;
    }

}
