package com.yunyisheng.app.yunys.tasks.bean;

/**
 * Created by liyalong on 2018/2/4.
 */

public class ProjectFormBean {
    private int id;
    private int typeId;
    private String applicationId;
    private String companyId;
    private String Uuid;
    private String baseName;
    private String Description;
    private String createTime;
    private String updateTime;
    private String Explanation;
    private String baseImage;
    private int isPrint;

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getUuid() {
        return Uuid;
    }

    public String getBaseName() {
        return baseName;
    }

    public String getDescription() {
        return Description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getExplanation() {
        return Explanation;
    }

    public String getBaseImage() {
        return baseImage;
    }

    public int getIsPrint() {
        return isPrint;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }

    public void setIsPrint(int isPrint) {
        this.isPrint = isPrint;
    }
}
