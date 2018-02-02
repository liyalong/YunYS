package com.yunyisheng.app.yunys.project.bean;

/**
 * Created by liyalong on 2018/1/17.
 */

public class ProjectBean {
    private String projectId;
    private String projectName;
    private String projectNum;
    private String projectCreate;
    private String projectUpdate;
    private String projectEndtime;
    private Integer projectStat;
    private Integer projectTypeId;
    private String companyId;
    private String terraceId;
    private String projectNationName;
    private String projectProvinceName;
    private String projectCityName;
    private String projectCountyName;
    private String projectAddress;
    private String projectRemarks;
    private String projectFrequency;
    private String projectStopIndicators;
    private String isFocus;
    private String projectLeader;
    private ProType proType;
    private Integer unSeeTaskNum;

    public void setUnSeeTaskNum(Integer unSeeTaskNum) {
        this.unSeeTaskNum = unSeeTaskNum;
    }

    public void setWaringNum(Integer waringNum) {
        this.waringNum = waringNum;
    }

    public Integer getUnSeeTaskNum() {

        return unSeeTaskNum;
    }

    public Integer getWaringNum() {
        return waringNum;
    }

    private Integer waringNum;
    public void setProType(ProType proType) {
        this.proType = proType;
    }

    public ProType getProType() {

        return proType;
    }
    public void setProjectCreate(String projectCreate) {
        this.projectCreate = projectCreate;
    }

    public String getProjectCreate() {

        return projectCreate;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectNum() {
        return projectNum;
    }


    public void setProjectUpdate(String projectUpdate) {
        this.projectUpdate = projectUpdate;
    }

    public String getProjectUpdate() {

        return projectUpdate;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setTerraceId(String terraceId) {
        this.terraceId = terraceId;
    }

    public String getCompanyId() {

        return companyId;
    }

    public String getTerraceId() {
        return terraceId;
    }

    public void setProjectEndtime(String projectEndtime) {
        this.projectEndtime = projectEndtime;
    }

    public String getProjectEndtime() {

        return projectEndtime;
    }

    public Integer getProjectStat() {
        return projectStat;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }



    public String getProjectNationName() {
        return projectNationName;
    }

    public String getProjectProvinceName() {
        return projectProvinceName;
    }

    public String getProjectCityName() {
        return projectCityName;
    }

    public String getProjectCountyName() {
        return projectCountyName;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public String getProjectRemarks() {
        return projectRemarks;
    }

    public String getProjectFrequency() {
        return projectFrequency;
    }

    public String getProjectStopIndicators() {
        return projectStopIndicators;
    }

    public String getIsFocus() {
        return isFocus;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public void setProjectStat(Integer projectStat) {
        this.projectStat = projectStat;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }



    public void setProjectNationName(String projectNationName) {
        this.projectNationName = projectNationName;
    }

    public void setProjectProvinceName(String projectProvinceName) {
        this.projectProvinceName = projectProvinceName;
    }

    public void setProjectCityName(String projectCityName) {
        this.projectCityName = projectCityName;
    }

    public void setProjectCountyName(String projectCountyName) {
        this.projectCountyName = projectCountyName;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public void setProjectRemarks(String projectRemarks) {
        this.projectRemarks = projectRemarks;
    }

    public void setProjectFrequency(String projectFrequency) {
        this.projectFrequency = projectFrequency;
    }

    public void setProjectStopIndicators(String projectStopIndicators) {
        this.projectStopIndicators = projectStopIndicators;
    }

    public void setIsFocus(String isFocus) {
        this.isFocus = isFocus;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    @Override
    public String toString() {
        return "ProjectBean{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectNum='" + projectNum + '\'' +
                ", projectCreate=" + projectCreate +
                ", projectUpdate=" + projectUpdate +
                ", projectEndtime=" + projectEndtime +
                ", projectStat=" + projectStat +
                ", projectTypeId=" + projectTypeId +
                ", companyId=" + companyId +
                ", terraceId=" + terraceId +
                ", projectNationName='" + projectNationName + '\'' +
                ", projectProvinceName='" + projectProvinceName + '\'' +
                ", projectCityName='" + projectCityName + '\'' +
                ", projectCountyName='" + projectCountyName + '\'' +
                ", projectAddress='" + projectAddress + '\'' +
                ", projectRemarks='" + projectRemarks + '\'' +
                ", projectFrequency='" + projectFrequency + '\'' +
                ", projectStopIndicators='" + projectStopIndicators + '\'' +
                ", isFocus='" + isFocus + '\'' +
                ", projectLeader='" + projectLeader + '\'' +
                '}';
    }
    public class ProType{
        private int projectTypeId;
        private String projectTypeName;
        private int projectTypeStat;

        public void setProjectTypeId(int projectTypeId) {
            this.projectTypeId = projectTypeId;
        }

        public void setProjectTypeName(String projectTypeName) {
            this.projectTypeName = projectTypeName;
        }

        public void setProjectTypeStat(int projectTypeStat) {
            this.projectTypeStat = projectTypeStat;
        }

        public int getProjectTypeId() {

            return projectTypeId;
        }

        public String getProjectTypeName() {
            return projectTypeName;
        }

        public int getProjectTypeStat() {
            return projectTypeStat;
        }
    }
}
