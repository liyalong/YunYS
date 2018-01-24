package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.DeviceBean;

import java.util.List;

/**
 * Created by liyalong on 2018/1/22.
 */

public class DeviceListModel extends BaseModel {
    private int total;
    private List<DeviceBean> list;
    private int lastPage;

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getLastPage() {

        return lastPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setList(List<DeviceBean> list) {
        this.list = list;
    }

    public int getTotal() {

        return total;
    }

    public List<DeviceBean> getList() {
        return list;
    }
}
