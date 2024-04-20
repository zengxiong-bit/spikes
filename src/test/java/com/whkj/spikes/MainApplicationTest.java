package com.whkj.spikes;

import com.whkj.spikes.bo.MyPerson;
import com.whkj.spikes.bo.MyUser;
import com.whkj.spikes.bootstrap.SampleAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class MainApplicationTest {

    @Autowired
    private SampleAppConfig sampleAppConfig;


    @Autowired
    private MyPerson myPerson;


    @Autowired
    private MyUser myUser;

    @Test
    public void test(){
        System.out.println("test");
    }

    @Test
    public void printSampleAppConfig(){
        System.out.println("sampleAppConfig: " + sampleAppConfig);
        System.out.println("sampleAppConfig: " + sampleAppConfig.getClass());
        System.out.println("myPerson:" + myPerson.getClass());
        System.out.println("myUser:" + myUser.getClass());
    }
}
