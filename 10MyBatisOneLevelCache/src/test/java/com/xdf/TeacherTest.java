package com.xdf;

import com.xdf.bean.Teacher;
import com.xdf.dao.TeacherDao;
import com.xdf.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TeacherTest {

    TeacherDao dao=null;
    SqlSession session=null;
    Logger log=Logger.getLogger(TeacherTest.class);

    /**
     * 在所有的test测试方法执行之前 都要执行的操作
     */
    @Before
    public void before(){
        //获取session
        session= SessionFactoryUtil.getSession();
        dao=session.getMapper(TeacherDao.class); //获取执行的类对象
    }

    @After
    public  void after(){
        if (session!=null){
            session.close();
        }
    }


    /**
     * mybatis的一级缓存是一直开启的！ 不能关闭！
     */
    @Test
    public  void  testSelectBySid(){
        Teacher teacher = dao.selectTeacherByID(1);
        log.debug("第一次从数据库中获取数据===》"+teacher);
        //再次查询id=1的老师信息
        teacher = dao.selectTeacherByID(1);
        log.debug("第二次从一级缓存中获取数据===》"+teacher);   // 只有一条sql语句产生

        // 问题？？
        // 01.缓存在底层存储都是key  value ！  value 就是查询的结果！  关键是 key 是谁？？？？？

        // 02.数据在做增删改的时候  会不会 清理缓存？？  会
    }
    /**
     *  验证mybatis的一级缓存的key！
     *
     *  sql语句是 一样一样的！
     *  sql的id 不一致！
     *  导致走了2次数据库！
     *
     *  key的组成是  sql的id  + sql语句！
     *  01.方法名就是我们的id  ！  有可能重复
     *  02.如果id 一致，那么sql 肯定不一致
     *  03.目的就是确保我们key的唯一性
     */
    @Test
    public  void  testSelectBySid2(){
        Teacher teacher = dao.selectTeacherByID(1);
        log.debug("第一次从数据库中获取数据===》"+teacher);
        //再次查询id=1的老师信息
        teacher = dao.selectTeacherByID2(1);
        log.debug("第二次从数据库中获取数据===》"+teacher);
    }

    /**
     * 一级缓存 是存在  sqlSession中的缓存！
     * 一个会话对应一个sqlSession！
     */


    /**
     * 验证数据在做增删改的时候  会不会 清理缓存？？
     */
    @Test
    public  void  testSelectByAdd(){
        Teacher teacher = dao.selectTeacherByID(1);
        log.debug("第一次从数据库中获取数据===》"+teacher);
        /**
         *  新增一条数据
         *  01.数据在commit之前 执行缓存清理
         *  02.缓存清理就会把之前缓存中的数据清空
         *  03.所以我们再次查询 会找数据库
         *
         *
         *    public void commit(boolean required) throws SQLException {
             if (this.closed) {
             throw new ExecutorException("Cannot commit, transaction is already closed");
             } else {
             this.clearLocalCache();  //清空本地缓存
             this.flushStatements();
             if (required) {
             this.transaction.commit();
             }

             }
             }
         */

        dao.insertTeacher(new Teacher(4,"嘿嘿嘿"));
        session.commit();
        //再次查询id=1的老师信息
        teacher = dao.selectTeacherByID(1);
        log.debug("第二次应该从缓存中获取数据===》"+teacher);
    }

}
