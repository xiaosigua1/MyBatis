package com.xdf;

import com.xdf.bean.Country;
import com.xdf.dao.CountryDao;
import com.xdf.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentTest {

    CountryDao dao=null;
    SqlSession session=null;
    Logger log=Logger.getLogger(StudentTest.class);

    /**
     * 在所有的test测试方法执行之前 都要执行的操作
     */
    @Before
    public void before(){
        //获取session
        session= SessionFactoryUtil.getSession();
        dao=session.getMapper(CountryDao.class); //获取执行的类对象
    }

    @After
    public  void after(){
        if (session!=null){
            session.close();
        }
    }

    /**
     * 根据国家的编号  查询出 国家对应省会的信息
     * 没有延迟加载
     */
    @Test
    public  void selectCountry(){
        Country country = dao.selectCountryById(1);
        log.debug(country);
    }
    /**
     * 根据国家的编号  查询出 国家对应省会的信息
     * 设置延迟加载
     *01.在mybati核心配置文件中 增加setting节点
     *02.节点中增加
     *  <setting name="lazyLoadingEnabled" value="true"/>
     *   <setting name="aggressiveLazyLoading" value="false"/>
     */
    @Test
    public  void selectCountryLazy(){
        Country country = dao.selectCountryById(2);
        log.debug(country.getcName()); //只查询国家的名称  如果开启了延迟加载有1条sql
        log.debug(country.getcName()); //只查询国家的名称  如果没开启了延迟加载有2条sql
        log.debug(country.getProvincials()); //查询国家对应的省会  无论有没有开启了延迟加载都有2条sql
    }



}
