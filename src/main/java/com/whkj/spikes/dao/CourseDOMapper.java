package com.whkj.spikes.dao;

import com.whkj.spikes.dao.dataobject.CourseDO;

public interface CourseDOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CourseDO record);

    CourseDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);
}