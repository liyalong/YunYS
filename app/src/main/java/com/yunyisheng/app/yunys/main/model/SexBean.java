package com.yunyisheng.app.yunys.main.model;

/**
 * 作者：fuduo on 2018/1/24 11:59
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SexBean {

    private int sexnum;

    private String sexname;

    public SexBean(int sexnum,String sexname) {
        this.sexnum=sexnum;
        this.sexname=sexname;
    }

    public int getSexnum() {
        return sexnum;
    }

    public void setSexnum(int sexnum) {
        this.sexnum = sexnum;
    }

    public String getSexname() {
        return sexname;
    }

    public void setSexname(String sexname) {
        this.sexname = sexname;
    }
}
