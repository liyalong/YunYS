package com.yunyisheng.app.yunys.login.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * Created by liyalong on 2017/12/16.
 */

public class UserModel extends BaseModel {

    /**
     * respBody : {"userPhoneState":"1","phone":null,"userMailboxState":"0","userPassword":null,"parent":"谢智","enterpriseId":"0d67725d97b644489419e7f700fcfcb6","enterpriseRolesId":5,"userIsShow":true,"userPicture":null,"userNumber":523458,"userType":"1","userState":"1","userJobTitle":"安卓工程师","userMailbox":"13635706239@163.com","userId":308,"userSex":"男","userName":"Android","userPhone":"18610922052","rolesId":null,"userSetTime":1516480519000}
     */

    private RespBodyBean respBody;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * userPhoneState : 1
         * phone : null
         * userMailboxState : 0
         * userPassword : null
         * parent : 谢智
         * enterpriseId : 0d67725d97b644489419e7f700fcfcb6
         * enterpriseRolesId : 5
         * userIsShow : true
         * userPicture : null
         * userNumber : 523458
         * userType : 1
         * userState : 1
         * userJobTitle : 安卓工程师
         * userMailbox : 13635706239@163.com
         * userId : 308
         * userSex : 男
         * userName : Android
         * userPhone : 18610922052
         * rolesId : null
         * userSetTime : 1516480519000
         */

        private String userPhoneState;
        private String phone;
        private String userMailboxState;
        private String userPassword;
        private String parent;
        private String enterpriseId;
        private int enterpriseRolesId;
        private boolean userIsShow;
        private String userPicture;
        private int userNumber;
        private String userType;
        private String userState;
        private String userJobTitle;
        private String userMailbox;
        private int userId;
        private String userSex;
        private String userName;
        private String userPhone;
        private int rolesId;
        private long userSetTime;

        public String getUserPhoneState() {
            return userPhoneState;
        }

        public void setUserPhoneState(String userPhoneState) {
            this.userPhoneState = userPhoneState;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserMailboxState() {
            return userMailboxState;
        }

        public void setUserMailboxState(String userMailboxState) {
            this.userMailboxState = userMailboxState;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public int getEnterpriseRolesId() {
            return enterpriseRolesId;
        }

        public void setEnterpriseRolesId(int enterpriseRolesId) {
            this.enterpriseRolesId = enterpriseRolesId;
        }

        public boolean isUserIsShow() {
            return userIsShow;
        }

        public void setUserIsShow(boolean userIsShow) {
            this.userIsShow = userIsShow;
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

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserState() {
            return userState;
        }

        public void setUserState(String userState) {
            this.userState = userState;
        }

        public String getUserJobTitle() {
            return userJobTitle;
        }

        public void setUserJobTitle(String userJobTitle) {
            this.userJobTitle = userJobTitle;
        }

        public String getUserMailbox() {
            return userMailbox;
        }

        public void setUserMailbox(String userMailbox) {
            this.userMailbox = userMailbox;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public int getRolesId() {
            return rolesId;
        }

        public void setRolesId(int rolesId) {
            this.rolesId = rolesId;
        }

        public long getUserSetTime() {
            return userSetTime;
        }

        public void setUserSetTime(long userSetTime) {
            this.userSetTime = userSetTime;
        }

        @Override
        public String toString() {
            return "RespBodyBean{" +
                    "userPhoneState='" + userPhoneState + '\'' +
                    ", phone=" + phone +
                    ", userMailboxState='" + userMailboxState + '\'' +
                    ", userPassword=" + userPassword +
                    ", parent='" + parent + '\'' +
                    ", enterpriseId='" + enterpriseId + '\'' +
                    ", enterpriseRolesId=" + enterpriseRolesId +
                    ", userIsShow=" + userIsShow +
                    ", userPicture=" + userPicture +
                    ", userNumber=" + userNumber +
                    ", userType='" + userType + '\'' +
                    ", userState='" + userState + '\'' +
                    ", userJobTitle='" + userJobTitle + '\'' +
                    ", userMailbox='" + userMailbox + '\'' +
                    ", userId=" + userId +
                    ", userSex='" + userSex + '\'' +
                    ", userName='" + userName + '\'' +
                    ", userPhone='" + userPhone + '\'' +
                    ", rolesId=" + rolesId +
                    ", userSetTime=" + userSetTime +
                    '}';
        }
    }
}
