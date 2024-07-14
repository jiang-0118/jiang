package com.softeem.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Title: DBConfig
 * @Author Jiang
 * @Package com.softeem
 * @Date 2024/7/13 20:42
 * @description: DB配置类
 */
@Configuration
public class DBConfig {
    //数据源配置
    @Bean
    DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("123456");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bookstore?serverTimezone=UTC&allowMultiQueries=true&characterEncoding=UTF-8");
        HikariDataSource source = new HikariDataSource(config);
        return source;
    }
    @Bean
    JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
    @Bean
    SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource){
        //SQLSession:SQL回话
        //JDBC：建立连接Conn 陈述命令(statement) 处理结果(resultSet)
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
}
