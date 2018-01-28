package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/27 14:04
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class FindWorkerBean extends BaseModel {

    private List<respBodyBean> respBody;

    public List<respBodyBean> getRespBodyBeanList() {
        return respBody;
    }

    public void setRespBodyBeanList(List<respBodyBean> respBodyBeanList) {
        this.respBody = respBodyBeanList;
    }

    public static class respBodyBean{
        /**
         * id : user302
         * userId : 302
         * children : false
         * text : 张鹏辉
         * userSex : 男
         * userJobTitle : null
         * icon : http://img01.sogoucdn.com/net/a/04/link?appid=100520145&url=http://img01.store.sogou.com/app/a/10010016/bb637c3e58cacb0d4cdf1f3d774b8491
         * userNumber : 344234242343
         * userPhone : 15313882985
         * userMailbox : zhangpenghui@163.com
         * userPhoneState : 1
         * userMailboxState : 0
         * userState : 1
         * userSetTime : 1515527444000
         * type : user
         * enterpriseId : 0d67725d97b644489419e7f700fcfcb6
         * parentName : 谢智
         * userIsShow : true
         * enterpriseRolesId : 1
         * userType : 1
         */

        private String id;
        private int userId;
        private boolean children;
        private String text;
        private String userSex;
        private String userJobTitle;
        private String icon;
        private String userNumber;
        private String userPhone;
        private String userMailbox;
        private String userPhoneState;
        private String userMailboxState;
        private String userState;
        private long userSetTime;
        private String type;
        private String enterpriseId;
        private String parentName;
        private boolean userIsShow;
        private int enterpriseRolesId;
        private String userType;
        private boolean ischeck;

        public boolean isIscheck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public boolean isChildren() {
            return children;
        }

        public void setChildren(boolean children) {
            this.children = children;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUserNumber() {
            return userNumber;
        }

        public void setUserNumber(String userNumber) {
            this.userNumber = userNumber;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserMailbox() {
            return userMailbox;
        }

        public void setUserMailbox(String userMailbox) {
            this.userMailbox = userMailbox;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
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
