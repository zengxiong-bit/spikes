package com.whkj.spikes.service;

import com.whkj.spikes.bo.LoginUser;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

@Service
public class PassportService {

    public Pair<Boolean, LoginUser> loginCheck(String token) {
        System.out.println("mock login check");
        boolean pass = RandomUtils.nextBoolean();


        if(pass){
            System.out.println("login success");
            Long userId = 2000091785 + RandomUtils.nextLong(1000, 10000);
            return Pair.of(true, new LoginUser(userId,"李四","lisi@qq.com"));
        }
        return Pair.of(false, null);
    }
}
