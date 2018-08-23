package com.xdf.bean;

import java.util.Set;

/**
 * 老师和导师对应的实体类   自连接
 */
public class Teacher {

    private   Integer  id;   //老师编号
    private   String   name;  //老师姓名
    //多个老师对应一个导师
    private   Teacher  teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher(Integer id, String name,Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher=teacher;
    }

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


    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
