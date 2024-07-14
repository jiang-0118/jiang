package com.softeem;

import com.softeem.entity.User;
import com.softeem.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test1(){
        System.out.println(userMapper.findByUsername("zhangsansan"));
    }


    @Test
    public void test2(){
        System.out.println(userMapper.findByUsernameAndPassword("zhangsansan","202cb962ac59075b964b07152d234b70"));
    }


    @Test
    public void test3(){
        // 瞬时状态
        User user = new User();
        user.setUsername("tianqi");
        user.setPassword("123");

        int row = userMapper.addUser(user);  // 持久状态
        System.out.println("row: "+row);
        System.out.println("当前用户: "+user.getId());

        // session.close() 瞬时

    }
}
