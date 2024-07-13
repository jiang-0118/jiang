package com.softeem.service;

import com.softeem.common.ResultInfo;

public interface IUserService {

    // 登录  查询
    ResultInfo login(String username,String password);
    // 注册  添加
    ResultInfo register(String username,String password);
}
