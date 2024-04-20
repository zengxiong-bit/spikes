package com.whkj.spikes.dao;

import com.whkj.spikes.dao.dataobject.ProductDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductDO record);

    ProductDO selectByPrimaryKey(Integer id);

    List<ProductDO> selectAll();

    List<ProductDO> listByName1(@Param("name") String name);

    List<ProductDO> listByName2(@Param("name") String name);

    int updateByPrimaryKey(ProductDO record);

}