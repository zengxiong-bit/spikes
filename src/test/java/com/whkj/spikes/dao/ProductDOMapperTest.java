package com.whkj.spikes.dao;

import com.whkj.spikes.MainApplication;
import com.whkj.spikes.dao.dataobject.ProductDO;
import com.whkj.spikes.util.JacksonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author chenxushao@kingsoft.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class ProductDOMapperTest {

    @Autowired
    private ProductDOMapper productDOMapper;

    @Test
    public void listByName1() {
        List<ProductDO> products = productDOMapper.listByName1("iphone");
        System.out.println(JacksonMapper.INSTANCE.toJson(products));
        products = productDOMapper.listByName1("'iphone' or 1=1");
        System.out.println(JacksonMapper.INSTANCE.toJson(products));
    }


    @Test
    public void listByName2() {
        List<ProductDO> products = productDOMapper.listByName2("'iphone'");
        System.out.println(JacksonMapper.INSTANCE.toJson(products));

        products = productDOMapper.listByName2("'iphone' or 1=1");
        System.out.println(JacksonMapper.INSTANCE.toJson(products));
    }


}
