package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Title: ${NAME}
 * @Author Jiang
 * @Package com.softeem
 * @Date 2024/7/14 11:19
 * @description: ${description}
 */
@ComponentScan
@MapperScan
public class AppConfig {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}