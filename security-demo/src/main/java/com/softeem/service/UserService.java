package com.softeem.service;

import cn.hutool.crypto.digest.MD5;
import com.mysql.cj.util.StringUtils;
import com.softeem.entity.TEcommerceUser;
import com.softeem.entity.User;
import com.softeem.mapper.TEcommerceUserMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;

/**
 * @Author: JY
 * @CreateTime: 2024-08-06
 * @Description:
 * @Version: 1.0
 */
@Service
public class UserService {
    @Resource
    private TEcommerceUserMapper userMapper;
    public String login(User user){
        //校验参数
        if (user==null){
            throw new RuntimeException("用户不可为空");
        }
        if (StringUtils.isNullOrEmpty(user.getUsername())||StringUtils.isNullOrEmpty(user.getPassword())){
            throw new RuntimeException("用户信息不可为空");
        }
        //判断是否有该用户
        TEcommerceUser currentUser = userMapper.findByUsername(user.getUsername());
        //校验密码
        if (!MD5.create().digestHex(user.getPassword()).equals(currentUser.getPassword())){
            throw new RuntimeException("密码有误");
        }

        //颁发凭证
       String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        System.currentTimeMillis() +500000))
                .claim("sysUserId", currentUser.getId())
                .signWith(SignatureAlgorithm.HS256, "b75de45a3e93353450689fb4d787d343b75de45a3e93353450689fb4d787d343").compact();
       /* SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        currentUser.getId(),
                        currentUser.getUsername(),
                        Collections.EMPTY_LIST
                )
        );*/

        return token;

    }
}
