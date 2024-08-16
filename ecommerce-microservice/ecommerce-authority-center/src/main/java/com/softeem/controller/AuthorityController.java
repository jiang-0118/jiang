package com.softeem.controller;

import com.softeem.constant.KeyConstant;
import com.softeem.service.AuthorityService;

import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.CurrentUserInfo;
import com.softeem.vo.UsernameAndPassword;

import org.springframework.web.bind.annotation.PostMapping;
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
   /* @PostMapping("checkToken")
    public ResultInfo checkToken(String token){
        System.out.println("参数凭证 = " + token);
        CurrentUserInfo currentUserInfo = getUserInfoByToken(token);
        String key= KeyConstant.USER_TOKEN_KEY.getKey().concat(currentUserInfo.getUsername().toString());
        String tokenInRedis = (String) redisTemplate.opsForValue().get(key);
        System.out.println("该用户存储凭证 = " + tokenInRedis);
        if (tokenInRedis.equals(token)){
            return ResultInfoFactory.buildSuccess("校验成功");
        }
        return ResultInfoFactory.buildError("校验失败");
    }*/
    @PostMapping("/me")
    public ResultInfo me(String token){

        return ResultInfoFactory.buildSuccess(getUserInfoByToken(token));
    }
    CurrentUserInfo getUserInfoByToken(String token){
        return JwtUtil.parseToken(token);
    }
}
