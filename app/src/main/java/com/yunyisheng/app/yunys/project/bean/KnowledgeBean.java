package com.yunyisheng.app.yunys.project.bean;

/**
 * Created by liyalong on 2018/1/24.
 */

public class KnowledgeBean {
    private int knowledgeId;
    private String knowledgeName;
    private String createt;

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

    @Override
    public String toString() {
        return "KnowledgeBean{" +
                "knowledgeId=" + knowledgeId +
                ", knowledgeName='" + knowledgeName + '\'' +
                ", createt='" + createt + '\'' +
                '}';
    }
}
