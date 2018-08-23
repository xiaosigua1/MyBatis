package com.xdf.dao;

import com.xdf.bean.Teacher;

import java.io.Serializable;
import java.util.List;

public interface TeacherDao {

    /**
     * 根据老师的编号 查询  所有导师的信息
     * 递归查询
     */

    Teacher selectTeachersById(Serializable id);

}
