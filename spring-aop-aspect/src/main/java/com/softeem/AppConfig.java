package com.softeem;




import com.softeem.service.IUserService;
import com.softeem.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Title: ${NAME}
 * @Author Jiang
 * @Package com.softeem
 * @Date 2024/7/13 13:52
 * @description: ${description}
 */
@ComponentScan
@Configuration
@EnableAspectJAutoProxy //启用基于AspectsJ的自动代理 自动帮我们创建代理类
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        IUserService bean = ctx.getBean(IUserService.class);
        bean.service1();
    }
}