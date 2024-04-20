package com.whkj.spikes.service;

import com.whkj.spikes.MainApplication;
import com.whkj.spikes.dao.dataobject.TagDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author chenxushao@kingsoft.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void addTag1() throws Exception {
        TagDO tagDO = new TagDO();
        tagDO.setName("电商x" + "040901");
        tagDO.setState(0);
        tagDO.setCreateTime(new Date());
        tagDO.setUpdateTime(new Date());


        tagService.addTag1(tagDO);
    }


    @Test
    public void addTag2() throws Exception {
        TagDO tagDO = new TagDO();
        tagDO.setName("电商x" + "040902");
        tagDO.setState(0);
        tagDO.setCreateTime(new Date());
        tagDO.setUpdateTime(new Date());

        tagService.addTag2(tagDO);
    }

}
