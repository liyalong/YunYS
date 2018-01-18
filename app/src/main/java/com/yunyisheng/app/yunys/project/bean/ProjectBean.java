package com.yunyisheng.app.yunys.project.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyalong on 2018/1/17.
 */

public class ProjectBean {
    private String projectId;
    private String projectName;
    private String projectNum;
    private Integer projectCreate;
    private Integer projectUpdate;
    private Integer projectEndtime;
    private Integer projectStat;
    private Integer projectTypeId;
    private Integer companyId;
    private Integer terraceId;
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

    private HashMap<String,String> proType;



    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public Integer getProjectCreate() {
        return projectCreate;
    }

    public Integer getProjectUpdate() {
        return projectUpdate;
    }

    public Integer getProjectEndtime() {
        return projectEndtime;
    }

    public Integer getProjectStat() {
        return projectStat;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public Integer getTerraceId() {
        return terraceId;
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

    public void setProjectCreate(Integer projectCreate) {
        this.projectCreate = projectCreate;
    }

    public void setProjectUpdate(Integer projectUpdate) {
        this.projectUpdate = projectUpdate;
    }

    public void setProjectEndtime(Integer projectEndtime) {
        this.projectEndtime = projectEndtime;
    }

    public void setProjectStat(Integer projectStat) {
        this.projectStat = projectStat;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setTerraceId(Integer terraceId) {
        this.terraceId = terraceId;
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
}
