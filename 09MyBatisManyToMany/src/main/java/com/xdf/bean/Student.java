package com.xdf.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 学生对应的实体类
 */
public class Student {
    private  Integer sid;    //编号
    private  Integer age;    //年龄
    private  String  sname;  //姓名

    //一个学生可以有多个老师
    private Set<Teacher> teachers=new HashSet<Teacher>();


    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Student() {
    }

    public Student(Integer sid, Integer age, String sname) {
        this.sid = sid;
        this.age = age;
        this.sname = sname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", age=" + age +
                ", sname='" + sname + '\'' +
                ", teachers=" + teachers.size() +
                '}';
    }
}
