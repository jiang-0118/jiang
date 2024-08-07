package com.softeem.controller;

import com.softeem.service.IUserService;
import com.softeem.util.ResultInfo;
import com.softeem.vo.UsernameAndPassword;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public ResultInfo login(@RequestBody UsernameAndPassword usernameAndPassword){
        return userService.login(usernameAndPassword);
    }
}
