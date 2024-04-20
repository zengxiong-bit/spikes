package com.whkj.spikes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.Enumeration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class SpringBootPrincipleTest {

    @Test
    public void test() throws Exception{
           String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
            Enumeration<URL> urls = MainApplication.class.getClassLoader().getResources(FACTORIES_RESOURCE_LOCATION);
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                System.out.println(url);
            }
    }


}
