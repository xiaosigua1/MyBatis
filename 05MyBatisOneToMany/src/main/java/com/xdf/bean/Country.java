package com.xdf.bean;

import javafx.scene.effect.SepiaTone;

import java.util.Set;

/**
 * 国家对应的实体类
 */
public class Country {
    private Integer  cId;   //国家编号
    private String  cName;  //国家名称
    //一个国家可以有多个省会
    private Set<Provincial> provincials;

    public Country(Integer cId, String cName, Set<Provincial> provincials) {
        this.cId = cId;
        this.cName = cName;
        this.provincials = provincials;
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

    public Set<Provincial> getProvincials() {
        return provincials;
    }

    public void setProvincials(Set<Provincial> provincials) {
        this.provincials = provincials;
    }

    public Country() {
    }

    @Override
    public String toString() {
        return "Country{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", provincials=" + provincials +
                '}';
    }
}
