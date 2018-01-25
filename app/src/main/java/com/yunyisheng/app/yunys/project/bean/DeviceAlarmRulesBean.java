package com.yunyisheng.app.yunys.project.bean;

/**
 * Created by liyalong on 2018/1/24.
 */

public class DeviceAlarmRulesBean {
    private int equwarnId; //报警规则id
    private String equwarnName; //报警规则名称
    private int equwarnLevel; //报警等级
    private String equwarnVal; //阈值
    private String equwarnCompare; //表达式
    private String createt; //创建时间
    private int equwarnStat; //报警状态
    private String description; //描述

    public int getEquwarnId() {
        return equwarnId;
    }

    public String getEquwarnName() {
        return equwarnName;
    }

    public int getEquwarnLevel() {
        return equwarnLevel;
    }

    public String getEquwarnVal() {
        return equwarnVal;
    }

    public String getEquwarnCompare() {
        return equwarnCompare;
    }

    public String getCreatet() {
        return createt;
    }

    public int getEquwarnStat() {
        return equwarnStat;
    }

    public String getDescription() {
        return description;
    }

    public void setEquwarnId(int equwarnId) {
        this.equwarnId = equwarnId;
    }

    public void setEquwarnName(String equwarnName) {
        this.equwarnName = equwarnName;
    }

    public void setEquwarnLevel(int equwarnLevel) {
        this.equwarnLevel = equwarnLevel;
    }

    public void setEquwarnVal(String equwarnVal) {
        this.equwarnVal = equwarnVal;
    }

    public void setEquwarnCompare(String equwarnCompare) {
        this.equwarnCompare = equwarnCompare;
    }

    public void setCreatet(String createt) {
        this.createt = createt;
    }

    public void setEquwarnStat(int equwarnStat) {
        this.equwarnStat = equwarnStat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DeviceAlarmRulesBean{" +
                "equwarnId=" + equwarnId +
                ", equwarnName='" + equwarnName + '\'' +
                ", equwarnLevel=" + equwarnLevel +
                ", equwarnVal='" + equwarnVal + '\'' +
                ", equwarnCompare='" + equwarnCompare + '\'' +
                ", createt='" + createt + '\'' +
                ", equwarnStat=" + equwarnStat +
                ", description='" + description + '\'' +
                '}';
    }
}
