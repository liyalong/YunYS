package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/6 11:15
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ReportListBean extends BaseModel {


    private List<RespBodyBean> respBody;

    public List<RespBodyBean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<RespBodyBean> respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * dateSources : 1
         * projectName : 项目A
         * plcName : null
         * fieldName : 组件A
         * formName : 表单A
         * unitName : L
         * datas : [{"value":0,"date":"2017-12-01 00:00:00"},{"value":0,"date":"2017-12-08 00:00:00"},{"value":0,"date":"2017-12-15 00:00:00"},{"value":0,"date":"2017-12-22 00:00:00"},{"value":0,"date":"2017-12-29 00:00:00"},{"value":0,"date":"2018-01-05 00:00:00"},{"value":0,"date":"2018-01-12 00:00:00"},{"value":0,"date":"2018-01-19 00:00:00"},{"value":0,"date":"2018-01-26 00:00:00"},{"value":2255,"date":"2018-02-02 00:00:00"}]
         */

        private String dateSources;
        private String projectName;
        private String plcName;
        private String fieldName;
        private String formName;
        private String unitName;
        private List<DatasBean> datas;

        public String getDateSources() {
            return dateSources;
        }

        public void setDateSources(String dateSources) {
            this.dateSources = dateSources;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getPlcName() {
            return plcName;
        }

        public void setPlcName(String plcName) {
            this.plcName = plcName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFormName() {
            return formName;
        }

        public void setFormName(String formName) {
            this.formName = formName;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * value : 0
             * date : 2017-12-01 00:00:00
             */

            private int value;
            private String date;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
