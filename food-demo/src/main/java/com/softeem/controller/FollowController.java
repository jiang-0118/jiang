package com.softeem.controller;

import com.softeem.service.IFollowService;
import com.softeem.util.ResultInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    private IFollowService followService;

    @GetMapping("/follow/{dinerId}/{status}")
    public ResultInfo follow(@PathVariable("dinerId") Integer followDinerId,
                             @PathVariable("status") Integer status){
        return followService.follow(followDinerId,status);
    }

    @GetMapping("/findCommonsFriends/{followDinerId}")
    public ResultInfo findCommonsFriends(@PathVariable("followDinerId") Integer followDinerId){
        return followService.findCommonsFriends(followDinerId);

    }
}
