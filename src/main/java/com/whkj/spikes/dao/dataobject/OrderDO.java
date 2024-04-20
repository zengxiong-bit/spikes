package com.whkj.spikes.dao.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDO {
    private Integer id;

    private String name;

    private Integer stock;

    private Date createTime;

    private Date updateTime;

}