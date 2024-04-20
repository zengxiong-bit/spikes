package com.whkj.spikes.dao.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class TagDO {

    private Long id;

    private String name;

    private Integer state;

    private Date createTime;

    private Date updateTime;

}