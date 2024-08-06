package com.softeem.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Title: DBConfig
 * @Author Jiang
 * @Package com.softeem.config
 * @Date 2024/7/12 21:21
 * @description:数据库配置类
 */
@Configuration //定义配置类
public class DBConfig {
    @Bean("pay")
    DataSource paySource() {
        //@Bean实例 名字(ID)
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("123456");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/pay?serverTimezone=UTC&allowMultiQueries=true&characterEncoding=UTF-8");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
    @Bean("food")
    DataSource foodSource() {
        //@Bean实例 名字(ID)
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("123456");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/food?serverTimezone=UTC&allowMultiQueries=true&characterEncoding=UTF-8");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
