package com.xdf;

import com.xdf.bean.Student;
import com.xdf.dao.StudentDao;
import com.xdf.dao.StudentDaoImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class StudentTest {

    StudentDao dao=null;
    Logger log=Logger.getLogger(StudentTest.class);

    /**
     * 在所有的test测试方法执行之前 都要执行的操作
     */
    @Before
    public void before(){
        dao=new StudentDaoImpl();
    }

    /**
     * 新增学生信息到数据库
     */

    @Test
    public   void  testAddStudent(){
        Student student=new Student(55,"嘿嘿");
        log.debug("insert之前学生的编号："+student.getId());
        dao.addStudent(student);
        /**
         * 我们现在的新增对象中 只有age和name
         * id是mysql数据库给我们生成的
         * 你没有去mysql数据库中查询！
         * id肯定沒值！
         */
        log.debug("insert之后学生的编号："+student.getId());
    }

    /**
     * 我们需要新增对象之后，接着对这个对象进行操作
     * 上面的新增方法能实现吗？
     * 怎么办？
     * 有一种方式 是在insert节点中新增一个selectkey节点
     * 在我们新增完对象之后，连接没有立即放回连接池中，
     * 而是接着查询数据库中 刚刚插入数据的id
     * 这样id我们就能获取了！
     */
    @Test
    public   void  addStudentByCache(){
        Student student=new Student(56,"嘿嘿666");
        log.debug("insert之前学生的编号："+student.getId());
        dao.addStudentByCache(student);
        log.debug("insert之后学生的编号："+student.getId()); //就会有值
    }




    /**
     * 删除
     */
    @Test
    public void delStudent(){
        dao.deleteStudent(13);
    }
    /**
     * 修改
     */
    @Test
    public void updateStudent(){
        Student student=new Student(14,555,"小尼古拉斯");
        dao.updateStudent(student);
    }
    /**
     * 查询所有 返回list
     */
    @Test
    public void listStudent(){
        List<Student> students = dao.selectAllStudents();
        log.debug(students);
    }
    /**
     * 查询所有 返回map
     */
    @Test
    public void mapStudent(){
        Map<String, Object> students = dao.selectAllByMap();
        log.debug(students.size());
        //根据key获取一个对象
       log.debug(students.get("小尼古拉斯"));
    }
    /**
     * 查询指定的student 返回Student对象
     */
    @Test
    public void selectStudentById(){
        Student student = dao.selectStudentById(14);
        //获取一个对象
       log.debug(student);
    }
    /**
     * 根据姓名模糊查询
     */
    @Test
    public void selectStudentByName(){
        List<Student> students = dao.selectByName("小");
        //获取一个对象
       log.debug(students);
    }

}
