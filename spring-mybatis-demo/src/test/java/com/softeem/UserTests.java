package com.softeem;

import com.softeem.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserTests {

    @Autowired
    private IUserService userService;


    @Test
    public void test1(){
        System.out.println(userService.findByUsername("zhangsansan"));
    }
}
