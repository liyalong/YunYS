package com.yunyisheng.app.yunys.project.bean;

/**
 * Created by liyalong on 2018/1/25.
 */

public class DevicePartsBean {
    private int spareId;
    private String spareName;
    private String createt;
    private String spareModel;
    private int replaceCycle;
    private String replaceVal;

    public void setReplaceCycle(int replaceCycle) {
        this.replaceCycle = replaceCycle;
    }

    public int getReplaceCycle() {

        return replaceCycle;
    }

    public void setSpareId(int spareId) {
        this.spareId = spareId;
    }

    public void setSpareName(String spareName) {
        this.spareName = spareName;
    }

    public void setCreatet(String createt) {
        this.createt = createt;
    }

    public void setSpareModel(String spareModel) {
        this.spareModel = spareModel;
    }


    public void setReplaceVal(String replaceVal) {
        this.replaceVal = replaceVal;
    }

    public int getSpareId() {

        return spareId;
    }

    public String getSpareName() {
        return spareName;
    }

    public String getCreatet() {
        return createt;
    }

    public String getSpareModel() {
        return spareModel;
    }


    public String getReplaceVal() {
        return replaceVal;
    }
}
