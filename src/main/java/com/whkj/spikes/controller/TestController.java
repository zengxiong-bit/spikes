package com.whkj.spikes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/testFilter")
    public String testFilter(){
        System.out.println(getClass().getName() + " testFilter");
         return "testFilter";
    }
}
