package com.softeem.config;

import cn.hutool.crypto.digest.MD5;
import com.softeem.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @Title: SecurityConfig
 * @Author Jiang
 * @Package com.softeem.config
 * @Date 2024/7/15 19:43
 * @description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //todo 白名单放行 校验凭证 存放凭证
        http.csrf().disable()
                .cors() //支持跨域
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //禁用session模式
                .and()
                .authorizeRequests()
                .antMatchers("/user/login","/user/register")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //将原来负责认证的过滤器换成我们自定义的过滤器
    }
    //Oauth2
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence password) {
                return MD5.create().digestHex(password.toString());
            }

            @Override
            public boolean matches(CharSequence password, String encodedPassword) {
                return encode(password.toString()).equals(encodedPassword);
            }
        };
    }

}
