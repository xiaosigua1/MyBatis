package com.xdf;

import com.xdf.bean.Student;
import com.xdf.dao.StudentDao;
import com.xdf.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class StudentTest {

    StudentDao dao=null;
    SqlSession session=null;
    Logger log=Logger.getLogger(StudentTest.class);

    /**
     * 在所有的test测试方法执行之前 都要执行的操作
     */
    @Before
    public void before(){
         //获取session
        session= SessionFactoryUtil.getSession();
        dao=session.getMapper(StudentDao.class); //获取执行的类对象
    }

    @After
    public  void after(){
        if (session!=null){
            session.close();
        }
    }

    /**
     * 新增学生信息到数据库
     */
    @Test
    public   void  testAddStudent(){
        Student student=new Student(555,"哈哈哈啊");
        dao.addStudent(student);
        session.commit();
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
        Student student=new Student(555,"哈哈哈啊1");
        dao.addStudentByCache(student);
        log.debug("新增学生的id====》"+student.getId());
        session.commit();
    }




    /**
     * 删除
     */
    @Test
    public void delStudent(){
        dao.deleteStudent(21);
        session.commit();
    }
    /**
     * 修改
     */
    @Test
    public void updateStudent(){
        Student student=new Student(24,66666,"255555");
        dao.updateStudent(student);
        log.debug("修改之后的学生信息是："+student);
        session.commit();
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
        List<Map<String,Object>> maps = dao.selectAllByMap();
        log.debug(maps);
    }
    /**
     * 查询指定的student 返回Student对象
     */
    @Test
    public void selectStudentById(){
       log.debug(dao.selectStudentById(24));
    }
    /**
     * 根据姓名模糊查询
     */
    @Test
    public void selectStudentByName(){
        List<Student> students = dao.selectByName("小");
        log.debug(students);
    }

}
