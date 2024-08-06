package com.softeem.filter;

import com.softeem.context.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: JY
 * @CreateTime: 2024-08-06
 * @Description:
 * @Version: 1.0
 */
//@Component
public class Filter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //白名单放行
        if (request.getRequestURI().contains("/login")){
            filterChain.doFilter(request,response);
            return;
        }
        //校验凭证
        String token = request.getHeader("token");
        Claims jwt = Jwts.parser()
                .setSigningKey("b75de45a3e93353450689fb4d787d343b75de45a3e93353450689fb4d787d343")
                .parseClaimsJws(token)
                .getBody();
        Integer id= ((Integer) jwt.get("sysUserId"));
        //处理上下文
        UserContext.set(id);
        filterChain.doFilter(request,response);
    }
}
