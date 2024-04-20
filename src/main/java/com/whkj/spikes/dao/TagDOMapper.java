package com.whkj.spikes.dao;

import com.whkj.spikes.dao.dataobject.TagDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagDOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TagDO record);

    /**
     * 批量情况下，ignore版本，返回的自增id是错乱的.
     */
    int batchInsert(List<TagDO> tags);

    TagDO selectByPrimaryKey(Long id);

    List<TagDO> selectAll();

    List<TagDO> listByName(@Param("names") List<String> names);

    List<TagDO> listById(@Param("ids") List<Long> ids);

    int updateByPrimaryKey(TagDO record);
}