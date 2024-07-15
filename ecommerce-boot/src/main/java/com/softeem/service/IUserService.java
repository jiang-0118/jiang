package com.softeem.service;

import com.softeem.model.vo.UsernameAndPassword;
import com.softeem.util.ResultInfo;

/**
 * @Title: IUserService
 * @Author Jiang
 * @Package com.softeem.service
 * @Date 2024/7/15 19:09
 * @description:
 */
public interface IUserService {
    public ResultInfo login(UsernameAndPassword usernameAndPassword);
    public ResultInfo register(UsernameAndPassword usernameAndPassword);
}
