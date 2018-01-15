package com.yunyisheng.app.yunys.main.model;

/**
 * 作者：fuduo on 2018/1/15 12:21
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SpinnerBean {

    private String sptype;
    private int sptypesize;

    public SpinnerBean(String sptype,int sptypesize) {
        this.sptype=sptype;
        this.sptypesize=sptypesize;
    }

    public String getSptype() {
        return sptype;
    }

    public void setSptype(String sptype) {
        this.sptype = sptype;
    }

    public int getSptypesize() {
        return sptypesize;
    }

    public void setSptypesize(int sptypesize) {
        this.sptypesize = sptypesize;
    }
}
