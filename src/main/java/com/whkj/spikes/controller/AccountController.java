package com.whkj.spikes.controller;

import com.whkj.spikes.bo.LoginUser;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = "/queryBalance" )
    @ResponseBody
    public String queryBalance(HttpServletRequest request){
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Long userId = loginUser.getUserId();;
        return "<"+userId + ">账户余额为："+ RandomUtils.nextLong(100,1000)+" 元";
    }

}
