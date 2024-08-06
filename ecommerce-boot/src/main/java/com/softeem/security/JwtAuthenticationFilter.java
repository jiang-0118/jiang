package com.softeem.security;

import com.softeem.expcetion.ParameterException;
import com.softeem.model.vo.CurrentUserInfo;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import org.apache.ibatis.builder.ParameterExpression;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @Title: JwtAuthenticationFilter
 * @Author Jiang
 * @Package com.softeem.security
 * @Date 2024/7/15 22:07
 * @description: 自定义认证过滤器
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //白名单放行
        if (request.getRequestURI().contains("/user/login") || request.getRequestURI().contains("/user/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        //校验凭证 从请求头中取得token
        String token = request.getHeader("token");
        AssertUtil.isNotEmpty(token, "凭证不可为空");
        try {
            CurrentUserInfo currentUserInfo = JwtUtil.parseToken(token);
            //处理上下文
            //将认证通过的用户处理上下文
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(currentUserInfo.toDetails(), null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        } catch (Exception ex) {
           throw   new ParameterException(ex.getMessage());
        }
    }
}
