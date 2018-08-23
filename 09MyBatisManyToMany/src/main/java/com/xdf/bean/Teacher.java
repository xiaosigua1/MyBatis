package com.xdf.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 老师对应的实体类
 */
public class Teacher {
    private  Integer  tid;
    private  String  tName;
    //一个老师可以有多个学生
    private Set<Student> students=new HashSet<Student>();

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

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
                ", students=" + students +
                '}';
    }
}
