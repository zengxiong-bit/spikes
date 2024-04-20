package com.whkj.spikes.dao.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class StudentCourseDO {
    private Integer id;

    private Integer studentId;

    private Integer courseId;

    private Date createTime;

    private Date updateTime;

}