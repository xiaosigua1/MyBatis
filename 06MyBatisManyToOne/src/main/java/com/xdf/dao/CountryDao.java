package com.xdf.dao;

import com.xdf.bean.Provincial;

import java.io.Serializable;

public interface CountryDao {
    /**
     * 根据省会的id 查询出 省会 以及对应的国家
     */
    Provincial selectProvincialById(Serializable id);
}
