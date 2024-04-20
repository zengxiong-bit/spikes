package com.whkj.spikes.controller;

import com.whkj.spikes.bo.User;
import com.whkj.spikes.bo.rest.RestResult;
import com.whkj.spikes.consts.MediaTypes;
import com.whkj.spikes.service.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUser/{id}")
    @ResponseBody
    public RestResult queryById(@PathVariable("id") Integer id){
        Validate.notNull(id);
        User user =  userService.selectById(id);
        return RestResult.success().data(user);
    }

    @RequestMapping(value = "/queryByName")
    @ResponseBody
    public RestResult queryByName(@RequestParam(value = "name") String name){
        Validate.notBlank(name);
        User user =  userService.selectByName(name);
        return RestResult.success().data(user);
    }


    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST},produces = {MediaTypes.JSON_UTF_8,MediaTypes.APPLICATION_XML_UTF_8},consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public RestResult list(){
        List<User> users =  userService.list();
        return RestResult.success().data(users);
    }

    @RequestMapping(value = "/save",method = {RequestMethod.POST})
    @ResponseBody
    public RestResult save(@RequestBody User user){
        User persistUser =  userService.save(user);
        return RestResult.success().data(persistUser);
    }
}
