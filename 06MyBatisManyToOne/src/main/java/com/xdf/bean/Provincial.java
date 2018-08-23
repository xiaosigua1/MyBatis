package com.xdf.bean;

/**
 * 省会对应的实体类
 * 多个省会属于一个国家
 */

public class Provincial {
    private Integer  pId;   //国家编号
    private String  pName;  //国家名称
    // 多个省会对应一个国家
    private Country country;  //域属性

    public Provincial(Integer pId, String pName, Country country) {
        this.pId = pId;
        this.pName = pName;
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }



    public Provincial() {
    }

    @Override
    public String toString() {
        return "Provincial{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", country=" + country +
                '}';
    }
}
