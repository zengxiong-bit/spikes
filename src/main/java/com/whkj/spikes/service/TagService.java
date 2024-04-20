package com.whkj.spikes.service;

import com.whkj.spikes.dao.TagDOMapper;
import com.whkj.spikes.dao.dataobject.TagDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务管理.
 */
@Service
public class TagService {

    @Autowired
    private TagDOMapper tagDOMapper;

    /**
     * ?
     */
    @Transactional
    public void addTag1(TagDO tag) throws Exception{
        tagDOMapper.insert(tag);
        if(true){
            throw new Exception("test");
        }
    }

    /**
     * ?
     */
    @Transactional
    public void addTag2(TagDO tag) {
        try {
            tagDOMapper.insert(tag);
            notifyMsg("add Tag Success");
        }catch (Exception e){;
            e.printStackTrace();
        }

    }

    private void notifyMsg(String msg){
        System.out.println("msg: " + msg);
        if(true){
            throw new RuntimeException("notifyMsg occur error");
        }
    }

}
