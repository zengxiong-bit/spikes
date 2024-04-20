package com.whkj.spikes.service;

import com.whkj.spikes.dao.StudentCourseDOMapper;
import com.whkj.spikes.dao.dataobject.StudentCourseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseDOMapper studentCourseDOMapper;

    @Transactional
    public void add(Integer studentId, Integer courseId) {
        StudentCourseDO studentCourseDO = new StudentCourseDO();
        studentCourseDO.setStudentId(studentId);
        studentCourseDO.setCourseId(courseId);
        studentCourseDO.setCreateTime(new Date());
        studentCourseDO.setUpdateTime(new Date());
        studentCourseDOMapper.insert(studentCourseDO);
        if(true){
            throw new IllegalArgumentException("mock");
        }
    }

}
