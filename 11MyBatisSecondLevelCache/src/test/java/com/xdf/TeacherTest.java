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
     * mybatis的二级缓存的配置：
     * 01.引入jar包  和  ehcache.xml文件
     * 02.在ehcache.xml文件去书写对应的配置
     * 03.需要缓存的类 需要实现序列化接口
     * 04. 在对应的mapper文件中设置  <cache/>
     */

    /**
     * 1.[DEBUG] com.xdf.TeacherTest 46 第一次从数据库中获取数据===》Teacher{tid=1, tName='导师1'}
     2.[DEBUG] com.xdf.dao.TeacherDao 62 Cache Hit Ratio [com.xdf.dao.TeacherDao]: 0.0
     3.[DEBUG] com.xdf.TeacherTest 49 第二次从一级缓存中获取数据===》Teacher{tid=1, tName='导师1'}

     步骤：
     01.当我们查询id=1的老师信息时
     02.先从一级缓存中查询，没有，之后 如果我们配置了2级缓存，就去2级缓存中查询！
     03.如果2级缓存也没有，就回去数据库中查询！  所以得到了
     [DEBUG] com.xdf.TeacherTest 46 第一次从数据库中获取数据===》Teacher{tid=1, tName='导师1'}
     04.第2次查询的时候先从一级缓存中查询，
     一级缓存中存在数据，直接使用！
     所以2级缓存的命中率是0.0！
     */
    @Test
    public  void  testSelectBySid(){
        Teacher teacher = dao.selectTeacherByID(1);
        log.debug("第一次从数据库中获取数据===》"+teacher);
        //再次查询id=1的老师信息
        teacher = dao.selectTeacherByID(1);
        log.debug("第二次从一级缓存中获取数据===》"+teacher);   // 只有一条sql语句产生
    }

    /**
     * 验证2级缓存配置成功
     */


    /**
     *01.先走一级缓存中查询  没有
     *02.去二级缓存中查询   没有  Cache Hit Ratio   0.0
     *03.去数据库查询  SELECT tid,tname from teacher where tid=?
     *04.输出结果
     * [DEBUG] com.xdf.TeacherTest 73 第一次从数据库中获取数据===》Teacher{tid=1, tName='导师1'}
     *05.session关闭了    一级缓存中的数据被清空
     *06.因为我们设置了二级缓存，所以数据会在2级缓存中保存
     *07.第2次查询  先去一级缓存中查询   session关闭没有
     *08.去二级缓存中查询   有
     *09.Cache Hit Ratio [com.xdf.dao.TeacherDao]: 0.5
     * 10.从二级缓存中的得到结果
     [DEBUG] com.xdf.TeacherTest 81 第二次从一级缓存中获取数据===》Teacher{tid=1, tName='导师1'}


      [DEBUG] com.xdf.dao.TeacherDao 62 Cache Hit Ratio [com.xdf.dao.TeacherDao]: 0.0
     [DEBUG] org.apache.ibatis.transaction.jdbc.JdbcTransaction 138 Opening JDBC Connection
     [DEBUG] org.apache.ibatis.datasource.pooled.PooledDataSource 387 Created connection 32863545.
     [DEBUG] org.apache.ibatis.transaction.jdbc.JdbcTransaction 102 Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@1f57539]
     [DEBUG] com.xdf.dao.TeacherDao.selectTeacherByID 142 ==>  Preparing: SELECT tid,tname from teacher where tid=?
     [DEBUG] com.xdf.dao.TeacherDao.selectTeacherByID 142 ==> Parameters: 1(Integer)
     [DEBUG] com.xdf.dao.TeacherDao.selectTeacherByID 148 <==    Columns: tid, tname
     [DEBUG] com.xdf.dao.TeacherDao.selectTeacherByID 148 <==        Row: 1, 导师1
     [DEBUG] com.xdf.dao.TeacherDao.selectTeacherByID 142 <==      Total: 1
     [DEBUG] com.xdf.TeacherTest 73 第一次从数据库中获取数据===》Teacher{tid=1, tName='导师1'}
     [DEBUG] org.apache.ibatis.transaction.jdbc.JdbcTransaction 124 Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@1f57539]
     [DEBUG] org.apache.ibatis.transaction.jdbc.JdbcTransaction 92 Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@1f57539]
     [DEBUG] org.apache.ibatis.datasource.pooled.PooledDataSource 344 Returned connection 32863545 to pool.
     [DEBUG] com.xdf.dao.TeacherDao 62 Cache Hit Ratio [com.xdf.dao.TeacherDao]: 0.5
     [DEBUG] com.xdf.TeacherTest 81 第二次从一级缓存中获取数据===》Teacher{tid=1, tName='导师1'}

      无论我们的2级缓存是是否有数据，都会执行命中率！
     */
    @Test
    public  void  testSelectBySid2(){
        Teacher teacher = dao.selectTeacherByID(1);
        log.debug("第一次从数据库中获取数据===》"+teacher);
        //关闭session    一级缓存中的数据被清空
        session.close();
        //再次获取session
        session=SessionFactoryUtil.getSession();
        dao=session.getMapper(TeacherDao.class); //获取执行的类对象
        //再次查询id=1的老师信息
        teacher = dao.selectTeacherByID(1);
        log.debug("第二次从二级缓存中获取数据===》"+teacher);
        teacher = dao.selectTeacherByID(2);
        log.debug("第三次从二级缓存中获取数据===》"+teacher);
    }

    /**
     * 2级缓存的局部关闭
     *  在对应的mappper文件中的节点中  增加 useCache=false
     */
    @Test
    public  void  testSelectByClose(){
        Teacher teacher = dao.selectTeacherByID(1);
        log.debug("第一次从数据库中获取数据===》"+teacher);
        //关闭session    一级缓存中的数据被清空
        session.close();
        //再次获取session
        session=SessionFactoryUtil.getSession();
        dao=session.getMapper(TeacherDao.class); //获取执行的类对象
        //再次查询id=1的老师信息
        teacher = dao.selectTeacherByID(1);
        log.debug("第二次从二级缓存中获取数据===》"+teacher);
    }




    /**
     * 增删改  也是 清空 2级缓存！
     */
}
