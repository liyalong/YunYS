package com.yunyisheng.app.yunys.project.model;

import android.media.audiofx.Equalizer;
import android.util.Property;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.project.bean.DeviceModelBean;
import com.yunyisheng.app.yunys.project.bean.ProjectBean;

import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Created by liyalong on 2018/5/22.
 */

public class AlarmDetailModel extends BaseModel {
    private AlarmDetail respBody;

    public AlarmDetail getRespBody() {
        return respBody;
    }

    public void setRespBody(AlarmDetail respBody) {
        this.respBody = respBody;
    }

    public class AlarmDetail {
        private Integer alarmId;
        private String alarmName;
        private String alarmRemark;
        private Integer alarmLevel;
        private Integer alarmType;
        private Integer alarmEquPcmId;
        private String alarmProjectId;
        private String alarmCompanyId;
        private String alarmCreateDate;
        private String alarmUpdateDate;
        private String alarmUpdateDateStart;
        private String alarmUpdateDateEnd;
        private String alarmRecoveryDate;
        private Integer alarmHandleType;
        private String alarmHandleUser;
        private String alarmValue;
        private String alarmStatus;
        private String propertyName;
        private Integer warnId;
        private String alarmDuration;
        private List<EquBean> equList;
        private ProjectBean project;

        public Integer getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(Integer alarmId) {
            this.alarmId = alarmId;
        }

        public String getAlarmName() {
            return alarmName;
        }

        public void setAlarmName(String alarmName) {
            this.alarmName = alarmName;
        }

        public String getAlarmRemark() {
            return alarmRemark;
        }

        public void setAlarmRemark(String alarmRemark) {
            this.alarmRemark = alarmRemark;
        }

        public Integer getAlarmLevel() {
            return alarmLevel;
        }

        public void setAlarmLevel(Integer alarmLevel) {
            this.alarmLevel = alarmLevel;
        }

        public Integer getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(Integer alarmType) {
            this.alarmType = alarmType;
        }

        public Integer getAlarmEquPcmId() {
            return alarmEquPcmId;
        }

        public void setAlarmEquPcmId(Integer alarmEquPcmId) {
            this.alarmEquPcmId = alarmEquPcmId;
        }

        public String getAlarmProjectId() {
            return alarmProjectId;
        }

        public void setAlarmProjectId(String alarmProjectId) {
            this.alarmProjectId = alarmProjectId;
        }

        public String getAlarmCompanyId() {
            return alarmCompanyId;
        }

        public void setAlarmCompanyId(String alarmCompanyId) {
            this.alarmCompanyId = alarmCompanyId;
        }

        public String getAlarmCreateDate() {
            return alarmCreateDate;
        }

        public void setAlarmCreateDate(String alarmCreateDate) {
            this.alarmCreateDate = alarmCreateDate;
        }

        public String getAlarmUpdateDate() {
            return alarmUpdateDate;
        }

        public void setAlarmUpdateDate(String alarmUpdateDate) {
            this.alarmUpdateDate = alarmUpdateDate;
        }

        public String getAlarmUpdateDateStart() {
            return alarmUpdateDateStart;
        }

        public void setAlarmUpdateDateStart(String alarmUpdateDateStart) {
            this.alarmUpdateDateStart = alarmUpdateDateStart;
        }

        public String getAlarmUpdateDateEnd() {
            return alarmUpdateDateEnd;
        }

        public void setAlarmUpdateDateEnd(String alarmUpdateDateEnd) {
            this.alarmUpdateDateEnd = alarmUpdateDateEnd;
        }

        public String getAlarmRecoveryDate() {
            return alarmRecoveryDate;
        }

        public void setAlarmRecoveryDate(String alarmRecoveryDate) {
            this.alarmRecoveryDate = alarmRecoveryDate;
        }

        public Integer getAlarmHandleType() {
            return alarmHandleType;
        }

        public void setAlarmHandleType(Integer alarmHandleType) {
            this.alarmHandleType = alarmHandleType;
        }

        public String getAlarmHandleUser() {
            return alarmHandleUser;
        }

        public void setAlarmHandleUser(String alarmHandleUser) {
            this.alarmHandleUser = alarmHandleUser;
        }

        public String getAlarmValue() {
            return alarmValue;
        }

        public void setAlarmValue(String alarmValue) {
            this.alarmValue = alarmValue;
        }

        public String getAlarmStatus() {
            return alarmStatus;
        }

        public void setAlarmStatus(String alarmStatus) {
            this.alarmStatus = alarmStatus;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public Integer getWarnId() {
            return warnId;
        }

        public void setWarnId(Integer warnId) {
            this.warnId = warnId;
        }

        public String getAlarmDuration() {
            return alarmDuration;
        }

        public void setAlarmDuration(String alarmDuration) {
            this.alarmDuration = alarmDuration;
        }

        public List<EquBean> getEquList() {
            return equList;
        }

        public void setEquList(List<EquBean> equList) {
            this.equList = equList;
        }

        public ProjectBean getProject() {
            return project;
        }

        public void setProject(ProjectBean project) {
            this.project = project;
        }

        public class EquBean {
            private Integer equipmentId;
            private String equipmentName;
            private String equipmentCode;
            private Integer equipmentStat;
            private String equipmentCreate;
            private String equipmentUpdate;
            private String equipmentRemark;
            private Integer isWarning;
            private Integer warningLevel;
            private String latestWarningTime;
            private Integer runDuration;
            private String supplier;
            private List<Propertys> propertyList;

            public List<Propertys> getPropertyList() {
                return propertyList;
            }

            public void setPropertyList(List<Propertys> propertyList) {
                this.propertyList = propertyList;
            }

            public Integer getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(Integer equipmentId) {
                this.equipmentId = equipmentId;
            }

            public String getEquipmentCode() {
                return equipmentCode;
            }

            public void setEquipmentCode(String equipmentCode) {
                this.equipmentCode = equipmentCode;
            }

            public Integer getEquipmentStat() {
                return equipmentStat;
            }

            public void setEquipmentStat(Integer equipmentStat) {
                this.equipmentStat = equipmentStat;
            }

            public String getEquipmentCreate() {
                return equipmentCreate;
            }

            public void setEquipmentCreate(String equipmentCreate) {
                this.equipmentCreate = equipmentCreate;
            }

            public String getEquipmentUpdate() {
                return equipmentUpdate;
            }

            public void setEquipmentUpdate(String equipmentUpdate) {
                this.equipmentUpdate = equipmentUpdate;
            }

            public String getEquipmentRemark() {
                return equipmentRemark;
            }

            public void setEquipmentRemark(String equipmentRemark) {
                this.equipmentRemark = equipmentRemark;
            }

            public Integer getIsWarning() {
                return isWarning;
            }

            public void setIsWarning(Integer isWarning) {
                this.isWarning = isWarning;
            }

            public Integer getWarningLevel() {
                return warningLevel;
            }

            public void setWarningLevel(Integer warningLevel) {
                this.warningLevel = warningLevel;
            }

            public String getLatestWarningTime() {
                return latestWarningTime;
            }

            public void setLatestWarningTime(String latestWarningTime) {
                this.latestWarningTime = latestWarningTime;
            }

            public Integer getRunDuration() {
                return runDuration;
            }

            public void setRunDuration(Integer runDuration) {
                this.runDuration = runDuration;
            }

            public String getSupplier() {
                return supplier;
            }

            public void setSupplier(String supplier) {
                this.supplier = supplier;
            }

            public String getEquipmentName() {

                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public class Propertys {
                private Integer propertyId;
                private String propertyName;
                private String units;
                private String propertyVal;
                private String projectId;
                private String deviceId;
                private Integer propertyStat;
                private Integer warningId;
                private String detail;
                private String companyId;
                private String terraceId;
                private String pcmId;

                public Integer getPropertyId() {
                    return propertyId;
                }

                public void setPropertyId(Integer propertyId) {
                    this.propertyId = propertyId;
                }

                public String getPropertyName() {
                    return propertyName;
                }

                public void setPropertyName(String propertyName) {
                    this.propertyName = propertyName;
                }

                public String getUnits() {
                    return units;
                }

                public void setUnits(String units) {
                    this.units = units;
                }

                public String getPropertyVal() {
                    return propertyVal;
                }

                public void setPropertyVal(String propertyVal) {
                    this.propertyVal = propertyVal;
                }

                public String getProjectId() {
                    return projectId;
                }

                public void setProjectId(String projectId) {
                    this.projectId = projectId;
                }

                public String getDeviceId() {
                    return deviceId;
                }

                public void setDeviceId(String deviceId) {
                    this.deviceId = deviceId;
                }

                public Integer getPropertyStat() {
                    return propertyStat;
                }

                public void setPropertyStat(Integer propertyStat) {
                    this.propertyStat = propertyStat;
                }

                public Integer getWarningId() {
                    return warningId;
                }

                public void setWarningId(Integer warningId) {
                    this.warningId = warningId;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getCompanyId() {
                    return companyId;
                }

                public void setCompanyId(String companyId) {
                    this.companyId = companyId;
                }

                public String getTerraceId() {
                    return terraceId;
                }

                public void setTerraceId(String terraceId) {
                    this.terraceId = terraceId;
                }

                public String getPcmId() {
                    return pcmId;
                }

                public void setPcmId(String pcmId) {
                    this.pcmId = pcmId;
                }
            }
        }
    }
}
