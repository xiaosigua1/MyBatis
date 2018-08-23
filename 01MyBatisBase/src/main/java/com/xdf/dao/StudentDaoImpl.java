package com.xdf.dao;

import com.xdf.bean.Student;
import com.xdf.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * alt +enter  快速实现方法
 */
public class StudentDaoImpl implements  StudentDao{


    /**
     * @param student  需要新增的学生对象
     */
    public void addStudent(Student student) {
     //获取session
        SqlSession session = null;
        try {
            session= SessionFactoryUtil.getSession();
            /**
             * 参数1：是我们执行的sql语句id
             * 参数2：如果方法是带参的  需要执行这个参数
             * 注意点：
             *    01.底层没有insert，delete 只有update
             *       001.点击insert观看源码
             *               新增操作
             *               public int insert(String statement, Object parameter) {
                             return this.update(statement, parameter);
                             }
                             删除方法
                             public int delete(String statement) {
                             return this.update(statement, (Object)null);
                             }
                    002.最终执行的是update！你怎么知道我到达执行的是新增还是删除呢？？？点进去update
                         001.根据mapper.xml文件中的sql语句！
                         002.只有sql语句  就能更新到数据库吗？
                         003.底层有一个属性叫dirty  是否是脏数据

                     session.commit()执行的时候，会进行缓存清理和flush（）操作！
                     缓存清理的时候会判断我们的对象是否是脏对象！如果是脏对象就会进行
                     同步数据库的操作！之后再把dirty 属性变为false！
             *
             */
            session.insert("addStudent",student);
            //手动提交事务
            session.commit();
        }catch (Exception e){
             e.printStackTrace();
        }finally {
            //务必要关闭session
            session.close();
        }

    }

    /**
     * 新增  并且获取新增对象的id
     */
    public void addStudentByCache(Student student) {
        //获取session
        SqlSession session = null;
        try {
            session= SessionFactoryUtil.getSession();
            session.insert("addStudentByCache",student);
            //手动提交事务
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //务必要关闭session
            session.close();
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteStudent(Serializable id) {

        //获取session
        SqlSession session = null;
        try {
            session = SessionFactoryUtil.getSession();
            //删除操作
            session.delete("deleteStudent",id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }


    }

    /**
     * 修改
     *
     * @param student
     */
    public void updateStudent(Student student) {
        //获取session
        SqlSession session = null;
        try {
            session = SessionFactoryUtil.getSession();
            //修改操作
            session.update("updateStudent",student);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /**
     * 查询所有
     */
    public List<Student> selectAllStudents() {
        //获取session
        SqlSession session = null;
        List<Student> students=null;
        try {
            session = SessionFactoryUtil.getSession();
            //查询所有操作
            students = session.selectList("selectAllStudents");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return  students;
    }

    /**
     * 查询所有返回map集合
     */
    public Map<String, Object> selectAllByMap() {
        //获取session
        SqlSession session = null;
        Map<String, Object> students=null;
        try {
            session = SessionFactoryUtil.getSession();
            /**
             *  查询所有操作 返回map
             *     s1: map集合中的key
             */
            students= session.selectMap("selectAllByMap","name");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return  students;
    }

    /**
     * 查询指定id的学生
     *
     * @param id
     */
    public Student selectStudentById(Serializable id) {
        //获取session
        SqlSession session = null;
        Student student=null;
        try {
            session = SessionFactoryUtil.getSession();
            student= session.selectOne("selectStudentById",id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return  student;
    }

    /**
     * 根据姓名模糊查询
     *
     * @param name
     */
    public List<Student> selectByName(String name) {
        //获取session
        SqlSession session = null;
        List<Student> students=null;
        try {
            session = SessionFactoryUtil.getSession();
            //查询所有操作
            students = session.selectList("selectByName",name);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return  students;
    }

}
