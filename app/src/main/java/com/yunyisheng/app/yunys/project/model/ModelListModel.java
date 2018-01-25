package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.ModelInfoBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/22.
 */

public class ModelListModel extends BaseModel {
    private int total;
    private List<ModelInfoBean> respBody;
    private int lastPage;

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getLastPage() {

        return lastPage;
    }

    public int getTotal() {
        return total;
    }


    public void setTotal(int total) {
        this.total = total;
    }

    public void setRespBody(List<ModelInfoBean> respBody) {
        this.respBody = respBody;
    }

    public List<ModelInfoBean> getRespBody() {

        return respBody;
    }
}
