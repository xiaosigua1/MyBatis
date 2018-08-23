package com.xdf.dao;

import com.xdf.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 动态查询
 */
public interface StudentDao {

    /**
     *  01.用户传递一个Student对象  但是我们不知道用户对那些属性赋了值
     */
    List<Student> selectStudentsByIf(Student student);
    /**
     * 02.where 标签 替换where 1=1
     */
    List<Student> selectStudentsByWhere(Student student);
    /**
     * 03.choose标签
     *  当我们的年龄不为空 按照年龄查询
     *  当我们的姓名不为空 按照姓名查询
     *  如果都会空 执行一个标签otherwise
     */
    List<Student> selectStudentsByChoose(Student student);
    /**
     * 04.foreach 遍历数组
     */
    List<Student> selectStudentsByForeachArray(int [] nums);
    /**
     * 05.foreach 遍历集合
     */
    List<Student> selectStudentsByForeachList(List<Integer> nums);
    /**
     * 06.foreach 遍历对象集合
     */
    List<Student> selectStudentsByForeachStudent(List<Student> students);
    /**
     * 07.foreach 遍历Map集合
     */
    List<Student> selectStudentsByForeachMap(@Param("myMap") Map<String,Object> map);
}
