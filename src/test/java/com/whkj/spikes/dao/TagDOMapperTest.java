package com.whkj.spikes.dao;

import com.google.common.collect.Lists;
import com.whkj.spikes.MainApplication;
import com.whkj.spikes.dao.dataobject.TagDO;
import com.whkj.spikes.util.JacksonMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author chenxushao@kingsoft.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class TagDOMapperTest {

    @Autowired
    private TagDOMapper tagDOMapper;

    @Test
    public void batchInsert() {
        TagDO tagDO1 = new TagDO();
        tagDO1.setName("电商x" + RandomStringUtils.randomAlphanumeric(5));
        tagDO1.setCreateTime(new Date());
        tagDO1.setUpdateTime(new Date());

        TagDO tagDO2 = new TagDO();
        tagDO2.setName("电商y" + RandomStringUtils.randomAlphanumeric(5));
        tagDO2.setCreateTime(new Date());
        tagDO2.setUpdateTime(new Date());

        TagDO tagDO3 = new TagDO();
        tagDO3.setName("电商z-" + RandomStringUtils.randomAlphanumeric(5));
        tagDO3.setCreateTime(new Date());
        tagDO3.setUpdateTime(new Date());

        List<TagDO> list = Lists.newArrayList(tagDO1, tagDO2, tagDO3);
        int size = tagDOMapper.batchInsert(list);
        System.out.println("size: " + size);
        for (TagDO tagDO : list) {
            System.out.println(tagDO.getId() +","  + tagDO.getName());
        }
    }

    @Test
    public void insert() {
        Date now = new Date();
        TagDO tagDO = new TagDO();
        tagDO.setName("电商888");
        tagDO.setCreateTime(now);
        tagDO.setUpdateTime(now);
        tagDOMapper.insert(tagDO);
        System.out.println(tagDO.getId()+","+tagDO.getName());
    }

    @Test
    public void listByName() {
        List<TagDO> tagDOS = tagDOMapper.listByName(Arrays.asList("电商x", "电商y"));
        System.out.println(tagDOS.size());
        System.out.println(JacksonMapper.INSTANCE.toJson(tagDOS));
    }

    @Test
    public void listById() {
        List<TagDO> tagDOS = tagDOMapper.listById(Arrays.asList(134L,135L,141L));
        System.out.println(tagDOS.size());
        System.out.println(JacksonMapper.INSTANCE.toJson(tagDOS));
    }

}
