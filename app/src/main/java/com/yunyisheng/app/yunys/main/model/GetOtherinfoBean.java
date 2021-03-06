package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：fuduo on 2018/1/24 15:38
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class GetOtherinfoBean extends BaseModel implements Serializable {


    /**
     * respBody : {"reloName":"总经理","section":[{"sectionUpdatedTime":null,"sectionStatus":null,"sectionId":155,"principal":null,"enterpriseId":null,"isParent":null,"sectionName":"定理纪念室-研发一组","sectionSort":null,"sectionCreatedTime":null,"sectionParentid":null}],"enterpriseUser":{"userPhoneState":"1","phone":null,"userMailboxState":"0","userPassword":null,"parent":null,"enterpriseId":"88d3c7fccd154c66861621c45ed4d75e","consoleUsername":null,"enterpriseRolesId":1,"userIsShow":false,"userPicture":"fgsggfdgdsgf","userNumber":523460,"userType":"1","userState":"1","userJobTitle":"前端开发工程师","userMailbox":"18735424162@163.com","userId":304,"userSex":"男","userName":"原昊","userPhone":"17610469028","rolesId":null,"userSetTime":1517556887000}}
     */

    private RespBodyBean respBody;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean implements Serializable {
        /**
         * reloName : 总经理
         * section : [{"sectionUpdatedTime":null,"sectionStatus":null,"sectionId":155,"principal":null,"enterpriseId":null,"isParent":null,"sectionName":"定理纪念室-研发一组","sectionSort":null,"sectionCreatedTime":null,"sectionParentid":null}]
         * enterpriseUser : {"userPhoneState":"1","phone":null,"userMailboxState":"0","userPassword":null,"parent":null,"enterpriseId":"88d3c7fccd154c66861621c45ed4d75e","consoleUsername":null,"enterpriseRolesId":1,"userIsShow":false,"userPicture":"fgsggfdgdsgf","userNumber":523460,"userType":"1","userState":"1","userJobTitle":"前端开发工程师","userMailbox":"18735424162@163.com","userId":304,"userSex":"男","userName":"原昊","userPhone":"17610469028","rolesId":null,"userSetTime":1517556887000}
         */

        private String reloName;
        private EnterpriseUserBean enterpriseUser;
        private List<SectionBean> section;

        public String getReloName() {
            return reloName;
        }

        public void setReloName(String reloName) {
            this.reloName = reloName;
        }

        public EnterpriseUserBean getEnterpriseUser() {
            return enterpriseUser;
        }

        public void setEnterpriseUser(EnterpriseUserBean enterpriseUser) {
            this.enterpriseUser = enterpriseUser;
        }

        public List<SectionBean> getSection() {
            return section;
        }

        public void setSection(List<SectionBean> section) {
            this.section = section;
        }

        public static class EnterpriseUserBean implements Serializable {
            /**
             * userPhoneState : 1
             * phone : null
             * userMailboxState : 0
             * userPassword : null
             * parent : null
             * enterpriseId : 88d3c7fccd154c66861621c45ed4d75e
             * consoleUsername : null
             * enterpriseRolesId : 1
             * userIsShow : false
             * userPicture : fgsggfdgdsgf
             * userNumber : 523460
             * userType : 1
             * userState : 1
             * userJobTitle : 前端开发工程师
             * userMailbox : 18735424162@163.com
             * userId : 304
             * userSex : 男
             * userName : 原昊
             * userPhone : 17610469028
             * rolesId : null
             * userSetTime : 1517556887000
             */

            private String userPhoneState;
            private String phone;
            private String userMailboxState;
            private String userPassword;
            private String parent;
            private String enterpriseId;
            private String consoleUsername;
            private String enterpriseRolesId;
            private boolean userIsShow;
            private String userPicture;
            private String userNumber;
            private String userType;
            private String userState;
            private String userJobTitle;
            private String userMailbox;
            private int userId;
            private String userSex;
            private String userName;
            private String userPhone;
            private String rolesId;
            private String userSetTime;

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

            public String getConsoleUsername() {
                return consoleUsername;
            }

            public void setConsoleUsername(String consoleUsername) {
                this.consoleUsername = consoleUsername;
            }

            public String getEnterpriseRolesId() {
                return enterpriseRolesId;
            }

            public void setEnterpriseRolesId(String enterpriseRolesId) {
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

            public String getUserNumber() {
                return userNumber;
            }

            public void setUserNumber(String userNumber) {
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

            public String getRolesId() {
                return rolesId;
            }

            public void setRolesId(String rolesId) {
                this.rolesId = rolesId;
            }

            public String getUserSetTime() {
                return userSetTime;
            }

            public void setUserSetTime(String userSetTime) {
                this.userSetTime = userSetTime;
            }
        }

        public static class SectionBean implements Serializable {
            /**
             * sectionUpdatedTime : null
             * sectionStatus : null
             * sectionId : 155
             * principal : null
             * enterpriseId : null
             * isParent : null
             * sectionName : 定理纪念室-研发一组
             * sectionSort : null
             * sectionCreatedTime : null
             * sectionParentid : null
             */

            private String sectionUpdatedTime;
            private String sectionStatus;
            private int sectionId;
            private String principal;
            private String enterpriseId;
            private String isParent;
            private String sectionName;
            private String sectionSort;
            private String sectionCreatedTime;
            private String sectionParentid;

            public String getSectionUpdatedTime() {
                return sectionUpdatedTime;
            }

            public void setSectionUpdatedTime(String sectionUpdatedTime) {
                this.sectionUpdatedTime = sectionUpdatedTime;
            }

            public String getSectionStatus() {
                return sectionStatus;
            }

            public void setSectionStatus(String sectionStatus) {
                this.sectionStatus = sectionStatus;
            }

            public int getSectionId() {
                return sectionId;
            }

            public void setSectionId(int sectionId) {
                this.sectionId = sectionId;
            }

            public String getPrincipal() {
                return principal;
            }

            public void setPrincipal(String principal) {
                this.principal = principal;
            }

            public String getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(String enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public String getIsParent() {
                return isParent;
            }

            public void setIsParent(String isParent) {
                this.isParent = isParent;
            }

            public String getSectionName() {
                return sectionName;
            }

            public void setSectionName(String sectionName) {
                this.sectionName = sectionName;
            }

            public String getSectionSort() {
                return sectionSort;
            }

            public void setSectionSort(String sectionSort) {
                this.sectionSort = sectionSort;
            }

            public String getSectionCreatedTime() {
                return sectionCreatedTime;
            }

            public void setSectionCreatedTime(String sectionCreatedTime) {
                this.sectionCreatedTime = sectionCreatedTime;
            }

            public String getSectionParentid() {
                return sectionParentid;
            }

            public void setSectionParentid(String sectionParentid) {
                this.sectionParentid = sectionParentid;
            }
        }
    }
}
