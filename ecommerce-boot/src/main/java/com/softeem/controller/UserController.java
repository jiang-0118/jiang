package com.softeem.controller;

import com.softeem.model.vo.UsernameAndPassword;
import com.softeem.security.MyUserDetails;
import com.softeem.service.IUserService;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("/api")
    public ResultInfo api(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return ResultInfoFactory.buildSuccess(userDetails.getId()+": "+userDetails.getUsername());
    }
}
