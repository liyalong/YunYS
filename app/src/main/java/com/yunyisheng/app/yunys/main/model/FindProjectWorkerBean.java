package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/27 21:12
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class FindProjectWorkerBean extends BaseModel {


    private List<RespBodyBean> respBody;

    public List<RespBodyBean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<RespBodyBean> respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * userId : 307
         * userName : 冀飞虎
         * userSex : 男
         * userJobTitle : 研发工程师
         * userPicture : PCM/null/e733032aeafb406199629a9b5ef9d127.jpg
         * userNumber : 523456
         * userPhone : 13294029164
         * phone : 4324324
         * userMailbox : 13294029164@163.com
         * userPassword : null
         * userPhoneState : 1
         * userMailboxState : 0
         * userState : 1
         * userSetTime : null
         * rolesId : 2
         * enterpriseId : 88d3c7fccd154c66861621c45ed4d75e
         * parent : null
         * userIsShow : true
         * enterpriseRolesId : 5
         * userType : 1
         * id : 23
         */

        private int userId;
        private String userName;
        private String userSex;
        private String userJobTitle;
        private String userPicture;
        private int userNumber;
        private String userPhone;
        private String phone;
        private String userMailbox;
        private Object userPassword;
        private String userPhoneState;
        private String userMailboxState;
        private String userState;
        private Object userSetTime;
        private int rolesId;
        private String enterpriseId;
        private Object parent;
        private boolean userIsShow;
        private int enterpriseRolesId;
        private String userType;
        private int id;
        private boolean ischeck;

        public boolean isIscheck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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

        public int getUserNumber() {
            return userNumber;
        }

        public void setUserNumber(int userNumber) {
            this.userNumber = userNumber;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserMailbox() {
            return userMailbox;
        }

        public void setUserMailbox(String userMailbox) {
            this.userMailbox = userMailbox;
        }

        public Object getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(Object userPassword) {
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

        public Object getUserSetTime() {
            return userSetTime;
        }

        public void setUserSetTime(Object userSetTime) {
            this.userSetTime = userSetTime;
        }

        public int getRolesId() {
            return rolesId;
        }

        public void setRolesId(int rolesId) {
            this.rolesId = rolesId;
        }

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }

        public boolean isUserIsShow() {
            return userIsShow;
        }

        public void setUserIsShow(boolean userIsShow) {
            this.userIsShow = userIsShow;
        }

        public int getEnterpriseRolesId() {
            return enterpriseRolesId;
        }

        public void setEnterpriseRolesId(int enterpriseRolesId) {
            this.enterpriseRolesId = enterpriseRolesId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
