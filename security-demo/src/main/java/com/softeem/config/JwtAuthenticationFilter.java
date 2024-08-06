package com.softeem.config;

import com.softeem.context.UserContext;
import com.softeem.mapper.TEcommerceRoleMapper;
import com.softeem.service.RoleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * @Author: JY
 * @CreateTime: 2024-08-06
 * @Description:
 * @Version: 1.0
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private RoleService roleService;
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
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        id,
                        null,
                        roleService.getRoles(id)
                )
        );
        System.out.println("已将权限配置上下文中"+roleService.getRoles(id));
        filterChain.doFilter(request,response);
    }
}
