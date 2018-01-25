package com.yunyisheng.app.yunys.project.bean;

/**
 * Created by liyalong on 2018/1/22.
 */

public class DeviceBean{
    private String equipmentName;
    private int equipmentStat;
    private String equipmentCreate;
    private String equipmentId;
    private String equipmentCode;
    private int isWarning;
    private int bindPlcNum;

    public void setBindPlcNum(int bindPlcNum) {
        this.bindPlcNum = bindPlcNum;
    }

    public int getBindPlcNum() {

        return bindPlcNum;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setEquipmentStat(int equipmentStat) {
        this.equipmentStat = equipmentStat;
    }

    public void setEquipmentCreate(String equipmentCreate) {
        this.equipmentCreate = equipmentCreate;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public void setIsWarning(int isWarning) {
        this.isWarning = isWarning;
    }

    public String getEquipmentName() {

        return equipmentName;
    }

    public int getEquipmentStat() {
        return equipmentStat;
    }

    public String getEquipmentCreate() {
        return equipmentCreate;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public int getIsWarning() {
        return isWarning;
    }

    @Override
    public String toString() {
        return "DeviceBean{" +
                "equipmentName='" + equipmentName + '\'' +
                ", equipmentStat=" + equipmentStat +
                ", equipmentCreate='" + equipmentCreate + '\'' +
                ", equipmentId='" + equipmentId + '\'' +
                ", equipmentCode='" + equipmentCode + '\'' +
                ", isWarning=" + isWarning +
                ", bindPlcNum=" + bindPlcNum +
                '}';
    }
}
