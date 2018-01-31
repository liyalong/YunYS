package com.yunyisheng.app.yunys.project.bean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/24.
 */

public class KnowledgeBean {
    private int knowledgeId;
    private String knowledgeName;
    private String knowledgeBase;
    private String knowledgeExtend;
    private int knowledgeTypeId;
    private String createt;
    private List<FileItem> files;

    public void setFiles(List<FileItem> files) {
        this.files = files;
    }

    public List<FileItem> getFiles() {

        return files;
    }

    public String getCreatet() {
        return createt;
    }

    public void setCreatet(String createt) {

        this.createt = createt;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public void setKnowledgeId(int knowledgeId) {

        this.knowledgeId = knowledgeId;
    }

    public int getKnowledgeId() {

        return knowledgeId;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeBase(String knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public void setKnowledgeExtend(String knowledgeExtend) {
        this.knowledgeExtend = knowledgeExtend;
    }

    public void setKnowledgeTypeId(int knowledgeTypeId) {
        this.knowledgeTypeId = knowledgeTypeId;
    }

    public String getKnowledgeBase() {

        return knowledgeBase;
    }

    public String getKnowledgeExtend() {
        return knowledgeExtend;
    }

    public int getKnowledgeTypeId() {
        return knowledgeTypeId;
    }
}
