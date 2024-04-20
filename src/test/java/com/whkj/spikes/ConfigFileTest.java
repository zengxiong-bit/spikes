package com.whkj.spikes;

import com.whkj.spikes.bootstrap.MqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class ConfigFileTest {

    @Autowired
    private MqConfig mqConfig;
    @Autowired
    private Environment environment;

    @Value("${http.mq.appKey}")
    private String appKey;

    @Test
    public void test(){
        System.out.println("appKey:"+appKey);
        System.out.println("appKey: " + environment.getProperty("http.mq.appKey"));
        System.out.println("appKey:"+mqConfig.getAppKey());
        System.out.println(mqConfig.getHost() + ", " + mqConfig.getAppKey() + " , " + mqConfig.getSecretKey());
    }
}
