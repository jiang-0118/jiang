package com.softeem.config;

import cn.hutool.crypto.digest.MD5;
import com.softeem.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthenticationFilter authenticationFilter;
    //完成security过滤器链
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/user/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(authenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
       return new PasswordEncoder() {
           public String encode(CharSequence charSequence) {
               return MD5.create().digestHex(charSequence.toString());
           }

           public boolean matches(CharSequence charSequence, String s) {
               return encode(charSequence).equals(s);
           }
       };
    }
}
