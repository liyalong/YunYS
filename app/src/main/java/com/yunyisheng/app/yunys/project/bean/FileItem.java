package com.yunyisheng.app.yunys.project.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liyalong on 2018/1/31.
 */

public class FileItem {
    private String fileId;
    private String fname;
    private String type;
    private String createt;
    private String path;
    private String md5code;
    private String knowledgeId;
    private String companyId;
    private String terraceId;

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreatet(String createt) {
        this.createt = createt;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMd5code(String md5code) {
        this.md5code = md5code;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setTerraceId(String terraceId) {
        this.terraceId = terraceId;
    }

    public String getFileId() {

        return fileId;
    }

    public String getFname() {
        return fname;
    }

    public String getType() {
        return type;
    }

    public String getCreatet() {
        return createt;
    }

    public String getPath() {
        return path;
    }

    public String getMd5code() {
        return md5code;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getTerraceId() {
        return terraceId;
    }

    @Override
    public String toString() {
        return "FileItem{" +
                "fileId='" + fileId + '\'' +
                ", fname='" + fname + '\'' +
                ", type='" + type + '\'' +
                ", createt='" + createt + '\'' +
                ", path='" + path + '\'' +
                ", md5code='" + md5code + '\'' +
                ", knowledgeId='" + knowledgeId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", terraceId='" + terraceId + '\'' +
                '}';
    }
}
