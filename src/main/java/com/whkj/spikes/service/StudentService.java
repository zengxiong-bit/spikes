package com.whkj.spikes.service;

import com.whkj.spikes.dao.StudentDOMapper;
import com.whkj.spikes.dao.dataobject.StudentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class StudentService {

    @Autowired
    private StudentDOMapper studentDOMapper;

    @Transactional
    public Integer add(String name, String college, String major) {
        StudentDO studentDO = new StudentDO();
        studentDO.setName(name);
        studentDO.setCollege(college);
        studentDO.setMajor(major);
        studentDO.setCreateTime(new Date());
        studentDO.setUpdateTime(new Date());
        studentDOMapper.insert(studentDO);
        return studentDO.getId();
    }

}
