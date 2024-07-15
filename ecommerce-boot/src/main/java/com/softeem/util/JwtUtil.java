package com.softeem.util;


import com.alibaba.fastjson.JSON;
import com.softeem.model.vo.CurrentUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;

/**
 *  JWT工具类，主要提供凭证的生成和校验API
 */
public class JwtUtil {

    private static final String SECRET="6a8dc08d278436e804c17881d63e9cd99f93191ddfeba1c3443c299b0e4d32ac";
    /**
     * 过期时间
     */
    private static final Duration exp = Duration.ofHours(2);
    public static void main(String[] args) {
    }
    /**
     * 使用当前用户生成凭证
     * @param userInfo
     * @return
     */
    public static String generatorToken(CurrentUserInfo userInfo){
        // 构建者设计模式
        // IssuedAt: 创建时间
        // Expiration: 过期时间
        return Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+exp.toMillis()))
                .claim("userInfo", JSON.toJSONString(userInfo))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }

    public static CurrentUserInfo parseToken(String token){
        //parser: 解析器
        //setSigningKey : 指定密钥
        //parseClaimsJws: 将凭证解析成 claims（payload部分）
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String currentUserJSON= body.get("userInfo").toString();  // 从claim中获取之前存入的userInfo
        CurrentUserInfo userInfo = JSON.parseObject(currentUserJSON, CurrentUserInfo.class);  // JSON字符串--Java对象
        return userInfo;

    }


}
