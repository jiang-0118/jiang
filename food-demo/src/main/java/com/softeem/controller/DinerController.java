package com.softeem.controller;

import com.softeem.service.IDinerService;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.CurrentUserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/diners")
public class DinerController {

    @Resource
    private IDinerService dinerService;
    @PostMapping("/login")
    public ResultInfo login(String username,String password){
        return dinerService.login(username,password);
    }

    @GetMapping("/context")
    public ResultInfo testContext(){
        CurrentUserInfo userInfo = (CurrentUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultInfoFactory.buildSuccess(
                userInfo
        );
    }
}
