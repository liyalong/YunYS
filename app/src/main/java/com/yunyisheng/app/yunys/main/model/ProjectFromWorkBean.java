package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/26 20:32
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProjectFromWorkBean extends BaseModel {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * projectId : c49558c1a2b541dd9915c52ef64a0f25
         * projectName : 飞虎的项目002
         * projectNum : feihu002
         * projectCreate : 2018-01-16 14:16:02
         * projectUpdate : 2018-01-18 14:39:57
         * projectEndtime : null
         * projectStat : 2
         * projectTypeId : 16
         * companyId : 88d3c7fccd154c66861621c45ed4d75e
         * terraceId : 706311a4101d4d409c264b451c8d4672
         * projectNationName : null
         * projectProvinceName : 北京
         * projectCityName : 北京
         * projectCountyName : 东城区
         * projectAddress : 飞虎家
         * projectRemarks : 飞虎的项目
         * projectFrequency : 6
         * projectStopIndicators : 6
         * isFocus : true
         * proType : {"projectTypeId":16,"projectTypeName":"项目类型2--158","projectTypeStat":null,"companyId":null,"terraceId":null,"createt":null,"updatet":null,"remark":null}
         * unSeeTaskNum : null
         * warningNum : null
         * userList : [{"userId":307,"userName":"冀飞虎","userSex":"男","userJobTitle":"研发工程师","userPicture":null,"userNumber":523456,"userPhone":"13294029164","phone":null,"userMailbox":"13294029164@163.com","userPassword":null,"userPhoneState":"1","userMailboxState":"0","userState":"1","userSetTime":null,"rolesId":null,"enterpriseId":"88d3c7fccd154c66861621c45ed4d75e","parent":null,"userIsShow":true,"enterpriseRolesId":5,"userType":"1","id":null},{"userId":303,"userName":"李亚龙","userSex":"男","userJobTitle":"Android开发工程师","userPicture":null,"userNumber":null,"userPhone":"13681587661","phone":null,"userMailbox":"13681587661@163.com","userPassword":null,"userPhoneState":"1","userMailboxState":"0","userState":"1","userSetTime":null,"rolesId":null,"enterpriseId":"88d3c7fccd154c66861621c45ed4d75e","parent":null,"userIsShow":true,"enterpriseRolesId":1,"userType":"1","id":null},{"userId":312,"userName":"毛硕","userSex":"男","userJobTitle":"java开发工程师","userPicture":"http://img4.imgtn.bdimg.com/it/u=2769473464,745456230&fm=214&gp=0.jpg","userNumber":null,"userPhone":"17600216923","phone":null,"userMailbox":"17600216923@163.com","userPassword":null,"userPhoneState":"1","userMailboxState":"0","userState":"1","userSetTime":null,"rolesId":null,"enterpriseId":"88d3c7fccd154c66861621c45ed4d75e","parent":null,"userIsShow":true,"enterpriseRolesId":1,"userType":"1","id":null}]
         * projectLeader : 冀飞虎
         */

        private String projectId;
        private String projectName;
        private String projectNum;
        private String projectCreate;
        private String projectUpdate;
        private String projectEndtime;
        private int projectStat;
        private int projectTypeId;
        private String companyId;
        private String terraceId;
        private String projectNationName;
        private String projectProvinceName;
        private String projectCityName;
        private String projectCountyName;
        private String projectAddress;
        private String projectRemarks;
        private int projectFrequency;
        private int projectStopIndicators;
        private boolean isFocus;
        private ProTypeBean proType;
        private String unSeeTaskNum;
        private String warningNum;
        private String projectLeader;
        private boolean ischeckgroup;
        private List<UserListBean> userList;

        public boolean isFocus() {
            return isFocus;
        }

        public void setFocus(boolean focus) {
            isFocus = focus;
        }

        public boolean isIscheckgroup() {
            return ischeckgroup;
        }

        public void setIscheckgroup(boolean ischeckgroup) {
            this.ischeckgroup = ischeckgroup;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectNum() {
            return projectNum;
        }

        public void setProjectNum(String projectNum) {
            this.projectNum = projectNum;
        }

        public String getProjectCreate() {
            return projectCreate;
        }

        public void setProjectCreate(String projectCreate) {
            this.projectCreate = projectCreate;
        }

        public String getProjectUpdate() {
            return projectUpdate;
        }

        public void setProjectUpdate(String projectUpdate) {
            this.projectUpdate = projectUpdate;
        }

        public String getProjectEndtime() {
            return projectEndtime;
        }

        public void setProjectEndtime(String projectEndtime) {
            this.projectEndtime = projectEndtime;
        }

        public int getProjectStat() {
            return projectStat;
        }

        public void setProjectStat(int projectStat) {
            this.projectStat = projectStat;
        }

        public int getProjectTypeId() {
            return projectTypeId;
        }

        public void setProjectTypeId(int projectTypeId) {
            this.projectTypeId = projectTypeId;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getTerraceId() {
            return terraceId;
        }

        public void setTerraceId(String terraceId) {
            this.terraceId = terraceId;
        }

        public String getProjectNationName() {
            return projectNationName;
        }

        public void setProjectNationName(String projectNationName) {
            this.projectNationName = projectNationName;
        }

        public String getProjectProvinceName() {
            return projectProvinceName;
        }

        public void setProjectProvinceName(String projectProvinceName) {
            this.projectProvinceName = projectProvinceName;
        }

        public String getProjectCityName() {
            return projectCityName;
        }

        public void setProjectCityName(String projectCityName) {
            this.projectCityName = projectCityName;
        }

        public String getProjectCountyName() {
            return projectCountyName;
        }

        public void setProjectCountyName(String projectCountyName) {
            this.projectCountyName = projectCountyName;
        }

        public String getProjectAddress() {
            return projectAddress;
        }

        public void setProjectAddress(String projectAddress) {
            this.projectAddress = projectAddress;
        }

        public String getProjectRemarks() {
            return projectRemarks;
        }

        public void setProjectRemarks(String projectRemarks) {
            this.projectRemarks = projectRemarks;
        }

        public int getProjectFrequency() {
            return projectFrequency;
        }

        public void setProjectFrequency(int projectFrequency) {
            this.projectFrequency = projectFrequency;
        }

        public int getProjectStopIndicators() {
            return projectStopIndicators;
        }

        public void setProjectStopIndicators(int projectStopIndicators) {
            this.projectStopIndicators = projectStopIndicators;
        }

        public boolean isIsFocus() {
            return isFocus;
        }

        public void setIsFocus(boolean isFocus) {
            this.isFocus = isFocus;
        }

        public ProTypeBean getProType() {
            return proType;
        }

        public void setProType(ProTypeBean proType) {
            this.proType = proType;
        }

        public String getUnSeeTaskNum() {
            return unSeeTaskNum;
        }

        public void setUnSeeTaskNum(String unSeeTaskNum) {
            this.unSeeTaskNum = unSeeTaskNum;
        }

        public String getWarningNum() {
            return warningNum;
        }

        public void setWarningNum(String warningNum) {
            this.warningNum = warningNum;
        }

        public String getProjectLeader() {
            return projectLeader;
        }

        public void setProjectLeader(String projectLeader) {
            this.projectLeader = projectLeader;
        }

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class ProTypeBean {
            /**
             * projectTypeId : 16
             * projectTypeName : 项目类型2--158
             * projectTypeStat : null
             * companyId : null
             * terraceId : null
             * createt : null
             * updatet : null
             * remark : null
             */

            private int projectTypeId;
            private String projectTypeName;
            private Object projectTypeStat;
            private Object companyId;
            private Object terraceId;
            private Object createt;
            private Object updatet;
            private Object remark;

            public int getProjectTypeId() {
                return projectTypeId;
            }

            public void setProjectTypeId(int projectTypeId) {
                this.projectTypeId = projectTypeId;
            }

            public String getProjectTypeName() {
                return projectTypeName;
            }

            public void setProjectTypeName(String projectTypeName) {
                this.projectTypeName = projectTypeName;
            }

            public Object getProjectTypeStat() {
                return projectTypeStat;
            }

            public void setProjectTypeStat(Object projectTypeStat) {
                this.projectTypeStat = projectTypeStat;
            }

            public Object getCompanyId() {
                return companyId;
            }

            public void setCompanyId(Object companyId) {
                this.companyId = companyId;
            }

            public Object getTerraceId() {
                return terraceId;
            }

            public void setTerraceId(Object terraceId) {
                this.terraceId = terraceId;
            }

            public Object getCreatet() {
                return createt;
            }

            public void setCreatet(Object createt) {
                this.createt = createt;
            }

            public Object getUpdatet() {
                return updatet;
            }

            public void setUpdatet(Object updatet) {
                this.updatet = updatet;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }
        }

        public static class UserListBean {
            /**
             * userId : 307
             * userName : 冀飞虎
             * userSex : 男
             * userJobTitle : 研发工程师
             * userPicture : null
             * userNumber : 523456
             * userPhone : 13294029164
             * phone : null
             * userMailbox : 13294029164@163.com
             * userPassword : null
             * userPhoneState : 1
             * userMailboxState : 0
             * userState : 1
             * userSetTime : null
             * rolesId : null
             * enterpriseId : 88d3c7fccd154c66861621c45ed4d75e
             * parent : null
             * userIsShow : true
             * enterpriseRolesId : 5
             * userType : 1
             * id : null
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
            private String userPassword;
            private String userPhoneState;
            private String userMailboxState;
            private String userState;
            private String userSetTime;
            private int rolesId;
            private String enterpriseId;
            private String parent;
            private boolean userIsShow;
            private int enterpriseRolesId;
            private String userType;
            private int id;
            private boolean ischeckchild;

            public boolean isIscheckchild() {
                return ischeckchild;
            }

            public void setIscheckchild(boolean ischeckchild) {
                this.ischeckchild = ischeckchild;
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

            public String getUserSetTime() {
                return userSetTime;
            }

            public void setUserSetTime(String userSetTime) {
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
