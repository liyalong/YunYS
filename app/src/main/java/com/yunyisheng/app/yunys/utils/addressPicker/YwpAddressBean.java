package com.yunyisheng.app.yunys.utils.addressPicker;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wepon on 2017/12/4.
 * 数据模型
 */

public class YwpAddressBean implements Serializable {

    private List<AddressItemBean> province;
    private List<AddressItemBean> city;
    private List<AddressItemBean> district;

    public List<AddressItemBean> getProvince() {
        return province;
    }

    public void setProvince(List<AddressItemBean> province) {
        this.province = province;
    }

    public List<AddressItemBean> getCity() {
        return city;
    }

    public void setCity(List<AddressItemBean> city) {
        this.city = city;
    }

    public List<AddressItemBean> getDistrict() {
        return district;
    }

    public void setDistrict(List<AddressItemBean> district) {
        this.district = district;
    }

    public static class AddressItemBean implements Serializable {
        private Integer id;
        private String name;
        private Integer pid;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }
    }
}
