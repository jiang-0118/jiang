package com.softeem.service;

import com.softeem.util.ResultInfo;

/**
 * @Title: IDinerService
 * @Author Jiang
 * @Package com.softeem.service
 * @Date 2024/7/17 9:39
 * @description:
 */
public interface IDinerService {
    ResultInfo login(String username,String password);
}
