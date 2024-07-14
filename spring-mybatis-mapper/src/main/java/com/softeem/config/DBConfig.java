package com.softeem.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Title: DBConfig
 * @Author Jiang
 * @Package com.softeem.config
 * @Date 2024/7/14 13:33
 * @description:
 */
@Configuration
public class DBConfig {
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
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    @Bean
    SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //定义映射文件的位置
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/*.xml")
        );
        return sqlSessionFactoryBean;
    }

}
