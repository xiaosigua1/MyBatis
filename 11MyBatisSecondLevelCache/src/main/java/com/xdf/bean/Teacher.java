package com.xdf.bean;

import java.io.Serializable;

public class Teacher  implements Serializable{

    private  Integer  tid;
    private  String  tName;


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Teacher() {
    }

    public Teacher(Integer tid, String tName) {
        this.tid = tid;
        this.tName = tName;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", tName='" + tName + '\'' +
                '}';
    }
}
