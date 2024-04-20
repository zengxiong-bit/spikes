package com.whkj.spikes.dao.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDO {
    private Integer id;

    private String name;

    private String college;

    private String major;

    private Date createTime;

    private Date updateTime;

}