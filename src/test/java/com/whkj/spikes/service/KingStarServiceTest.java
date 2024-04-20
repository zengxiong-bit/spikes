package com.whkj.spikes.service;

import com.whkj.spikes.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenxushao@kingsoft.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class KingStarServiceTest {

    @Autowired
    private KingStarService kingStarService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Test
    public void mockCourseData(){
        courseService.add("知识导入");
        courseService.add("能力进阶");
    }

    @Test
    public void mockStudentData(){
        studentService.add("张三","武汉科技大学","计算机科学与技术");
        studentService.add("王五","武汉科技大学","软件工程");
        studentService.add("李四","武汉工程大学","计算机科学与技术");
        studentService.add("赵六","武汉工程大学","人工智能");
    }

    @Test
    public void register(){
        kingStarService.register("张三","武汉科技大学","计算机科学与技术",1);
//        kingStarService.register("王五","武汉科技大学","软件工程",1);
//        kingStarService.register("李四","武汉工程大学","计算机科学与技术",2);
//        kingStarService.register("赵六","武汉工程大学","人工智能",2);
    }

}
