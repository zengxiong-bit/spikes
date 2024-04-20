package com.whkj.spikes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KingStarService {


    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private StudentService studentService;

    @Transactional
    public void register(String name, String college, String major, Integer courseId) {
        Integer studentId = studentService.add(name, college, major);
        try {
            studentCourseService.add(studentId, courseId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
