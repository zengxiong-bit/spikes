package com.whkj.spikes.dao;

import com.whkj.spikes.dao.dataobject.StudentCourseDO;

public interface StudentCourseDOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StudentCourseDO record);

    StudentCourseDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentCourseDO record);

    int updateByPrimaryKey(StudentCourseDO record);
}