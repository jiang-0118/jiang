package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: ${NAME}
 * @Author Jiang
 * @Package com.softeem
 * @Date 2024/7/13 20:12
 * @description: ${description}
 */
@Configuration
@ComponentScan
@MapperScan("com.softeem.dao")
public class AppConfig {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}