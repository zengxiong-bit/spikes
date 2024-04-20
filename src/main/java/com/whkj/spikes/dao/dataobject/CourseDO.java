package com.whkj.spikes.dao.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class CourseDO {
    private Integer id;

    private String name;

    private Integer regNum;

    private Date createTime;

    private Date updateTime;

}