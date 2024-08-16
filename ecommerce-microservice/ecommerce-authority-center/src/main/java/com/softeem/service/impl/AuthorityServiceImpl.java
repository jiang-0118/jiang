package com.softeem.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.softeem.constant.KeyConstant;
import com.softeem.entity.TEcommerceUser;
import com.softeem.mapper.TEcommerceUserMapper;
import com.softeem.service.AuthorityService;
import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.CurrentUserInfo;
import com.softeem.vo.UsernameAndPassword;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Resource
    private TEcommerceUserMapper userMapper;
    @Override
    public ResultInfo token(UsernameAndPassword usernameAndPassword) {
        TEcommerceUser currentUser=  userMapper.findByUsername(usernameAndPassword.getUsername());
        if (currentUser==null){
            return ResultInfoFactory.buildError("用户不可为空");
        }
        if (!currentUser.getPassword().equals(MD5.create().digestHex(usernameAndPassword.getPassword()))){
            return ResultInfoFactory.buildError("密码错误");
        }
        String token = JwtUtil.generatorToken(new CurrentUserInfo(currentUser.getId(), currentUser.getUsername()));
        return ResultInfoFactory.buildError(token);
    }
}
