package com.softeem.service.impl;

import com.softeem.config.JwtUserDetailsService;
import com.softeem.service.IUserService;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.CurrentUserInfo;
import com.softeem.vo.UsernameAndPassword;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private AuthenticationManager authenticationManager;
    public ResultInfo login(UsernameAndPassword usernameAndPassword) {
        AssertUtil.isNotNull(usernameAndPassword,"用户信息不可为空");
        AssertUtil.isNotEmpty(usernameAndPassword.getUsername(),"用户账号不可为空");
        AssertUtil.isNotEmpty(usernameAndPassword.getPassword(),"用户密码不可为空");
        UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken(usernameAndPassword.getUsername(), usernameAndPassword.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticated);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CurrentUserInfo currentUserInfo = (CurrentUserInfo) authentication.getPrincipal();
        String token = JwtUtil.generatorToken(currentUserInfo);
        return ResultInfoFactory.buildSuccess(token);
    }
}
