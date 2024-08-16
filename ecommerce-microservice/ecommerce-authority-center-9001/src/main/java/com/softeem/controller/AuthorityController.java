package com.softeem.controller;

import com.softeem.service.AuthorityService;

import com.softeem.util.ResultInfo;
import com.softeem.vo.UsernameAndPassword;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Resource
    private AuthorityService authorityService;

    @RequestMapping("/token")
    ResultInfo token(@RequestBody UsernameAndPassword usernameAndPassword){
        return authorityService.token(usernameAndPassword);
    }
}
