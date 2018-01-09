package com.yunyisheng.app.yunys.login.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.Date;

/**
 * Created by liyalong on 2017/12/16.
 */

public class UserModel extends BaseModel {
    private Integer userId;
    private String userName;
    private String userSex;
    private String userJobTitle;
    private String userPicture;
    private String userPhone;
    private Integer userNumber;
    private String userMailbox;
    private String userPassword;
    private String userPhoneState;
    private String userMailboxState;
    private String userState;
    private Date userSetTime;
    private Integer rolesId;
    private String enterpriseId;
    private String parent;
    private Integer userIsShow;
    private Integer enterpriseRolesId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserJobTitle() {
        return userJobTitle;
    }

    public void setUserJobTitle(String userJobTitle) {
        this.userJobTitle = userJobTitle;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserMailbox() {
        return userMailbox;
    }

    public void setUserMailbox(String userMailbox) {
        this.userMailbox = userMailbox;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoneState() {
        return userPhoneState;
    }

    public void setUserPhoneState(String userPhoneState) {
        this.userPhoneState = userPhoneState;
    }

    public String getUserMailboxState() {
        return userMailboxState;
    }

    public void setUserMailboxState(String userMailboxState) {
        this.userMailboxState = userMailboxState;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public Date getUserSetTime() {
        return userSetTime;
    }

    public void setUserSetTime(Date userSetTime) {
        this.userSetTime = userSetTime;
    }

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getUserIsShow() {
        return userIsShow;
    }

    public void setUserIsShow(Integer userIsShow) {
        this.userIsShow = userIsShow;
    }

    public Integer getEnterpriseRolesId() {
        return enterpriseRolesId;
    }

    public void setEnterpriseRolesId(Integer enterpriseRolesId) {
        this.enterpriseRolesId = enterpriseRolesId;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userJobTitle='" + userJobTitle + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userNumber=" + userNumber +
                ", userMailbox='" + userMailbox + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhoneState='" + userPhoneState + '\'' +
                ", userMailboxState='" + userMailboxState + '\'' +
                ", userState='" + userState + '\'' +
                ", userSetTime=" + userSetTime +
                ", rolesId=" + rolesId +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", parent='" + parent + '\'' +
                ", userIsShow=" + userIsShow +
                ", enterpriseRolesId=" + enterpriseRolesId +
                '}';
    }
}
