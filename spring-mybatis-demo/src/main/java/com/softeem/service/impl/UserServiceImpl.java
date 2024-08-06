package com.softeem.service.impl;

import com.softeem.dao.UserMapper;
import com.softeem.entity.User;
import com.softeem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: UserServiceImpl
 * @Author Jiang
 * @Package com.softeem.service.impl
 * @Date 2024/7/13 20:33
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {

        return userMapper.findByUsername(username);
    }
}
