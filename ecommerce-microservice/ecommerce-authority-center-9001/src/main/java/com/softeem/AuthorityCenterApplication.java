package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.softeem.mapper")
public class AuthorityCenterApplication
{

    public static void main( String[] args )
    {
        System.out.println("service on 9001");

        SpringApplication.run(AuthorityCenterApplication.class,args);
    }
}
