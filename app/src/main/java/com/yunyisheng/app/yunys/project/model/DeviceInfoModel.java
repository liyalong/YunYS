package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;

/**
 * Created by liyalong on 2018/1/23.
 */

public class DeviceInfoModel extends BaseModel {
    private String title; //显示文字
    private int type; //1:文本显示，2：箭头指向
    private int status; //文本显示对应的状态
    private String index;//每项对应的标识

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndex() {

        return index;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {

        return title;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "DeviceInfoModel{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", index='" + index + '\'' +
                '}';
    }
}
