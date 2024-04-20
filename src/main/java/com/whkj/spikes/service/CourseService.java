package com.whkj.spikes.service;

import com.whkj.spikes.dao.CourseDOMapper;
import com.whkj.spikes.dao.dataobject.CourseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CourseService {

    @Autowired
    private CourseDOMapper courseMapper;

    @Transactional
    public void add(String name) {
        CourseDO  courseDO = new CourseDO();
        courseDO.setName(name);
        courseDO.setRegNum(0);
        courseDO.setCreateTime(new Date());
        courseDO.setUpdateTime(new Date());
        courseMapper.insert(courseDO);
    }

}
