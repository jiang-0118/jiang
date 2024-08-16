package com.softeem.config;

import com.softeem.util.JwtUtil;
import com.softeem.vo.CurrentUserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: JY
 * @CreateTime: 2024-08-12
 * @Description:
 * @Version: 1.0
 */
public class SecurityContextInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //前置处理内放行登录
        if (request.getRequestURI().contains("/login")){
            return true;
        }
        //校验凭证
        String token = request.getHeader("token");
        CurrentUserInfo currentUserInfo = JwtUtil.parseToken(token);
        if (currentUserInfo!=null){
            UserContext.set(currentUserInfo);
            return true;
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.clear();
    }
}
