package com.softeem.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.softeem.mapper.TEcommerceUserMapper;
import com.softeem.model.entity.TEcommerceUser;
import com.softeem.model.vo.CurrentUserInfo;
import com.softeem.model.vo.UsernameAndPassword;
import com.softeem.security.MyUserDetails;
import com.softeem.service.IUserService;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @Title: UserService
 * @Author Jiang
 * @Package com.softeem.service.impl
 * @Date 2024/7/15 19:09
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public ResultInfo login(UsernameAndPassword usernameAndPassword) {
        AssertUtil.isNotNull(usernameAndPassword,"参数不可为空");
        AssertUtil.isNotNull(usernameAndPassword.getUsername(), "用户名不可为空");
        AssertUtil.isNotNull(usernameAndPassword.getPassword(), "密码不可为空");
        //构建当前用户
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usernameAndPassword.getUsername(), usernameAndPassword.getPassword());
        //将未验证的用户进行验证----->已验证的用户
        //认证管理器标准认证流程
        //1.用户加载 UserDetailsService loadByUsername
        //2.密码校验 PasswordEncoder matches
        Authentication authenticate = authenticationManager.authenticate(authentication);
        MyUserDetails userDetails = (MyUserDetails) authenticate.getPrincipal();
        String token = JwtUtil.generatorToken(new CurrentUserInfo(
                userDetails.getId(),
                userDetails.getUsername()
        ));
        //存放通过验证的用户信息
        /*SecurityContextHolder.getContext().setAuthentication(authenticate);*/
        return ResultInfoFactory.buildSuccess(token);
    }

    @Override
    public ResultInfo register(UsernameAndPassword usernameAndPassword) {
        return null;
    }
}
