package com.whkj.spikes.controller;

import com.whkj.spikes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passport")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(){
        return "登录页面，仅仅是一个Mock，用于演示登录拦截器LoginInterceptor";
    }

}
