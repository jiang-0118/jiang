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
public class EcommerceBalanceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(EcommerceBalanceApplication.class,args);
    }
}
