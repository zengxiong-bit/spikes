package com.whkj.spikes.service;

import com.google.common.collect.Lists;
import com.whkj.spikes.bo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public User save(User user){
        user.setId(100);
        return user;
    }
    public User selectById(Integer id){
        return new User(id,"小明","武汉水果湖");
    }

    public User selectByName(String name){
        return new User(1,name,"武汉水果湖");
    }

    public List<User> list(){

        List<User> users = Lists.newArrayListWithCapacity(3);

        for(int i=1; i<=2; i++){
            User user = new User();
            user.setId(i);
            user.setName("小明-" +RandomStringUtils.randomAlphanumeric(5));
            user.setAddr("武汉水果湖-" +RandomStringUtils.randomAlphanumeric(5));
            users.add(user);
        }

        return users;
    }
}
