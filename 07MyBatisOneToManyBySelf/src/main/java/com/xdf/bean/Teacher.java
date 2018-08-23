package com.xdf.bean;

import java.util.Set;

/**
 * 老师和导师对应的实体类   自连接
 */
public class Teacher {

    private   Integer  id;   //老师编号
    private   String   name;  //老师姓名
    //一个导师下面可以有多个老师
    private Set<Teacher> teachers;

    public Teacher(Integer id, String name, Set<Teacher> teachers) {
        this.id = id;
        this.name = name;
        this.teachers = teachers;
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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
