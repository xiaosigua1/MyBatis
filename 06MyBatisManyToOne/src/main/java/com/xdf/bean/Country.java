package com.xdf.bean;

import java.util.Set;

/**
 * 国家对应的实体类
 */
public class Country {
    private Integer  cId;   //国家编号
    private String  cName;  //国家名称

    public Country(Integer cId, String cName) {
        this.cId = cId;
        this.cName = cName;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }


    public Country() {
    }

    @Override
    public String toString() {
        return "Country{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                '}';
    }
}
