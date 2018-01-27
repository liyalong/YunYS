package com.yunyisheng.app.yunys.project.bean;

/**
 * Created by liyalong on 2018/1/26.
 */

public class ModelAlarmRulesBean {
    private String pcmwarnId;
    private String pcmwarnName;
    private int pcmwarnLevel;
    private String compare;
    private String pcmId;
    private int pcmwarnStat;
    private String createt;
    private String remark;

    public void setPcmwarnId(String pcmwarnId) {
        this.pcmwarnId = pcmwarnId;
    }

    public void setPcmwarnName(String pcmwarnName) {
        this.pcmwarnName = pcmwarnName;
    }

    public void setPcmwarnLevel(int pcmwarnLevel) {
        this.pcmwarnLevel = pcmwarnLevel;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public void setPcmId(String pcmId) {
        this.pcmId = pcmId;
    }

    public void setPcmwarnStat(int pcmwarnStat) {
        this.pcmwarnStat = pcmwarnStat;
    }

    public void setCreatet(String createt) {
        this.createt = createt;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPcmwarnId() {

        return pcmwarnId;
    }

    public String getPcmwarnName() {
        return pcmwarnName;
    }

    public int getPcmwarnLevel() {
        return pcmwarnLevel;
    }

    public String getCompare() {
        return compare;
    }

    public String getPcmId() {
        return pcmId;
    }

    public int getPcmwarnStat() {
        return pcmwarnStat;
    }

    public String getCreatet() {
        return createt;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return "ModelAlarmRulesBean{" +
                "pcmwarnId='" + pcmwarnId + '\'' +
                ", pcmwarnName='" + pcmwarnName + '\'' +
                ", pcmwarnLevel=" + pcmwarnLevel +
                ", compare='" + compare + '\'' +
                ", pcmId='" + pcmId + '\'' +
                ", pcmwarnStat=" + pcmwarnStat +
                ", createt='" + createt + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
