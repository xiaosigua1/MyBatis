package com.xdf.bean;

/**
 * 省会对应的实体类
 * 多个省会属于一个国家
 */

public class Provincial {
    private Integer  pId;   //国家编号
    private String  pName;  //国家名称

    public Provincial(Integer pId, String pName) {
        this.pId = pId;
        this.pName = pName;
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
                '}';
    }
}
