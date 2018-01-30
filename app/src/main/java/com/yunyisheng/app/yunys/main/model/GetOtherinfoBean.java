package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.io.Serializable;

/**
 * 作者：fuduo on 2018/1/24 15:38
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class GetOtherinfoBean extends BaseModel implements Serializable {


    /**
     * respBody : {"userId":308,"userName":"Android","userSex":"男","userJobTitle":"男安卓工程师","userPicture":"user/308/2f9fdea8134240539bee4d79e1081259.png","userNumber":523458,"userPhone":"18610922052","phone":null,"userMailbox":"18610922052","userPassword":null,"userPhoneState":"1","userMailboxState":"0","userState":"1","userSetTime":1516715641000,"rolesId":null,"enterpriseId":"0d67725d97b644489419e7f700fcfcb6","parent":"谢智","userIsShow":true,"enterpriseRolesId":5,"userType":"1"}
     */

    private RespBodyBean respBody;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean implements Serializable{
        /**
         * userId : 308
         * userName : Android
         * userSex : 男
         * userJobTitle : 男安卓工程师
         * userPicture : user/308/2f9fdea8134240539bee4d79e1081259.png
         * userNumber : 523458
         * userPhone : 18610922052
         * phone : null
         * userMailbox : 18610922052
         * userPassword : null
         * userPhoneState : 1
         * userMailboxState : 0
         * userState : 1
         * userSetTime : 1516715641000
         * rolesId : null
         * enterpriseId : 0d67725d97b644489419e7f700fcfcb6
         * parent : 谢智
         * userIsShow : true
         * enterpriseRolesId : 5
         * userType : 1
         */

        private int userId;
        private String userName;
        private String userSex;
        private String userJobTitle;
        private String userPicture;
        private int userNumber;
        private String userPhone;
        private Object phone;
        private String userMailbox;
        private Object userPassword;
        private String userPhoneState;
        private String userMailboxState;
        private String userState;
        private long userSetTime;
        private Object rolesId;
        private String enterpriseId;
        private String parent;
        private boolean userIsShow;
        private int enterpriseRolesId;
        private String userType;

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

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
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

        public long getUserSetTime() {
            return userSetTime;
        }

        public void setUserSetTime(long userSetTime) {
            this.userSetTime = userSetTime;
        }

        public Object getRolesId() {
            return rolesId;
        }

        public void setRolesId(Object rolesId) {
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
    }
}
