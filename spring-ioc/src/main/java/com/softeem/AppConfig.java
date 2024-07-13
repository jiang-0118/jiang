package com.softeem;


import com.softeem.config.DBConfig;
import com.softeem.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Title: ${NAME}
 * @Author Jiang
 * @Package com.softeem
 * @Date 2024/7/12 16:09
 * @description: ${description}
 */
@ComponentScan //组件扫描 扫描当前类所在包的所有类
public class AppConfig {
    public static void main(String[] args) throws SQLException {
        //创建容器
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = ctx.getBean(UserService.class);
        bean.getConn();
    }
}