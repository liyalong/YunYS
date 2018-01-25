package com.yunyisheng.app.yunys.main.model;

/**
 * 作者：fuduo on 2018/1/24 17:55
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class WorkerBean {
    private String name;
    private int userId;
    private String icon;
    private String userPhone;
    private String sex;
    private String eamil;
    private boolean ischeckchild;

    public boolean isIscheckchild() {
        return ischeckchild;
    }

    public void setIscheckchild(boolean ischeckchild) {
        this.ischeckchild = ischeckchild;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
