package com.softeem.controller;

import com.softeem.entity.User;
import com.softeem.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.login(user);
    }

    @GetMapping("/api")
    @PreAuthorize("hasAuthority('superadmin')") //前置处理用户请求
    public String api(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
