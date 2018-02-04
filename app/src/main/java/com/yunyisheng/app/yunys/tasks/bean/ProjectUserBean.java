package com.yunyisheng.app.yunys.tasks.bean;

import java.io.Serializable;

/**
 * Created by liyalong on 2018/2/2.
 */

public class ProjectUserBean implements Serializable {
    private int userId;
    private String userName;
    private String userSex;
    private boolean isCheck;

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isCheck() {

        return isCheck;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserId() {

        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSex() {
        return userSex;
    }

    @Override
    public String toString() {
        return "ProjectUserBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
