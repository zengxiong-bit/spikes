package com.whkj.spikes.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TradeService {


    public void trade(){
        //下单
        //扣减库存
        //扣减余额
        //增加积分
        //
    }


    @Transactional(rollbackFor = Exception.class)
    public void createOrder(){
        //保存订单信息
        //MQ通知开机
        //发送客户通知
    }


    @Transactional
    public void reduceStock(){

    }



}
