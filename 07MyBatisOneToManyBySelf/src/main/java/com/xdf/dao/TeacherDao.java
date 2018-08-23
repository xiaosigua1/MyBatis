package com.xdf.dao;

import com.xdf.bean.Teacher;

import java.io.Serializable;
import java.util.List;

public interface TeacherDao {
    /**
     * 根据导师的编号查询出所有的老师信息
     * 递归查询
     */
    List<Teacher> selectTeachersByTid(Serializable id);

    /**
     * 根据老师的编号查询自身信息
     * 如果这个老师是导师，那么再把id当成tid再次查询下面的老师信息
     */
    Teacher  selectByTeacherId(Serializable id);
}
