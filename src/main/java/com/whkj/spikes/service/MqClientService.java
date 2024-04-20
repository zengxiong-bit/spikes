package com.whkj.spikes.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqClientService {

    @Value("${http.mq.appKey}")
    private String appKey;

    public void invoke(){
        System.out.println("appKey:"+appKey);
    }
}
