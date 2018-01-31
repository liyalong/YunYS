package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.KnowledgeBean;

/**
 * Created by liyalong on 2018/1/29.
 */

public class KnowledgDetailModel extends BaseModel {
    private KnowledgeBean respBody;

    public void setRespBody(KnowledgeBean respBody) {
        this.respBody = respBody;
    }

    public KnowledgeBean getRespBody() {

        return respBody;
    }

    @Override
    public String toString() {
        return "KnowledgDetailModel{" +
                "respBody=" + respBody +
                '}';
    }
}
