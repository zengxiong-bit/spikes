package com.whkj.spikes.dao;

import com.whkj.spikes.dao.dataobject.OrderDO;

import java.util.List;

public interface OrderDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDO record);

    OrderDO selectByPrimaryKey(Integer id);

    List<OrderDO> selectAll();

    int updateByPrimaryKey(OrderDO record);
}