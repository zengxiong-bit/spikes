package com.whkj.spikes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MsgNotifyService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void notifyMsg(String msg){
        System.out.println("msg: " + msg);
        if(true){
            throw new RuntimeException("notifyMsg occur error");
        }
    }
}
