package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * Created by liyalong on 2018/5/23.
 */

public class AlarmPLCDataModel extends BaseModel {
    private List<PLCModel> respBody;

    public List<PLCModel> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<PLCModel> respBody) {
        this.respBody = respBody;
    }

    public class PLCModel {
        private Integer equipmentId;
        private String equipmentName;
        private String plcDetail;
        private String plcName;
        private String projectName;
        private String unitName;
        private List<Data> datas;

        public Integer getEquipmentId() {
            return equipmentId;
        }

        public void setEquipmentId(Integer equipmentId) {
            this.equipmentId = equipmentId;
        }

        public String getEquipmentName() {
            return equipmentName;
        }

        public void setEquipmentName(String equipmentName) {
            this.equipmentName = equipmentName;
        }

        public String getPlcDetail() {
            return plcDetail;
        }

        public void setPlcDetail(String plcDetail) {
            this.plcDetail = plcDetail;
        }

        public String getPlcName() {
            return plcName;
        }

        public void setPlcName(String plcName) {
            this.plcName = plcName;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public List<Data> getDatas() {
            return datas;
        }

        public void setDatas(List<Data> datas) {
            this.datas = datas;
        }

        private class Data {
            private String value;
            private String date;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
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
