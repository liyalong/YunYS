package com.yunyisheng.app.yunys.project.bean;

/**
 * Created by liyalong on 2018/1/22.
 */

public class ModelInfoBean {
    private String pcmId;
    private String pcmName;
    private int pcmStat;
    private String pcmBlueprint;
    private String pcmCreate;
    private String pcmRemark;
    private String projectId;
    private String pcmUnicode;
    private int pcmIsWarning;
    private String pcmWarningLevel;
    private String pcmBindnum;
    private String companyId;
    private String equs;

    public String getPcmId() {
        return pcmId;
    }

    public String getPcmName() {
        return pcmName;
    }

    public int getPcmStat() {
        return pcmStat;
    }

    public String getPcmBlueprint() {
        return pcmBlueprint;
    }

    public String getPcmCreate() {
        return pcmCreate;
    }

    public String getPcmRemark() {
        return pcmRemark;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getPcmUnicode() {
        return pcmUnicode;
    }

    public int getPcmIsWarning() {
        return pcmIsWarning;
    }

    public String getPcmWarningLevel() {
        return pcmWarningLevel;
    }

    public String getPcmBindnum() {
        return pcmBindnum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getEqus() {
        return equs;
    }

    public void setPcmId(String pcmId) {
        this.pcmId = pcmId;
    }

    public void setPcmName(String pcmName) {
        this.pcmName = pcmName;
    }

    public void setPcmStat(int pcmStat) {
        this.pcmStat = pcmStat;
    }

    public void setPcmBlueprint(String pcmBlueprint) {
        this.pcmBlueprint = pcmBlueprint;
    }

    public void setPcmCreate(String pcmCreate) {
        this.pcmCreate = pcmCreate;
    }

    public void setPcmRemark(String pcmRemark) {
        this.pcmRemark = pcmRemark;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setPcmUnicode(String pcmUnicode) {
        this.pcmUnicode = pcmUnicode;
    }

    public void setPcmIsWarning(int pcmIsWarning) {
        this.pcmIsWarning = pcmIsWarning;
    }

    public void setPcmWarningLevel(String pcmWarningLevel) {
        this.pcmWarningLevel = pcmWarningLevel;
    }

    public void setPcmBindnum(String pcmBindnum) {
        this.pcmBindnum = pcmBindnum;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setEqus(String equs) {
        this.equs = equs;
    }

    @Override
    public String toString() {
        return "ModelInfoBean{" +
                "pcmId=" + pcmId +
                ", pcmName='" + pcmName + '\'' +
                ", pcmStat=" + pcmStat +
                ", pcmBlueprint='" + pcmBlueprint + '\'' +
                ", pcmCreate='" + pcmCreate + '\'' +
                ", pcmRemark='" + pcmRemark + '\'' +
                ", projectId='" + projectId + '\'' +
                ", pcmUnicode='" + pcmUnicode + '\'' +
                ", pcmIsWarning='" + pcmIsWarning + '\'' +
                ", pcmWarningLevel='" + pcmWarningLevel + '\'' +
                ", pcmBindnum=" + pcmBindnum +
                ", companyId=" + companyId +
                ", equs='" + equs + '\'' +
                '}';
    }
}
