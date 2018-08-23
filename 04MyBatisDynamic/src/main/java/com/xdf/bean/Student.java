package com.xdf.bean;

/**
 * 学生对应的实体类
 */
public class Student {
    private  int id;
    private  int age;
    private  String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     * 快捷生成 set和get  以及有参无参构造和toString  alt+insert
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Student(int id) {
        this.id = id;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
