package com.softeem.service.impl;

import com.softeem.service.IUserService;
import org.springframework.stereotype.Service;


/**
 * @Title: UserServiceImpl
 * @Author Jiang
 * @Package com.softeem.service.impl
 * @Date 2024/7/13 14:06
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public void service1() {
        System.out.println("service1");
    }

    @Override
    public void service2() {
        System.out.println("service2");
    }
}
