package com.whkj.spikes.dao;

import com.whkj.spikes.dao.dataobject.StudentDO;

public interface StudentDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentDO record);
    StudentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentDO record);

    int updateByPrimaryKey(StudentDO record);
}