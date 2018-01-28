package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/28 14:12
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class RoleBean extends BaseModel {

    private List<RespBodyBean> respBody;

    public List<RespBodyBean> getRespBody() {
        return respBody;
    }

    public void setRespBody(List<RespBodyBean> respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * roleId : 1
         * roleName : 总经理
         * roleWeight : 0
         * roleSetTime : 1513871982000
         * roleStatus : 0
         * enterpriseId :
         */

        private int roleId;
        private String roleName;
        private int roleWeight;
        private long roleSetTime;
        private String roleStatus;
        private String enterpriseId;

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public int getRoleWeight() {
            return roleWeight;
        }

        public void setRoleWeight(int roleWeight) {
            this.roleWeight = roleWeight;
        }

        public long getRoleSetTime() {
            return roleSetTime;
        }

        public void setRoleSetTime(long roleSetTime) {
            this.roleSetTime = roleSetTime;
        }

        public String getRoleStatus() {
            return roleStatus;
        }

        public void setRoleStatus(String roleStatus) {
            this.roleStatus = roleStatus;
        }

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }
    }
}
