package com.softeem.filter;

import com.softeem.expcetion.ParameterException;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import com.softeem.vo.CurrentUserInfo;
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
 * @Package com.softeem.filter
 * @Date 2024/7/17 9:46
 * @description:
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //白名单放行
        if(request.getRequestURI().contains("/login")){
            filterChain.doFilter(request,response);
            return;
        }
        try {
            //校验凭证
            String token=request.getHeader("token");
            AssertUtil.isNotEmpty(token,"凭证不可为空");
            CurrentUserInfo userInfo = JwtUtil.parseToken(token);
            AssertUtil.isNotNull(userInfo,"凭证校验失败");

            //处理上下文
            //这里为了快速搭建 没有启用security标准流程 注意强转
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            userInfo,
                            null,
                            Collections.emptyList()
                    )
            );
            filterChain.doFilter(request,response);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ParameterException(ex.getMessage());
        }
    }
}
