package com.yunyisheng.app.yunys.login.model;

import com.yunyisheng.app.yunys.base.BaseModel;
import com.yunyisheng.app.yunys.utils.addressPicker.CityInterface;

import java.util.List;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by liyalong on 2018/5/7.
 */

public class CityModel extends BaseModel {
    private List<City> respBody;

    public List<City> getRespBody() {
        return respBody;
    }
    public void setRespBody(List<City> respBody) {
        this.respBody = respBody;
    }

    public class City implements CityInterface{
        private String name;
        private Integer id;
        private Integer pid;
        private String IsMunicipality;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getIsMunicipality() {
            return IsMunicipality;
        }

        public void setIsMunicipality(String isMunicipality) {
            IsMunicipality = isMunicipality;
        }

        @Override
        public String getCityName() {
            return name;
        }

        @Override
        public Integer getCityPid() {
            return id;
        }
    }


}
