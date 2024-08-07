package com.softeem.security;

import com.softeem.expcetion.ParameterException;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import com.softeem.vo.CurrentUserInfo;
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

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 白名单放行
        if(request.getRequestURI().contains("/login")){
            filterChain.doFilter(request,response);
            return;
        }
        // 校验凭证
        String token = request.getHeader("token");
        AssertUtil.isNotEmpty(token,"凭证不可为空");
        try {
            CurrentUserInfo userInfo = JwtUtil.parseToken(token);
            // 处理上下文
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            userInfo,
                            null,
                            Collections.emptyList()
                    )
            );

        }catch (Exception ex){
            ex.printStackTrace();
            throw new ParameterException(ex.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
