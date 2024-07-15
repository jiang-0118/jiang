package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.softeem.mapper")
public class EcommerceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(EcommerceApplication.class,args);
    }
}
