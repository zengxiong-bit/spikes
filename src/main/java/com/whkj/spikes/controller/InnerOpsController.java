package com.whkj.spikes.controller;

import com.whkj.spikes.bo.AppInfo;
import com.whkj.spikes.bo.rest.RestResult;
import com.whkj.spikes.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/inner/ops")
public class InnerOpsController {

    @Autowired
    private AppInfoService appInfoService;


    @RequestMapping(value = "/health/app/info", method = {RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public RestResult getAppInfo() {
        AppInfo appInfo = appInfoService.build();
        return RestResult.success().data(appInfo);
    }


}