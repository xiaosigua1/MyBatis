package com.xdf.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * SqlSessionFactory的单例类
 * 工具类的目的就是创建sqlSession
 */
public class SessionFactoryUtil {
    //01.创建需要单例的对象实例
    private  static SqlSessionFactory sessionFactory;

    //02.私有化构造
    private SessionFactoryUtil(){}

    /**
     *  03.对外提供访问的接口
     *
     *     捋一捋
     *     001.SqlSession的创建依赖SqlSessionFactory
     *     002.SqlSessionFactory依赖于SqlSessionFactoryBuilder
     *     003.SqlSessionFactoryBuilder依赖于配置文件
     *     004.获取配置文件
     */
    public   static  synchronized SqlSession getSession(){
        //给我一个文件 返回一个输入流 到 内存中
        try {
            InputStream stream = Resources.getResourceAsStream("mybatis.xml");
            //判断SqlSessionFactory是否为空
            if (sessionFactory==null){
                sessionFactory=new SqlSessionFactoryBuilder().build(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 之前还需要写一个finally用来关闭流！  现在不需要   为什么不需要??
         * 01.查询源码build(stream)
         * 02.SqlSessionFactoryBuilder类中已经关闭reader.close()
         * 03.所以我们如果关闭流  会报错！
         */
        return  sessionFactory.openSession();  //创建session返回
    }

}
