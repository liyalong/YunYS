package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.KnowledgeBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/24.
 */

public class KnowledgeListModel extends BaseModel {
    private List<KnowledgeBean> respBody;

    public void setRespBody(List<KnowledgeBean> respBody) {
        this.respBody = respBody;
    }

    public List<KnowledgeBean> getRespBody() {

        return respBody;
    }
}
