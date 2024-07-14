package com.softeem.service;

import com.softeem.entity.User;

/**
 * @Title: IUserService
 * @Author Jiang
 * @Package com.softeem.service
 * @Date 2024/7/13 20:33
 * @description:
 */
public interface IUserService {
    User findByUsername(String username);
}
